package com.smg.utils;

import java.lang.reflect.Method;

public class Base64
{
	/***
	 * encode by Base64
	 */
	@SuppressWarnings("rawtypes")
	public static String encodeBase64(byte[]input) throws Exception{
		Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		@SuppressWarnings("unchecked")
		Method mainMethod= clazz.getMethod("encode", byte[].class);
		mainMethod.setAccessible(true);
		 Object retObj=mainMethod.invoke(null, new Object[]{input});
		 return (String)retObj;
	}
	/***
	 * decode by Base64
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] decodeBase64(String input) throws Exception{
		Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod= clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		 Object retObj=mainMethod.invoke(null, input);
		 return (byte[])retObj;
	}

}