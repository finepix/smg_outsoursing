package com.smg.service.data.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseProcess {

	
	/**
	 * 如果code不为0 则返回null
	 * @param parse 待解析的文本
	 * 
	 * */
	public JSONObject parseObject(String parse){
		JSONObject object = new JSONObject();
		JSONObject root = JSONObject.fromObject(parse);
		
		if((Integer) root.get("code")!=0)
			return null;
//		JSONArray tokens = (JSONArray) root.get("tokens");
		
		JSONArray tokens = root.getJSONArray("tokens");
		Boolean action = false;
		JSONArray array = new JSONArray();

		int count = 0;
		for(int i=0;i < tokens.size() ; i++){
			JSONObject tObject = tokens.getJSONObject(i);
			if(tObject.get("wtype").equals("动词")){
				String  acword = tObject.getString("word");
				/**
				 * 这里还需要进一步操作（利用近义词进行操作）
				 * 
				 * */
				if(acword.equals("打开"))
					action = true;
				if(acword.equals("关闭"))
					action = false;
			}
			if(tObject.get("wtype").equals("名词")){
				JSONObject machineObject = new JSONObject();
				machineObject.accumulate(String.valueOf(count++), tObject.getString("word") );
				array.add(machineObject);
			}
		}
		object.accumulate("code", 0);
		object.accumulate("状态", action);
		object.accumulate("电器", array);
//		System.out.println(object.toString());
		return object;
		
	}
	
	@Test(timeout=1200)
	public void testParse(){
		String parse = "{\"message\":\"\",\"tokens\":[{\"wlen\":\"2\",\"wtype_pos\":31,\"word\":\"帮\",\"wtype\":\"动词\",\"pos\":0},{\"wlen\":\"2\",\"wtype_pos\":27,\"word\":\"我\",\"wtype\":\"代词\",\"pos\":2},{\"wlen\":\"4\",\"wtype_pos\":31,\"word\":\"打开\",\"wtype\":\"动词\",\"pos\":4},{\"wlen\":\"4\",\"wtype_pos\":16,\"word\":\"空调\",\"wtype\":\"名词\",\"pos\":8}],\"combtokens\":[null],\"code\":0}";
		parseObject(parse);
	}
}
