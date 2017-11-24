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
public class connect {//还有一些要加的输出语句没有写进来,最后是需要输出一个字符串
    String sc;
    char[] newwordtobeguess;
    String newblanktobefilled;
    Generateword testWord;
  static int LIFE;
    int CONNECTTIME;
    int sCore;
    int wordflag;


  void connect (int time, String str,int score,Generateword theword,int left) throws Exception{
    //void connect (int time, String str) throws Exception{ 
        sCore=score;
        CONNECTTIME=time;
        if(time==1){
            wordflag=0;
            sc = "Your Score :  "+score+"   Do you want to start playing? (YES or NO)";//还差分数
            LIFE=left;
        }
        else if(time==2){//（首先判断接收到的是不是）产词函数产词，并且产生空白，产生存货次数
             wordflag=1;
            if("YES".equals(str)){
                //Generateword NEW=new Generateword();
                testWord=theword;
                sc="Your left chance:"+left+" : "+theword.Blank;//change
               // System.out.println(str);
               }
            else{
                wordflag=0;
              CONNECTTIME=time-1;//不是YES的时候，重新赋值并且要求重输
              sc="WRONG InPut,please obey the rule";
            }
        }
        else{
            wordflag=1;
            Compare L=new Compare(str,theword,left);
            testWord=L.wordaftercompare;            
            LIFE=L.Guesstimeleft;
            

                    
            if(LIFE==0)
						{
							sc=L.Outputword+" your answer: "+String.valueOf(L.blank);
                                                               CONNECTTIME=0 ;
										
						}
						else
						{
							String num1 = String.valueOf(LIFE);							
							sc=L.Outputword+"Your left chance(s): "+num1+"  "+String.valueOf(L.blank);//这里的num1有问题，并且blank显示结果很奇怪
						  //System.out.println(sc);
                                                }
                
                     //byte[] answerf = new byte[20];
                     //for (int x = 0; x < testWord.Word.length; x++) {
                     //   int t =  x;
                     //   char [] blank=testWord.Blank.toCharArray();
                     //   answerf[x] = (byte) blank[t];
                   // }	
                    //System.out.println(answerf);
                    String ansstr = String.valueOf(L.blank);
                     System.out.println(ansstr);
                    String questionstr = String.valueOf(testWord.Word);
                     System.out.println(questionstr);
                    if (ansstr.equals(questionstr))
                    {
                        sCore = sCore + 1;//score还没有穿出去，每次的生存时间也还没有穿出去，没有把链接次数归位
                        System.out.println(sCore);
                        
                    }
        }
       /* else{
        Compare CON=new Compare(str,theword,left);//调用判别函数,将产出字符串和比较后库进行对比
        sc=CON.Outputword;
        testWord=CON.wordaftercompare;
        if(CON.Guesstimeleft==0){
            CONNECTTIME=0;
        } 
        }*/
        
    }
}
