package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;

public class MappedPojoAssertDoubleGetSetTest extends MappedPojoAssertValueGetSetTest<Double>
		implements DefaultTestValues{
	
	public MappedPojoAssertDoubleGetSetTest(){
		super(MappedPojoTest::assertDoubleGetSet, MappedPojoTest::assertDoubleGetSet,
				DEFAULT_TEST_DOUBLE, DEFAULT_TEST_DOUBLE_2, DEFAULT_WRONG_DOUBLE);
	}
}
