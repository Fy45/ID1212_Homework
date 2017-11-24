/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NIOClient;

/**
 *
 * @author user
 */


import java.io.IOException;  
import java.net.InetSocketAddress;  
import java.nio.ByteBuffer;  
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.SocketChannel;  
import java.nio.charset.Charset;  
import java.util.Scanner;  
  
  
public class TESTclient {  
  
    private Selector selector = null;  
    static final int port = 30001;  
    private Charset charset = Charset.forName("UTF-8");  
    private SocketChannel sc = null;  
    public void init() throws IOException  
    {  
        selector = Selector.open();  
        //����Զ��������IP�Ͷ˿�  
        sc = SocketChannel.open(new InetSocketAddress("192.168.1.103",port));  
        sc.configureBlocking(false);  
        sc.register(selector, SelectionKey.OP_READ);  
        //����һ�����߳�����ȡ�ӷ������˵�����  
        new Thread(new ClientThread()).start();  
        //�����߳��� �Ӽ��̶�ȡ�������뵽��������  
        Scanner scan = new Scanner(System.in);  
        while(scan.hasNextLine())  
        {  
            String line = scan.nextLine(); 
            if(line.equals("NO")){
                System.exit(0);
            }
            sc.write(charset.encode(line)); 
           
        }  
          
    }  
    private class ClientThread implements Runnable  
    {  
        public void run()  
        {  
            try  
            {  
                while(selector.select() > 0)  
                {  
                    for(SelectionKey sk : selector.selectedKeys())  
                    {  
                        selector.selectedKeys().remove(sk);  
                        if(sk.isReadable())  
                        {  
                            //ʹ�� NIO ��ȡ Channel�е�����  
                            SocketChannel sc = (SocketChannel)sk.channel();  
                            ByteBuffer buff = ByteBuffer.allocate(1024);  
                            String content = "  ";  
                            while(sc.read(buff) > 0)  
                            {  
                                buff.flip();  
                                content += charset.decode(buff);  
                                System.out.println("Message " + content);
                            }  
                              
                            sk.interestOps(SelectionKey.OP_READ);  
                        }  
                    }  
                }  
            }  
            catch (IOException io)  
            {}  
        }  
    }  
      
      
      
    public static void main(String[] args) throws IOException  
    {  
        new TESTclient().init();  
    }  
}  
