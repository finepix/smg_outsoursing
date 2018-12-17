package com.smg.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SocketThread extends Thread{
	Socket socket;
	String filePath;
	
	public SocketThread(Socket socket) {
		this.socket=socket;
	}

	public void setFilePath(String filePath) {
		this.filePath=filePath;
	}
	public void run() {
		CatText ct=new CatText(filePath);;
		byte out[],in[]=new byte[1];
		byte sign[]=new byte[1];
		int flag=0;
		while (true) {
			try {
				out=ct.read();
				//write
				
//				System.out.println("server output ="+new String(out));//111
				
				socket.getOutputStream().write(out);
				sign=out;
				
				//get
				flag=socket.getInputStream().read(in);
				
//				System.out.println("server input ="+new String(in));//111
				
				if(flag!=-1){
					
					if(!new String(sign,"utf-8").equals(new String(in,"utf-8"))&&new String(in,"utf-8").length()!=0){
						ct.write(in);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				close();
			} 
			
			
		}
		
	}

	public void  close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
