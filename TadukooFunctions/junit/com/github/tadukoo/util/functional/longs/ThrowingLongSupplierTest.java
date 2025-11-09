package com.github.tadukoo.util.functional.longs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ThrowingLongSupplierTest{
	private ThrowingLongSupplier<IllegalArgumentException> supplier;
	
	@BeforeEach
	public void setup(){
		supplier = () -> {
			throw new IllegalArgumentException("Unsupported");
		};
	}
	
	@Test
	public void testThrowingSupplier(){
		try{
			supplier.get();
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("Unsupported", e.getMessage());
		}
	}
}
