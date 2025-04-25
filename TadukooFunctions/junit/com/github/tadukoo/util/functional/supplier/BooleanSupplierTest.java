package com.github.tadukoo.util.functional.supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanSupplierTest{
	private BooleanSupplier supplier;
	
	@BeforeEach
	public void setup(){
		supplier = () -> true;
	}
	
	@Test
	public void testSupplier(){
		assertTrue(supplier.get());
	}
}
