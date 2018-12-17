package com.smg.service.data.feel.model;

import java.io.IOException;

import com.smg.service.weatherutils.Weather;
import com.smg.service.weatherutils.model.WeatherInfo;
import com.smg.utils.ConvertCityName;
import com.smg.utils.WeatherUtil;

public class FeelWeatherModel {

	private String rain;
	private double temp;
	private double v;
	private double RH;
	
	public FeelWeatherModel(String lat,String lng){
		//set the rain info
		try {
			this.rain = new WeatherUtil().getWeather(lat, lng);
		} catch (IOException e) {
			this.rain = "”Í";
		}
		//get the name from lat and lng
		String cityName = "∫º÷›";
		try {
			String str = new ConvertCityName().getCItyName(lat, lng);
			cityName = str.substring(0, str.length()-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WeatherInfo info = new Weather().getWeather(cityName);
		
		this.temp = info.getTemperature();
		this.v = info.getWind_speed();
		this.RH = info.getHumidity();
		
	}
	
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getV() {
		return v;
	}
	public void setV(double v) {
		this.v = v;
	}
	public double getRH() {
		return RH;
	}
	public void setRH(double rH) {
		RH = rH;
	}
}
