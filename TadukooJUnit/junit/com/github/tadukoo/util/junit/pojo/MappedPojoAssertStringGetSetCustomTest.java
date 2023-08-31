package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;

public class MappedPojoAssertStringGetSetCustomTest extends MappedPojoAssertValueGetSetCustomTest<String>
		implements DefaultTestValues{
	
	public MappedPojoAssertStringGetSetCustomTest(){
		super(MappedPojoTest::assertStringGetSetCustom, MappedPojoTest::assertStringGetSetCustom,
				DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING);
	}
}
