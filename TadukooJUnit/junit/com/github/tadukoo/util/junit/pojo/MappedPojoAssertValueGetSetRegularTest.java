package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;

public class MappedPojoAssertValueGetSetRegularTest extends MappedPojoAssertValueGetSetTest<String>
		implements DefaultTestValues{
	
	protected MappedPojoAssertValueGetSetRegularTest(){
		super((pojo, key) -> MappedPojoTest.assertValueGetSet(pojo, key, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2),
				(pojo, key, message) ->
						MappedPojoTest.assertValueGetSet(pojo, key, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, message),
				DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, DEFAULT_WRONG_STRING);
	}
}
