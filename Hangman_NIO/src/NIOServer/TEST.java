/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NIOServer;

/**
 *
 * @author user
 */

import java.io.IOException;  
import java.net.InetSocketAddress;  
import java.net.SocketAddress;
import java.nio.ByteBuffer;  
import java.nio.channels.Channel;  
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel;  
import java.nio.charset.Charset;  
import java.util.logging.Level;
import java.util.logging.Logger;
  
  
public class TEST {  
    private String [] client=new String [20];
    private Selector selector = null;
    private int queque;
    private Generateword [] Good=new Generateword[20];//����
    private int [] Score=new int [20];//����
    private int [] lefttime=new int [20];//ʣ�����
    private int [] connecttime=new int [20];
    static final int port = 30001;  
    private Charset charset = Charset.forName("UTF-8");  
    public void init() throws IOException, Exception  
    {  
        selector = Selector.open();  
        ServerSocketChannel server = ServerSocketChannel.open();  
        server.bind(new InetSocketAddress(port));  
        //�������ķ�ʽ  
        server.configureBlocking(false);  
        //ע�ᵽѡ�����ϣ�����Ϊ����״̬  
        server.register(selector, SelectionKey.OP_ACCEPT);  
          
        System.out.println("Server is listening now...");  
          
        //�˴���������  
        while(selector.select() > 0)  
        {  
              
            for(SelectionKey sk : selector.selectedKeys())  
            {  
                selector.selectedKeys().remove(sk);  
                //�������Կͻ��˵���������  
                if(sk.isAcceptable())  
                {  
                    SocketChannel sc = server.accept();  
                    //������ģʽ  
                    sc.configureBlocking(false);  
                    //ע��ѡ������������Ϊ��ȡģʽ  
                    sc.register(selector, SelectionKey.OP_READ);  
                    //���˶�Ӧ��channel����Ϊ׼�����������ͻ�������  
                    sk.interestOps(SelectionKey.OP_ACCEPT);  
                      
                }  
                //�������Կͻ��˵����ݶ�ȡ����  
                if(sk.isReadable())  
                {  
                    //���ظ�SelectionKey��Ӧ�� Channel��������������Ҫ��ȡ    ��acceptable����
                    SocketChannel sc = (SocketChannel)sk.channel();  
                    ByteBuffer buff = ByteBuffer.allocate(1024);  
                    StringBuilder content = new StringBuilder();  
                    try  
                    {      int ISthere = 0;
                           int sequence = 0;
                        String IPport=sc.getRemoteAddress().toString();
                        
                        if(queque==0){
                            client[0]=IPport;
                        }
                        queque=MAX(client);
                        
                            for (int i=0;i<queque;i++){
                                if (client[i].equals(IPport)){
                                  connecttime[i]= connecttime[i]+1;
                                  ISthere=1;
                                  sequence=i;
                                }
                            }
                            if(ISthere==0){
                                client[queque]=IPport;
                                connecttime[queque]= connecttime[queque]+1;
                                sequence=queque;
                            }
                            if(connecttime[sequence]==1){
                                Good[sequence]=new Generateword();
                                Good[sequence].getWord();
                                lefttime[sequence]=Good[sequence].lifetime;
                               
                               System.out.println(Good[sequence].Word);
                            }
                            
                        
                        while(sc.read(buff) > 0)  
                        {    System.out.println("Server is listening from client :" + sc.getRemoteAddress());                                                      
                            buff.flip();  
                           // content.append(charset.decode(buff)); //��һ�仰�����ָ��
                          
                            String X=charset.decode(buff).toString();
                            
                                
                             content.append(X);
                             content.append(" ");
                             
                            int life=lefttime[sequence];//�Ĺ�
                           connect C=new connect();
                           C.connect(connecttime[sequence], X,Score[sequence],Good[sequence],life);//�Ĺ�
                           //System.out.println(lefttime[sequence]);
                           connecttime[sequence]=C.CONNECTTIME;
                           if(C.wordflag==1){
                               Good[sequence]=C.testWord;
                           }
                           Score[sequence]=C.sCore;
                           //System.out.println(Score[sequence]);
                           lefttime[sequence]=C.LIFE;
                            String output=C.sc;
                            content.append(output);
                           // content.append("  connect times").append(String.valueOf(connecttime[sequence]));
                            System.out.println("Server is listening from client " + sc.getRemoteAddress() + " data rev is: " + content);  
                       sc.write(charset.encode(content.toString()));
                        }  
                        
                          
                        //���˶�Ӧ��channel����Ϊ׼����һ�ν�������  
                        sk.interestOps(SelectionKey.OP_READ);  
                    }  
                    catch (IOException io)  
                    {  
                        sk.cancel();  
                        if(sk.channel() != null)  
                        {  
                            sk.channel().close();  
                        }  
                    }  
                   if(content.length() > 0)  
                    {  
                        //�㲥���ݵ����е�SocketChannel��  
                        for(SelectionKey key : selector.keys())  
                        {  
                            Channel targetchannel = key.channel();  
                            if(targetchannel instanceof SocketChannel)  
                            {  
                                SocketChannel dest = (SocketChannel)targetchannel;  
                               // dest.write(charset.encode(content.toString()));  
                            }  
                        }  
                    }  
                      
                }  
            }  
        }  
    }  
    public int MAX(String[] M){
     int n = 0;  //����Ԫ�ظ����ı���
        for (String M1 : M) {
            if (null != M1) {
                n++;
            }
        }
    return n;    
    }  
    public static void main(String[] args) throws IOException, Exception
    {  
      
            while(true){
            new TEST().init();
            }
        
    }  
}
