package com.smg.utils;

/**
 * 	@author F-zx
 * 	是从 SHA1 哈希函数构造的一种键控哈希算法，被用作 HMAC（基于哈希的消息验证代码）。 此 HMAC 进程将密钥与消息数据混合，使用哈希函数对混合结果
 * 	进行哈希计算，将所得哈希值与该密钥混合，然后再次应用哈希函数。 输出的哈希值长度为 160 位。
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
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名 
     * @param encryptText 被签名的字符串 
     * @param encryptKey  密钥 
     * @return 
     * @throws Exception 
     */  
    public static byte[] HmacSHA1Encrypt(String encryptText,String keyString) throws Exception   
    {         
    	byte[] data=keyString.getBytes(ENCODING);
    	//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME); 
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME); 
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);  
        
        byte[] text = encryptText.getBytes(ENCODING);  
        //完成 Mac 操作 
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




