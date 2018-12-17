package com.smg.control;

import java.util.Vector;

public class SocketManager {
	//single example
	private SocketManager() {}
	
	public static final SocketManager sm=new SocketManager();
	
	public static SocketManager getSocketManager(){
		return sm;
	}
	
	Vector<SocketThread> vector=new Vector<SocketThread>();
	
	public void addSocket(SocketThread st) {
		vector.add(st);
	}
	
	public void publish(SocketThread st,String msg){		
		SocketThread sto;
		for (int i=0;i<vector.size();i++) {
			sto=vector.get(i);
			if(!st.equals(sto)){
				sto.send(msg);
			}
			
		}
	}

	public void destory(SocketThread st){
		for (int i=0;i<vector.size();i++) {
			if(st.equals(vector.get(i))){
				vector.get(i).send("Connect destory!");
				vector.get(i).close();
				vector.remove(i);
			}
			
		}
	}

}
