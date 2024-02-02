package com.mychatapp.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLconnection {
	
static private Connection con=null;
	
	//this class is used to create connection with DataBases
	
	public static Connection createConnectionWithMychatapp()
	{
		
		//this method create connection with database - mychatapp
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox","root","rohit@99");
		} 
		catch (Exception e) 
		{ }
		return con;
	}

}