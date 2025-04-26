package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.consumer.Consumer2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingObjLongConsumerTest{
	private long value1, value2;
	private ThrowingObjLongConsumer<Long, IllegalArgumentException> success;
	private ThrowingObjLongConsumer<Long, IllegalArgumentException> add2;
	private ThrowingObjLongConsumer<Long, IllegalArgumentException> consumer;
	private Consumer2<Long, Long> regularConsumer;
	
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
		regularConsumer = (i, j) -> {
			value1 = i + 2;
			value2 = j + 2;
		};
	}
	
	@Test
	public void testThrowingObjLongConsumer(){
		try{
			consumer.accept(5L, 3L);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5L, 3L);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5L, value1);
			assertEquals(3L, value2);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5L, 3L);
		assertEquals(7L, value1);
		assertEquals(5L, value2);
	}
	
	@Test
	public void testAndThenRegularConsumer(){
		success.andThen(regularConsumer).accept(5L, 3L);
		assertEquals(7L, value1);
		assertEquals(5L, value2);
	}
}
