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

        Group group4 = identityService.newGroup("RukovodilacPrograma");
        identityService.saveGroup(group4);

        identityService.createMembership(username4, "RukovodilacPrograma");

        String username5 = "zapsinicar";
        User user5 = identityService.newUser(username5);
        user5.setPassword("123");
        user5.setEmail("proba@proba.com");
        user5.setFirstName("Manually");
        user5.setLastName("created");
        identityService.saveUser(user4);

        Group group5 = identityService.newGroup("RukovodilacPrograma");
        identityService.saveGroup(group5);

        identityService.createMembership(username5, "RukovodilacPrograma");
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<String> checkIfTasks(String username){
        System.out.println("Username u check metodi je: "+username);
        List<User>users =identityService.createUserQuery().userId(username).list();
        User current = users.get(0);
        System.out.println("current user u metodi: "+current.getId().toUpperCase());
        List<MyTask> allTasks = new ArrayList<MyTask>();
        List<Group> groups = identityService.createGroupQuery().groupMember(current.getId()).list();
        for(Group g: groups){
            System.out.println("g.getId() u foreach je: "+g.getId());
            List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(g.getId()).list();
            System.out.println("tasks size: "+tasks);
            for(Task t : tasks){
                System.out.println(t.getId());
                MyTask mt = new MyTask();
                mt.setId(t.getId());
                mt.setName(t.getName());
                allTasks.add(mt);
            }

//            List<Task> studentsTasks2 = taskService.createTaskQuery().taskAssignee(g.getId()).list();
//            System.out.println("studentsTasks2 size: "+studentsTasks2);
//            for(Task t : studentsTasks2){
//                System.out.println(t.getId());
//                MyTask mt = new MyTask();
//                mt.setId(t.getId());
//                mt.setName(t.getName());
//                allTasks.add(mt);
//                System.out.println(mt.toString());
//            }
//            List<Task> studentsTasks22 = taskService.createTaskQuery().taskCandidateOrAssigned(g.getId()).list();
//            System.out.println("studentsTasks22 size: "+studentsTasks22);
//            for(Task t : studentsTasks22){
//                System.out.println(t.getId());
////                MyTask mt = new MyTask();
////                mt.setId(t.getId());
////                mt.setName(t.getName());
////                allTasks.add(mt);
//            }
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
            mt.setMyFormList(myForms);
        }


        Gson gson = new Gson();
        String json = gson.toJson(allTasks);
        System.out.println(json);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }

    @RequestMapping(value="/execute/{taskId}", method = RequestMethod.POST)
    public Response execcuteTask(@PathVariable String taskId, String username, @RequestParam Map<String,String> allRequestParams){

        System.out.println(allRequestParams);

        Task task = null;
        for (Task t : taskService.createTaskQuery().taskCandidateUser(username).list()){
            if (t.getId().equals(taskId)){
                System.out.println(t.getName());
                task = t;
            }
        }
        TaskFormData tfd =  formService.getTaskFormData(taskId);
        List<FormProperty>   fpList = tfd.getFormProperties();
        Map<String, String> params = new HashMap<>();
        for(FormProperty fp: fpList){
            System.out.println(fp.getType());
            for(String s: allRequestParams.keySet()){
                System.out.println(s);
                System.out.println("C");
                if(fp.getName().equals(s)){
                    if(s.equals("on")||s.equals("off")){
                        String bool = (s.equals("on"))? "true" : "false";
                        params.put(fp.getId(), bool);
                    }else{
                        params.put(fp.getId(), s);
                    }
                    System.out.println(allRequestParams.get(s)+" uraaaa");
                }
            }
        }
        formService.submitTaskFormData(taskId, params);
//        Map<String, String > params = new HashMap<>();
//        params.put("ime_studenta", "Djoko");
//        params.put("prezime_studenta", "Salic");
//        params.put("broj_indeksa", "sf13-2014");
//        params.put("predlog_teme","testTEma");
//        params.put("predlog_mentora", "TEst Test");
//        params.put("vrsta_studenta", "true");
//
        return Response.ok("Zadatak uspesno izvrsen").build();
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
