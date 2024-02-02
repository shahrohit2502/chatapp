package com.mychatapp.network;

import java.io.*;
import java.net.Socket;

import com.mychatapp.screens.UserDashboard;


public class Client extends Thread{
	
	BufferedReader in;
	BufferedWriter out;
	
	Socket client;
	
	UserDashboard user;
	
	public Client(UserDashboard user)
	{
		try {
			this.user=user;
			client = new Socket("localhost",9002);
			in= new BufferedReader(new InputStreamReader(client.getInputStream()));
			out= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			this.start();
			
		}
		catch(Exception e)
		{ }
	}
	
	public void run()
	{
		try {
			readMessage();
		} 
		catch (IOException e) 
		{ }
	}
	
	public void readMessage() throws IOException
	{
		try {			
			String message="";
			while((message= in.readLine())!=null)
			{
					user.messages.append(message+"\n");
			}
		}
		catch (Exception e) 
		{ }
		finally {
			try {
				client.close();
			} 
			catch (IOException e) 
			{ }
		}
	}
	
	public void sendMessage(String message)
	{
		try {
			out.write(message+"\n");
			out.flush();
		} 
		catch (IOException e) 
		{ }			
	}
}

