/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NIOServer;

import java.io.File;
import java.io.FileReader;

/**
 *
 * @author user
 */
public class Generateword {
	public char [] Word;
	public String Blank;
	public int lifetime;
	int RestBlank;

	public void getWord() throws Exception{
		File file = new File("src/NIOServer/words.txt");
		@SuppressWarnings("resource")
		FileReader reader = new FileReader(file);
		int fileLen = (int)file.length();
		char[] chars = new char[fileLen];
		reader.read(chars);
		String txt = String.valueOf(chars);
		String[] workspace = txt.split("\n");
		
		try{
			
			String NEWWORD=workspace[(int)(Math.random()*51527)];
			lifetime = NEWWORD.length();
			char [] blank=new char [lifetime];
			Word=new char [lifetime];//
			Word=NEWWORD.toCharArray();
			RestBlank=lifetime;
			for(int i=0;i<lifetime;i++) {
				blank[i]='*';}
			Blank=new String(blank,0,blank.length);
			System.out.println("word to guess: "+String.valueOf(Blank));
			
			
		}catch(Exception e){

		}
	}
}
