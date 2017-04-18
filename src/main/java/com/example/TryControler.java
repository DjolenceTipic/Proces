package com.example;

import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Djordje on 4/15/2017.
 */
@RestController
@RequestMapping
public class TryControler {

    private static final String filename = "processes/proces.bpmn";

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void startProcess(String username, String password){

        List<User> users = identityService.createUserQuery().userId(username).list();

        for (Deployment d : repositoryService.createDeploymentQuery().list())
            repositoryService.deleteDeployment(d.getId(), true);

        try {
            identityService.setAuthenticatedUserId(users.toString());
            repositoryService.createDeployment().addClasspathResource(filename).deploy();
            runtimeService.startProcessInstanceByKey("procesZavrsetkaMasterStudija");
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        for (Deployment d : repositoryService.createDeploymentQuery().list()) {
            System.out.println(d.getTenantId());
            System.out.println(""+ runtimeService.createProcessInstanceQuery().count());
        }
        System.out.println("Ukupan broj deployment-a: " + repositoryService.createDeploymentQuery().count());
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
        Group group2 = identityService.newGroup("ReferentSS");
        identityService.saveGroup(group2);

        identityService.createMembership(username2, "ReferentSS");


        String username3 = "Zora";
        User user3 = identityService.newUser(username3);
        user3.setPassword("123");
        user3.setFirstName("Manually");
        user3.setLastName("created");
        identityService.saveUser(user3);

        // Add admin group
        Group group3 = identityService.newGroup("RukovodilacPrograma");
        identityService.saveGroup(group3);

        identityService.createMembership(username3, "RukovodilacPrograma");
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Response checkIfTasks(String username, String password){

//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("referentStudentskeSluzbe").list();
//        System.out.println(tasks.size());
//        List<Task> studentsTasks = taskService.createTaskQuery().taskCandidateGroup("student").list();
//        System.out.println(studentsTasks.size());

        List<Task> studentsTasks2 = taskService.createTaskQuery().taskAssignee("student").list();
        System.out.println(studentsTasks2.size());

        List<Task> referenSSTasks = taskService.createTaskQuery().taskAssignee("ReferentSS").list();
        System.out.println(referenSSTasks.size());

        List<Task> rukovodilacProgramaTasks = taskService.createTaskQuery().taskAssignee("RukovodilacPrograma").list();
        System.out.println(rukovodilacProgramaTasks.size());
        String taskId = "";
        if(studentsTasks2.size()>0){
            Task task = studentsTasks2.get(0);
            taskId = task.getId();
            System.out.println(task.getName().toString());
        }
        if(referenSSTasks.size()>0){
            Task task = referenSSTasks.get(0);
            taskId = task.getId();
            System.out.println(task.getName().toString());
        }
        if(rukovodilacProgramaTasks.size()>0){
            Task task = rukovodilacProgramaTasks.get(0);
            taskId = task.getId();
            System.out.println(task.getName().toString());
        }

        TaskFormData tfd =formService.getTaskFormData(taskId);
        List<FormProperty> fp = tfd.getFormProperties();

        String form = formService.getTaskFormData(taskId).getFormKey();
        System.out.println(taskId);

        Gson gson = new Gson();
        String json = gson.toJson(fp);
        return Response.ok(json, MediaType.APPLICATION_JSON_VALUE).build();
    }


    @RequestMapping(value="/execute/{taskId}", method = RequestMethod.POST)
    public Response execcuteTask(@PathVariable String taskId, String username){

        for (Task t : taskService.createTaskQuery().taskCandidateUser(username).list()){
            if (t.getId().equals(taskId)){
                System.out.println(t.getName());
            }
        }
        Map<String, String > params = new HashMap<>();
        params.put("ime_studenta", "Djoko");
        params.put("prezime_studenta", "Salic");
        params.put("broj_indeksa", "sf13-2014");
        params.put("predlog_teme","testTEma");
        params.put("predlog_mentora", "TEst Test");
        params.put("vrsta_studenta", "true");
        formService.submitTaskFormData(taskId, params);
//        String userId = user.getUsername();
//        String message;
//        if (canExecute(taskId, userId)){
//            //pre ovog koraka bi se trebala sprovesti validacija
//            //da li su uneti svi potrebni parametri (required), da li ima neslaganja tipova
//            //ako se unosi email adresa, da li je validna i sl.
//            formService.submitTaskFormData(taskId, params);
//            message = "Zadatak uspešno izvršen";
//        }
//        else
//            message = "Ne možete izvršiti zadatak";
//
//        model.addAttribute("message", message);
//        return showUsersTasks(model);
        return Response.ok("").build();
    }
}
