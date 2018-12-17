package com.smg.nlp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

import com.smg.utils.CharSetUtil;

/**
 * @author F-zx
 * 
 * https://wenzhi.api.qcloud.com/v2/index.php?
    Action=LexicalAnalysis
    &Nonce=345122
    &Region=sz
    &SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA
    &Timestamp=1408704141
    &Signature=HgIYOPcx5lN6gz8JsCFBNAWp2oQ
    &text=Œ“∞Æœ¥‘Ë
    &code=2097152
 * 
 * */
public class NPL {
	private static String urlpath = "https://wenzhi.api.qcloud.com/v2/index.php?";
	private static String Action = "LexicalAnalysis";
	private  int Nonce = new Random().nextInt(java.lang.Integer.MAX_VALUE);
	private static String Region = "gz";
	private static String SecretId = "AKIDpzlrUyF2DNV369zx2PNn5syzRvdah26F";
	private  Long unix = System.currentTimeMillis()/1000L;
	private static  String codeString = "2097152";
	
	private static String textString;
	
	public  String NlpText(String text) throws MalformedURLException, Exception{
		textString = text;
		URL url = new URL(connectString());
		URLConnection connection = url.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		
		
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while((line=reader.readLine())!=null)
			buffer.append(line);
		System.out.println(CharSetUtil.decodeUnicode(buffer.toString()));
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private  String connectString() throws Exception{
		String sign = new Signature().getSign(null,unix,Nonce,codeString,textString);
		sign = sign.substring(0,sign.length()-1);
//		System.out.println(sign);
		return urlpath+"Action="+Action+"&Nonce="+Nonce+"&Region="+Region+"&SecretId="+SecretId+"&Timestamp="+unix
				+"&Signature="+URLEncoder.encode(sign)+"&text="+textString+"&code="+codeString;
	}
	
	public static void main(String[] args) throws MalformedURLException, Exception {
		new NPL().NlpText("Œ“∞Æœ¥‘Ë”¥");
	}
}
