package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ObjIntConsumerTest{
	private int value1, value2;
	private ObjIntConsumer<Integer> success;
	private ObjIntConsumer<Integer> add2;
	private ThrowingConsumer2<Integer, Integer, IllegalArgumentException> throwingConsumer;
	private ThrowingConsumer2<Integer, Integer, IllegalArgumentException> throwingSuccess;
	
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
		throwingConsumer = (i, j) -> {
			throw new IllegalArgumentException("Not supported");
		};
		throwingSuccess = (i, j) -> {
			value1 = i + 2;
			value2 = j + 2;
		};
	}
	
	@Test
	public void testObjIntConsumer(){
		success.accept(5, 3);
		assertEquals(5, value1);
		assertEquals(3, value2);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwingConsumer).accept(5, 3);
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
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5, 3);
		assertEquals(7, value1);
		assertEquals(5, value2);
	}
}
