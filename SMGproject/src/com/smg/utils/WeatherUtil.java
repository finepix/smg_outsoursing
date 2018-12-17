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
 * 		ps:这里的城市名字cityName如果不能获取，可以用百度逆地址编码
 * 	若有cityName则用getInfo(string cityName)
 * 无则getInfoWithLatLng(String lat,String lng) 
 * 
 * */
public class WeatherUtil {

	//秘钥，用于调用百度api加密
	private String keys = "mo8N6Dys4Ry3ocHm8xsreV4Z89DDEo7m";
	//网址
	String urlString = "";
	
	/**
	 * 	获得天气json数据
	 * @param cityName 城市名字
	 * @throws IOException 
	 * */
	private String getWeatherInfo(String cityName) throws IOException{
		StringBuffer buffer = new StringBuffer();
		String lineString;
		
		//通过URLEncoder.encode()函数对于中文进行转
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
	 * 	将传入的天气json数据解析出来，排除不需要的信息
	 * @param json getWeatherInfo返回的json数据
	 * */
	private WeatherInfo parseInfo(String json){
		WeatherInfo info = new WeatherInfo();
		
		JSONObject object = JSONObject.fromObject(json);
		//error==0位正常获取信息
		if(object.getInt("error")!=0)
			return null;
		
		JSONArray results = object.getJSONArray("results");
		info.setLocation(results.getJSONObject(0).getString("currentCity"));
		info.setPm2p5(results.getJSONObject(0).getString("pm25"));
		
		
		JSONArray index = results.getJSONObject(0).getJSONArray("index");
		
		 
        try{
        	JSONObject index0 = index.getJSONObject(0);//穿衣  
            JSONObject index1 = index.getJSONObject(1);//洗车  
            JSONObject index2 = index.getJSONObject(2);//感冒  
            JSONObject index3 = index.getJSONObject(3);//运动  
            JSONObject index4 = index.getJSONObject(4);//紫外线强度 
            
	        String dressAdvise = index0.getString("des");//穿衣建议  
	        String washCarAdvise = index1.getString("des");//洗车建议  
	        String coldAdvise = index2.getString("des");//感冒建议  
	        String sportsAdvise = index3.getString("des");//运动建议  
	        String ultravioletRaysAdvise = index4.getString("des");//紫外线建议
	        
	        info.setDressAdvise(dressAdvise);  
	        info.setWashCarAdvise(washCarAdvise);  
	        info.setColdAdvise(coldAdvise);  
	        info.setSportsAdvise(sportsAdvise);  
	        info.setUltravioletRaysAdvise(ultravioletRaysAdvise);  
          
	    }catch(JSONException jsonExp){  
	        //如果没有取到值，就自己设定
	    	info.setDressAdvise("要温度，也要风度。天热缓减衣，天凉及添衣！");  
	        info.setWashCarAdvise("你洗还是不洗，灰尘都在哪里，不增不减。");  
	        info.setColdAdvise("一天一个苹果，感冒不来找我！多吃水果和蔬菜。");  
	        info.setSportsAdvise("生命在于运动！不要总宅在家里哦！");  
	        info.setUltravioletRaysAdvise("心灵可以永远年轻，皮肤也一样可以！");  
	    }
		
        //这里保存了四天的信息，只取前一天的信息
        JSONArray weather_data = results.getJSONObject(0).getJSONArray("weather_data");
        
        //当天的信息
        JSONObject today = weather_data.getJSONObject(0);
        
        info.setDate(today.getString("date"));
        info.setWeather(today.getString("weather"));
        info.setWindString(today.getString("wind"));
        info.setTemper(today.getString("temperature"));
        
        return info;
		
	}
	
	/**
	 * 	逆地址编码 
	 * @param lat 纬度坐标 <a href="#">eg:38.76623</a>
	 * @param lng 经度坐标<a href="#">eg:116.43213</a>
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
	 * 	处理逆地址编码传回的json数据，输出cityName
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
	 * 		公共调用方法
	 * @param cityName 城市名字
	 * @throws IOException 
	 * */
	public WeatherInfo getInfo(String cityName) throws IOException{
		return cityName==null? null:parseInfo(getWeatherInfo(cityName));
	}
	
	/**
	 * 
	 * 		公共调用方法2（没有cityName通过逆地址编码解析产生）
	 * @param lat 纬度坐标 <a href="#">eg:38.76623</a>
	 * @param lng 经度坐标<a href="#">eg:116.43213</a>
	 * @throws IOException 
	 * 
	 * */
	public WeatherInfo getInfoWithLatLng(String lat,String lng) throws IOException{
		return lat!=null&&lng!=null? parseInfo(getWeatherInfo(getCItyName(lat, lng))):null;
	}
	
	/**	
	 * 		JUnit 测试方法
	 * */
	@Test
	public void testGetWeather() throws IOException{
//		System.out.println(getWeatherInfo("北京"));
//		System.out.println(getInfo("北京").toString());
//		getCItyName("38.76623","116.43213");
		System.out.println(getInfoWithLatLng("38.76623","116.43213").toString());
	}
	

}

/**
 * 	用于存放天气信息类
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
	private String dressAdvise;//穿衣建议  
    private String washCarAdvise;//洗车建议  
    private String coldAdvise;//感冒建议  
    private String sportsAdvise;//运动建议  
    private String ultravioletRaysAdvise;//紫外线建议
    
    private String weather;
    private String windString;
    private String temper;//温度
    
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
