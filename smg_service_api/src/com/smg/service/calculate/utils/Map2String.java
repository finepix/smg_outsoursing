package com.smg.service.calculate.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 	@author F-zx
 * 
 * 	将map数据转化为string
 * */
public class Map2String {

	private static Map2String map2String = new Map2String();
	private static String encode = "utf-8";
	
	private Map2String(){
		
	}
	
	/**
	 * 		获得map2string对象
	 * */
	public static Map2String getInstance(){
		return map2String;
	}
	
	
	/**
	 * 	 转换数据
	 * */
	public String Tran2String(Map<String, String> params){
		 StringBuffer stringBuffer = new StringBuffer();

		    if (params != null && !params.isEmpty()) {
		      for (Map.Entry<String, String> entry : params.entrySet()) {
		        try {
		          stringBuffer
		              .append(entry.getKey())
		              .append("=")
		              .append(URLEncoder.encode(entry.getValue(), encode))
		              .append("&");

		        } catch (UnsupportedEncodingException e) {
		          e.printStackTrace();
		        }
		      }
		    }
		return stringBuffer.toString();
	}
	
}
