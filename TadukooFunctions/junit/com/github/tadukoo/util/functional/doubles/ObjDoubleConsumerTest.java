package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ObjDoubleConsumerTest{
	private double value1, value2;
	private ObjDoubleConsumer<Double> success;
	private ObjDoubleConsumer<Double> add2;
	private ThrowingConsumer2<Double, Double, IllegalArgumentException> throwingConsumer;
	private ThrowingConsumer2<Double, Double, IllegalArgumentException> throwingSuccess;
	
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
	public void testObjDoubleConsumer(){
		success.accept(5.0, 3.0);
		assertEquals(5.0, value1);
		assertEquals(3.0, value2);
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(throwingConsumer).accept(5.0, 3.0);
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
	public void testAndThenThrowingSuccess(){
		success.andThen(throwingSuccess).accept(5.0, 3.0);
		assertEquals(7.0, value1);
		assertEquals(5.0, value2);
	}
}
