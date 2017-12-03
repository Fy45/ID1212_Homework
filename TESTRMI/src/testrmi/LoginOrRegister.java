/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrmi;//首先得在服务端写出来如果收到了一连串的字符应该怎么办，如果在客户端选择不同的东西应该怎么处理的；
import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class LoginOrRegister {
    //public static String [] client=new String[20];
    //public static String [] username = new String[20];
    //public static String [] password = new String[20];
    //public static int queque;
    public static int flag;//这个的作用是判断是否成功登入了
    public static String feedback1;//这是给予不同反馈
    public String feedback2;
    public static String user=null;
   public static String login(String loginname){
       String loginname1=getCTX(loginname,"%","$");
       String loginpassword=getCTX(loginname,"$","*");
            Connection conn = Mysqlconnector.Mysqlconnector();       
            String sql = "select * from UserInfo";
            PreparedStatement pstmt;
            try{
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(loginname1)&&rs.getString(2).equals(loginpassword)){
                    feedback1="Log in successfully"; 
                    user=loginname1;
                    break;
                 
                }
                else{
                    feedback1="Username or password wrong";
                }
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(LoginOrRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
   /**    int Max;
       flag=0;
       Max = MAX(client);
       for(int i=0;i<Max;i++){
           System.out.println(client[i]);
           System.out.println(loginname);
           if (loginname.equals(client[i])){              
           feedback1="Log in Successfully";
           
           queque=i;
       }
     
       }
       if(flag==0){
           feedback1="Wrong Input .  Try again";
       }**/
          return feedback1;
      }
       
   public static String register(String registername){
   
        System.out.println(registername);
        String registername1=getCTX(registername,"%","$");
        String registerpassword1 = getCTX(registername,"$","*");
        
        
            Connection conn = Mysqlconnector.Mysqlconnector();
            String sql = "select * from UserInfo";
            PreparedStatement pstmt;
            try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
                {
                    
                    if(rs.getString(1).equals(registername1))
                    {
                        feedback1 = "Wrong! User exists!" ;
                        flag = 1;
                        break;
                    }
                    
                }
            if (flag==0)
            {
                DatabaseFunction.dataInsert(new UserInfo(registername1, registerpassword1));
                feedback1 = "Create user Sucessfully! Welcome user: " + registername1;
                user=registername1;
                DatabaseFunction.dataBaseGetAll();
            }
            } catch (SQLException e) {
        e.printStackTrace();
    }
        
        
    /**    
        
        
        for(int i=0;i<Max;i++){
           if (registername1.equals(client[i])){              
           feedback1="User exsist";
           flag=1;
       }
     
       }
       if(flag==0){
           client[Max]=registername;
           System.out.println("wow: "+ client[Max]);
           username[Max]=getCTX(registername,"%","$");
           password[Max]=getCTX(client[Max],"$","*");
           System.out.println(username[Max]);
           queque=Max;
           feedback1="Successfully register";
       } **/
          return feedback1;
      }
     
      
          
 
      
     public static int MAX(String[] M){
     int n = 0;  //保存元素个数的变量
        for (String M1 : M) {
            if (null != M1) {
                n++;
            }
        }
    return n;    
    }  

       
     public static String getCTX(String originalCTX,String firstSplit,String secondSplit){//怎样利用分隔符分割结构
        String resultCTX = originalCTX.substring(originalCTX.lastIndexOf(firstSplit), 
        originalCTX.lastIndexOf(secondSplit));
        resultCTX = resultCTX.substring(1,resultCTX.length());
        return resultCTX;
    }
    
}    

