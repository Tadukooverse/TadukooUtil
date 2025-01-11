package com.github.tadukoo.util.functional.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Consumer7Test{
	private int value1, value2, value3, value4, value5, value6, value7;
	private Consumer7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> success;
	private Consumer7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> add2;
	private ThrowingConsumer7<Integer, Integer, Integer, Integer, Integer,
			Integer, Integer, IllegalArgumentException> throwing;
	private ThrowingConsumer7<Integer, Integer, Integer, Integer, Integer,
			Integer, Integer, IllegalArgumentException> throwingSuccess;
	
	@BeforeEach
	public void setup(){
		success = (i, j, k, l, m, n, o) -> {
			value1 = i;
			value2 = j;
			value3 = k;
			value4 = l;
			value5 = m;
			value6 = n;
			value7 = o;
		};
		add2 = (i, j, k, l, m, n, o) -> {
			value1 = i + 2;
			value2 = j + 2;
			value3 = k + 2;
			value4 = l + 2;
			value5 = m + 2;
			value6 = n + 2;
			value7 = o + 2;
		};
		throwing = (i, j, k, l, m, n, o) -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = (i, j, k, l, m, n, o) -> {
			value1 = i + 2;
			value2 = j + 2;
			value3 = k + 2;
			value4 = l + 2;
			value5 = m + 2;
			value6 = n + 2;
			value7 = o + 2;
		};
	}
	
	@Test
	public void testConsumer7(){
		success.accept(5, 3, 17, 13, 23, 19, 209);
		assertEquals(5, value1);
		assertEquals(3, value2);
		assertEquals(17, value3);
		assertEquals(13, value4);
		assertEquals(23, value5);
		assertEquals(19, value6);
		assertEquals(209, value7);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwing).accept(5, 3, 17, 13, 23, 19, 209);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5, value1);
			assertEquals(3, value2);
			assertEquals(17, value3);
			assertEquals(13, value4);
			assertEquals(23, value5);
			assertEquals(19, value6);
			assertEquals(209, value7);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5, 3, 17, 13, 23, 19, 209);
		assertEquals(7, value1);
		assertEquals(5, value2);
		assertEquals(19, value3);
		assertEquals(15, value4);
		assertEquals(25, value5);
		assertEquals(21, value6);
		assertEquals(211, value7);
	}
	
	@Test
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5, 3, 17, 13, 23, 19, 209);
		assertEquals(7, value1);
		assertEquals(5, value2);
		assertEquals(19, value3);
		assertEquals(15, value4);
		assertEquals(25, value5);
		assertEquals(21, value6);
		assertEquals(211, value7);
	}
}
