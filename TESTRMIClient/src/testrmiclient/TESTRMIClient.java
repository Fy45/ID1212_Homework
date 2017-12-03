/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrmiclient;
import testrmi.RMIinter;
import java.io.*; 
import java.net.MalformedURLException;

import java.rmi.*; 
import java.util.Scanner;
/**
 *
 * @author user
 */
public class TESTRMIClient {
 public static String URL;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
      //System.setSecurityManager(new RMISecurityManager()); 
        // TODO code application logic here
       // Scanner sc = new Scanner(System.in);
        URL="rmi://"+"192.168.1.126"+":9999/testrmi.interimplement";
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
