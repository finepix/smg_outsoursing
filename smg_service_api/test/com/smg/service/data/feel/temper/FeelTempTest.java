package com.smg.service.data.feel.temper;

import org.junit.Test;

import com.smg.service.data.feel.model.FeelTemperModel;
import com.smg.service.data.feel.model.FeelWeatherModel;

public class FeelTempTest {

	@Test
	public void testFeelTemp(){
		FeelTemper temper = new FeelTemper(new FeelTemperModel(new FeelWeatherModel("30.3", "120.25"), 23.1));
		System.out.println(temper.getFeelTemper());
	}
}
