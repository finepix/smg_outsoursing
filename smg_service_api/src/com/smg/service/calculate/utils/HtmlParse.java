package com.smg.service.calculate.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParse {
	
	private static Document doc;
	private static String resultText;
	
	
	/**
	 * 		返回list单数为x解，偶数为y
	 * @param html 将呀解析的html传入
	 * @return 返回list
	 * 
	 * */
	public static List<Double> parseHtml(String html){
		doc = Jsoup.parse(html);
		Element element = getElement();
		resultText = element.html();
		return getSolveList();
	}
	
	private static Element getElement(){
		Elements elements = doc.getElementsByAttributeValue("type", "math/tex");
		return elements.first();
	}
	
	private static List<Double> getSolveList(){
		List<Double> list = new ArrayList<Double>();
		String[] arr = resultText.split(",");
		for(int i = 0 ; i < arr.length ; i++ ){
			String str  = arr[i];
			if(!str.contains("="))
				continue;
			String s = str.split("=")[1];
			if(s.contains("i")||s.contains("-"))
				continue;
			double z  = Double.parseDouble(str.split("=")[1]);
			list.add(z);
		}
		return list;
	}

}
