package com.smg.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SocketThread extends Thread{
	Socket socket;
	BufferedReader br;
	BufferedWriter bw;
	
	public SocketThread(Socket socket) {
		this.socket=socket;		
	}

	public void run() {
		try {
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String line=null;
			while((line=br.readLine())!=null){
				if (line.equals("bye-bye")) {
					SocketManager.getSocketManager().destory(this);
					
				}
				SocketManager.getSocketManager().publish(this, line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(String out) {
		try {
			bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
			bw.write(out+"\n");
			bw.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  close() {
		try {
			br.close();
			bw.close();
			br=null;
			bw=null;
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
