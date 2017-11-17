/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
*
* @author Lida Liu && Yuan Fan
* 
* 
*/


public class Threadin extends Thread{
	
	
    Socket socket;
    public Threadin(Socket socket){
        this.socket=socket;
    }
    
    
    @Override
    public void run(){
    try{
        BufferedReader socketin=
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str=null;
        while((str=socketin.readLine())!=null){
            System.out.println(str);  
        }
        
    }   catch (IOException e) {    
           
        }    
    
}
    
            
    
}
