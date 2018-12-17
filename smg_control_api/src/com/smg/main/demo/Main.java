package com.smg.main.demo;

import com.smg.control.MySocket;

public class Main {
public static void main(String[] args) {
	int port=6666;
	MySocket ms=new MySocket(port);
	ms.setFilePath("/home/zhuxin/smg/status.txt");
	ms.start();
	
//	SocketQ sq=new SocketQ("127.0.0.1", 10086);
//	sq.get();
}
}
