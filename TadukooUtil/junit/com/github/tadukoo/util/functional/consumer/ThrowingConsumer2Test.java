package com.github.tadukoo.util.functional.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingConsumer2Test{
	private int value1, value2;
	private ThrowingConsumer2<Integer, Integer, IllegalArgumentException> success;
	private ThrowingConsumer2<Integer, Integer, IllegalArgumentException> add2;
	private ThrowingConsumer2<Integer, Integer, IllegalArgumentException> consumer;
	
	@BeforeEach
	public void setup(){
		success = (i, j) -> {
			value1 = i;
			value2 = j;
		};
		add2 = (i, j) -> {
			value1 = i + 2;
			value2 = j + 2;
		};
		consumer = (i, j) -> {
			throw new IllegalArgumentException("Not supported");
		};
	}
	
	@Test
	public void testThrowingConsumer2(){
		try{
			consumer.accept(5, 3);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5, 3);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5, value1);
			assertEquals(3, value2);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5, 3);
		assertEquals(7, value1);
		assertEquals(5, value2);
	}
}
