package com.smg.service.util.height;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseHeight {
	
	private static ParseHeight parseHeight ;
	private String htmlString;
	
	private ParseHeight(String htmlString){
		this.htmlString = htmlString;
		
	}
	
	public static ParseHeight getInsetance(String htmlString){
		parseHeight = new ParseHeight(htmlString);
		return parseHeight;
	}
	
	public double height(){
		JSONObject object = JSONObject.fromObject(htmlString);
		
		JSONArray results = object.getJSONArray("results");
		JSONObject re = results.getJSONObject(0);
		
		
		return  re.getDouble("elevation");
	}

}
