package com.smg.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.qcloud.NlpProc;
import com.smg.service.data.process.BaseProcess;
import com.smg.service.data.process.CheckProcess;

@WebServlet("/nlp")
public class NlpService extends HttpServlet{
	private Logger logger = Logger.getLogger(NlpService.class);
	private BaseProcess process = new BaseProcess();

	/**
	 * 	自然语言处理
	 * */
	NlpProc nlpProc = new NlpProc();

	/**
	 * 
	 */
	private static final long serialVersionUID = -1935550126665147622L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			 {

		resp.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			doGet(req, resp);
		} catch (Exception e) {
			out.write("{\"code\":-1,\"message\":\"error happend!\"}");
			out.flush();
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//get outPrint
		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("utf-8");
		String prodata = "";
		//get parameters
		Map<String, String[]> data = req.getParameterMap();
		try {
			 prodata = nlpLexical(data);
		} catch (Exception e) {
			logger.error("NLPEXCEPTION:----"+e.getMessage());
			prodata = "{\"code\":-1,\"message\":\"error happend!\"}";
		}
		System.out.println(prodata);
		out.write(prodata);
		out.flush();
		out.close();
		
		
		 
	}

	
	
	private String nlpLexical(Map<String, String[]> data){
		String resul = null;
		Set<String> keySet = data.keySet();  
		if(keySet.contains("nlpText")){
			String [] values = (String [] ) data.get("nlpText");
			String value = values[0];
//			value = new CheckProcess().CheckText(value);
			resul = nlpProc.NLPLexicalAnalysis(new CheckProcess().CheckText(value));
			//处理返回值
			JSONObject object = process.parseObject(resul);
			
			if(object==null){
				return "{\"code\":-1,\"message\":\"no object is send!!\"}";
			}
			return object.toString();
		}
		
		return resul;
	}
	
	@SuppressWarnings("unused")
	private void putAll(Map<String, String[]> data){
		Set<String> keySet = data.keySet();  
		for (String key : keySet) {
		    String[] values = (String[]) data.get(key);  
		    for (String value : values) {  
		        logger.info(key + "=" + value);
		    }  
		}
	}
}
