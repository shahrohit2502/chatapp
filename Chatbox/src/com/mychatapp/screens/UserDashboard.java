 package com.mychatapp.screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import com.mychatapp.dao.UserDAO;
import com.mychatapp.network.*;
import java.awt.event.*;

public class UserDashboard extends JFrame implements Runnable{
	
	JTextArea messageTextArea;
	public JTextArea messages;
	JList friendsList;
	
	String userID;
	
	Client client;
	
	static Server s;
	
	public UserDashboard(String userID){
		new Thread(this).start();
		this.userID=userID;
		initialize();
		createThread();
	}
	
	public void run()
	{
		Server.main(null);
	}
	
	private void initialize() {
		setBounds(100, 50, 500, 600);
		setTitle("MyChatApp:DashBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 153, 102));
		header.setBounds(0, 0, 1000, 80);
		getContentPane().add(header);
		header.setLayout(null);
		
		JLabel usernameHeader = new JLabel("WELCOME    "+getUserName());
		usernameHeader.setForeground(Color.WHITE);
		usernameHeader.setFont(new Font("Mongolian Baiti", Font.BOLD | Font.ITALIC, 40));
		usernameHeader.setBounds(0, 0, 500, 80);
		header.add(usernameHeader);
		
		JPanel membersHeading = new JPanel();
		membersHeading.setBackground(Color.LIGHT_GRAY);
		membersHeading.setBounds(10, 90, 200, 60);
		getContentPane().add(membersHeading);
		membersHeading.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Members");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 30));
		lblNewLabel.setBounds(40, 10, 120, 40);
		membersHeading.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(25, 50, 150, 5);
		membersHeading.add(separator);
		
		JButton sendButton = new JButton("SEND");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		
		sendButton.setBackground(new Color(0, 153, 102));
		sendButton.setFont(new Font("Century", Font.BOLD, 20));
		sendButton.setBounds(365, 480, 100, 60);
		sendButton.setForeground(Color.WHITE);
		getContentPane().add(sendButton);
		
		friendsList = new JList(getFriendList());
		friendsList.setBounds(0, 0, 200, 300);
		friendsList.setFont(new Font("Monospaced", Font.PLAIN, 25));
		friendsList.setLayout(null);
		
		JScrollPane friendsScrollPane = new JScrollPane(friendsList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		friendsScrollPane.setBounds(10, 150, 200, 300);
		getContentPane().add(friendsScrollPane);
		
		messages = new JTextArea();
		messages.setEditable(false);
		messages.setWrapStyleWord(true);
		messages.setLineWrap(true);
		messages.setFont(new Font("Monospaced", Font.PLAIN, 15));
		messages.setBounds(240, 90, 700, 350);

		JScrollPane messagesScrollPane = new JScrollPane(messages,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messagesScrollPane.setBounds(240, 90, 200, 350);
		getContentPane().add(messagesScrollPane);
		
		messageTextArea = new JTextArea();
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setLineWrap(true);
		messageTextArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
		messageTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTextArea.setBounds(10, 470, 800, 80);
		
		JScrollPane messageTextAreaScrollPane = new JScrollPane(messageTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messageTextAreaScrollPane.setBounds(10, 470, 350, 80);
		getContentPane().add(messageTextAreaScrollPane);
		
	}
	
	private String getUserName()
	{
		String userName="";
		UserDAO user=new UserDAO();
		userName=user.getUserName(userID);
		return userName;
	}
	
	private String[] getFriendList()
	{
		UserDAO user=new UserDAO();
		ArrayList<String> fl=user.getFriendList(userID);
		String friends[]=fl.toArray(new String[fl.size()]);
		return friends;
	}
	
	public void createThread() {
		client=new Client(this);
	}
	
	public void sendMessage()
	{
		try {
			String message=messageTextArea.getText();
			messageTextArea.setText("");
			client.sendMessage(getUserName()+" : "+message);
		}
		catch(Exception e)
		{ }
	}

}
