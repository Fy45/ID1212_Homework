/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrmi;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class Mysqlconnector {
   
    
    public static Connection Mysqlconnector(){
        Connection con =null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/firstDB";
        String user = "root";
        String password = "";//链接数据库到JAVA
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
                 
         } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
            return con;
    
}
}
