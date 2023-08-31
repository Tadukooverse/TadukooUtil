package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;

public class MappedPojoAssertStringGetSetTest extends MappedPojoAssertValueGetSetTest<String>
		implements DefaultTestValues{
	
	public MappedPojoAssertStringGetSetTest(){
		super(MappedPojoTest::assertStringGetSet, MappedPojoTest::assertStringGetSet,
				DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING);
	}
}
