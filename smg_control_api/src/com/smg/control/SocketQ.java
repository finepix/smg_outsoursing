package com.smg.control;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketQ {
	String url;
	int port;
	
	public SocketQ(String url,int port) {
		this.url=url;
		this.port=port;
	}
	void get(){
		Socket socket=new Socket();
				
				try {
					socket.connect(new InetSocketAddress(url,port));
					byte b[]=new byte[1];
					String serverStr=null;
					int flag; 
					int num;
					
					
					while (true) {
						
						flag= socket.getInputStream().read(b);
//						System.out.println("client input ="+new String(b));//111
						
			            if (flag != -1) {
			                serverStr = new String(b);			                
			            }
			            num=Integer.parseInt(new String(b,"utf-8"));
			            
//			            System.out.println("client output ="+new String((num+0)+""));//111
			            
			            socket.getOutputStream().write(new String((num+0)+"").substring(0, 1).getBytes("utf-8"));
			            
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						socket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
	}
	
}
