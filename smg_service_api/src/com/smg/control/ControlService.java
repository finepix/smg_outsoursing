package com.smg.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smg.control.file.WriteFile;

@WebServlet("/con")
public class ControlService extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1023586676518273252L;
	
	private WriteFile writeFile= null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op = req.getParameter("op");
		PrintWriter out = resp.getWriter();
		
		if(op.equals("open")){
			writeFile = new WriteFile(true);
			writeFile.write();
			out.print("the file is write with 1");
		}
		else if(op.equals("close")){
			writeFile = new WriteFile(false);
			writeFile.write();
			out.print("the file is write with 0");
		}
		
		out.flush();out.close();
	}

	
}
