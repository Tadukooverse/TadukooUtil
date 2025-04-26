package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.Consumer2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingObjDoubleConsumerTest{
	private double value1, value2;
	private ThrowingObjDoubleConsumer<Double, IllegalArgumentException> success;
	private ThrowingObjDoubleConsumer<Double, IllegalArgumentException> add2;
	private ThrowingObjDoubleConsumer<Double, IllegalArgumentException> consumer;
	private Consumer2<Double, Double> regularConsumer;
	
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
	public void testThrowingObjDoubleConsumer(){
		try{
			consumer.accept(5.0, 3.0);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5.0, 3.0);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5.0, value1);
			assertEquals(3.0, value2);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5.0, 3.0);
		assertEquals(7.0, value1);
		assertEquals(5.0, value2);
	}
	
	@Test
	public void testAndThenRegularConsumer(){
		success.andThen(regularConsumer).accept(5.0, 3.0);
		assertEquals(7.0, value1);
		assertEquals(5.0, value2);
	}
}
