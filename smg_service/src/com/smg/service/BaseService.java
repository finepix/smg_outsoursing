package com.smg.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.jni.OS;

@WebServlet("/base")
public class BaseService extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1607777014752731749L;

	
	private static Logger logger = Logger.getLogger(BaseService.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		File file = new File("log.log");
		
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream oStream = new FileOutputStream(file);
		System.out.println(file.getAbsolutePath());
		
		oStream.write("zhuxin is a good person".getBytes());
		
		oStream.close();
		req.setCharacterEncoding("utf-8");
		Map<String, String[]> data = req.getParameterMap();
		Set<String> keySet = data.keySet();  
		for (String key : keySet) {  
		    String[] values = (String[]) data.get(key);  
		    for (String value : values) {  
		        logger.info(key + "=" + value);
		    	
		    }  
		}  
		
	}

}
