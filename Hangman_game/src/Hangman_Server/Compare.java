/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman_Server;

/**
 *
 * @author Lida Liu && Yuan Fan
 * 
 * 
 */



public class Compare {
	//game body
	String Outputword;
	Generateword wordaftercompare;
	int Guesstimeleft;
	char []blank;


	//to see if the string is a word or a letter or wrong length
	Compare(String strIn, Generateword wordtoguess,int guesstimeleft){


		blank=wordtoguess.Blank.toCharArray();
		int tapinlengh=strIn.length();
		String word=new String(wordtoguess.Word,0,wordtoguess.Word.length);
		if(tapinlengh==wordtoguess.Word.length){
			if(strIn.equals(word)){
				blank=wordtoguess.Word;
				Outputword="Excellent! right choice!";
				guesstimeleft=0;

			}
			else{
				Outputword="Wrong word!";
			}
		}
		else if(tapinlengh==1){
			char [] getin=strIn.toCharArray();

			int flag=0;
			for(int i=0;i<word.length();i++){
				if(getin[0]==wordtoguess.Word[i]){
					if(getin[0]!=blank[i]){
						blank[i]=getin[0];
						guesstimeleft=guesstimeleft+1;
						flag=1;
					}else{
						flag=2;
					}       
				}
				if (flag==0)
				{Outputword="wrong guess!";}
				else if(flag==1)
				{Outputword="right guess";}
				else
				{Outputword="dumplicate input";}                                                    
			}

		}

		else{
			Outputword="Wrong word length";
		}
		wordtoguess.Blank=new String(blank,0,wordtoguess.Word.length);
		if(word.equals(wordtoguess.Blank)){
			guesstimeleft=0;
		}
		wordaftercompare=wordtoguess;
		Guesstimeleft=guesstimeleft;
	}

}



