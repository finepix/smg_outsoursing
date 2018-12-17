package com.smg.service.weatherutils.model;

public class WeatherInfo {

	//温度，单位为c摄氏度或f华氏度
	private double temperature;
	//体感温度，单位为c摄氏度或f华氏度
//	private float feels_like;
	//相对湿度，0~100，单位为百分比
	private double humidity;
	//风速，单位为km/h公里每小时或mph英里每小时
	private double wind_speed;
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}
	
	
	
	
	@Override
	public String toString() {
		return "WeatherInfo [temperature=" + temperature + ", humidity="
				+ humidity + ", wind_speed=" + wind_speed + "]";
	}

	

}
