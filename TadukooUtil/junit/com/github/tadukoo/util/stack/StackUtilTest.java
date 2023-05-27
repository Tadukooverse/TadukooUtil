package com.github.tadukoo.util.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackUtilTest{
	
	private Class<?> test() throws ClassNotFoundException{
		return StackUtil.getCallingClass();
	}
	
	@Test
	public void testGetCallingClassName(){
		assertEquals("jdk.internal.reflect.NativeMethodAccessorImpl", StackUtil.getCallingClassName());
	}
	
	@Test
	public void testGetCallingClass() throws ClassNotFoundException{
		assertEquals(StackUtilTest.class, test());
	}
	
	@Test
	public void testGetCallingClassMethodName(){
		assertEquals("invoke0", StackUtil.getCallingMethodName());
	}
}
