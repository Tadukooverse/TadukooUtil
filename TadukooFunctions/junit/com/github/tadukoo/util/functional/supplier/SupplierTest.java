package com.github.tadukoo.util.functional.supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplierTest{
	private Supplier<Integer> supplier;
	
	@BeforeEach
	public void setup(){
		supplier = () -> 42;
	}
	
	@Test
	public void testSupplier(){
		assertEquals(42, supplier.get());
	}
}
