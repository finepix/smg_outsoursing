package com.zhuxin.calculate;

import org.junit.Test;

import com.smg.service.calculate.Calculate;

public class TestCalculating {
	@Test
	public void testCal(){
		System.out.println(new Calculate(12.0,24,0.55).bestSolveMap().get("x"));
	}

	public static void main(String[] args) {
		System.out.println(new Calculate(12.0,24,0.55).bestSolveMap().get("x"));
	}

}
