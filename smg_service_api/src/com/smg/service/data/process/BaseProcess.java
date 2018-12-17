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
	 * ���code��Ϊ0 �򷵻�null
	 * @param parse ���������ı�
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
			if(tObject.get("wtype").equals("����")){
				String  acword = tObject.getString("word");
				/**
				 * ���ﻹ��Ҫ��һ�����������ý���ʽ��в�����
				 * 
				 * */
				if(acword.equals("��"))
					action = true;
				if(acword.equals("�ر�"))
					action = false;
			}
			if(tObject.get("wtype").equals("����")){
				JSONObject machineObject = new JSONObject();
				machineObject.accumulate(String.valueOf(count++), tObject.getString("word") );
				array.add(machineObject);
			}
		}
		object.accumulate("code", 0);
		object.accumulate("״̬", action);
		object.accumulate("����", array);
//		System.out.println(object.toString());
		return object;
		
	}
	
	@Test(timeout=1200)
	public void testParse(){
		String parse = "{\"message\":\"\",\"tokens\":[{\"wlen\":\"2\",\"wtype_pos\":31,\"word\":\"��\",\"wtype\":\"����\",\"pos\":0},{\"wlen\":\"2\",\"wtype_pos\":27,\"word\":\"��\",\"wtype\":\"����\",\"pos\":2},{\"wlen\":\"4\",\"wtype_pos\":31,\"word\":\"��\",\"wtype\":\"����\",\"pos\":4},{\"wlen\":\"4\",\"wtype_pos\":16,\"word\":\"�յ�\",\"wtype\":\"����\",\"pos\":8}],\"combtokens\":[null],\"code\":0}";
		parseObject(parse);
	}
}
