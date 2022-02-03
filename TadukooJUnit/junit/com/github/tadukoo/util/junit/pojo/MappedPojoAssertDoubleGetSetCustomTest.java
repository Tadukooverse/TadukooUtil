package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;

public class MappedPojoAssertDoubleGetSetCustomTest extends MappedPojoAssertValueGetSetCustomTest<Double>
		implements DefaultTestValues{
	
	public MappedPojoAssertDoubleGetSetCustomTest(){
		super(MappedPojoTest::assertDoubleGetSetCustom, MappedPojoTest::assertDoubleGetSetCustom,
				DEFAULT_TEST_DOUBLE, DEFAULT_TEST_DOUBLE_2, DEFAULT_WRONG_DOUBLE);
	}
}
