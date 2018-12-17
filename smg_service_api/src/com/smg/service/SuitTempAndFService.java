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

import org.apache.log4j.Logger;

import com.smg.service.data.sindex.process.SuitTempAndF;
import com.smg.service.weatherutils.Weather;
import com.smg.service.weatherutils.model.WeatherInfo;

import net.sf.json.JSONObject;

@WebServlet("/best")
public class SuitTempAndFService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1338619064276461503L;
	
	private static Logger logger = Logger.getLogger(SuitTempAndFService.class);
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
		JSONObject object = new JSONObject();
		//get parameters
		Map<String, String[]> data = req.getParameterMap();
		try {
			String cityName = findCityName(data);
			/*获得weatherinfo*/
			WeatherInfo info = new Weather().getWeather(cityName);
			/*获得最优解*/
			SuitTempAndF tempAndF = new SuitTempAndF(info);
			Map<String, Double> sovelMap = tempAndF.getSolve();
			JSONObject result = new JSONObject();
			result.accumulate("temp", sovelMap.get("x"));
			result.accumulate("huminity", sovelMap.get("y"));
			/*添加到object*/
			 object.accumulate("code", 0);
			 object.accumulate("result", result);
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

	
	/**
	 * 	从参数map中找到cityName
	 * @param data 传入的参数Map
	 * @return 城市名字
	 * 
	 * */
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

}
