package com.smg.service.calculate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smg.service.calculate.utils.HtmlParse;
import com.smg.service.calculate.utils.InternetSolveUtils;


/**
 * 	���������ȥ��ȡҪ��ķ�����Ľ�Ȼ���ж��Ƿ�Ϊ��ѵĽ�
 * */
public class Calculate {
	private static String URLPATH = "http://zh.numberempire.com/equationsolver.php";
	
	private double A = 2;//�ܺ�A
	private double B = 1;//�ܺ�B
	
	//���Ž�
	private double fx;
	private double fy;
	
	//wind_speed
	private double v;
	private double f;
	private double t;
	
	//����
	private static double SSD = 65.0;
	private static double DI = 62.5;
	private double Z ;//= SSD-34.23476+3.2*v;
	
	
	List<Double> solveList = null;//��δ��ʼ��
	
	public Calculate(double v,double f,double t){
		this.v = v;
		this.f = f;
		this.t = t;
		Z = SSD-34.23476+3.2*this.v;
	}
	
	
	
	/**
	 * ������Ž�<a href='#'>Ҫ�����쳣</a>
	 * @return �������Ž�map �������<b>x,y</b>
	 * */
	public Map<String, Double> bestSolveMap(){
		Map<String, Double> solveMap = new HashMap<String, Double>();
		//��Internet��ȡ��list
		getList();
		for(int i=0;i<solveList.size();){
			if(i==0){
				fx = solveList.get(i++);
				fy = solveList.get(i++);
			}

			double x = solveList.get(i++);
			double y = solveList.get(i++);
			
			
			//���ܺĵ���x��y����fy��fx�����ģ�
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
	 * ���ز�������solvelist
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
