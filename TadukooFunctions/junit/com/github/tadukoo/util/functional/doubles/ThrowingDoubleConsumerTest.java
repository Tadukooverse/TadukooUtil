package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingDoubleConsumerTest{
	private double value;
	private ThrowingDoubleConsumer<IllegalArgumentException> success;
	private ThrowingDoubleConsumer<IllegalArgumentException> add2;
	private ThrowingDoubleConsumer<IllegalArgumentException> consumer;
	private Consumer<Double> regularConsumer;
	
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
			consumer.accept(5.0);
			fail();
		}catch(IllegalArgumentException e){
			// Success
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenThrowing(){
		try{
			success.andThen(consumer).accept(5.0);
			fail();
		}catch(IllegalArgumentException e){
			assertEquals(5.0, value);
			assertEquals("Not supported", e.getMessage());
		}
	}
	
	@Test
	public void testAndThenSuccess(){
		success.andThen(add2).accept(5.0);
		assertEquals(7.0, value);
	}
	
	@Test
	public void testAndThenRegularConsumer(){
		success.andThen(regularConsumer).accept(5.0);
		assertEquals(10.0, value);
	}
}
