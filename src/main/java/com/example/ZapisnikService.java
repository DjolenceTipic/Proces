package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Djordje on 4/22/2017.
 */
public class ZapisnikService implements JavaDelegate {

    Expression resultVariableName;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> vars = execution.getVariables();
        Map<String, String> komisija = new HashMap<>();

        for(Map.Entry<String, Object> var : vars.entrySet()){
            System.out.println(var.toString());
            if(isKomisija(var)){
                if(!var.getValue().toString().equals("")){
                    String username = var.getValue().toString().split(" ")[0];
                    if(var.getKey().equals("mentor_izabrani")){
                        komisija.put("mentorIzabrani", username);
                    }else{
                        komisija.put(var.getKey().toString(), username);
                    }
                }
            }
            System.out.println(komisija.values().toString());
        }
        execution.setVariable("resultServiceTask", komisija);
    }

    private boolean isKomisija(Map.Entry<String, Object> var) {
        return var.getKey().equals("mentor_izabrani")||var.getKey().equals("presednik_komisije")||var.getKey().equals("treci_clan")||var.getKey().equals("cetvrti_clan")||var.getKey().equals("peti_clan");
    }


}
