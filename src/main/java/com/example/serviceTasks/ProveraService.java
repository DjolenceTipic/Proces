package com.example.serviceTasks;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by Djordje on 4/22/2017.
 */
public class ProveraService implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("usao sam u ProveraService");
    }
}
