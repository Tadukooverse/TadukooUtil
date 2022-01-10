package com.github.tadukoo.util.junit;

import com.github.tadukoo.util.junit.constant.DefaultTestValues;

public class MappedPojoAssertStringGetSetCustomTest extends MappedPojoAssertValueGetSetCustomTest<String>
		implements DefaultTestValues{
	
	public MappedPojoAssertStringGetSetCustomTest(){
		super(MappedPojoTest::assertStringGetSetCustom, DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY,
				DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING);
	}
}
