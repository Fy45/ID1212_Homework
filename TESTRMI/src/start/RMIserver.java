/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.RMIinitial;

/**
 *
 * @author user
 */
public class RMIserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
        	
            RMIinitial.interinitial();
        } catch (Exception ex) {
            Logger.getLogger(RMIserver.class.getName()).log(Level.SEVERE, null, ex);
        }

        }     
    }
    
