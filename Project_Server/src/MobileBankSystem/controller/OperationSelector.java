/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileBankSystem.controller;

import MobileBankSystem.model.DatabaseFunction;
import MobileBankSystem.model.LoginCreateAc;


/**
 *
 * @author harry
 */
public class OperationSelector {
    
    public static String operationSelect(String in){
        String flag=DatabaseFunction.getCTX(in, "[", "]");
        String out = null;
                if ("login".equals(flag)) {
                        LoginCreateAc.login(in);

                        out= LoginCreateAc.out;
                        System.out.println(out);


                } else if("register".equals(flag)){
                        System.out.println("We want to register with these information:  "+in);
                        out = LoginCreateAc.createAc(in);
                       
                        System.out.println(out);


                } else if("operation".equals(flag)){
                        Operation.operationSystem(in);   
                        //out= Operation.result+"!"+userName+"@"+userType+"&"+userBalance+"*";
                        out= Operation.result;
                        
                        System.out.println(out);

                }
                
                return out;
    }
    
}
