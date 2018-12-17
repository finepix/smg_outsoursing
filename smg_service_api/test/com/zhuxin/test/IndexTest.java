package com.zhuxin.test;

import org.junit.Test;

import com.smg.service.data.sindex.process.IndexProcess;
import com.smg.service.weatherutils.Weather;

public class IndexTest {
	
	@Test
	public void testIndexProcess(){
		System.out.println(new IndexProcess(new Weather().getWeather("º¼ÖÝ")).calculate());
	}

}
