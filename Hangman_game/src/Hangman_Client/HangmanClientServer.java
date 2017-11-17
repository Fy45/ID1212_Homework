/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Client;

import java.net.Socket;
import java.util.Scanner;



/**
 *
 * @author Lida Liu && Yuan Fan
 */


public class HangmanClientServer {

	/**
	 * @param args the command line arguments
	 * @throws java.lang.Exception
	 */
	public static void main(String[] args) throws Exception{
		System.out.println("Input server IP address: ");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String serverip= scanner.next();
		Socket socket= new Socket(serverip,9999) ;
		new Threadin(socket).start();
		new Threadout(socket).start();

	}

}
