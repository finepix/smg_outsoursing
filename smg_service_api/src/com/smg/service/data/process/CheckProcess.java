package com.smg.service.data.process;

import net.sf.json.JSONObject;

import com.qcloud.NlpProc;


/**
 * 	����������������ı���������ȷ������		eg��
{"code":0,"message":"","text":"˯���Է�","text_annotate":"<em>˯��<\/em>�Է�","conf":2}
 * */
public class CheckProcess {

	private String text = "";
	
	
	/**
	 * 
	 * ���������""
	 * @param text �ı�
	 * 
	 * */
	public String CheckText(String text){
		//get parse text
		String json = new NlpProc().NLPLexicalCheck(text);
		//get jsonobject
		JSONObject object = JSONObject.fromObject(json);
		
		if(object.getInt("code")!=0)
			return object.getString("message");
		
		return object.getString("text");
	}
}
