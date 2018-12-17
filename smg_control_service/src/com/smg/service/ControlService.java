package com.smg.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.control.ServerListener;

@WebServlet("/open")
public class ControlService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 833482336770793686L;
	
	private static ServerListener listener ;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String op = req.getParameter("op");
		
		try {
			if(op==null){
				out.print("Give a optiions!");
			}else if(op.equals("open")&&listener==null){//
				listener = new ServerListener(10086);
				new Thread(){
					public void run() {
						//listen the port
						listener.listen();
					};
				}.start();
				out.print("This port is open now!");
				
			}else if(op.equals("close")&&listener!=null){
				listener.close();
				listener = null;
				out.print("The port is close now!");
			}else{
				out.print("Options can not match the choise!");
			}
		} catch (Exception e) {
			out.print("error happened on the server!!!!");
		}
		
		out.flush();
		out.close();
		
		
	}
	
	

}
