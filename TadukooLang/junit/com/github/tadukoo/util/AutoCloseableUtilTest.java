package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AutoCloseableUtilTest{
	private static class SomethingCloseable implements AutoCloseable{
		@Override
		public void close() throws Exception{
			throw new ClassNotFoundException();
		}
	}
	private static class SomethingCloseableNoThrow implements AutoCloseable{
		@Override
		public void close(){
		
		}
	}
	
	@Test
	public void testCloseQuietly(){
		SomethingCloseable closeable = new SomethingCloseable();
		
		try{
			closeable.close();
			fail();
		}catch(Exception e){
			// Do nothing, this is intended
		}
		
		SomethingCloseable closeable2 = new SomethingCloseable();
		
		try{
			AutoCloseableUtil.closeQuietly(closeable2);
			// Good path
		}catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testCloseQuietlyNull(){
		AutoCloseableUtil.closeQuietly(null);
	}
	
	@Test
	public void testCloseQuietlyNoThrow(){
		SomethingCloseableNoThrow closeable = new SomethingCloseableNoThrow();
		
		AutoCloseableUtil.closeQuietly(closeable);
	}
}
