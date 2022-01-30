package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer3;
import com.github.tadukoo.util.junit.DefaultTestValues;
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
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapNull;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetMapNull2;
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
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
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
public abstract class MappedPojoAssertValueGetSetTest<V> implements DefaultTestValues{
	private final ThrowingConsumer2<MappedPojo, String, NoException> assertValueGetSetFunc;
	private final ThrowingConsumer3<MappedPojo, String, String, NoException> assertValueGetSetMsgFunc;
	private final static String defaultCustomErrorMsg = DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE;
	private final static String defaultTestKey = DEFAULT_TEST_KEY;
	private final static String defaultWrongKey = DEFAULT_WRONG_KEY;
	private final V defaultTestValue;
	private final V defaultTestValue2;
	private final V defaultWrongValue;
	
	protected MappedPojoAssertValueGetSetTest(
			ThrowingConsumer2<MappedPojo, String, NoException> assertValueGetSetFunc,
			ThrowingConsumer3<MappedPojo, String, String, NoException> assertValueGetSetMsgFunc,
			V defaultTestValue, V defaultTestValue2, V defaultWrongValue){
		this.assertValueGetSetFunc = assertValueGetSetFunc;
		this.assertValueGetSetMsgFunc = assertValueGetSetMsgFunc;
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
	private final MappedPojoBadGetMapNull<V> pojoBadGetMapNull = new MappedPojoBadGetMapNull<>();
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
	private final MappedPojoBadGetMapNull2<V> pojoBadGetMapNull2 = new MappedPojoBadGetMapNull2<>(){
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
	
	/*
	 * Regular Method Tests
	 */
	
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
			assertEquals(buildMultiPartError("pojo must be empty for assertValueGetSet!",
					"pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoNullMap, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("pojo must be empty for assertValueGetSet!",
					"Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMap(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyMap, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("pojo must be empty for assertValueGetSet!",
					"Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNullKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("pojo must be empty for assertValueGetSet!",
					"getKeys() returned null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("pojo must be empty for assertValueGetSet!",
					"getKeys() was not empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing key after 1st set item!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing item after 1st set item!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFails(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("value is wrong after 1st set item!",
					buildAssertError(defaultTestValue ,defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys are null after 1st set item!", ASSERT_NOT_NULL_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys size is wrong after 1st set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("key is wrong after 1st set item!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMapRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapNull, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is null after 1st set item!",
					ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map size is wrong after 1st set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is missing key after 1st set item!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map has wrong value after 1st set item!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing key after 2nd set item!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing item after 2nd set item!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("value is wrong after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys are null after 2nd set item!", ASSERT_NOT_NULL_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys size is wrong after 2nd set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("key is wrong after 2nd set item!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetMapNullRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapNull2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is null after 2nd set item!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map size is wrong after 2nd set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is missing key after 2nd set item!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map has wrong value after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	/*
	 * Custom Message Method Tests
	 */
	
	@Test
	public void testAssertValueGetSetPassCustomMessage(){
		assertValueGetSetMsgFunc.accept(pojoA, defaultTestKey, defaultCustomErrorMsg);
	}
	
	@Test
	public void testAssertValueGetSetFalseIsEmptyCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadIsEmpty, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo must be empty for assertValueGetSet!",
					"pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNullMap, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo must be empty for assertValueGetSet!",
					"Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNonEmptyMap, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo must be empty for assertValueGetSet!",
					"Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNullKeys, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo must be empty for assertValueGetSet!",
					"getKeys() returned null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNonEmptyKeys, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo must be empty for assertValueGetSet!",
					"getKeys() was not empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasKey, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo is missing key after 1st set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasItem, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo is missing item after 1st set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetItem, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "value is wrong after 1st set item!",
					buildAssertError(defaultTestValue ,defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeys, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "keys are null after 1st set item!",
							ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysExtraKey, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "keys size is wrong after 1st set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysWrongKey, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "key is wrong after 1st set item!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMapRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapNull, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map is null after 1st set item!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapExtraItem, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map size is wrong after 1st set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongKey, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map is missing key after 1st set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongValue, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map has wrong value after 1st set item!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasKey2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo is missing key after 2nd set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasItem2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "pojo is missing item after 2nd set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetItem2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "value is wrong after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeys2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "keys are null after 2nd set item!",
							ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysExtraKey2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "keys size is wrong after 2nd set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysWrongKey2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "key is wrong after 2nd set item!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetMapNullRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapNull2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map is null after 2nd set item!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapExtraItem2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map size is wrong after 2nd set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongKey2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map is missing key after 2nd set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongValue2, defaultTestKey, defaultCustomErrorMsg);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomErrorMsg, "map has wrong value after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
}
