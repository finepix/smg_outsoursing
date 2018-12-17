package com.smg.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListener {
	ServerSocket ss;
	int port;
	
	public ServerListener(int port) {
		this.port=port;
	}

	public void listen() {
		try {
			ss = new ServerSocket(port);
			SocketThread st;
			while (true) {
				st=new SocketThread(ss.accept());
				System.out.println("Someone connect!");
				st.start();
				SocketManager.getSocketManager().addSocket(st);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	public void  close() {
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
