package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer4;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer5;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoA;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadClear;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadClearNonEmptyKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadClearNonEmptyMap;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadClearNullKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadClearNullMap;
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
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetter;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadGetter2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasItem;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasItem2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadHasKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadIsEmpty;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomExtraKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomExtraKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomExtraMapKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomExtraMapKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomNullKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomNullKeys2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomNullMap;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomNullMap2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomWrongKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomWrongKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomWrongMapKey;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomWrongMapKey2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomWrongMapValue;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoCustomWrongMapValue2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoHasItemFalse;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoHasItemFalse2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoHasKeyFalse;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoHasKeyFalse2;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNonEmptyKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNonEmptyMap;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNullKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNullMap;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoWrongItem;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoWrongItem2;
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
public abstract class MappedPojoAssertValueGetSetCustomTest<V> implements DefaultTestValues{
	private final ThrowingConsumer4<MappedPojo, ThrowingSupplier<V, NoException>,
			ThrowingConsumer<V, NoException>, String, NoException> assertValueGetSetFunc;
	private final ThrowingConsumer5<MappedPojo, ThrowingSupplier<V, NoException>,
				ThrowingConsumer<V, NoException>, String, String, NoException> assertValueGetSetMsgFunc;
	private static final String defaultCustomMessage = DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE;
	private static final String defaultTestKey = DEFAULT_TEST_KEY;
	private static final String defaultWrongKey = DEFAULT_WRONG_KEY;
	private final V defaultTestValue;
	private final V defaultTestValue2;
	private final V defaultWrongValue;
	
	protected MappedPojoAssertValueGetSetCustomTest(
			ThrowingConsumer4<MappedPojo, ThrowingSupplier<V, NoException>,
					ThrowingConsumer<V, NoException>, String, NoException> assertValueGetSetFunc,
			ThrowingConsumer5<MappedPojo, ThrowingSupplier<V, NoException>,
					ThrowingConsumer<V, NoException>, String, String, NoException> assertValueGetSetMsgFunc,
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
	private final MappedPojoBadClear<V> pojoBadClear = new MappedPojoBadClear<>();
	private final MappedPojoBadClearNullMap<V> pojoBadClearNullMap = new MappedPojoBadClearNullMap<>();
	private final MappedPojoBadClearNonEmptyMap<V> pojoBadClearNonEmptyMap = new MappedPojoBadClearNonEmptyMap<>();
	private final MappedPojoBadClearNullKeys<V> pojoBadClearNullKeys = new MappedPojoBadClearNullKeys<>();
	private final MappedPojoBadClearNonEmptyKeys<V> pojoBadClearNonEmptyKeys = new MappedPojoBadClearNonEmptyKeys<>();
	private final MappedPojoBadGetter<V> pojoBadGetter = new MappedPojoBadGetter<>(){
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoHasKeyFalse<V> pojoHasKeyFalse = new MappedPojoHasKeyFalse<>(){
		@Override
		protected V getDefaultTestValue(){
			return defaultTestValue;
		}
	};
	private final MappedPojoHasItemFalse<V> pojoHasItemFalse = new MappedPojoHasItemFalse<>(){
		@Override
		protected V getDefaultTestValue(){
			return defaultTestValue;
		}
	};
	private final MappedPojoWrongItem<V> pojoWrongItem = new MappedPojoWrongItem<>(){
		@Override
		protected V getDefaultTestValue(){
			return defaultTestValue;
		}
		
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoCustomNullKeys<V> pojoCustomNullKeys = new MappedPojoCustomNullKeys<>();
	private final MappedPojoCustomExtraKey<V> pojoCustomExtraKey = new MappedPojoCustomExtraKey<>();
	private final MappedPojoCustomWrongKey<V> pojoCustomWrongKey = new MappedPojoCustomWrongKey<>();
	private final MappedPojoCustomNullMap<V> pojoCustomNullMap = new MappedPojoCustomNullMap<>();
	private final MappedPojoCustomExtraMapKey<V> pojoCustomExtraMapKey = new MappedPojoCustomExtraMapKey<>();
	private final MappedPojoCustomWrongMapKey<V> pojoCustomWrongMapKey = new MappedPojoCustomWrongMapKey<>();
	private final MappedPojoCustomWrongMapValue<V> pojoCustomWrongMapValue = new MappedPojoCustomWrongMapValue<>(){
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoBadGetter2<V> pojoBadGetter2 = new MappedPojoBadGetter2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
		
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoHasKeyFalse2<V> pojoHasKeyFalse2 = new MappedPojoHasKeyFalse2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoHasItemFalse2<V> pojoHasItemFalse2 = new MappedPojoHasItemFalse2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoWrongItem2<V> pojoWrongItem2 = new MappedPojoWrongItem2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
		
		@Override
		protected V getDefaultWrongValue(){
			return defaultWrongValue;
		}
	};
	private final MappedPojoCustomNullKeys2<V> pojoCustomNullKeys2 = new MappedPojoCustomNullKeys2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoCustomExtraKey2<V> pojoCustomExtraKey2 = new MappedPojoCustomExtraKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoCustomWrongKey2<V> pojoCustomWrongKey2 = new MappedPojoCustomWrongKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoCustomNullMap2<V> pojoCustomNullMap2 = new MappedPojoCustomNullMap2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoCustomExtraMapKey2<V> pojoCustomExtraMapKey2 = new MappedPojoCustomExtraMapKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoCustomWrongMapKey2<V> pojoCustomWrongMapKey2 = new MappedPojoCustomWrongMapKey2<>(){
		@Override
		protected V getDefaultTestValue2(){
			return defaultTestValue2;
		}
	};
	private final MappedPojoCustomWrongMapValue2<V> pojoCustomWrongMapValue2 = new MappedPojoCustomWrongMapValue2<>(){
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
		assertValueGetSetFunc.accept(pojoA, pojoA::getTest, pojoA::setTest, defaultTestKey);
	}
	
	@Test
	public void testAssertValueGetSetFalseIsEmpty(){
		try{
			assertValueGetSetFunc.accept(pojoBadIsEmpty, pojoBadIsEmpty::getTest, pojoBadIsEmpty::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "pojo must be empty for assertValueGetSet!",
					"pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoNullMap, pojoNullMap::getTest, pojoNullMap::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "pojo must be empty for assertValueGetSet!",
					"Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMap(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyMap, pojoNonEmptyMap::getTest, pojoNonEmptyMap::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "pojo must be empty for assertValueGetSet!",
					"Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNullKeys, pojoNullKeys::getTest, pojoNullKeys::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "pojo must be empty for assertValueGetSet!",
					"getKeys() returned null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyKeys, pojoNonEmptyKeys::getTest, pojoNonEmptyKeys::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "pojo must be empty for assertValueGetSet!",
					"getKeys() was not empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey, pojoBadHasKey::getTest, pojoBadHasKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!",
							"pojo is missing key after 1st set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem, pojoBadHasItem::getTest, pojoBadHasItem::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!",
							"pojo is missing item after 1st set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFails(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem, pojoBadGetItem::getTest, pojoBadGetItem::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "value is wrong after 1st set item!",
					buildAssertError(defaultTestValue ,defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys, pojoBadGetKeys::getTest, pojoBadGetKeys::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "keys are null after 1st set item!",
							ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey, pojoBadGetKeysExtraKey::getTest,
					pojoBadGetKeysExtraKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "keys size is wrong after 1st set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey, pojoBadGetKeysWrongKey::getTest,
					pojoBadGetKeysWrongKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "key is wrong after 1st set item!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMapRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapNull, pojoBadGetMapNull::getTest,
					pojoBadGetMapNull::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "map is null after 1st set item!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem, pojoBadGetMapExtraItem::getTest,
					pojoBadGetMapExtraItem::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "map size is wrong after 1st set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey, pojoBadGetMapWrongKey::getTest,
					pojoBadGetMapWrongKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "map is missing key after 1st set item!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue, pojoBadGetMapWrongValue::getTest,
					pojoBadGetMapWrongValue::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "map has wrong value after 1st set item!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey2, pojoBadHasKey2::getTest, pojoBadHasKey2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!",
							"pojo is missing key after 2nd set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem2, pojoBadHasItem2::getTest, pojoBadHasItem2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!",
							"pojo is missing item after 2nd set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem2, pojoBadGetItem2::getTest, pojoBadGetItem2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "value is wrong after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys2, pojoBadGetKeys2::getTest, pojoBadGetKeys2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "keys are null after 2nd set item!",
							ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey2, pojoBadGetKeysExtraKey2::getTest,
					pojoBadGetKeysExtraKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "keys size is wrong after 2nd set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey2, pojoBadGetKeysWrongKey2::getTest,
					pojoBadGetKeysWrongKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "key is wrong after 2nd set item!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetMapNullRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapNull2, pojoBadGetMapNull2::getTest,
					pojoBadGetMapNull2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "map is null after 2nd set item!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem2, pojoBadGetMapExtraItem2::getTest,
					pojoBadGetMapExtraItem2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!", "map size is wrong after 2nd set item!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey2, pojoBadGetMapWrongKey2::getTest,
					pojoBadGetMapWrongKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!",
							"map is missing key after 2nd set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue2, pojoBadGetMapWrongValue2::getTest,
					pojoBadGetMapWrongValue2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("regular get/setItem failed!",
					"map has wrong value after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClear(){
		try{
			assertValueGetSetFunc.accept(pojoBadClear, pojoBadClear::getTest, pojoBadClear::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("clear didn't empty the pojo!", "pojo was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoBadClearNullMap,
					pojoBadClearNullMap::getTest, pojoBadClearNullMap::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("clear didn't empty the pojo!", "Map was null in empty pojo!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNonEmptyMap(){
		try{
			assertValueGetSetFunc.accept(pojoBadClearNonEmptyMap,
					pojoBadClearNonEmptyMap::getTest, pojoBadClearNonEmptyMap::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("clear didn't empty the pojo!", "Map was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoBadClearNullKeys,
					pojoBadClearNullKeys::getTest, pojoBadClearNullKeys::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("clear didn't empty the pojo!", "getKeys() returned null in empty pojo!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNonEmptyKeys(){
		try{
			assertValueGetSetFunc.accept(pojoBadClearNonEmptyKeys,
					pojoBadClearNonEmptyKeys::getTest, pojoBadClearNonEmptyKeys::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("clear didn't empty the pojo!", "getKeys() was not empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetter, pojoBadGetter::getTest, pojoBadGetter::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("value is wrong in custom get on 1st set!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoHasKeyFalse, pojoHasKeyFalse::getTest, pojoHasKeyFalse::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing key after 1st custom set!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoHasItemFalse, pojoHasItemFalse::getTest, pojoHasItemFalse::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing value after 1st custom set!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue(){
		try{
			assertValueGetSetFunc.accept(pojoWrongItem, pojoWrongItem::getTest, pojoWrongItem::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("value is wrong in normal get on 1st custom set!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoCustomNullKeys, pojoCustomNullKeys::getTest, pojoCustomNullKeys::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys are null on 1st custom set!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraKey, pojoCustomExtraKey::getTest, pojoCustomExtraKey::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys size is wrong on 1st custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongKey, pojoCustomWrongKey::getTest, pojoCustomWrongKey::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("key is wrong on 1st custom set!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoCustomNullMap, pojoCustomNullMap::getTest,
					pojoCustomNullMap::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is null on 1st custom set!",
					ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraMapKey, pojoCustomExtraMapKey::getTest,
					pojoCustomExtraMapKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map size is wrong on 1st custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapKey(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongMapKey, pojoCustomWrongMapKey::getTest,
					pojoCustomWrongMapKey::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is missing key on 1st custom set!",
					ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongMapValue, pojoCustomWrongMapValue::getTest,
					pojoCustomWrongMapValue::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map value is wrong on 1st custom set!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetter2, pojoBadGetter2::getTest, pojoBadGetter2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("value is wrong in custom get on 2nd set!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse2(){
		try{
			assertValueGetSetFunc.accept(pojoHasKeyFalse2, pojoHasKeyFalse2::getTest, pojoHasKeyFalse2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing key after 2nd custom set!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse2(){
		try{
			assertValueGetSetFunc.accept(pojoHasItemFalse2, pojoHasItemFalse2::getTest, pojoHasItemFalse2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo is missing value after 2nd custom set!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue2(){
		try{
			assertValueGetSetFunc.accept(pojoWrongItem2, pojoWrongItem2::getTest, pojoWrongItem2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("value is wrong in normal get on 2nd custom set!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomNullKeys2, pojoCustomNullKeys2::getTest, pojoCustomNullKeys2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys are null on 2nd custom set!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraKey2, pojoCustomExtraKey2::getTest, pojoCustomExtraKey2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("keys size is wrong on 2nd custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongKey2, pojoCustomWrongKey2::getTest, pojoCustomWrongKey2::setTest,
					defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("key is wrong on 2nd custom set!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullMap2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomNullMap2, pojoCustomNullMap2::getTest,
					pojoCustomNullMap2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is null on 2nd custom set!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomExtraMapKey2, pojoCustomExtraMapKey2::getTest,
					pojoCustomExtraMapKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map size is wrong on 2nd custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapKey2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongMapKey2, pojoCustomWrongMapKey2::getTest,
					pojoCustomWrongMapKey2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map is missing key on 2nd custom set!", ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue2(){
		try{
			assertValueGetSetFunc.accept(pojoCustomWrongMapValue2, pojoCustomWrongMapValue2::getTest,
					pojoCustomWrongMapValue2::setTest, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("map value is wrong on 2nd custom set!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	/*
	 * Custom Message Method Tests
	 */
	
	@Test
	public void testAssertValueGetSetPassCustomMessage(){
		assertValueGetSetMsgFunc.accept(pojoA, pojoA::getTest, pojoA::setTest, defaultTestKey, defaultCustomMessage);
	}
	
	@Test
	public void testAssertValueGetSetFalseIsEmptyCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadIsEmpty, pojoBadIsEmpty::getTest, pojoBadIsEmpty::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo must be empty for assertValueGetSet!", "pojo was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNullMap, pojoNullMap::getTest, pojoNullMap::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo must be empty for assertValueGetSet!", "Map was null in empty pojo!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNonEmptyMap, pojoNonEmptyMap::getTest, pojoNonEmptyMap::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo must be empty for assertValueGetSet!", "Map was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNullKeys, pojoNullKeys::getTest, pojoNullKeys::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo must be empty for assertValueGetSet!", "getKeys() returned null in empty pojo!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoNonEmptyKeys, pojoNonEmptyKeys::getTest, pojoNonEmptyKeys::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo must be empty for assertValueGetSet!", "getKeys() was not empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasKey, pojoBadHasKey::getTest, pojoBadHasKey::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo is missing key after 1st set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasItem, pojoBadHasItem::getTest, pojoBadHasItem::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo is missing item after 1st set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetItem, pojoBadGetItem::getTest, pojoBadGetItem::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"value is wrong after 1st set item!", buildAssertError(defaultTestValue ,defaultWrongValue)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeys, pojoBadGetKeys::getTest, pojoBadGetKeys::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"keys are null after 1st set item!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysExtraKey, pojoBadGetKeysExtraKey::getTest,
					pojoBadGetKeysExtraKey::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"keys size is wrong after 1st set item!", buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysWrongKey, pojoBadGetKeysWrongKey::getTest,
					pojoBadGetKeysWrongKey::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"key is wrong after 1st set item!", buildAssertError(defaultTestKey, defaultWrongKey)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMapRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapNull, pojoBadGetMapNull::getTest,
					pojoBadGetMapNull::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map is null after 1st set item!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapExtraItem, pojoBadGetMapExtraItem::getTest,
					pojoBadGetMapExtraItem::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map size is wrong after 1st set item!", buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongKey, pojoBadGetMapWrongKey::getTest,
					pojoBadGetMapWrongKey::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map is missing key after 1st set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongValue, pojoBadGetMapWrongValue::getTest,
					pojoBadGetMapWrongValue::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map has wrong value after 1st set item!", buildAssertError(defaultTestValue, defaultWrongValue)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasKey2, pojoBadHasKey2::getTest, pojoBadHasKey2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo is missing key after 2nd set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadHasItem2, pojoBadHasItem2::getTest, pojoBadHasItem2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"pojo is missing item after 2nd set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetItem2, pojoBadGetItem2::getTest, pojoBadGetItem2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"value is wrong after 2nd set item!", buildAssertError(defaultTestValue2, defaultWrongValue)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeys2, pojoBadGetKeys2::getTest, pojoBadGetKeys2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"keys are null after 2nd set item!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysExtraKey2, pojoBadGetKeysExtraKey2::getTest,
					pojoBadGetKeysExtraKey2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"keys size is wrong after 2nd set item!", buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetKeysWrongKey2, pojoBadGetKeysWrongKey2::getTest,
					pojoBadGetKeysWrongKey2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"key is wrong after 2nd set item!", buildAssertError(defaultTestKey, defaultWrongKey)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetMapNullRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapNull2, pojoBadGetMapNull2::getTest,
					pojoBadGetMapNull2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map is null after 2nd set item!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapExtraItem2, pojoBadGetMapExtraItem2::getTest,
					pojoBadGetMapExtraItem2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map size is wrong after 2nd set item!", buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongKey2, pojoBadGetMapWrongKey2::getTest,
					pojoBadGetMapWrongKey2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map is missing key after 2nd set item!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetMapWrongValue2, pojoBadGetMapWrongValue2::getTest,
					pojoBadGetMapWrongValue2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "regular get/setItem failed!",
					"map has wrong value after 2nd set item!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadClear, pojoBadClear::getTest, pojoBadClear::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "clear didn't empty the pojo!",
					"pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNullMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadClearNullMap,
					pojoBadClearNullMap::getTest, pojoBadClearNullMap::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "clear didn't empty the pojo!",
					"Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNonEmptyMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadClearNonEmptyMap,
					pojoBadClearNonEmptyMap::getTest, pojoBadClearNonEmptyMap::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "clear didn't empty the pojo!",
					"Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNullKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadClearNullKeys,
					pojoBadClearNullKeys::getTest, pojoBadClearNullKeys::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "clear didn't empty the pojo!",
					"getKeys() returned null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadClearNonEmptyKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadClearNonEmptyKeys,
					pojoBadClearNonEmptyKeys::getTest, pojoBadClearNonEmptyKeys::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "clear didn't empty the pojo!",
					"getKeys() was not empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetterCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetter, pojoBadGetter::getTest, pojoBadGetter::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "value is wrong in custom get on 1st set!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalseCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoHasKeyFalse, pojoHasKeyFalse::getTest, pojoHasKeyFalse::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "pojo is missing key after 1st custom set!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalseCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoHasItemFalse, pojoHasItemFalse::getTest, pojoHasItemFalse::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "pojo is missing value after 1st custom set!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValueCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoWrongItem, pojoWrongItem::getTest, pojoWrongItem::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "value is wrong in normal get on 1st custom set!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeysCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomNullKeys, pojoCustomNullKeys::getTest, pojoCustomNullKeys::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "keys are null on 1st custom set!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKeyCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomExtraKey, pojoCustomExtraKey::getTest, pojoCustomExtraKey::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "keys size is wrong on 1st custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKeyCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomWrongKey, pojoCustomWrongKey::getTest, pojoCustomWrongKey::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "key is wrong on 1st custom set!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullMapCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomNullMap, pojoCustomNullMap::getTest,
					pojoCustomNullMap::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map is null on 1st custom set!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKeyCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomExtraMapKey, pojoCustomExtraMapKey::getTest,
					pojoCustomExtraMapKey::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map size is wrong on 1st custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapKeyCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomWrongMapKey, pojoCustomWrongMapKey::getTest,
					pojoCustomWrongMapKey::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map is missing key on 1st custom set!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValueCustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomWrongMapValue, pojoCustomWrongMapValue::getTest,
					pojoCustomWrongMapValue::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map value is wrong on 1st custom set!",
					buildAssertError(defaultTestValue, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetBadGetter2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoBadGetter2, pojoBadGetter2::getTest, pojoBadGetter2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "value is wrong in custom get on 2nd set!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasKeyFalse2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoHasKeyFalse2, pojoHasKeyFalse2::getTest, pojoHasKeyFalse2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "pojo is missing key after 2nd custom set!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomHasItemFalse2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoHasItemFalse2, pojoHasItemFalse2::getTest, pojoHasItemFalse2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "pojo is missing value after 2nd custom set!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongValue2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoWrongItem2, pojoWrongItem2::getTest, pojoWrongItem2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "value is wrong in normal get on 2nd custom set!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullKeys2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomNullKeys2, pojoCustomNullKeys2::getTest, pojoCustomNullKeys2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "keys are null on 2nd custom set!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraKey2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomExtraKey2, pojoCustomExtraKey2::getTest, pojoCustomExtraKey2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "keys size is wrong on 2nd custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongKey2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomWrongKey2, pojoCustomWrongKey2::getTest, pojoCustomWrongKey2::setTest,
					defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "key is wrong on 2nd custom set!",
					buildAssertError(defaultTestKey, defaultWrongKey)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomNullMap2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomNullMap2, pojoCustomNullMap2::getTest,
					pojoCustomNullMap2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map is null on 2nd custom set!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomExtraMapKey2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomExtraMapKey2, pojoCustomExtraMapKey2::getTest,
					pojoCustomExtraMapKey2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map size is wrong on 2nd custom set!",
					buildAssertError(1, 2)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapKey2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomWrongMapKey2, pojoCustomWrongMapKey2::getTest,
					pojoCustomWrongMapKey2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map is missing key on 2nd custom set!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetCustomWrongMapValue2CustomMessage(){
		try{
			assertValueGetSetMsgFunc.accept(pojoCustomWrongMapValue2, pojoCustomWrongMapValue2::getTest,
					pojoCustomWrongMapValue2::setTest, defaultTestKey, defaultCustomMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomMessage, "map value is wrong on 2nd custom set!",
					buildAssertError(defaultTestValue2, defaultWrongValue)), e.getMessage());
		}
	}
}
