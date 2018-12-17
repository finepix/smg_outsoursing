package com.smg.demo;

import com.smg.control.ServerListener;

public class Startup {
	public static void main(String[] args) {
		new ServerListener(10086).listen();
	}
}
