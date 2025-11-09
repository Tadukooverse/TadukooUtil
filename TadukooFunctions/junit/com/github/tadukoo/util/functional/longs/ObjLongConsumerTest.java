package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ObjLongConsumerTest{
	private long value1, value2;
	private ObjLongConsumer<Long> success;
	private ObjLongConsumer<Long> add2;
	private ThrowingConsumer2<Long, Long, IllegalArgumentException> throwingConsumer;
	private ThrowingConsumer2<Long, Long, IllegalArgumentException> throwingSuccess;
	
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
	public void testObjLongConsumer(){
		success.accept(5L, 3L);
		assertEquals(5L, value1);
		assertEquals(3L, value2);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwingConsumer).accept(5L, 3L);
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
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5L, 3L);
		assertEquals(7L, value1);
		assertEquals(5L, value2);
	}
}
