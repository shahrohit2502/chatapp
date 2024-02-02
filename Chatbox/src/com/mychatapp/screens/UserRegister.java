package com.mychatapp.screens;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mychatapp.dao.UserDAO;
import com.mychatapp.dto.UserRegisterDTO;

import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;


public class UserRegister extends JFrame {
	
	private JTextField userName;
	private JTextField userMobileNumber;
	private JTextField userID;
	private JPasswordField userPassword;
	ButtonGroup userGender;
	JRadioButton maleGender;
	JRadioButton femaleGender;
	JRadioButton otherGender;
	JLabel passwordLabel;
	private JLabel mobileLabel;
	
	/**
	 * this is registration window class 
	 */
	
	public UserRegister()
	{
		initialize();
	}
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,600,400);
		getContentPane().setLayout(null);
		
		setTitle("MyChatApp: Register");
		
		JLabel Name = new JLabel("User Name :");
		Name.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		Name.setBounds(80, 50, 140, 30);
		getContentPane().add(Name);
		
		JLabel MobileNumber = new JLabel("Mobile Number :");
		MobileNumber.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		MobileNumber.setBounds(80, 100, 140, 30);
		getContentPane().add(MobileNumber);
		
		JLabel userid = new JLabel("User ID :");
		userid.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		userid.setBounds(80, 150, 140, 30);
		getContentPane().add(userid);
		
		JLabel password = new JLabel("Password :");
		password.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		password.setBounds(80, 200, 140, 30);
		getContentPane().add(password);
		
		userName = new JTextField();
		userName.setToolTipText("Enter user name");
		userName.setBounds(215, 40, 260, 25);
		getContentPane().add(userName);
		userName.setColumns(10);
		
		userMobileNumber = new JTextField();
		userMobileNumber.setToolTipText("Enter mobile number");
		userMobileNumber.setColumns(10);
		userMobileNumber.setBounds(215, 90, 260, 25);
		getContentPane().add(userMobileNumber);
		
		userID = new JTextField();
		userID.setToolTipText("Enter user ID ");
		userID.setColumns(10);
		userID.setBounds(215, 140, 260, 25);
		getContentPane().add(userID);
		
		userPassword = new JPasswordField();
		userPassword.setToolTipText("Enter password");
		userPassword.setBounds(215, 190, 260, 25);
		getContentPane().add(userPassword);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					register(); // this function will register the new user
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		registerBtn.setFont(new Font("Serif", Font.BOLD, 16));
		registerBtn.setBounds(100, 310, 120, 40);
		getContentPane().add(registerBtn);
		
		JLabel gender = new JLabel("Gender :");
		gender.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		gender.setBounds(80, 250, 140, 30);
		getContentPane().add(gender);
		
		maleGender = new JRadioButton("Male");
		maleGender.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		maleGender.setBounds(215, 250, 60, 30);
		getContentPane().add(maleGender);
		
		femaleGender = new JRadioButton("Female");
		femaleGender.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		femaleGender.setBounds(300, 250, 90, 30);
		getContentPane().add(femaleGender);
		
		otherGender = new JRadioButton("Others");
		otherGender.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		otherGender.setBounds(400, 250, 80, 30);
		getContentPane().add(otherGender);
		
		userGender=new ButtonGroup();
		userGender.add(maleGender);
		userGender.add(femaleGender);
		userGender.add(otherGender);
		
		JButton loginBtn = new JButton("Back to Login page");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToLogin(); // this function will open the login window
			}
		});
		loginBtn.setFont(new Font("Sitka Display", Font.BOLD, 16));
		loginBtn.setBounds(300, 310, 160, 40);
		getContentPane().add(loginBtn);
		
		passwordLabel = new JLabel("");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordLabel.setBounds(215, 215, 550, 30);
		getContentPane().add(passwordLabel);
		
		mobileLabel = new JLabel("");
		mobileLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mobileLabel.setBounds(215, 125, 260, 15);
		getContentPane().add(mobileLabel);
		
		
	}
	
	private void backToLogin()
	{
		this.setVisible(false);
		// opening login window
		UserLogin loginWindow=new UserLogin();
		loginWindow.setVisible(true);
	}
	
	private void register() throws SQLException
	{
		String name=userName.getText();
		String mobile=userMobileNumber.getText();
		String userid=userID.getText();
		char []password=userPassword.getPassword();
		String gender;
		if(maleGender.isSelected())
			gender="male";
		else if(femaleGender.isSelected())
			gender="female";
		else
			gender="other";
		if(name.equals(""))
			JOptionPane.showMessageDialog(null,"Please fill the details");
		else
		{				
			if(checkMobileno(mobile).equals("Invalid")) // validation on mobile number
			{
				JOptionPane.showMessageDialog(null,"Invalid Mobile number");
				return;
			}
			if(checkUserID(userid).equals("Invalid"))  // validation on userID
			{
				JOptionPane.showMessageDialog(null,"Invalid User ID");
				return;
			}
			if(checkPassword(password).equals("Invalid"))  // validation on password
			{
				JOptionPane.showMessageDialog(null,"Invalid Password");
				return;
			}
				
			UserRegisterDTO registerDetails=new UserRegisterDTO();
			registerDetails.setName(name);
			registerDetails.setMobile(mobile);
			registerDetails.setUserid(userid);
			registerDetails.setPassword(password);
			registerDetails.setGender(gender);
			UserDAO user=new UserDAO();
			user.register(registerDetails);
			clearRegistrationForm(); // this function will clear the fields of the registration form
		}
	}
	
	private String checkMobileno(String mobile)
	{
		if(mobile.length()!=10)
		{
			mobileLabel.setText("Must contain 10 digits only");
			return "Invalid";
		}
		try
		{
			Long.parseLong(mobile);
		}
		catch(Exception e)
		{
			return "Invalid";
		}
		return "Valid";
	}
	
	private String checkUserID(String userid)
	{
		if(userid.indexOf('@')<1)
			return "Invalid";
		if(userid.lastIndexOf('.')>userid.length()-3)
			return "Invalid";
		if(userid.lastIndexOf('.')-userid.indexOf('@')<3)
			return "Invalid";
		return "Valid";
	}
	
	private String checkPassword(char []password)
	{
		if(password.length<8)
		{
			passwordLabel.setText("Must contain at least 8 characters");
			return "Invalid";
		}
		boolean smallLetter=true;
		boolean capitalLetter=true;
		boolean number=true;
		boolean specialSymbol=true;
		for(int i=0;i<password.length;i++)
		{
			char ch=password[i];
			if(ch>='a' && ch<='z')
			{
				smallLetter=false;
			}
			if(ch>='A' && ch<='Z')
			{
				capitalLetter=false;
			}
			if(ch>='0' && ch<='9')
			{
				number=false;
			}
			if(ch=='@' || ch=='&' || ch=='%' || ch=='#'  || ch=='$')
			{
				specialSymbol=false;
			}
		}
		if(smallLetter || capitalLetter || number  || specialSymbol)
		{
			passwordLabel.setText("Must contain at least 1 small and capital alphabet and number and special symbol-@,&,%,#,$");
			return "Invalid";
		}
		return "Valid";
	}
	
	private void clearRegistrationForm()
	{
		userName.setText("");
		userMobileNumber.setText("");
		userID.setText("");
		userPassword.setText("");
		userGender.clearSelection();
	}

}