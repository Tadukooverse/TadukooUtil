package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;

public class MappedPojoAssertValueGetSetRegularCustomTest extends MappedPojoAssertValueGetSetCustomTest<String>
		implements DefaultTestValues{
	
	protected MappedPojoAssertValueGetSetRegularCustomTest(){
		super((pojo, getter, setter, key) ->
				MappedPojoTest.assertValueGetSetCustom(pojo, getter, setter, key, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2),
				DEFAULT_TEST_KEY, DEFAULT_WRONG_KEY, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING);
	}
}
