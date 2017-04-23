package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.MyForm;
import models.MyTask;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Djordje on 4/15/2017.
 */
@RestController
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
            System.out.println(d.getId());
            System.out.println(d.getTenantId());
            System.out.println(""+ runtimeService.createProcessInstanceQuery().count());
        }
        System.out.println("Ukupan broj deployment-a: " + repositoryService.createDeploymentQuery().count());
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void initUser(DelegateExecution execution){

        String username = "Kermit";
        User user = identityService.newUser(username);
        user.setPassword("123");
        user.setEmail("proba@proba.com");
        user.setFirstName("Manually");
        user.setLastName("created");
        identityService.saveUser(user);

        Group group = identityService.newGroup("student");
        identityService.saveGroup(group);

        identityService.createMembership(username, "student");

        String username2 = "Gospava";
        User user2 = identityService.newUser(username2);
        user2.setPassword("123");
        user2.setEmail("proba@proba.com");
        user2.setFirstName("Manually");
        user2.setLastName("created");
        identityService.saveUser(user2);

        Group group2 = identityService.newGroup("ReferentSS");
        identityService.saveGroup(group2);

        identityService.createMembership(username2, "ReferentSS");

        String username3 = "Zora";
        User user3 = identityService.newUser(username3);
        user3.setPassword("123");
        user3.setEmail("proba@proba.com");
        user3.setFirstName("Manually");
        user3.setLastName("created");
        identityService.saveUser(user3);

        Group group3 = identityService.newGroup("RukovodilacPrograma");
        identityService.saveGroup(group3);

        identityService.createMembership(username3, "RukovodilacPrograma");

        String username4 = "dekan";
        User user4 = identityService.newUser(username4);
        user4.setPassword("123");
        user4.setEmail("proba@proba.com");
        user4.setFirstName("Manually");
        user4.setLastName("created");
        identityService.saveUser(user4);

        Group group4 = identityService.newGroup("Dekan");
        identityService.saveGroup(group4);

        identityService.createMembership(username4, "Dekan");

        String username5 = "zapisnicar";
        User user5 = identityService.newUser(username5);
        user5.setPassword("123");
        user5.setEmail("proba@proba.com");
        user5.setFirstName("Manually");
        user5.setLastName("created");
        identityService.saveUser(user5);

        Group group5 = identityService.newGroup("zapisnicar");
        identityService.saveGroup(group5);

        identityService.createMembership(username5, "zapisnicar");

        String username6 = "bibliotekarka";
        User user6 = identityService.newUser(username6);
        user6.setPassword("123");
        user6.setEmail("proba@proba.com");
        user6.setFirstName("Manually");
        user6.setLastName("created");
        identityService.saveUser(user6);

        Group group6 = identityService.newGroup("bibliotekarka");
        identityService.saveGroup(group6);

        identityService.createMembership(username6, "bibliotekarka");

        String username7 = "profesor1";
        User user7 = identityService.newUser(username7);
        user7.setPassword("123");
        user7.setEmail("proba@proba.com");
        user7.setFirstName("Manually");
        user7.setLastName("created");
        identityService.saveUser(user7);

        String username8 = "profesor2";
        User user8 = identityService.newUser(username8);
        user8.setPassword("123");
        user8.setEmail("proba@proba.com");
        user8.setFirstName("Manually");
        user8.setLastName("created");
        identityService.saveUser(user8);

        String username9 = "profesor3";
        User user9 = identityService.newUser(username9);
        user9.setPassword("123");
        user9.setEmail("proba@proba.com");
        user9.setFirstName("Manually");
        user9.setLastName("created");
        identityService.saveUser(user9);

        String username10 = "profesor4";
        User user10 = identityService.newUser(username10);
        user10.setPassword("123");
        user10.setEmail("proba@proba.com");
        user10.setFirstName("Manually");
        user10.setLastName("created");
        identityService.saveUser(user10);

        String username11 = "profesor5";
        User user11 = identityService.newUser(username11);
        user11.setPassword("123");
        user11.setEmail("proba@proba.com");
        user11.setFirstName("Manually");
        user11.setLastName("created");
        identityService.saveUser(user11);

        Group group7 = identityService.newGroup("profesor");
        identityService.saveGroup(group7);

        Group group8 = identityService.newGroup("fakultet1");
        identityService.saveGroup(group8);

        Group group9 = identityService.newGroup("fakultet2");
        identityService.saveGroup(group9);

        identityService.createMembership(username7, "profesor");
        identityService.createMembership(username8, "profesor");
        identityService.createMembership(username9, "profesor");
        identityService.createMembership(username10, "profesor");
        identityService.createMembership(username11, "profesor");

        identityService.createMembership(username7, "fakultet1");
        identityService.createMembership(username8, "fakultet1");
        identityService.createMembership(username9, "fakultet1");
        identityService.createMembership(username10, "fakultet2");
        identityService.createMembership(username11, "fakultet2");

    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<String> checkIfTasks(String username){
        System.out.println(username);
        List<User>users =identityService.createUserQuery().userId(username).list();
        User current = users.get(0);
        List<MyTask> allTasks = new ArrayList<MyTask>();
        List<Group> groups = identityService.createGroupQuery().groupMember(current.getId()).list();
        System.out.println(groups.size());
        for(Group g: groups){
            System.out.println(g.getId());
            List<Task> tasks = taskService.createTaskQuery().taskAssignee(g.getId()).list();
            System.out.println(tasks.size());
            if(tasks.size()!=0){
                for(Task t : tasks){
                    System.out.println(t.getId());
                    MyTask mt = new MyTask();
                    mt.setId(t.getId());
                    mt.setName(t.getName());
                    allTasks.add(mt);
                }
            }else{
                List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup(g.getId()).list();
                System.out.println(taskList.size());
                for(Task t : taskList){
                    System.out.println(t.getId());
                    MyTask mt = new MyTask();
                    mt.setId(t.getId());
                    mt.setName(t.getName());
                    allTasks.add(mt);
                }
            }
        }

        for(MyTask mt: allTasks){
            List<MyForm> myForms = new ArrayList<>();
            TaskFormData tfd = formService.getTaskFormData(mt.getId());
            List<FormProperty> fpList = tfd.getFormProperties();
            for(FormProperty f : fpList){
                MyForm mf = new MyForm();
                mf.setId(f.getId());
                mf.setName(f.getName());
                mf.setType(f.getType().getName().toString());
                myForms.add(mf);
            }
            System.out.println(mt.toString());
            mt.setMyFormList(myForms);
        }
        Gson gson = new Gson();
        String json = gson.toJson(allTasks);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }

    @RequestMapping(value="/execute/{taskId}", method = RequestMethod.POST)
    public Response execcuteTask(@PathVariable String taskId, String username, @RequestParam Map<String,String> allRequestParams){
        Task task = null;
        for (Task t : taskService.createTaskQuery().taskCandidateUser(username).list()){
            if (t.getId().equals(taskId)){
                System.out.println(t.getName());
                task = t;
            }
        }
        System.out.println(allRequestParams.entrySet().toString());
        TaskFormData tfd =  formService.getTaskFormData(taskId);
        List<FormProperty>   fpList = tfd.getFormProperties();
        Map<String, String> params = new HashMap<>();
        for(FormProperty fp: fpList){
            for(Map.Entry<String, String> entry: allRequestParams.entrySet()){
                System.out.println(entry.toString());
                if(!fp.getType().getName().equals("boolean")){
                    if(fp.getName().equals(entry.getKey())) {
                        System.out.println("    Trenutni postavljeni kljuc je: " + fp.getId() + ", a vrednost: " + entry.getValue());
                        params.put(fp.getId(), entry.getValue());
                        continue;
                    }
                    continue;
                }else{
                    if(allRequestParams.containsKey(fp.getName())){
                        if(fp.getName().equals(entry.getKey())){
                            String bool = (entry.getValue().equals("on"))? "true" : "false";
                            System.out.println("            Trenutni postavljeni kljuc je: "+fp.getId()+", a vrednost(bool): "+bool);
                            params.put(fp.getId(), bool);
                            continue;
                        }
                        continue;
                    }else{
                        if(fp.getType().getName().equals("boolean")){
                            if(params.get(fp.getId())!=null){
                                System.out.println("    Trenutni postavljeni kljuc je: "+fp.getId()+", a vrednost: false");
                                params.put(fp.getId(), "false");
                            }
                            continue;
                        }
                        continue;
                    }
                }
            }
        }
        formService.submitTaskFormData(taskId, params);

        try{
            URI uri = new URI("http://localhost:8080");
            return Response.temporaryRedirect(uri).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.noContent().build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(String username, String password){
        try {
            Boolean checked = identityService.checkPassword(username, password);
            if(checked){// Return the token on the response{
                System.out.println(username);
                return Response.ok(username,javax.ws.rs.core.MediaType.APPLICATION_JSON).build();
            }
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value =  "/checkAll", method = RequestMethod.GET)
    public void checkAll(){
        List<Task> allTasks = taskService.createTaskQuery().active().list();
        for(Task t: allTasks)
            System.out.println(t.toString());
    }
}
