package com.smg.service.data.feel.temper;

import com.smg.service.data.feel.model.FeelTemperModel;

public class FeelTemper {

	private static final double A = 36.75*(1-0.618);
	
	private  double RHs = 0.618;//0.5
	private double Ta;
	private double Ts;
	private double RH;
	private double v;
	
	
	/**
	 *  构造函数（参数model）
	 *  @param model 计算体感温度的model
	 *  
	 * 
	 * */
	public FeelTemper(FeelTemperModel model){
		this.RHs = model.getRHs();
		this.RH = model.getRH();
		this.Ts = model.getTs();
		this.Ta = model.getTa();
		this.v = model.getV();
	}
	
	public double getFeelTemper(){
		return calculate();
	}

	private double calculate() {
		double c1,c2;
		if(Ta>=Ts){
			c1 = 0.05;
			c2 = 0.03;
		}else {
			c1 = 0.013;
			c2 = 0.01;
		}
		double Tg;
		Tg = Ta+A*(Math.exp(c1*(Ta-Ts)*(RH-RHs))-1)-c2*(Ta-Ts)*v;
		
		return Tg;
	}
	
}
