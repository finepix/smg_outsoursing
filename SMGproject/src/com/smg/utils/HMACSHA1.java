package com.smg.utils;

/**
 * 	@author F-zx
 * 	�Ǵ� SHA1 ��ϣ���������һ�ּ��ع�ϣ�㷨�������� HMAC�����ڹ�ϣ����Ϣ��֤���룩�� �� HMAC ���̽���Կ����Ϣ���ݻ�ϣ�ʹ�ù�ϣ�����Ի�Ͻ��
 * 	���й�ϣ���㣬�����ù�ϣֵ�����Կ��ϣ�Ȼ���ٴ�Ӧ�ù�ϣ������ ����Ĺ�ϣֵ����Ϊ 160 λ��
 * 
 * */

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HMACSHA1 {

	private static final String MAC_NAME = "HmacSHA1";  
    private static final String ENCODING = "UTF-8";  
    
	
    
    /** 
     * ʹ�� HMAC-SHA1 ǩ�������Զ�encryptText����ǩ�� 
     * @param encryptText ��ǩ�����ַ��� 
     * @param encryptKey  ��Կ 
     * @return 
     * @throws Exception 
     */  
    public static byte[] HmacSHA1Encrypt(String encryptText,String keyString) throws Exception   
    {         
    	byte[] data=keyString.getBytes(ENCODING);
    	//���ݸ������ֽ����鹹��һ����Կ,�ڶ�����ָ��һ����Կ�㷨������
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME); 
        //����һ��ָ�� Mac �㷨 �� Mac ����
        Mac mac = Mac.getInstance(MAC_NAME); 
        //�ø�����Կ��ʼ�� Mac ����
        mac.init(secretKey);  
        
        byte[] text = encryptText.getBytes(ENCODING);  
        //��� Mac ���� 
        return mac.doFinal(text);  
    }  
    //GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=345122&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1408704141
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
    	String string = "GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=345122&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1408704141";
		byte [] b = HMACSHA1.HmacSHA1Encrypt(string,"Gu5t9xGARNpq86cd98joQYCN3Cozk1qA");
		
		System.out.println(URLEncoder.encode(Base64.encodeBase64(b)));
//		System.out.println(Base64.decodeBase64("HgIYOPcx5lN6gz8JsCFBNAWp2oQ=").toString());
	}
}


//class Base64Enc {
//	
//	public static String getBase64(byte [] b){
//		return org.apache.catalina.util.Base64.encode(b);
//	}
//}




