//package com.zhuxin.calculate;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLDecoder;
//import java.util.Map;
////
////import zhuxin.util.Map2String;
//
///**
// * @author F-zx
// * 
// * 		������������servlet�������ݵ����磬�������post���ӣ��������еĲ�������<b>����post�İ�ͷ����</b>
// * */
//
//public class CalculateTest {
//	//�����ǲ���url������Ҫ��		http://120.27.49.173:8080/smg_service_api/
//	private String url = "http://zh.numberempire.com/equationsolver.php";			//url http://f-zx:8080/smg_service_api/
//	private String object;
//	
//	public CalculateTest(Map<String, String>  params){
//		object = Map2String.getInstance().Tran2String(params);
//	}
//	
//	/**
//	 * 
//	 * 		�������ݣ�����servlet
//	 * 
//	 * */
//	public void trans(){
//		PrintWriter out = null;
//		
//		try {
//			URL httpUrl = new URL(url);
//			URLConnection connection = httpUrl.openConnection();
//			//����Ҫ������ݵ�servlet
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//			 //����ͨ�õ��������� ģ����������� 
//			connection.setRequestProperty("accept", "*/*");  
//			connection.setRequestProperty("connection", "Keep-Alive");  
//			connection.setRequestProperty("Content-Lenth",
//		            String.valueOf(object.length()));
//			
//			out = new PrintWriter(connection.getOutputStream());
//			out.write(object);
//			out.flush();
//			connection.getInputStream();
//			
//			/*********����Ϊ���ֵ*******/
//			System.out.println(getOut(connection));;
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			if(out != null)
//				out.close();
//		}
//		
//	}
//	
//	public String getOut(URLConnection connection) throws IOException{
//		InputStream inputStream = connection.getInputStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
//		
//		StringBuffer buffer = new StringBuffer();
//		
//		String lineString;
//		while((lineString=reader.readLine())!=null)
//		{
////			System.out.println(lineString);
////			buffer.append(CharSetUtil.decodeUnicode(lineString));
//			buffer.append(lineString);
//		}
//		
//		reader.close();
//		inputStream.close();
//		return URLDecoder.decode(buffer.toString(),"utf-8");
//	}
//}
