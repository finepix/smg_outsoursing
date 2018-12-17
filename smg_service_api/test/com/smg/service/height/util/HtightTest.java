package com.smg.service.height.util;

import java.io.IOException;

import org.junit.Test;

import com.smg.service.util.height.HeightUtils;

public class HtightTest {
	
	@Test
	public void testHeight() throws IOException{
		
		HeightUtils utils = new HeightUtils("50.26", "125.2");
		System.out.println(utils.getHeight());
	}

}
