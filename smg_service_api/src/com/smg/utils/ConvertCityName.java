package com.smg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class ConvertCityName {

	//秘钥，用于调用百度api加密
	private String keys = "mo8N6Dys4Ry3ocHm8xsreV4Z89DDEo7m";
	//网址
	String urlString = "";
	
	/**
	 * 	逆地址编码 
	 * @param lat 纬度坐标 <a href="#">eg:38.76623</a>
	 * @param lng 经度坐标<a href="#">eg:116.43213</a>
	 * @throws IOException 
	 * 
	 * */
	public String getCItyName(String lat,String lng) throws IOException{
		String cityName = null;
		String urlString = "http://api.map.baidu.com/geocoder/v2/?"
				+ "ak="+keys
				+ "&location="+lat+","+lng
				+ "&output=json";
		
//		System.out.println(urlString);
		
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		StringBuffer buffer = new StringBuffer();
		
		String line;
		while((line=reader.readLine())!=null)
			buffer.append(line);
		
		cityName = parseCityName(buffer.toString());
		return cityName;
	}
	
	
	/**
	 * 	处理逆地址编码传回的json数据，输出cityName
	 * 
	 * 
	 * */
	private String parseCityName(String json){
		String cityName = null;
//		System.out.println(json);
		
		JSONObject object = JSONObject.fromObject(json);
		JSONObject addressComponent = object.getJSONObject("result").getJSONObject("addressComponent");
		
		cityName = addressComponent.getString("city");
		
//		System.out.println(cityName);
		return cityName;
	}
}
