package com.smg.nlp;

import javassist.expr.NewArray;

import com.smg.utils.Base64;
import com.smg.utils.HMACSHA1;



/**
 * @author F-zx
 * 		�����ַ���ȡ��ǰ��ǩ��
 * 
 * https://wenzhi.api.qcloud.com/v2/index.php?Action=LexicalAnalysis&Nonce=345122&Region=sz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1408704141&Signature=HgIYOPcx5lN6gz8JsCFBNAWp2oQ&text=�Ұ�ϴ��&code=2097152
 * 
 * GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=345122&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1408704141
 * 
 * */
public class Signature {
	//��ǰUnixʱ���
//	private  Long unix = System.currentTimeMillis()/1000L;
	//Ĭ��action
	private static String Action = "LexicalAnalysis";
	//Ĭ������ʵ��Ϊgz
	private static String Region = "gz";
	//id
	private static String SecretId = "AKIDpzlrUyF2DNV369zx2PNn5syzRvdah26F";
	//�����
//	private  int Nonce = (int) (Math.random()*25535);
	//METHOD
	private static final String METHOD = "GET";
	//url
	private static String urlpath = "wenzhi.api.qcloud.com/v2/index.php";	//cvm.api.qcloud.com/v2/index.php		https://
	//key
	private static String keyString = "AN8RdiA8v6SANWp4JmnJdStyQO40fOz3";//AN8RdiA8v6SANWp4JmnJdStyQO40fOz3  Gu5t9xGARNpq86cd98joQYCN3Cozk1qA
	
	
	private  String connectPath(Long unix,int Nonce,String codeString,String textString){
		return METHOD+urlpath+"?Action="+Action+"&Nonce="+Nonce+"&Region="+Region+"&SecretId="+SecretId+"&Timestamp="+unix+"&text="+textString+"&code="+codeString;
	}
	
	public String getSign(String action,Long unix,int Nonce,String codeString,String textString) throws Exception{
//		if(action!=null||action!="")
//			Action = action;
		System.out.println(connectPath(unix,Nonce,codeString,textString));
		return Base64.encodeBase64(HMACSHA1.HmacSHA1Encrypt(connectPath(unix,Nonce,codeString,textString),keyString));
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(new Signature().getSign(null));;
	}
	
}
