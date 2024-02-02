package com.mychatapp.dto;

public class UserLoginDTO {
	
	private String userID;
	private char []userPassword;
	
	//this DTO for taking login information
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setUserPassword(char[] userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserID() {
		return userID;
	}
	public char[] getUserPassword() {
		return userPassword;
	}

}