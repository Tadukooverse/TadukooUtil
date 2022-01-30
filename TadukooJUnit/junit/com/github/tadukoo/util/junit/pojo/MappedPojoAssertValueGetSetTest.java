package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoA;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetItem;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetItem2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetKeys2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetKeysExtraKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetKeysExtraKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetKeysWrongKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetKeysWrongKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapExtraItem;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapExtraItem2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapWrongKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapWrongKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapWrongValue;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapWrongValue2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasItem;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasItem2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadIsEmpty;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNonEmptyKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNonEmptyMap;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNullKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNullMap;
import com.github.tadukoo.util.pojo.MappedPojo;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_NOT_NULL_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class is used to test out assertion functions that are extending
 * {@link MappedPojoTest#assertValueGetSet(MappedPojo, String, Object, Object)} to verify that they function properly.
 *
 * @param <V> The type of value being tested
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public abstract class MappedPojoAssertValueGetSetTest<V>{
	private final ThrowingConsumer2<MappedPojo, String, NoException> assertValueGetSetFunc;
	private final String defaultTestKey;
	private final String defaultWrongKey;
	private final V defaultTestValue;
	private final V defaultTestValue2;
	private final V defaultWrongValue;
	
	protected MappedPojoAssertValueGetSetTest(
			ThrowingConsumer2<MappedPojo, String, NoException> assertValueGetSetFunc,
			String defaultTestKey, String defaultWrongKey,
			V defaultTestValue, V defaultTestValue2, V defaultWrongValue){
		this.assertValueGetSetFunc = assertValueGetSetFunc;
		this.defaultTestKey = defaultTestKey;
		this.defaultWrongKey = defaultWrongKey;
		this.defaultTestValue = defaultTestValue;
		this.defaultTestValue2 = defaultTestValue2;
		this.defaultWrongValue = defaultWrongValue;
	}
	
	private final MappedPojoA<V> pojoA = new MappedPojoA<>();
	private final MappedPojoBadIsEmpty<V> pojoBadIsEmpty = new MappedPojoBadIsEmpty<>();
	private final MappedPojoNullMap<V> pojoNullMap = new MappedPojoNullMap<>();
	private final MappedPojoNonEmptyMap<V> pojoNonEmptyMap = new MappedPojoNonEmptyMap<>();
	private final MappedPojoNullKeys<V> pojoNullKeys = new MappedPojoNullKeys<>();
	private final MappedPojoNonEmptyKeys<V> pojoNonEmptyKeys = new MappedPojoNonEmptyKeys<>();
	private final MappedPojoBadHasKey<V> pojoBadHasKey = new MappedPojoBadHasKey<>();
	private final MappedPojoBadHasItem<V> pojoBadHasItem = new MappedPojoBadHasItem<>();
	private final MappedPojoBadGetItem<V> pojoBadGetItem = new MappedPojoBadGetItem<>(){
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoBadGetKeys<V> pojoBadGetKeys = new MappedPojoBadGetKeys<>();
	private final MappedPojoBadGetKeysExtraKey<V> pojoBadGetKeysExtraKey = new MappedPojoBadGetKeysExtraKey<>();
	private final MappedPojoBadGetKeysWrongKey<V> pojoBadGetKeysWrongKey = new MappedPojoBadGetKeysWrongKey<>();
	private final MappedPojoBadGetMapExtraItem<V> pojoBadGetMapExtraItem = new MappedPojoBadGetMapExtraItem<>();
	private final MappedPojoBadGetMapWrongKey<V> pojoBadGetMapWrongKey = new MappedPojoBadGetMapWrongKey<>();
	private final MappedPojoBadGetMapWrongValue<V> pojoBadGetMapWrongValue = new MappedPojoBadGetMapWrongValue<>(){
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoBadHasKey2<V> pojoBadHasKey2 = new MappedPojoBadHasKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadHasItem2<V> pojoBadHasItem2 = new MappedPojoBadHasItem2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadGetItem2<V> pojoBadGetItem2 = new MappedPojoBadGetItem2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
		
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoBadGetKeys2<V> pojoBadGetKeys2 = new MappedPojoBadGetKeys2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadGetKeysExtraKey2<V> pojoBadGetKeysExtraKey2 = new MappedPojoBadGetKeysExtraKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadGetKeysWrongKey2<V> pojoBadGetKeysWrongKey2 = new MappedPojoBadGetKeysWrongKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadGetMapExtraItem2<V> pojoBadGetMapExtraItem2 = new MappedPojoBadGetMapExtraItem2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadGetMapWrongKey2<V> pojoBadGetMapWrongKey2 = new MappedPojoBadGetMapWrongKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoBadGetMapWrongValue2<V> pojoBadGetMapWrongValue2 = new MappedPojoBadGetMapWrongValue2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
		
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	
	@Test
	public void testAssertValueGetSetPass(){
		assertValueGetSetFunc.accept(pojoA, defaultTestKey);
	}
	
	@Test
	public void testAssertValueGetSetFalseIsEmpty(){
		try{
			assertValueGetSetFunc.accept(pojoBadIsEmpty, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoNullMap, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMap(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyMap, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNullKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getKeys() returned null in empty pojo!",
					ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getKeys() was not empty in empty pojo!",
					ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFails(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(defaultTestValue ,defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(defaultTestValue, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
}
