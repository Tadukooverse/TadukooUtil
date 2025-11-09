package com.github.tadukoo.util.functional.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingConsumer4Test{
	private int value1, value2, value3, value4;
	private ThrowingConsumer4<Integer, Integer, Integer, Integer, IllegalArgumentException> success;
	private ThrowingConsumer4<Integer, Integer, Integer, Integer, IllegalArgumentException> add2;
	private ThrowingConsumer4<Integer, Integer, Integer, Integer, IllegalArgumentException> consumer;
	private Consumer4<Integer, Integer, Integer, Integer> regularConsumer;
	
	@BeforeEach
	public void setup(){
		success = (i, j, k, l) -> {
			value1 = i;
			value2 = j;
			value3 = k;
			value4 = l;
		};
		add2 = (i, j, k, l) -> {
			value1 = i + 2;
			value2 = j + 2;
			value3 = k + 2;
			value4 = l + 2;
		};
		consumer = (i, j, k, l) -> {
			throw new IllegalArgumentException("Not supported");
		};
		regularConsumer = (i, j, k, l) -> {
			value1 = i + 2;
			value2 = j + 2;
			value3 = k + 2;
			value4 = l + 2;
		};
	}
	
	@Test
	public void testThrowingConsumer4(){
		try{
			consumer.accept(5, 3, 17, 13);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5, 3, 17, 13);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5, value1);
			assertEquals(3, value2);
			assertEquals(17, value3);
			assertEquals(13, value4);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5, 3, 17, 13);
		assertEquals(7, value1);
		assertEquals(5, value2);
		assertEquals(19, value3);
		assertEquals(15, value4);
	}
	
	@Test
	public void testAndThenRegularConsumer(){
		success.andThen(regularConsumer).accept(5, 3, 17, 13);
		assertEquals(7, value1);
		assertEquals(5, value2);
		assertEquals(19, value3);
		assertEquals(15, value4);
	}
}
