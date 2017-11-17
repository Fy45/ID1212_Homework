/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Lida Liu && Yuan Fan
 */


public class Threadout extends Thread{
	Socket socket;
	public Threadout(Socket socket){
		this.socket=socket;
	}
	@Override
	public void run(){
		try{
			while(true){
				BufferedReader tapin=
						new BufferedReader(new InputStreamReader(System.in ));//键盘输入
				BufferedWriter socketout=
						new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//送包出去
				String line;
				line=tapin.readLine();
				if((line)!=null){
					if((line).equals("NO")){
						break;
					}

					socketout.write(line);
					socketout.newLine();
					socketout.flush();

				}
			}
			socket.close();


		} catch (IOException e) {

		}
	}

}
