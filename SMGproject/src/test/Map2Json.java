package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 	@author F-zx
 * 			工具类，看名字
 * 
 * */
import java.util.Map;


/**
 * 	@author F-zx
 * 		用于将list转化为json,这里调用<a href="#">json-lib2.4 jar包</a>
 * 
 * */

public class Map2Json {

	private static Map2Json list2Json = new Map2Json();
	
	/**
	 * 			单例模式(饿汉式)，这里声明private构造函数
	 * */
	private Map2Json(){
		
	}
	
	/**
	 * 			公用方法调用instance
	 * */
	public static Map2Json getInstance(){
		return list2Json;
	}
	
	/**
	 * 将Map数据转化为json数据 
	 * @param <a href="#">参数Map</a>
	 * 
	 * */
	public  JSONObject trans2json(Map<String, String> param){
		
		JSONArray array = JSONArray.fromObject(param);
		
//		System.out.println(array.toString());
		
		JSONObject object = new JSONObject();
		object.accumulate("param", array);
		
//		System.out.println(object.toString());
		
		return object;
	}
	
}
