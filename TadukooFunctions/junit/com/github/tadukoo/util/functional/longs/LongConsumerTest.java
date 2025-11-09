package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LongConsumerTest{
	private long value;
	private LongConsumer success;
	private LongConsumer add2;
	private ThrowingConsumer<Long, IllegalArgumentException> throwingConsumer;
	private ThrowingConsumer<Long, IllegalArgumentException> throwingSuccess;
	
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
		success.accept(5L);
		assertEquals(5L, value);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwingConsumer).accept(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5L, value);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5L);
		assertEquals(7L, value);
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5L);
		assertEquals(7L, value);
	}
}
