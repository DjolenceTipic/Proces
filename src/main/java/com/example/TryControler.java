package com.example;

import com.sun.javafx.collections.MappingChange;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Djordje on 4/15/2017.
 */
@RestController
@RequestMapping
public class TryControler {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void startProcess(String username, String password){

        List<User> users = identityService.createUserQuery().userId(username).list();


        try {
            identityService.setAuthenticatedUserId(users.toString());
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Owner", username);
            runtimeService.startProcessInstanceByKey("procesZavrsetkaMasterStudija",parameters);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        for (Deployment d : repositoryService.createDeploymentQuery().list()) {

            System.out.println(""+ runtimeService.createProcessInstanceQuery().count());
        }

    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void initUser(DelegateExecution execution){
        //identityService = execution.getEngineServices().getIdentityService();

        String username = "Kermit";
        User user = identityService.newUser(username);
        user.setPassword("123");
        user.setFirstName("Manually");
        user.setLastName("created");
        identityService.saveUser(user);

        // Add admin group
        Group group = identityService.newGroup("student");
        identityService.saveGroup(group);

        identityService.createMembership(username, "student");


        String username2 = "Gospava";
        User user2 = identityService.newUser(username2);
        user2.setPassword("123");
        user2.setFirstName("Manually");
        user2.setLastName("created");
        identityService.saveUser(user2);

        // Add admin group
        Group group2 = identityService.newGroup("referentStudentskeSluzbe");
        identityService.saveGroup(group2);

        identityService.createMembership(username2, "referentStudentskeSluzbe");


        String username3 = "Zora";
        User user3 = identityService.newUser(username3);
        user3.setPassword("123");
        user3.setFirstName("Manually");
        user3.setLastName("created");
        identityService.saveUser(user3);

        // Add admin group
        Group group3 = identityService.newGroup("RukovodilacStudijskogPrograma");
        identityService.saveGroup(group3);

        identityService.createMembership(username3, "RukovodilacStudijskogPrograma");
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public void checkIfTasks(String username, String password){

        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("referentStudentskeSluzbe").list();
        System.out.println(tasks.size());

        List<Task> student = taskService.createTaskQuery().taskCandidateGroup("student").list();
        System.out.println(student.size());


    }


}
