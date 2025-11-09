package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingUnaryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LongUnaryOperatorTest{
	private ThrowingUnaryOperator<Long, IllegalArgumentException> thrower;
	private LongUnaryOperator success;
	private LongUnaryOperator add2;
	private ThrowingUnaryOperator<Long, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> i;
		add2 = i -> i + 2;
		throwingSuccess = i -> i + 2;
	}
	
	@Test
	public void testSelfFunction(){
		assertEquals(5, success.apply(5L));
	}
	
	@Test
	public void testComposeThrowing(){
		try{
			thrower.compose(success).apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeSuccess(){
		assertEquals(7, add2.compose(success).apply(5L));
	}
	
	@Test
	public void testComposeThrowingSuccess(){
		assertEquals(9, add2.compose(throwingSuccess).apply(5L));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(thrower).apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7L, success.andThen(add2).apply(5L));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7L, success.andThen(throwingSuccess).apply(5L));
	}
	
	@Test
	public void testIdentity(){
		LongUnaryOperator identity = LongUnaryOperator.identity();
		assertEquals(5L, identity.apply(5L));
	}
}
