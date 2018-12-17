package com.smg.service.util.height;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



/**
 * 		根据lat和lng获取海拔
 * */
public class HeightUtils{
	private String path = "http://23.83.250.253:8080/zx_service_height/height?";
	
	private String location;
	
	public HeightUtils(String lat,String lng){
		this.location = lat+','+lng;
	}
	
	public double getHeight() throws IOException{
		return ParseHeight.getInsetance(getHtml()).height();
	}
	
	private String getHtml() throws IOException{
		URL url = new URL(urlPath());
		URLConnection connection = url.openConnection();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							connection.getInputStream()));
		String line;
		while((line=reader.readLine())!=null){
			buffer.append(line+"\n");
		}
		return buffer.toString();
	}
	
	private String urlPath(){
		return path+"location="+location;
	}
	
	
}