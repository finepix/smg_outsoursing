package com.smg.service.calculate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smg.service.calculate.utils.HtmlParse;
import com.smg.service.calculate.utils.InternetSolveUtils;


/**
 * 	改类从网上去获取要解的方程组的解然后判断是否为最佳的解
 * */
public class Calculate {
	private static String URLPATH = "http://zh.numberempire.com/equationsolver.php";
	
	private double A = 2;//能耗A
	private double B = 1;//能耗B
	
	//最优解
	private double fx;
	private double fy;
	
	//wind_speed
	private double v;
	private double f;
	private double t;
	
	//参数
	private static double SSD = 65.0;
	private static double DI = 62.5;
	private double Z ;//= SSD-34.23476+3.2*v;
	
	
	List<Double> solveList = null;//尚未初始化
	
	public Calculate(double v,double f,double t){
		this.v = v;
		this.f = f;
		this.t = t;
		Z = SSD-34.23476+3.2*this.v;
	}
	
	
	
	/**
	 * 获得最优解<a href='#'>要捕获异常</a>
	 * @return 返回最优解map 里面包含<b>x,y</b>
	 * */
	public Map<String, Double> bestSolveMap(){
		Map<String, Double> solveMap = new HashMap<String, Double>();
		//从Internet获取解list
		getList();
		for(int i=0;i<solveList.size();){
			if(i==0){
				fx = solveList.get(i++);
				fy = solveList.get(i++);
			}

			double x = solveList.get(i++);
			double y = solveList.get(i++);
			
			
			//若能耗低于x，y设置fy，fx（核心）
			if((fx*A+fy*B)>(Math.abs(x-t)*A+Math.abs(y-f)*B)){
				fx = x;
				fy = y;
			}
			
		}
		solveMap.put("x", fx);
		solveMap.put("y", fy);
		return solveMap;
	}
	
	
	/**
	 * 返回并且设置solvelist
	 * */
	private void getList(){
		Map<String, String> para  = new HashMap<String, String>();
		//= SSD-34.23476+3.2*v;
		String functionString = Z+"=1.59984*x+0.3636*x*y+(x-32)/(45-x),"+DI+"=x-0.55*(1-y)*(x-58)";
		
		//set the parameters
		para.put("function", functionString);
		para.put("var", "x,y");
		para.put("result_type", "false");
		
		InternetSolveUtils utils = new InternetSolveUtils(URLPATH, para);
		solveList = HtmlParse.parseHtml(utils.trans());//set the list
	}
	
}
