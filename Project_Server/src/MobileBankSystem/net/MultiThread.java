package MobileBankSystem.net;


import MobileBankSystem.controller.OperationSelector;
import java.io.*;
import java.net.*;
import MobileBankSystem.model.LoginCreateAc;
import MobileBankSystem.model.LoginCreateAc;
import MobileBankSystem.controller.Operation;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fanyuan
 */
public class MultiThread extends Thread{

        private String out;
	public static String in;
	Socket socket;
        private boolean STOP_FLG = false;
        BufferedReader mBufferedReader;
	public MultiThread(Socket socket) {
		this.socket=socket;
	}
        
        
        @Override
    public void run() {
        try {
            while(!STOP_FLG){
                mBufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "utf-8"));
                OutputStream os = socket.getOutputStream();
                String rcvCTX = mBufferedReader.readLine();
                
                if (!rcvCTX.equals(null)){
                String sendCTX = null;
                //Extracting the message about whether to continue the game or 
                //not with the flag continueFLG.
                System.out.println("server recevied:"+ rcvCTX);
                in = rcvCTX;
                out =  OperationSelector.operationSelect(in);
                sendCTX = out;
                System.out.println("server sent:"+ sendCTX);
                os.write((sendCTX+"\n").getBytes("utf-8"));
                // The result will be sent to the client.
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();    
        }   
    }

}
        
        
     


