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

import com.smg.service.data.feel.model.FeelTemperModel;
import com.smg.service.data.feel.model.FeelWeatherModel;
import com.smg.service.data.feel.temper.FeelTemper;
import com.smg.service.data.sindex.process.SuitTemperatureCalculate;
import com.smg.service.data.suit.model.SuitModel;

import net.sf.json.JSONObject;

@WebServlet("/suitT")
public class GoldSuitTempService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2450642505983422952L;
	
	//logger log4j.jar
	private Logger logger = Logger.getLogger(GoldSuitTempService.class);

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
			String location = findLocation(data);
			String [] lngLat = location.split(",");
			//get Temp
			double suitTemp = new SuitTemperatureCalculate(
										new SuitModel(Double.parseDouble(lngLat[0]),Double.parseDouble(lngLat[1]))
										).getSuitTemp();
			
			//get feelTemper
			FeelTemper temper = new FeelTemper(
												new FeelTemperModel(
													new FeelWeatherModel("30.3", "120.25"), suitTemp));
			double feel_temper = temper.getFeelTemper();
			
			JSONObject result = new JSONObject();
			result.accumulate("suitTemp", suitTemp);
			result.accumulate("feel_temper", feel_temper);
			
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
	
	
	private String findLocation(Map<String, String[]> data){
		Set<String> keySet = data.keySet();  
		if(keySet.contains("location")){
			String [] values = (String [] ) data.get("location");
			String value = values[0];
			return value;
		}
		else{
			return "{\"code\":-1,\"message\":\"no location is send!!\"}";
		}
	}


}
