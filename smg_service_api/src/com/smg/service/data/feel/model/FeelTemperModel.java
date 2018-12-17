package com.smg.service.data.feel.model;


public class FeelTemperModel {

	private double Ta;
	private double Ts;
	private double RH;
	private double v;
	private double RHs;
	
	public FeelTemperModel(FeelWeatherModel model,double Ts){
		//set RHs
		if(model.getRain().contains("гъ"))
			this.RHs = 0.618;
		else
			this.RHs = 0.5;
		
		this.Ta = model.getTemp();
		this.RH = model.getRH();
		this.v = model.getV();
		this.Ts = Ts;
		
	}
	
	public double getTa() {
		return Ta;
	}
	public void setTa(double ta) {
		Ta = ta;
	}
	public double getTs() {
		return Ts;
	}
	public void setTs(double ts) {
		Ts = ts;
	}
	public double getRH() {
		return RH;
	}
	public void setRH(double rH) {
		RH = rH;
	}
	public double getV() {
		return v;
	}
	public void setV(double v) {
		this.v = v;
	}
	public double getRHs() {
		return RHs;
	}
	public void setRHs(double rHs) {
		RHs = rHs;
	}
	
}
