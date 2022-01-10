package com.github.tadukoo.util.junit;

import com.github.tadukoo.util.junit.constant.DefaultTestValues;

public class MappedPojoAssertStringGetSetTest extends MappedPojoAssertValueGetSetTest<String>
		implements DefaultTestValues{
	
	public MappedPojoAssertStringGetSetTest(){
		super(MappedPojoTest::assertStringGetSet, DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY,
				DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING);
	}
}
