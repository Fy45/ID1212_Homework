/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Server;

/**
 *
 * @author user
 */
public class Generateword {
     char [] Word;
     String Blank;
     int lifetime;
    int RestBlank;
  
    public void getWord() throws Exception{
        try{       
        	
        
        
        
       String NEWWORD=HangmanSeverClient.workspace[(int)(Math.random()*51527)];
       lifetime = NEWWORD.length();
       char [] blank=new char [lifetime];
       Word=new char [lifetime];//
	 Word=NEWWORD.toCharArray();
	 RestBlank=lifetime;
	for(int i=0;i<lifetime;i++) {
		blank[i]='*';}
        Blank=new String(blank,0,blank.length);
	//System.out.println("word to guess: "+String.valueOf(Blank));
        }catch(Exception e){
            
        }
}
}
