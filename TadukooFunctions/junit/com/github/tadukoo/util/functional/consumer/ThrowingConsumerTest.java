package com.github.tadukoo.util.functional.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingConsumerTest{
	private int value;
	private ThrowingConsumer<Integer, IllegalArgumentException> success;
	private ThrowingConsumer<Integer, IllegalArgumentException> add2;
	private ThrowingConsumer<Integer, IllegalArgumentException> consumer;
	
	@BeforeEach
	public void setup(){
		success = i -> value = i;
		add2 = i -> value = i + 2;
		consumer = i -> {
			throw new IllegalArgumentException("Not supported");
		};
	}
	
	@Test
	public void testThrowingConsumer(){
		try{
			consumer.accept(5);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5, value);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5);
		assertEquals(7, value);
	}
}
