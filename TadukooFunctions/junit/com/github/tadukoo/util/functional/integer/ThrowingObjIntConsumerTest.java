package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.consumer.Consumer2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingObjIntConsumerTest{
	private int value1, value2;
	private ThrowingObjIntConsumer<Integer, IllegalArgumentException> success;
	private ThrowingObjIntConsumer<Integer, IllegalArgumentException> add2;
	private ThrowingObjIntConsumer<Integer, IllegalArgumentException> consumer;
	private Consumer2<Integer, Integer> regularConsumer;
	
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
	public void testThrowingObjIntConsumer(){
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
	
	@Test
	public void testAndThenRegularConsumer(){
		success.andThen(regularConsumer).accept(5, 3);
		assertEquals(7, value1);
		assertEquals(5, value2);
	}
}
