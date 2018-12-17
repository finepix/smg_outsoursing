package com.smg.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocket extends Thread {
	String filePath;
	int port;
	ServerSocket ss;
	
	public MySocket(int port){
		this.port=port;
	}
	public void setFilePath(String filePath) {
		this.filePath=filePath;
	}
	
	public void run() {
		try {
			ss = new ServerSocket(port);
			SocketThread st;
			while (true) {
				st=new SocketThread(ss.accept());
				st.setFilePath(filePath);
				st.start();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
