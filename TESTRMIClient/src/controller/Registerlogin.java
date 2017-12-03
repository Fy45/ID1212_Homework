/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.rmi.Naming;
import java.util.Scanner;

import common.RMIinter;
import start.RMIClient;

/**
 *
 * @author user
 */
public class Registerlogin {
    public static int flag=0; 
    public static void registerlogin() throws Exception{
       
        Scanner sc = new Scanner(System.in);
        RMIinter userOperation=(RMIinter)Naming.lookup(RMIClient.URL);
        System.out.println("Welcome! choose to start: 1-login 2-register 3-quit ");
        String Firstchoice= sc.nextLine();
        if(Firstchoice.equals("1")){
            System.out.println("User name:");
            String loginUsername=sc.nextLine();
            System.out.println("Password:");
            String loginpassword=sc.nextLine();
            String login="%"+loginUsername+"$"+loginpassword+"*";
            String feedback=userOperation.login(login);
            if(feedback.equals("Log in successfully")){ flag=1;}
            System.out.println(feedback);
           
            
        }
        else if(Firstchoice.equals("2")){
            System.out.println("User name:");
            String registerUsername=sc.nextLine();
            System.out.println("Password:");
            String registerpassword=sc.nextLine();
            String register="%"+registerUsername+"$"+registerpassword+"*";
            String feedback=userOperation.register(register);
            if(feedback.equals("Successfully register")){ flag=1;}
            System.out.println(feedback);
            
        }
        else if(Firstchoice.equals("3")){
            System.exit(0);
        }
        else{
            System.out.println("Wrong input");
        }
    }
    
}
