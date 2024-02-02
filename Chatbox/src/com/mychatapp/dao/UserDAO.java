package com.mychatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mychatapp.dto.UserLoginDTO;
import com.mychatapp.dto.UserRegisterDTO;
import com.mychatapp.sql.SQLconnection;

public class UserDAO {
	
	private Connection con;
	private String query="";
	
	public boolean login(UserLoginDTO loginDetails) throws SQLException
	{
	
		try
		{
			con=SQLconnection.createConnectionWithMychatapp();
			query="SELECT userid,password FROM registered_user WHERE userid=? and password=?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,loginDetails.getUserID());
			statement.setString(2,String.valueOf(loginDetails.getUserPassword()));
			ResultSet rs=statement.executeQuery();
			
			if(rs.next())  
				return true;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally
		{
			con.close();
		}
		return false;
	}
	
	public void register(UserRegisterDTO registerDetails) throws SQLException
	{
		/**
		 * this method will register new user in registered_user table in mychatapp DataBase
		 */
		try
		{
			con=SQLconnection.createConnectionWithMychatapp();
			if(con!=null)
			{
				query="INSERT INTO registered_user (userid,password,username,mobileNumber,gender) VALUES (?,?,?,?,?)";
				PreparedStatement statement=con.prepareStatement(query);
				statement.setString(1, registerDetails.getUserid());
				statement.setString(2, String.valueOf(registerDetails.getPassword()));
				statement.setString(3, registerDetails.getName());
				statement.setLong(4, Long.parseLong(registerDetails.getMobile()));
				statement.setString(5, registerDetails.getGender());
				statement.execute();
				JOptionPane.showMessageDialog(null,"Registration Sucessful.\nGo back to Login page for login...");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		finally
		{
			con.close();
		}
	}
	
	public String getUserName(String userID)
	{
		try
		{
			con=SQLconnection.createConnectionWithMychatapp();
			if(con!=null)
			{
				query="SELECT username FROM registered_user WHERE userid=?";
				PreparedStatement statement=con.prepareStatement(query);
				statement.setString(1, userID);
				ResultSet rs=statement.executeQuery();
				rs.next();
				String userName=rs.getString("username");
				userName= userName.substring(0,1).toUpperCase()+userName.substring(1);
				return userName;
			}
		}
		catch (Exception e)
		{ }
		finally
		{
			try {
				con.close();
			} catch (SQLException e) 
			{ }
		}
		return "Unkown";
	}
	
	public ArrayList<String> getFriendList(String userID)
	{
		try
		{
			con=SQLconnection.createConnectionWithMychatapp();
			if(con!=null)
			{
				query="SELECT username FROM registered_user WHERE userid!=?";
				PreparedStatement statement=con.prepareStatement(query);
				statement.setString(1, userID);
				ResultSet rs=statement.executeQuery();
				
				ArrayList<String> friends=new ArrayList<String>();
				
				while(rs.next())
				{
					String friend=rs.getString("username");
					friend= friend.substring(0,1).toUpperCase()+friend.substring(1);
					friends.add(friend);
				}
				return friends;
			}
		}
		catch (Exception e)
		{ }
		finally
		{
			try {
				con.close();
			} catch (SQLException e) 
			{ }
		}
		return null;
	}

}