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
public class connect {//����һЩҪ�ӵ�������û��д����,�������Ҫ���һ���ַ���
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
            sc = "Your Score :  "+score+"   Do you want to start playing? (YES or NO)";//�������
            LIFE=left;
        }
        else if(time==2){//�������жϽ��յ����ǲ��ǣ����ʺ������ʣ����Ҳ����հף������������
             wordflag=1;
            if("YES".equals(str)){
                //Generateword NEW=new Generateword();
                testWord=theword;
                sc="Your left chance:"+left+" : "+theword.Blank;//change
               // System.out.println(str);
               }
            else{
                wordflag=0;
              CONNECTTIME=time-1;//����YES��ʱ�����¸�ֵ����Ҫ������
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
							sc=L.Outputword+"Your left chance(s): "+num1+"  "+String.valueOf(L.blank);//�����num1�����⣬����blank��ʾ��������
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
                        sCore = sCore + 1;//score��û�д���ȥ��ÿ�ε�����ʱ��Ҳ��û�д���ȥ��û�а����Ӵ�����λ
                        System.out.println(sCore);
                        
                    }
        }
       /* else{
        Compare CON=new Compare(str,theword,left);//�����б���,�������ַ����ͱȽϺ����жԱ�
        sc=CON.Outputword;
        testWord=CON.wordaftercompare;
        if(CON.Guesstimeleft==0){
            CONNECTTIME=0;
        } 
        }*/
        
    }
}
