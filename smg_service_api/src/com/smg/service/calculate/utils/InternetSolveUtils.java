package com.smg.service.calculate.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


public class InternetSolveUtils {
	
	private String urlpath;
	private String object;
	
	public InternetSolveUtils(String urlpath,Map<String, String>  params){
		this.urlpath = urlpath;
		this.object = Map2String.getInstance().Tran2String(params);
	}
	

	
	/**
	 * 
	 * 		传输数据
	 * @return string 返回的html
	 * 
	 * */
	public String trans(){
		PrintWriter out = null;
		String resultString = "";
		try {
			URL httpUrl = new URL(urlpath);
			URLConnection connection = httpUrl.openConnection();
			//设置要输出内容到servlet
			connection.setDoOutput(true);
			connection.setDoInput(true);
			 //设置通用的请求属性 模仿浏览器访问 
			connection.setRequestProperty("accept", "*/*");  
			connection.setRequestProperty("connection", "Keep-Alive");  
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1 )");
			connection.setRequestProperty("Content-Lenth",
		            String.valueOf(object.length()));
			
			out = new PrintWriter(connection.getOutputStream());
			out.write(object);
			out.flush();
			connection.getInputStream();
			
			/*********这里为输出值*******/
			resultString = getOut(connection);
			System.out.println(resultString+"\n\n\n\n\n\n");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null)
				out.close();
		}
		return resultString;
		
	}
	
	public String getOut(URLConnection connection) throws IOException{
		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		
		StringBuffer buffer = new StringBuffer();
		
		String lineString;
		while((lineString=reader.readLine())!=null)
		{
//			System.out.println(lineString);
//			buffer.append(CharSetUtil.decodeUnicode(lineString));
			buffer.append(lineString+"\n");
		}
		
		reader.close();
		inputStream.close();
		return buffer.toString();
	}

}
