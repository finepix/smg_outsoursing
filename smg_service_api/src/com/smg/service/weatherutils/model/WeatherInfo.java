package com.smg.service.weatherutils.model;

public class WeatherInfo {

	//�¶ȣ���λΪc���϶Ȼ�f���϶�
	private double temperature;
	//����¶ȣ���λΪc���϶Ȼ�f���϶�
//	private float feels_like;
	//���ʪ�ȣ�0~100����λΪ�ٷֱ�
	private double humidity;
	//���٣���λΪkm/h����ÿСʱ��mphӢ��ÿСʱ
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
