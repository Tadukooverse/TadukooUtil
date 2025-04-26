package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingUnaryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IntUnaryOperatorTest{
	private ThrowingUnaryOperator<Integer, IllegalArgumentException> thrower;
	private IntUnaryOperator success;
	private IntUnaryOperator add2;
	private ThrowingUnaryOperator<Integer, IllegalArgumentException> throwingSuccess;
	
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
		assertEquals(5, success.apply(5));
	}
	
	@Test
	public void testComposeThrowing(){
		try{
			thrower.compose(success).apply(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testComposeSuccess(){
		assertEquals(7, add2.compose(success).apply(5));
	}
	
	@Test
	public void testComposeThrowingSuccess(){
		assertEquals(9, add2.compose(throwingSuccess).apply(5));
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(thrower).apply(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		assertEquals(7, success.andThen(add2).apply(5));
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		assertEquals(7, success.andThen(throwingSuccess).apply(5));
	}
	
	@Test
	public void testIdentity(){
		IntUnaryOperator identity = IntUnaryOperator.identity();
		assertEquals(5, identity.apply(5));
	}
}
