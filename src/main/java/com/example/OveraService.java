package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.Set;

/**
 * Created by EXLRT-0021 on 5/4/2017.
 */
public class OveraService implements JavaDelegate {

    Expression prosecna_ocena;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> map = delegateExecution.getVariables();
        int brojOcena = 0;
        int zbirOcena = 0;
        for(Map.Entry<String, Object> s: map.entrySet()){
            System.out.println("s je: "+s);
            String key = s.getKey();
            System.out.println(key);
            if(s.getKey().contains("ocena_")){
                if(s.getValue()!=null){
                    System.out.println(s.getValue().toString());
                    brojOcena++;
                    int ocena = Integer.parseInt(s.getValue().toString());
                    zbirOcena+=ocena;
                }
            }
        }
        double prosek = 0.0;

        prosek = zbirOcena/brojOcena;

        System.out.println("prosek je: "+ prosek);
        delegateExecution.setVariable("prosecna_ocena", prosek);
    }
}
