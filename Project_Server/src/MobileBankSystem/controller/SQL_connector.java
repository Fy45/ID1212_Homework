package MobileBankSystem.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author yfan
 */
public class SQL_connector {

	private static final String userName = "root";
	private static final String password = "";
	private static final String dburl="jdbc:mysql://localhost:3306/bank";
	private static final String driver="com.mysql.jdbc.Driver";


	public static Connection sqlconnector(){
		Connection con =null;


		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dburl,userName,password);
			if(!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;

	}
}
