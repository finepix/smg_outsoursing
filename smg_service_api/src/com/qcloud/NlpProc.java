package com.qcloud;

import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.qcloud.Module.Wenzhi;
import com.qcloud.Utilities.Json.JSONObject;
import com.smg.utils.CharSetUtil;

public class NlpProc {
	private Logger logger = Logger.getLogger(NlpProc.class);
	private final String SECRET_ID = "AKIDpzlrUyF2DNV369zx2PNn5syzRvdah26F";
	private final String SECRET_KEY = "AN8RdiA8v6SANWp4JmnJdStyQO40fOz3";
	private final String METHOD = "GET";
	private TreeMap<String, Object> config = new TreeMap<String, Object>();
	
	
	public NlpProc(){
		config.put("SecretId", SECRET_ID);
		config.put("SecretKey", SECRET_KEY);
		config.put("RequestMethod", METHOD);
		config.put("DefaultRegion", "gz");
	}
	
	public String NLPLexicalAnalysis(String text){
		
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(),
				config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("code", "2097152");
		params.put("text", text);
		
		String result = null;
		try {
			result = module.call("LexicalAnalysis", params);
			JSONObject json_result = new JSONObject(result);
//			System.out.println(json_result);
			logger.debug("lexicalAnalysis+"+json_result);
		} catch (Exception e) {
//			System.out.println("error..." + e.getMessage());
			logger.error("error..." + e.getMessage());
		}
		return result;
	}
	
	public String NLPLexicalCheck(String text){
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(), config);
		
		
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("text", text);
		
		String result = null;
		try {
			result = module.call("LexicalCheck", params);
			JSONObject json_result = new JSONObject(result);
//			System.out.println(json_result);
			logger.debug("lexicalAnalysis+"+json_result);
		} catch (Exception e) {
//			System.out.println("error..." + e.getMessage());
			logger.error("error..." + e.getMessage());
		}
		return result;
	}
	
	@Test
	public void testLexicalCheck(){
		String result = new NlpProc().NLPLexicalCheck("Ë­½Ð³Ô·¹");
		System.out.println(CharSetUtil.decodeUnicode(result));
	}
	

}
