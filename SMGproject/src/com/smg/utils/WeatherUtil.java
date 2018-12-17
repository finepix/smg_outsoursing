package com.smg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * 		ps:����ĳ�������cityName������ܻ�ȡ�������ðٶ����ַ����
 * 	����cityName����getInfo(string cityName)
 * ����getInfoWithLatLng(String lat,String lng) 
 * 
 * */
public class WeatherUtil {

	//��Կ�����ڵ��ðٶ�api����
	private String keys = "mo8N6Dys4Ry3ocHm8xsreV4Z89DDEo7m";
	//��ַ
	String urlString = "";
	
	/**
	 * 	�������json����
	 * @param cityName ��������
	 * @throws IOException 
	 * */
	private String getWeatherInfo(String cityName) throws IOException{
		StringBuffer buffer = new StringBuffer();
		String lineString;
		
		//ͨ��URLEncoder.encode()�����������Ľ���ת
		urlString = "http://api.map.baidu.com/telematics/v3/weather?location="+URLEncoder.encode(cityName, "utf-8")+
					"&output=json&ak="+keys;
		URL url = new URL(urlString);
		
		URLConnection connection = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		while((lineString=reader.readLine())!=null)
			buffer.append(lineString+" ");
		reader.close();
		return buffer.toString();
	}
	
	/**
	 * 	�����������json���ݽ����������ų�����Ҫ����Ϣ
	 * @param json getWeatherInfo���ص�json����
	 * */
	private WeatherInfo parseInfo(String json){
		WeatherInfo info = new WeatherInfo();
		
		JSONObject object = JSONObject.fromObject(json);
		//error==0λ������ȡ��Ϣ
		if(object.getInt("error")!=0)
			return null;
		
		JSONArray results = object.getJSONArray("results");
		info.setLocation(results.getJSONObject(0).getString("currentCity"));
		info.setPm2p5(results.getJSONObject(0).getString("pm25"));
		
		
		JSONArray index = results.getJSONObject(0).getJSONArray("index");
		
		 
        try{
        	JSONObject index0 = index.getJSONObject(0);//����  
            JSONObject index1 = index.getJSONObject(1);//ϴ��  
            JSONObject index2 = index.getJSONObject(2);//��ð  
            JSONObject index3 = index.getJSONObject(3);//�˶�  
            JSONObject index4 = index.getJSONObject(4);//������ǿ�� 
            
	        String dressAdvise = index0.getString("des");//���½���  
	        String washCarAdvise = index1.getString("des");//ϴ������  
	        String coldAdvise = index2.getString("des");//��ð����  
	        String sportsAdvise = index3.getString("des");//�˶�����  
	        String ultravioletRaysAdvise = index4.getString("des");//�����߽���
	        
	        info.setDressAdvise(dressAdvise);  
	        info.setWashCarAdvise(washCarAdvise);  
	        info.setColdAdvise(coldAdvise);  
	        info.setSportsAdvise(sportsAdvise);  
	        info.setUltravioletRaysAdvise(ultravioletRaysAdvise);  
          
	    }catch(JSONException jsonExp){  
	        //���û��ȡ��ֵ�����Լ��趨
	    	info.setDressAdvise("Ҫ�¶ȣ�ҲҪ��ȡ����Ȼ����£����������£�");  
	        info.setWashCarAdvise("��ϴ���ǲ�ϴ���ҳ������������������");  
	        info.setColdAdvise("һ��һ��ƻ������ð�������ң����ˮ�����߲ˡ�");  
	        info.setSportsAdvise("���������˶�����Ҫ��լ�ڼ���Ŷ��");  
	        info.setUltravioletRaysAdvise("���������Զ���ᣬƤ��Ҳһ�����ԣ�");  
	    }
		
        //���ﱣ�����������Ϣ��ֻȡǰһ�����Ϣ
        JSONArray weather_data = results.getJSONObject(0).getJSONArray("weather_data");
        
        //�������Ϣ
        JSONObject today = weather_data.getJSONObject(0);
        
        info.setDate(today.getString("date"));
        info.setWeather(today.getString("weather"));
        info.setWindString(today.getString("wind"));
        info.setTemper(today.getString("temperature"));
        
        return info;
		
	}
	
	/**
	 * 	���ַ���� 
	 * @param lat γ������ <a href="#">eg:38.76623</a>
	 * @param lng ��������<a href="#">eg:116.43213</a>
	 * @throws IOException 
	 * 
	 * */
	private String getCItyName(String lat,String lng) throws IOException{
		String cityName = null;
		String urlString = "http://api.map.baidu.com/geocoder/v2/?"
				+ "ak="+keys
				+ "&location="+lat+","+lng
				+ "&output=json";
		
//		System.out.println(urlString);
		
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		StringBuffer buffer = new StringBuffer();
		
		String line;
		while((line=reader.readLine())!=null)
			buffer.append(line);
		
		cityName = parseCityName(buffer.toString());
		return cityName;
	}
	
	/**
	 * 	�������ַ���봫�ص�json���ݣ����cityName
	 * 
	 * 
	 * */
	private String parseCityName(String json){
		String cityName = null;
//		System.out.println(json);
		
		JSONObject object = JSONObject.fromObject(json);
		JSONObject addressComponent = object.getJSONObject("result").getJSONObject("addressComponent");
		
		cityName = addressComponent.getString("city");
		
//		System.out.println(cityName);
		return cityName;
	}
	
	
	/**
	 * 		�������÷���
	 * @param cityName ��������
	 * @throws IOException 
	 * */
	public WeatherInfo getInfo(String cityName) throws IOException{
		return cityName==null? null:parseInfo(getWeatherInfo(cityName));
	}
	
	/**
	 * 
	 * 		�������÷���2��û��cityNameͨ�����ַ�������������
	 * @param lat γ������ <a href="#">eg:38.76623</a>
	 * @param lng ��������<a href="#">eg:116.43213</a>
	 * @throws IOException 
	 * 
	 * */
	public WeatherInfo getInfoWithLatLng(String lat,String lng) throws IOException{
		return lat!=null&&lng!=null? parseInfo(getWeatherInfo(getCItyName(lat, lng))):null;
	}
	
	/**	
	 * 		JUnit ���Է���
	 * */
	@Test
	public void testGetWeather() throws IOException{
//		System.out.println(getWeatherInfo("����"));
//		System.out.println(getInfo("����").toString());
//		getCItyName("38.76623","116.43213");
		System.out.println(getInfoWithLatLng("38.76623","116.43213").toString());
	}
	

}

/**
 * 	���ڴ��������Ϣ��
 * */
class WeatherInfo{
	private String pm2p5;//pm2.5
	@Override
	public String toString() {
		return "WeatherInfo [pm2p5=" + pm2p5 + ", date=" + date + ", location="
				+ location + ", dressAdvise=" + dressAdvise
				+ ", washCarAdvise=" + washCarAdvise + ", coldAdvise="
				+ coldAdvise + ", sportsAdvise=" + sportsAdvise
				+ ", ultravioletRaysAdvise=" + ultravioletRaysAdvise
				+ ", weather=" + weather + ", windString=" + windString
				+ ", temper=" + temper + "]";
	}
	private String date;
	private String location;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private String dressAdvise;//���½���  
    private String washCarAdvise;//ϴ������  
    private String coldAdvise;//��ð����  
    private String sportsAdvise;//�˶�����  
    private String ultravioletRaysAdvise;//�����߽���
    
    private String weather;
    private String windString;
    private String temper;//�¶�
    
    public WeatherInfo(){
    	
    }
	public String getPm2p5() {
		return pm2p5;
	}
	public void setPm2p5(String pm2p5) {
		this.pm2p5 = pm2p5;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDressAdvise() {
		return dressAdvise;
	}
	public void setDressAdvise(String dressAdvise) {
		this.dressAdvise = dressAdvise;
	}
	public String getWashCarAdvise() {
		return washCarAdvise;
	}
	public void setWashCarAdvise(String washCarAdvise) {
		this.washCarAdvise = washCarAdvise;
	}
	public String getColdAdvise() {
		return coldAdvise;
	}
	public void setColdAdvise(String coldAdvise) {
		this.coldAdvise = coldAdvise;
	}
	public String getSportsAdvise() {
		return sportsAdvise;
	}
	public void setSportsAdvise(String sportsAdvise) {
		this.sportsAdvise = sportsAdvise;
	}
	public String getUltravioletRaysAdvise() {
		return ultravioletRaysAdvise;
	}
	public void setUltravioletRaysAdvise(String ultravioletRaysAdvise) {
		this.ultravioletRaysAdvise = ultravioletRaysAdvise;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWindString() {
		return windString;
	}
	public void setWindString(String windString) {
		this.windString = windString;
	}
	public String getTemper() {
		return temper;
	}
	public void setTemper(String temper) {
		this.temper = temper;
	}
    
}
