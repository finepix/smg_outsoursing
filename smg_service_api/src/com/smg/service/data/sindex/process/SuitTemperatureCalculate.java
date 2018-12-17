package com.smg.service.data.sindex.process;

import com.smg.service.data.suit.model.SuitModel;

/**
 * @author F-zx
 * 
 * 
 * 基于黄金分割算法计算最合适的温度
 * 	
 * */
public class SuitTemperatureCalculate {
	
	
	
	private double lat;
	private double m;
	private double height;
	
	
	/**
	 * 构造函数：传入参数model对改类初始化
	 * @param model suitModel模型（已经计算好需要的各个数据值）
	 * */
	public SuitTemperatureCalculate(SuitModel model){
		this.lat = model.getLat();
		this.m = model.getM();
		this.height = model.getHeight();
		
	}
	
	public double getSuitTemp(){
//		double m_lat = 22.7*(1.0-0.3*Math.sin(Math.PI*(lat-23.5)/180));
//		double m_height = 0-2.0*2.0*Math.tan(height/100);
		
		double m_lat ;
		double m_height ;
		
		double m_month = 0-Math.abs(0.3*(Math.cos(Math.PI*(15*(m-1))/180)));
		
		m_lat = this.lat < 23.5 ? 22.7 : 22.7*(1.0-0.3*Math.sin(Math.PI*(lat-23.5)/180));
		m_height = this.height < 500 ? 0 : 0-2.0*2.0*Math.tan(height/100);
		
		return m_lat+m_month+m_height;
	}
	
	
	

}
