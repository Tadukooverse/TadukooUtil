package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.UnaryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongUnaryOperatorTest{
	private ThrowingLongUnaryOperator<IllegalArgumentException> thrower;
	private ThrowingLongUnaryOperator<IllegalArgumentException> success;
	private ThrowingLongUnaryOperator<IllegalArgumentException> add2;
	private UnaryOperator<Long> regularFunction;
	
	@BeforeEach
	public void setup(){
		thrower = i -> {
			throw new IllegalArgumentException("Unsupported");
		};
		success = i -> i;
		add2 = i -> i + 2;
		regularFunction = i -> i + 2;
	}
	
	@Test
	public void testThrowingSelfFunction(){
		try{
			thrower.apply(5L);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Unsupported", e.getMessage());
		}
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
		assertEquals(7L, add2.compose(success).apply(5L));
	}
	
	@Test
	public void testComposeRegularFunction(){
		assertEquals(9L, add2.compose(regularFunction).apply(5L));
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
	public void testAndThenRegularFunction(){
		assertEquals(7L, success.andThen(regularFunction).apply(5L));
	}
	
	@Test
	public void testIdentity() throws Throwable{
		ThrowingLongUnaryOperator<Throwable> identity = ThrowingLongUnaryOperator.identity();
		assertEquals(5L, identity.apply(5L));
	}
}
