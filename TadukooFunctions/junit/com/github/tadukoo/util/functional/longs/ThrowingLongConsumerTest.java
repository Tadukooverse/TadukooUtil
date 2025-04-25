package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.consumer.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongConsumerTest{
	private long value;
	private ThrowingLongConsumer<IllegalArgumentException> success;
	private ThrowingLongConsumer<IllegalArgumentException> add2;
	private ThrowingLongConsumer<IllegalArgumentException> consumer;
	private Consumer<Long> regularConsumer;
	
	@BeforeEach
	public void setup(){
		success = i -> value = i;
		add2 = i -> value = i + 2;
		consumer = i -> {
			throw new IllegalArgumentException("Not supported");
		};
		regularConsumer = i -> value = i * 2;
	}
	
	@Test
	public void testThrowingConsumer(){
		try{
			consumer.accept(5L);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5L, value);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5L);
		assertEquals(7L, value);
	}
	
	@Test
	public void testAndThenRegularConsumer(){
		success.andThen(regularConsumer).accept(5L);
		assertEquals(10L, value);
	}
}
