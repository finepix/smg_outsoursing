package com.smg.service.data.sindex.process;

import com.smg.service.weatherutils.model.WeatherInfo;



/**
 * 	@author F-zx
 * 
 * 		��weatherInfo�����ֵ���в��ԣ��������һ�����ʶȣ����ظ�service��
 * 
 * */
public class IndexProcess {

	private double t;//temperature
	private double f;//huminity
	private double v;//windspeed
	
	/**
	 * 		�Ը�����г�ʼ��
	 * @param info ������Ϣ�ࣨ�¶ȣ�ʪ�ȣ����٣�
	 * 
	 * */
	public IndexProcess(WeatherInfo info){
		this.t = info.getTemperature();
		this.f = info.getHumidity();
		this.v = info.getWind_speed();
	}
	
	/**
	 * 	�����һ��SSD���ظ����õ���
	 * */
	public double calculate(){
		return (double) ((double) ((1.818*t+18.18)*(0.88+0.002*f))+(t-32)/(45-t)-3.2*v+18.2);
	}
	
}
