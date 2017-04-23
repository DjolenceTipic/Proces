package com.example.serviceTasks;


import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Djordje on 4/22/2017.
 */
public class ZapisnikService implements JavaDelegate {

    @Autowired
    private TaskService taskService;

    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("usao sam u ZapisnikService");
        taskService.createTaskQuery().list();
    }
}
