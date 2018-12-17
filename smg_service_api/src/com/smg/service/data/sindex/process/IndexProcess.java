package com.smg.service.data.sindex.process;

import com.smg.service.weatherutils.model.WeatherInfo;



/**
 * 	@author F-zx
 * 
 * 		对weatherInfo传入的值进行测试，并且算出一个舒适度，返回给service层
 * 
 * */
public class IndexProcess {

	private double t;//temperature
	private double f;//huminity
	private double v;//windspeed
	
	/**
	 * 		对该类进行初始化
	 * @param info 天气信息类（温度，湿度，风速）
	 * 
	 * */
	public IndexProcess(WeatherInfo info){
		this.t = info.getTemperature();
		this.f = info.getHumidity();
		this.v = info.getWind_speed();
	}
	
	/**
	 * 	计算出一个SSD返回给调用的类
	 * */
	public double calculate(){
		return (double) ((double) ((1.818*t+18.18)*(0.88+0.002*f))+(t-32)/(45-t)-3.2*v+18.2);
	}
	
}
