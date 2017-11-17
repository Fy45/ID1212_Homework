/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author 
 *
 *  Lida Liu && Yuan Fan
 *
 */

import java.net.Socket;


public class MultiThread extends Thread{
	Socket socket;
	public MultiThread (Socket socket){
		this.socket=socket;
	}


	@Override  
	public void run(){
		try{

			int score = 0;
			BufferedReader bufin =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bufout =
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true)
			{
				String num2 = String.valueOf(score);
				String sc = "Your Score :" + num2+"  "+"Do you want to start playing? (YES or NO)";


				bufout.write(sc);
				bufout.newLine();
				bufout.flush();
				//ask if start the game

				String line = bufin.readLine();
				String ip = socket.getInetAddress().getHostAddress();

				if("NO".equals(line))
					break;
				else if("YES".equals(line)){

					Generateword Q = new Generateword();
					Q.getWord();
					System.out.println(Q.Word);

					String prt = Q.Blank;
					//initial the game

					byte[] answerf = new byte[Q.Word.length];

					String num1 = String.valueOf(Q.Word.length);
					String st = "Your left chance(s): ";
					String ot = st + num1+"  :  ";
					ot=ot+prt;


					bufout.write(ot);
					bufout.newLine();
					bufout.flush();

					for (int x = Q.Word.length;x>0 ;x--) {

						String str = bufin.readLine();
						



						Compare L = new Compare(str,Q,x);

						x = L.Guesstimeleft;
						Q = L.wordaftercompare;
						prt=Q.Blank;

						if(x==0)
						{
							ot=L.Outputword+" your answer: "+prt;
							bufout.write(ot);
							bufout.newLine();
							bufout.flush();							
						}
						else
						{
							num1 = String.valueOf(x-1);
							st = "  Your left chance(s): ";
							ot = st + num1+"   ";
							ot=L.Outputword+ot+"  "+prt;
							bufout.write(ot);
							bufout.newLine();
							bufout.flush();
						}


					}
					for (int x = 0; x < Q.Word.length; x++) {
						int t =  x;
						char [] blank=Q.Blank.toCharArray();
						answerf[x] = (byte) blank[t];
					}	
					String ansstr = new String(answerf, 0, answerf.length);
					String questionstr = new String(Q.Word, 0, Q.Word.length);


					if (ansstr.equals(questionstr))
					{
						System.out.println(ip+":"+ansstr);
						score = score + 1;
					}
				}
			}

			socket.close();
		}

		catch (Exception e)
		{

		}


	}
}


