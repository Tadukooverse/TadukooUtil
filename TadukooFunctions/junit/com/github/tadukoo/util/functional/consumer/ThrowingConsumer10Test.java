package com.github.tadukoo.util.functional.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingConsumer10Test{
	private int value1, value2, value3, value4, value5, value6, value7, value8, value9, value10;
	private ThrowingConsumer10<Integer, Integer, Integer, Integer, Integer,
			Integer, Integer, Integer, Integer, Integer, IllegalArgumentException> success;
	private ThrowingConsumer10<Integer, Integer, Integer, Integer, Integer,
			Integer, Integer, Integer, Integer, Integer, IllegalArgumentException> add2;
	private ThrowingConsumer10<Integer, Integer, Integer, Integer, Integer,
			Integer, Integer, Integer, Integer, Integer, IllegalArgumentException> consumer;
	
	@BeforeEach
	public void setup(){
		success = (i, j, k, l, m, n, o, p, q, r) -> {
			value1 = i;
			value2 = j;
			value3 = k;
			value4 = l;
			value5 = m;
			value6 = n;
			value7 = o;
			value8 = p;
			value9 = q;
			value10 = r;
		};
		add2 = (i, j, k, l, m, n, o, p, q, r) -> {
			value1 = i + 2;
			value2 = j + 2;
			value3 = k + 2;
			value4 = l + 2;
			value5 = m + 2;
			value6 = n + 2;
			value7 = o + 2;
			value8 = p + 2;
			value9 = q + 2;
			value10 = r + 2;
		};
		consumer = (i, j, k, l, m, n, o, p, q, r) -> {
			throw new IllegalArgumentException("Not supported");
		};
	}
	
	@Test
	public void testThrowingConsumer10(){
		try{
			consumer.accept(5, 3, 17, 13, 23, 19, 209, 12, 75, 42);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5, 3, 17, 13, 23, 19, 209, 12, 75, 42);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5, value1);
			assertEquals(3, value2);
			assertEquals(17, value3);
			assertEquals(13, value4);
			assertEquals(23, value5);
			assertEquals(19, value6);
			assertEquals(209, value7);
			assertEquals(12, value8);
			assertEquals(75, value9);
			assertEquals(42, value10);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5, 3, 17, 13, 23, 19, 209,12, 75, 42);
		assertEquals(7, value1);
		assertEquals(5, value2);
		assertEquals(19, value3);
		assertEquals(15, value4);
		assertEquals(25, value5);
		assertEquals(21, value6);
		assertEquals(211, value7);
		assertEquals(14, value8);
		assertEquals(77, value9);
		assertEquals(44, value10);
	}
}
