/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrmi;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author user
 */
public class RMIinitial {
    public static void interinitial() throws Exception{
   
        int port=9999;
        String address=InetAddress.getLocalHost().getHostAddress().toString();
        String url= "rmi://"+address+":9999/testrmi.interimplement";
        LocateRegistry.createRegistry(port); 
         Naming.rebind(url, new interimplement()); 
        System.out.println("Service Begin!");
    }
  
    
}
