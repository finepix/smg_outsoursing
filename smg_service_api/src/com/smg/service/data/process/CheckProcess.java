package com.smg.service.data.process;

import net.sf.json.JSONObject;

import com.qcloud.NlpProc;


/**
 * 	纠错处理，传入待检测的文本，返回正确的文字		eg：
{"code":0,"message":"","text":"睡觉吃饭","text_annotate":"<em>睡觉<\/em>吃饭","conf":2}
 * */
public class CheckProcess {

	private String text = "";
	
	
	/**
	 * 
	 * 如出错，返回""
	 * @param text 文本
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
