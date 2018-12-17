package com.zhuxin.test;

import org.junit.Test;

import com.smg.service.data.sindex.process.IndexMessage;

public class MessageTest {

	@Test
	public void testMessage(){
		System.out.println(new IndexMessage().getMessage(59));
		System.out.println(new IndexMessage().getMessage(64));
		System.out.println(new IndexMessage().getMessage(72));
		System.out.println(new IndexMessage().getMessage(78));
		System.out.println(new IndexMessage().getMessage(84));
		System.out.println(new IndexMessage().getMessage(88));
		System.out.println(new IndexMessage().getMessage(100));
	}
}
