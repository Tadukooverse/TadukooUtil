package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoubleConsumerTest{
	private double value;
	private DoubleConsumer success;
	private DoubleConsumer add2;
	private ThrowingConsumer<Double, IllegalArgumentException> throwingConsumer;
	private ThrowingConsumer<Double, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = i -> value = i;
		add2 = i -> value = i + 2;
		throwingConsumer = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = i -> value = i + 2;
	}
	
	@Test
	public void testConsumer(){
		success.accept(5.0);
		assertEquals(5.0, value);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwingConsumer).accept(5.0);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5.0, value);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5.0);
		assertEquals(7.0, value);
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5.0);
		assertEquals(7.0, value);
	}
}
