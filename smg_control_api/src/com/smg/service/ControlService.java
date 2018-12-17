package com.smg.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.control.MySocket;


@WebServlet("/open")
public class ControlService extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 833482336770793686L;
	private static MySocket ms;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//get the printWriter
		PrintWriter out = resp.getWriter();
		
		String options = req.getParameter("options");
		
		if(options==null){
			out.print("please give a options!!");
			return ;
		}
		
		if(options.equals("close")&&ms!=null){//&&(ms.isAlive()||)
			ms.interrupt();
			try {
				ms.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ms=null;
			out.print("this Thread is close anyway!");
			destroy();
			
			return ;
		}
		
		else if(options.equals("open")&&ms==null){
			//¿ªÆô²Ù×÷
			try {
				int port=10086;
				ms=new MySocket(port);
				ms.setFilePath("/home/zhuxin/smg/status.txt");
				ms.start();
				
				out.print("this port is open !");
			} catch (Exception e) {
				out.print("failed!!!");
			}
			
		}
		else{
			out.print("what you are called is never read , can not find a options");
		}
		
		
		out.flush();
		out.close();
		
		
	}
	
	

}
