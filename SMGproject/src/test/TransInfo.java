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
 * 		������������servlet�������ݵ����磬�������post���ӣ��������еĲ�������<b>����post�İ�ͷ����</b>
 * */

public class TransInfo {
	//�����ǲ���url������Ҫ��
	private String url = "http://f-zx:8080/TestDataIn/";			//url http://120.27.49.173:8080/
	private String object;
	/**
	 * 	���췽����
	 * @param servlet servlet������
	 * @param param �����������list��Ȼ�������Ϊjson����
	 * 
	 * 
	 * */
	public TransInfo(String servlet,Map<String, String>  params){
		object = Map2String.getInstance().Tran2String(params);
		url += servlet;
	}
	
	/**
	 * 
	 * 		�������ݣ�����servlet
	 * 
	 * */
	public void trans(){
		PrintWriter out = null;
		
		try {
			URL httpUrl = new URL(url);
			URLConnection connection = httpUrl.openConnection();
			//����Ҫ������ݵ�servlet
			connection.setDoOutput(true);
			connection.setDoInput(true);
			 //����ͨ�õ��������� ģ����������� 
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
