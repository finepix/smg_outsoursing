package com.smg.service.data.sindex.process;

import org.junit.Test;

import com.smg.service.data.suit.model.SuitModel;

public class SuitTempCalTest {
	
	@Test
	public void testSuitTemp(){
		System.out.println(new SuitTemperatureCalculate(new SuitModel(45, 10)).getSuitTemp());
	}

}
