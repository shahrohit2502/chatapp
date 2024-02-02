package com.mychatapp.network;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread{
	
static ServerSocket server;
	
	BufferedReader in;
	BufferedWriter out;
	
	Socket socket;
	
	static ArrayList<BufferedWriter> clientsOut=new ArrayList<BufferedWriter>();
	
	public Server(Socket socket)
	{
		this.socket=socket;
	}
	
	public static void main(String[] args)  {
		
		try {	
			server= new ServerSocket(9002);
			while(true)
			{
				Socket client=server.accept();
				Server s=new Server(client);
				s.start();
			}
		}
		catch(Exception e)
		{ }
	}
	
	public void run()
	{
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			clientsOut.add(out);
			readMessage();
		} 
		catch (Exception e) 
		{ }
	}
	
	void readMessage() throws IOException
	{
		try {	
			String message="";
			while((message=in.readLine())!=null)
			{
				for(int i=0;i<clientsOut.size();i++)
				{
					out=(BufferedWriter)clientsOut.get(i);
					out.write(message+"\n");
					out.flush();
				}
			}
		}
		finally {
			server.close();
		}
	}

}