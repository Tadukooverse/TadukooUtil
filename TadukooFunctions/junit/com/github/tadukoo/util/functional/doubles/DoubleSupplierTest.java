package com.github.tadukoo.util.functional.doubles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleSupplierTest{
	private DoubleSupplier supplier;
	
	@BeforeEach
	public void setup(){
		supplier = () -> 5.0;
	}
	
	@Test
	public void testSupplier(){
		assertEquals(5.0, supplier.get());
	}
}
