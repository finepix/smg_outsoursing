package com.smg.service.data.suit.model;

import java.io.IOException;
import java.util.Calendar;

import com.smg.service.util.height.HeightUtils;

/**
 * 	���������¶��ǵ�ģ��
 * */
public class SuitModel {

	private double lat;//γ��
	private double lng;
	private double height;//����
	private int M;//�·�
	

	private double temper;//wendu 
	
	public SuitModel(double lat,double lng){
		this.lat = lat;
		this.lng = lng;
		
		//��ȡ��ǰ���·�
		Calendar calendar = Calendar.getInstance();
		this.M = calendar.get(Calendar.MONTH)+1;
		
		//��ȡ�߶�
		HeightUtils utils;
		utils = new HeightUtils(String.valueOf(this.lat), String.valueOf(this.lng));
		try {
			this.height = utils.getHeight();
		} catch (IOException e) {
			this.height = 0;
		}
		
		//��ȡ�����¶�
		
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getTemper() {
		return temper;
	}

	public void setTemper(double temper) {
		this.temper = temper;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new SuitModel(23.12,26.25,12).height);
//	}
//	
}
