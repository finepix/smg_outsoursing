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

import com.smg.service.data.sindex.process.IndexMessage;
import com.smg.service.data.sindex.process.IndexProcess;
import com.smg.service.weatherutils.Weather;
@WebServlet("/ssd")
public class SatificationIndexService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4462918518892490969L;
	
	//logger log4j.jar
	private Logger logger = Logger.getLogger(SatificationIndexService.class);
	
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		try {
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
				JSONObject object = new JSONObject();
				//get parameters
				Map<String, String[]> data = req.getParameterMap();
				try {
					 JSONObject pro = calculate(findCityName(data));
					 object.accumulate("code", 0);
					 object.accumulate("pro", pro);
					 prodata = object.toString();
				} catch (Exception e) {
					logger.error("NLPEXCEPTION:----"+e.getMessage());
					prodata = "{\"code\":-1,\"message\":\"error happend!\"}";
				}
				
				System.out.println(prodata);
				out.write(prodata);
				out.flush();
				out.close();
	}

	
	private String findCityName(Map<String, String[]> data){
		Set<String> keySet = data.keySet();  
		if(keySet.contains("city")){
			String [] values = (String [] ) data.get("city");
			String value = values[0];
			return value;
		}
		else{
			return "{\"code\":-1,\"message\":\"no city is send!!\"}";
		}
	}
	
	/**
	 * 计算出一个ssd值并且返回<b>System.out.println(new Weather().getWeather("杭州").toString());</b>
	 * */
	private JSONObject calculate(String cityName) {
		JSONObject object = new JSONObject();
		
		try {
			double ssd = new IndexProcess(new Weather().getWeather(cityName)).calculate();
			String info = new IndexMessage().getMessage(ssd);
			
			object.accumulate("ssd", ssd);
			object.accumulate("info", info);
		} catch (Exception e) {
			logger.error("CALCULATE");
		}
		
		return object;
	}


	
}
