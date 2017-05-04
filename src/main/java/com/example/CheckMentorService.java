package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by EXLRT-0021 on 5/3/2017.
 */
public class CheckMentorService implements JavaDelegate {

    Expression postojiVariableName;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("usao sam u ProveraService");


        delegateExecution.setVariable("postojiVariableName", true);
    }
}
