package com.github.tadukoo.util.functional.integer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntSupplierTest{
	private IntSupplier supplier;
	
	@BeforeEach
	public void setup(){
		supplier = () -> 5;
	}
	
	@Test
	public void testSupplier(){
		assertEquals(5, supplier.get());
	}
}
