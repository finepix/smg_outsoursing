package com.zhuxin.test.suitFangt;

import org.junit.Test;

import com.smg.service.data.sindex.process.SuitTempAndF;
import com.smg.service.weatherutils.Weather;
import com.smg.service.weatherutils.model.WeatherInfo;

public class FAndTTest {
	@Test
	public void testCalcu(){
		WeatherInfo info = new Weather().getWeather("º¼ÖÝ");
//		info.setWind_speed(2.56);
		System.out.println(new SuitTempAndF(info).getSolve().get("y"));
	}

}
