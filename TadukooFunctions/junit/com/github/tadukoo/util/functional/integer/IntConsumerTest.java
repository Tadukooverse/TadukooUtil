package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IntConsumerTest{
	private int value;
	private IntConsumer success;
	private IntConsumer add2;
	private ThrowingConsumer<Integer, IllegalArgumentException> throwingConsumer;
	private ThrowingConsumer<Integer, IllegalArgumentException> throwingSuccess;
	
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
		success.accept(5);
		assertEquals(5, value);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwingConsumer).accept(5);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5, value);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5);
		assertEquals(7, value);
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5);
		assertEquals(7, value);
	}
}
