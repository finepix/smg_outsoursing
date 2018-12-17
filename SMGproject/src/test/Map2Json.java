package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 	@author F-zx
 * 			�����࣬������
 * 
 * */
import java.util.Map;


/**
 * 	@author F-zx
 * 		���ڽ�listת��Ϊjson,�������<a href="#">json-lib2.4 jar��</a>
 * 
 * */

public class Map2Json {

	private static Map2Json list2Json = new Map2Json();
	
	/**
	 * 			����ģʽ(����ʽ)����������private���캯��
	 * */
	private Map2Json(){
		
	}
	
	/**
	 * 			���÷�������instance
	 * */
	public static Map2Json getInstance(){
		return list2Json;
	}
	
	/**
	 * ��Map����ת��Ϊjson���� 
	 * @param <a href="#">����Map</a>
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
