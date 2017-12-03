/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrmi;
import java.io.*; 
import java.rmi.*; 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class TESTRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
            // TODO code application logic here
            RMIinitial.interinitial();
        } catch (Exception ex) {
            Logger.getLogger(TESTRMI.class.getName()).log(Level.SEVERE, null, ex);
        }

        }     
    }
    
