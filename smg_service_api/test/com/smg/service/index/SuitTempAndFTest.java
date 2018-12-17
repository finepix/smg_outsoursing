package com.smg.service.index;

import org.junit.Test;

import com.smg.service.data.sindex.process.SuitTempAndF;
import com.smg.service.weatherutils.model.WeatherInfo;

public class SuitTempAndFTest {

	@Test
	public void testGetTemp(){
		WeatherInfo info = new WeatherInfo();
		info.setHumidity(61);
		info.setTemperature(2);
		info.setWind_speed(23);
		SuitTempAndF f = new SuitTempAndF(info);
//		System.out.println(f.getTemp());
//		System.out.println(f.getHumidity());
	}
}
