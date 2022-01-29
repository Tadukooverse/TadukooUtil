package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.junit.AssertionFailedErrors;
import com.github.tadukoo.util.map.MapUtil;
import com.github.tadukoo.util.pojo.MappedPojo;
import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	
	/**
	 * A good {@link MappedPojo} to use for passing tests
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.1
	 */
	private static class MappedPojoA implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoA(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadIsEmpty implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadIsEmpty(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean isEmpty(){
			return false;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoNullMap implements MappedPojo{
		
		public MappedPojoNullMap(){ }
		
		@Override
		public Map<String, Object> getMap(){
			return null;
		}
	}
	
	private class MappedPojoNonEmptyMap implements MappedPojo{
		private final Map<String, Object> map = MapUtil.createMap(Pair.of(defaultWrongKey, defaultWrongValue));
		
		public MappedPojoNonEmptyMap(){ }
		
		@Override
		public boolean isEmpty(){
			return true;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoNullKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoNullKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			return null;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoNonEmptyKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoNonEmptyKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>();
			keys.add(defaultWrongKey);
			return keys;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadHasKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasKey(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			return false;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadHasItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasItem(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			return false;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetItem(){
			map = new HashMap<>();
		}
		
		@Override
		public Object getItem(String key){
			return defaultWrongValue;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadGetKeys implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeys(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			if(!keys.isEmpty()){
				return null;
			}else{
				return keys;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysExtraKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysExtraKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			if(!keys.isEmpty()){
				keys.add(defaultWrongKey);
			}
			return keys;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysWrongKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			if(!keys.isEmpty()){
				Set<String> newKeys = new HashSet<>();
				newKeys.add(defaultWrongKey);
				return newKeys;
			}else{
				return map.keySet();
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetMapExtraItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapExtraItem(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongKey(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.remove(defaultTestKey);
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongValue implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongValue(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			if(!map.isEmpty()){
				map.put(defaultTestKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadHasKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasKey(String key){
			Object val = map.get(key);
			if(val.equals(defaultTestValue2)){
				return false;
			}else{
				return map.containsKey(key);
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadHasItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadHasItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public boolean hasItem(String key){
			Object val = map.get(key);
			if(val.equals(defaultTestValue2)){
				return false;
			}else{
				return map.containsKey(key) && map.get(key) != null;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public Object getItem(String key){
			Object val = map.get(key);
			if(val.equals(defaultTestValue2)){
				return defaultWrongValue;
			}else{
				return map.get(key);
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeys2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeys2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			Object val = map.get(defaultTestKey);
			if(!keys.isEmpty() && val.equals(defaultTestValue2)){
				return null;
			}else{
				return keys;
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysExtraKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysExtraKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			Object val = map.get(defaultTestKey);
			if(!keys.isEmpty() && val.equals(defaultTestValue2)){
				keys.add(defaultWrongKey);
			}
			return keys;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetKeysWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetKeysWrongKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = map.keySet();
			Object val = map.get(defaultTestKey);
			if(!keys.isEmpty() && val.equals(defaultTestValue2)){
				Set<String> newKeys = new HashSet<>();
				newKeys.add(defaultWrongKey);
				return newKeys;
			}else{
				return map.keySet();
			}
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private class MappedPojoBadGetMapExtraItem2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapExtraItem2(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(defaultTestKey);
			if(!map.isEmpty() && val.equals(defaultTestValue2)){
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongKey2(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(defaultTestKey);
			if(!map.isEmpty() && val.equals(defaultTestValue2)){
				map.remove(defaultTestKey);
				map.put(defaultWrongKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private class MappedPojoBadGetMapWrongValue2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadGetMapWrongValue2(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			Object val = map.get(defaultTestKey);
			if(!map.isEmpty() && val.equals(defaultTestValue2)){
				map.put(defaultTestKey, defaultWrongValue);
			}
			return map;
		}
	}
	
	private final MappedPojoA pojoA = new MappedPojoA();
	private final MappedPojoBadIsEmpty pojoBadIsEmpty = new MappedPojoBadIsEmpty();
	private final MappedPojoNullMap pojoNullMap = new MappedPojoNullMap();
	private final MappedPojoNonEmptyMap pojoNonEmptyMap = new MappedPojoNonEmptyMap();
	private final MappedPojoNullKeys pojoNullKeys = new MappedPojoNullKeys();
	private final MappedPojoNonEmptyKeys pojoNonEmptyKeys = new MappedPojoNonEmptyKeys();
	private final MappedPojoBadHasKey pojoBadHasKey = new MappedPojoBadHasKey();
	private final MappedPojoBadHasItem pojoBadHasItem = new MappedPojoBadHasItem();
	private final MappedPojoBadGetItem pojoBadGetItem = new MappedPojoBadGetItem();
	private final MappedPojoBadGetKeys pojoBadGetKeys = new MappedPojoBadGetKeys();
	private final MappedPojoBadGetKeysExtraKey pojoBadGetKeysExtraKey = new MappedPojoBadGetKeysExtraKey();
	private final MappedPojoBadGetKeysWrongKey pojoBadGetKeysWrongKey = new MappedPojoBadGetKeysWrongKey();
	private final MappedPojoBadGetMapExtraItem pojoBadGetMapExtraItem = new MappedPojoBadGetMapExtraItem();
	private final MappedPojoBadGetMapWrongKey pojoBadGetMapWrongKey = new MappedPojoBadGetMapWrongKey();
	private final MappedPojoBadGetMapWrongValue pojoBadGetMapWrongValue = new MappedPojoBadGetMapWrongValue();
	private final MappedPojoBadHasKey2 pojoBadHasKey2 = new MappedPojoBadHasKey2();
	private final MappedPojoBadHasItem2 pojoBadHasItem2 = new MappedPojoBadHasItem2();
	private final MappedPojoBadGetItem2 pojoBadGetItem2 = new MappedPojoBadGetItem2();
	private final MappedPojoBadGetKeys2 pojoBadGetKeys2 = new MappedPojoBadGetKeys2();
	private final MappedPojoBadGetKeysExtraKey2 pojoBadGetKeysExtraKey2 = new MappedPojoBadGetKeysExtraKey2();
	private final MappedPojoBadGetKeysWrongKey2 pojoBadGetKeysWrongKey2 = new MappedPojoBadGetKeysWrongKey2();
	private final MappedPojoBadGetMapExtraItem2 pojoBadGetMapExtraItem2 = new MappedPojoBadGetMapExtraItem2();
	private final MappedPojoBadGetMapWrongKey2 pojoBadGetMapWrongKey2 = new MappedPojoBadGetMapWrongKey2();
	private final MappedPojoBadGetMapWrongValue2 pojoBadGetMapWrongValue2 = new MappedPojoBadGetMapWrongValue2();
	
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
			assertEquals(AssertionFailedErrors.buildTwoPartError("pojo was non-empty in empty pojo!", AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullMap(){
		try{
			assertValueGetSetFunc.accept(pojoNullMap, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("Map was null in empty pojo!", AssertionFailedErrors.ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyMap(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyMap, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("Map was non-empty in empty pojo!", AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNullKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("getKeys() returned null in empty pojo!",
					AssertionFailedErrors.ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNonEmptyKeys(){
		try{
			assertValueGetSetFunc.accept(pojoNonEmptyKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildTwoPartError("getKeys() was not empty in empty pojo!",
					AssertionFailedErrors.ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalse(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFails(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue ,defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun1(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasKeyFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetHasItemFalseRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadHasItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetGetItemFailsRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetNullKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeys2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraKeysRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysExtraKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetKeysWrongKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestKey, defaultWrongKey), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetExtraItemRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapExtraItem2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(1, 2), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapKeyRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongKey2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.ASSERT_TRUE_ERROR.toString(), e.getMessage());
		}
	}
	
	@Test
	public void testAssertValueGetSetWrongMapValueRun2(){
		try{
			assertValueGetSetFunc.accept(pojoBadGetMapWrongValue2, defaultTestKey);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(AssertionFailedErrors.buildAssertError(defaultTestValue2, defaultWrongValue), e.getMessage());
		}
	}
}
