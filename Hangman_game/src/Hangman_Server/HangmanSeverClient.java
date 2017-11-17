/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Server;


import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



/**
 *
 * @author Lida Liu && Yuan Fan
 */


public class HangmanSeverClient {

	/**
	 * @param args the command line arguments
	 * @throws java.lang.Exception
	 */
	static String[] workspace;

	public static void main(String[] args) throws Exception{
		File file = new File("src/Hangman_Server/words.txt");
		@SuppressWarnings("resource")
		FileReader reader = new FileReader(file);
		int fileLen = (int)file.length();
		char[] chars = new char[fileLen];
		reader.read(chars);
		String txt = String.valueOf(chars);
		workspace = txt.split("\n"); 



		ArrayList<Socket> list = new ArrayList<Socket>();
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(9999);
		while(true){
			Socket socket=server.accept();
			list.add(socket);
			String hostname=socket.getInetAddress().getHostAddress();
			System.out.println(hostname+" is connected to server");
			new MultiThread(socket).start();
		}
	}




}


