package com.zhuxin.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smg.service.weatherutils.Weather;

public class WeatherTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testGetWeather(){
		System.out.println(new Weather().getWeather("º¼ÖÝ").toString());
	}

}
