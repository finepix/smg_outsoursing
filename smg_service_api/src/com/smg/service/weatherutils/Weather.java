package com.smg.service.weatherutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.smg.service.weatherutils.model.WeatherInfo;

/**api
 * eg£º
 *		http://api.jisuapi.com/weather/query?
 *			appkey=df436992fa2ed53f&
 *				city=%E5%AE%89%E9%A1%BA
 * */
public class Weather {
	private static Logger logger = Logger.getLogger(Weather.class);
	
	private static final String  APPKEY = "df436992fa2ed53f";
	private static String urlPath = "http://api.jisuapi.com/weather/query?";
	
	private String city;
	
	private String connectPath(){
		try {
			return urlPath+"appkey="+APPKEY+"&city="+URLEncoder.encode(city,"utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("URlEncoding error");
		}
		return "";
	}
	
	/**
	 * ·µ»ØWeatherinfoÀàÊµÀý
	 * */
	public WeatherInfo getWeather(String city){
		//39.93:116.40 
		this.city = city;
		WeatherInfo info = new WeatherInfo();
		
		try {
			String jsonString = ping();
			
			JSONObject object = JSONObject.fromObject(jsonString);
			JSONObject result = object.getJSONObject("result");
			
			info.setTemperature(result.getDouble("temp"));
			info.setHumidity(result.getDouble("humidity"));
			info.setWind_speed(result.getDouble("windspeed"));
			
		} catch (IOException e) {
			logger.error("Weather Error£¡£¡");
			return null;
		}
		
		return info;
	}
	
	private String ping() throws IOException{
		
		URL url = new URL(connectPath());
//		URL url = new URL("http://api.jisuapi.com/weather/query?appkey=df436992fa2ed53f&city=%E6%9D%AD%E5%B7%9E");
		System.out.println(connectPath());
		URLConnection connection = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		
		StringBuffer buffer = new StringBuffer();
		String lineString = "";
		
		while((lineString=reader.readLine())!=null){
			buffer.append(lineString);
		}
		
//		System.out.println(buffer.toString());
		
		
		
		return buffer.toString();
		
	}
	
	
}
