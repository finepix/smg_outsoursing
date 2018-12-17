package com.smg.service.data.sindex.process;

import java.util.HashMap;
import java.util.Map;

import com.smg.service.calculate.Calculate;
import com.smg.service.weatherutils.model.WeatherInfo;

public class SuitTempAndF {
	
//	private static double KSSD = 62.5;
//	private static double DI = 65.0;
	
	private double v  ;
	private double f  ;
	private double t  ;
	
	public SuitTempAndF(WeatherInfo info){
		this.v = info.getWind_speed();
		this.f = info.getHumidity();
		this.t = info.getTemperature();
	}
	
	public Map<String, Double> getSolve(){
		Map<String, Double> map = new HashMap<String, Double>();
		
		try {
			map = new Calculate(this.v,this.f,this.v).bestSolveMap();
		} catch (Exception e) {
			double x = 16+Math.random()*12;
			double y = Math.random();
			map.put("x", x);
			map.put("y", y);
		}
		
		
		return map;
	}
}
