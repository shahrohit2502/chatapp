package com.mychatapp.screens;

import com.mychatapp.dao.UserDAO;
import com.mychatapp.dto.UserLoginDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class UserLogin extends JFrame{
	
	private JTextField userID;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		UserLogin loginWindow=new UserLogin();
		loginWindow.setVisible(true); 
	}

	
	public UserLogin() {
		initialize();
	}
	private void initialize()
	{
		setBounds(100,100,450,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		//title of the login window
		setTitle("MyChatApp: Login");
		
		JLabel userid = new JLabel("User Id :");
		userid.setBounds(60, 40, 85, 35);
		userid.setFont(new Font("Calibri", Font.PLAIN, 20));
		getContentPane().add(userid);
		
		
		//for take input userID
		userID = new JTextField();
		userID.setToolTipText("Enter user ID");
		userID.setBounds(220, 40, 150, 35);
		getContentPane().add(userID);
		userID.setColumns(10);
		
		JLabel password = new JLabel("Password :");
		password.setBounds(60, 120, 110, 35);
		password.setFont(new Font("Calibri", Font.PLAIN, 20));
		getContentPane().add(password);

		//for take input password
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter password");
		passwordField.setBounds(220, 120, 150, 35);
		getContentPane().add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(60, 200, 120, 35);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doLogin(); //this function will take the user to dashboard if user is registered
				} 
				catch (Exception e1) 
				{ }
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(loginBtn);
		
		JButton createBtn = new JButton("Register");
		createBtn.setBounds(250, 200, 120, 35);
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();  //this function will register new users
			}
		});
		createBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(createBtn);
		
	}
	
	private void createAccount()
	{
		this.setVisible(false);
		//Opening register window to take information of user to register
		UserRegister registerWindow=new UserRegister();
		registerWindow.setVisible(true);
	}
	
	private void doLogin() throws SQLException
	{
		String userId=userID.getText();
		char []userPassword=passwordField.getPassword();
		//DTO for login information
		if(userId.equals("") || String.valueOf(userPassword).equals(""))
			JOptionPane.showMessageDialog(null,"Please fill the details");
		else
		{
			UserLoginDTO loginDetails=new UserLoginDTO();
			loginDetails.setUserID(userId);
			loginDetails.setUserPassword(userPassword);
			UserDAO user=new UserDAO();
			boolean valid=user.login(loginDetails); // if user is registered then return true otherwise return false
			if(valid==true)
			{
				this.setVisible(false);
				//taking the user to dashboard
				UserDashboard dashboard=new UserDashboard(userId);
				dashboard.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Incorrect userID or Password");
			}
		}
	}

}