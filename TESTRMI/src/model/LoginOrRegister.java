/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.UserInfo;

/**
 *
 * @author user
 */
public class LoginOrRegister {


	public static int flag;
	public static String feedback1;
	public String feedback2;
	public static String user=null;
	public static String login(String loginname){
		String loginname1=getCTX(loginname,"%","$");
		String loginpassword=getCTX(loginname,"$","*");
		Connection conn = SQL_connector.sqlconnector();       
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

		return feedback1;
	}

	public static String register(String registername){

		System.out.println(registername);
		String registername1=getCTX(registername,"%","$");
		String registerpassword1 = getCTX(registername,"$","*");


		Connection conn = SQL_connector.sqlconnector();
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


		return feedback1;
	}





	public static int MAX(String[] M){
		int n = 0;  
		for (String M1 : M) {
			if (null != M1) {
				n++;
			}
		}
		return n;    
	}  


	public static String getCTX(String originalCTX,String firstSplit,String secondSplit){
		String resultCTX = originalCTX.substring(originalCTX.lastIndexOf(firstSplit), 
				originalCTX.lastIndexOf(secondSplit));
		resultCTX = resultCTX.substring(1,resultCTX.length());
		return resultCTX;
	}

}    

