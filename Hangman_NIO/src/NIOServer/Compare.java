package NIOServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Compare {//to see if the string is a word or a letter or wrong length
     String Outputword;
     Generateword wordaftercompare;
     int Guesstimeleft;
     char []blank;
     Compare(String strIn, Generateword wordtoguess,int guesstimeleft){
          blank=wordtoguess.Blank.toCharArray();
        int tapinlengh=strIn.length();
        String word=new String(wordtoguess.Word,0,wordtoguess.Word.length);//word是字符串，Word是字符数组
        if(tapinlengh==wordtoguess.Word.length){
            if(strIn.equals(word)){//看是否是整词相等
                blank=wordtoguess.Word;
                Outputword="Excellent! right choice!";
                guesstimeleft=0;              
            }
            else{
                Outputword="Wrong word!";
                guesstimeleft=guesstimeleft-1;
                 
            }
        }
            else if(tapinlengh==1){//看单词效果
                char [] getin=strIn.toCharArray();
                   
                    int flag=0;
                    
               for(int i=0;i<word.length();i++){
                   if(getin[0]==wordtoguess.Word[i]){
                      if(getin[0]!=blank[i]){
                        blank[i]=getin[0];
                        
                        flag=1;
                      }else{
                          flag=2;
                       
                      }       
                   }
                                                                   
               }
             if (flag==0)
                   {Outputword="wrong guess!";
                   guesstimeleft=guesstimeleft-1;}
                   else if(flag==1)
                   {Outputword="right guess";}
                   else
                   {Outputword="dumplicate input";
                    guesstimeleft=guesstimeleft-1;}    
            }
            
            else{
                Outputword="Wrong word length";
                guesstimeleft=guesstimeleft-1;
            }
          wordtoguess.Blank=new String(blank,0,wordtoguess.Word.length);
        if(word.equals(wordtoguess.Blank)){
            guesstimeleft=0;
        }
        wordaftercompare=wordtoguess;
        Guesstimeleft=guesstimeleft;
        }
        
    }
    
