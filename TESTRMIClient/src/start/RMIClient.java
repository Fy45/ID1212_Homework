/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;
import java.rmi.Naming;

import common.RMIinter;
import controller.Opration;
import controller.Registerlogin;
/**
 *
 * @author user
 */
public class RMIClient {
 public static String URL;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    	
        URL="rmi://"+"192.168.1.103"+":9999/common.interimplement";
        RMIinter userOperation=(RMIinter)Naming.lookup(URL);
        String wow = userOperation.print();
        System.out.println(wow);
        
        
        while(true){
        Registerlogin.registerlogin();
        if(Registerlogin.flag==1){
            break;
        }       
        }
        while(true){
        Opration.Operation();       
        }
    }
    
}
