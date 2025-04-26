package com.github.tadukoo.util.functional.longs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongSupplierTest{
	private LongSupplier supplier;
	
	@BeforeEach
	public void setup(){
		supplier = () -> 5L;
	}
	
	@Test
	public void testSupplier(){
		assertEquals(5L, supplier.get());
	}
}
