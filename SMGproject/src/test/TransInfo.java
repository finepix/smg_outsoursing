package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @author F-zx
 * 
 * 		该类用于连接servlet传输数据到网络，这里采用post连接，所以所有的参数都是<b>存在post的包头里面</b>
 * */

public class TransInfo {
	//这里是测试url，后面要改
	private String url = "http://f-zx:8080/TestDataIn/";			//url http://120.27.49.173:8080/
	private String object;
	/**
	 * 	构造方法：
	 * @param servlet servlet的名字
	 * @param param 参数这里接收list，然后将其解析为json数据
	 * 
	 * 
	 * */
	public TransInfo(String servlet,Map<String, String>  params){
		object = Map2String.getInstance().Tran2String(params);
		url += servlet;
	}
	
	/**
	 * 
	 * 		传输数据，连接servlet
	 * 
	 * */
	public void trans(){
		PrintWriter out = null;
		
		try {
			URL httpUrl = new URL(url);
			URLConnection connection = httpUrl.openConnection();
			//设置要输出内容到servlet
			connection.setDoOutput(true);
			connection.setDoInput(true);
			 //设置通用的请求属性 模仿浏览器访问 
			connection.setRequestProperty("accept", "*/*");  
			connection.setRequestProperty("connection", "Keep-Alive");  
			connection.setRequestProperty("Content-Lenth",
		            String.valueOf(object.length()));
			
			out = new PrintWriter(connection.getOutputStream());
			out.write(object);
			out.flush();
			connection.getInputStream();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null)
				out.close();
		}
		
	}
}
