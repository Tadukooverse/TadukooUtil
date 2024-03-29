package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.junit.pojo.testpojos.BadMappedPojoB;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoA;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoBadIsEmpty;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNonEmptyKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNonEmptyMap;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNullKeys;
import com.github.tadukoo.util.junit.pojo.testpojos.MappedPojoNullMap;
import com.github.tadukoo.util.pojo.MappedPojo;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_FALSE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_NOT_NULL_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static com.github.tadukoo.util.junit.pojo.MappedPojoTest.assertEmptyConstructor;
import static com.github.tadukoo.util.junit.pojo.MappedPojoTest.assertEmptyPojo;
import static com.github.tadukoo.util.junit.pojo.MappedPojoTest.assertPojoConstructor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MappedPojoTestTest implements DefaultTestValues{
	
	private static class BadMappedPojoA implements MappedPojo{
		private final Map<String, Object> map;
		
		public BadMappedPojoA(MappedPojo pojo){
			map = pojo.getMap();
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class BadMappedPojoA2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public BadMappedPojoA2(){
			map = new HashMap<>();
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class BadMappedPojoC implements MappedPojo{
		
		@SuppressWarnings("unused")
		public BadMappedPojoC(){
			throw new IllegalArgumentException("Me a bad boy");
		}
		
		@SuppressWarnings("unused")
		public BadMappedPojoC(MappedPojo pojo){
			throw new IllegalArgumentException("Me a bad boy");
		}
		
		@Override
		public Map<String, Object> getMap(){
			return null;
		}
	}
	
	private static abstract class AbstractMappedPojoA implements MappedPojo{
		
		@SuppressWarnings("unused")
		public AbstractMappedPojoA(){ }
		
		@SuppressWarnings("unused")
		public AbstractMappedPojoA(MappedPojo pojo){ }
	}
	
	private static class MappedPojoBadValue1 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadValue1(MappedPojo pojo){
			map = pojo.getMap();
			map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_DOUBLE);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoBadValue2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoBadValue2(MappedPojo pojo){
			map = pojo.getMap();
			map.put(DEFAULT_TEST_KEY_2, DEFAULT_WRONG_STRING);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoConstructorNullMap implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorNullMap(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return null;
		}
	}
	
	private static class MappedPojoConstructorEmptyMap implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorEmptyMap(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			return new HashMap<>();
		}
	}
	
	private static class MappedPojoConstructorMapExtraItem implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorMapExtraItem(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			return map;
		}
	}
	
	private static class MappedPojoConstructorMapMissingTestKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorMapMissingTestKey(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			map.remove(DEFAULT_TEST_KEY);
			map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			return map;
		}
	}
	
	private static class MappedPojoConstructorMapWrongTestValue implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorMapWrongTestValue(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			map.put(DEFAULT_TEST_KEY, DEFAULT_WRONG_DOUBLE);
			return map;
		}
	}
	
	private static class MappedPojoConstructorMapMissingTestKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorMapMissingTestKey2(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			map.remove(DEFAULT_TEST_KEY_2);
			map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
			return map;
		}
	}
	
	private static class MappedPojoConstructorMapWrongTest2Value implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorMapWrongTest2Value(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Object getItem(String key){
			return map.get(key);
		}
		
		@Override
		public Map<String, Object> getMap(){
			map.put(DEFAULT_TEST_KEY_2, DEFAULT_WRONG_STRING);
			return map;
		}
	}
	
	private static class MappedPojoConstructorKeysWrongSize implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorKeysWrongSize(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			keys.remove(DEFAULT_TEST_KEY);
			return keys;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoConstructorKeysMissingTestKey implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorKeysMissingTestKey(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			keys.remove(DEFAULT_TEST_KEY);
			keys.add(DEFAULT_WRONG_KEY);
			return keys;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private static class MappedPojoConstructorKeysMissingTestKey2 implements MappedPojo{
		private final Map<String, Object> map;
		
		public MappedPojoConstructorKeysMissingTestKey2(MappedPojo pojo){
			this.map = pojo.getMap();
		}
		
		@Override
		public Set<String> getKeys(){
			Set<String> keys = new HashSet<>(map.keySet());
			keys.remove(DEFAULT_TEST_KEY_2);
			keys.add(DEFAULT_WRONG_KEY);
			return keys;
		}
		
		@Override
		public Map<String, Object> getMap(){
			return map;
		}
	}
	
	private final MappedPojoA<String> pojoA = new MappedPojoA<>();
	private final MappedPojoBadIsEmpty<String> pojoBadIsEmpty = new MappedPojoBadIsEmpty<>();
	private final MappedPojoNullMap<String> pojoNullMap = new MappedPojoNullMap<>();
	private final MappedPojoNonEmptyMap<String> pojoNonEmptyMap = new MappedPojoNonEmptyMap<>();
	private final MappedPojoNullKeys<String> pojoNullKeys = new MappedPojoNullKeys<>();
	private final MappedPojoNonEmptyKeys<String> pojoNonEmptyKeys = new MappedPojoNonEmptyKeys<>();
	
	/*
	 * Assert Empty Constructor
	 */
	
	@Test
	public void testAssertEmptyConstructorPass()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		assertEmptyConstructor(MappedPojoA.class);
	}
	
	@Test
	public void testAssertEmptyConstructorMissingMethod()
			throws InvocationTargetException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(BadMappedPojoA.class);
			fail();
		}catch(NoSuchMethodException e){
			assertEquals("com.github.tadukoo.util.junit.pojo.MappedPojoTestTest$BadMappedPojoA.<init>()",
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorPrivateMethod()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException{
		try{
			assertEmptyConstructor(BadMappedPojoB.class);
			fail();
		}catch(IllegalAccessException e){
			assertEquals("class " + MappedPojoTest.class.getCanonicalName() + " cannot access a member of " +
					"class " + BadMappedPojoB.class.getCanonicalName() + " with modifiers \"private\"", e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorThrows()
			throws NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(BadMappedPojoC.class);
			fail();
		}catch(InvocationTargetException e){
			assertNull(e.getMessage());
			Throwable t = e.getTargetException();
			assertTrue(t instanceof IllegalArgumentException);
			assertEquals("Me a bad boy", t.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorAbstract()
			throws InvocationTargetException, NoSuchMethodException, IllegalAccessException{
		try{
			assertEmptyConstructor(AbstractMappedPojoA.class);
			fail();
		}catch(InstantiationException e){
			assertNull(e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorFalseIsEmpty()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoBadIsEmpty.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("New pojo is not empty!", "pojo was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNullMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("New pojo is not empty!", "Map was null in empty pojo!",
					ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNonEmptyMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("New pojo is not empty!", "Map was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNullKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("New pojo is not empty!", "getKeys() returned null in empty pojo!",
							ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNonEmptyKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError("New pojo is not empty!", "getKeys() was not empty in empty pojo!",
							ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	/*
	 * Assert Empty Constructor Custom Message
	 */
	
	@Test
	public void testAssertEmptyConstructorPassCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		assertEmptyConstructor(MappedPojoA.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertEmptyConstructorFalseIsEmptyCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoBadIsEmpty.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "New pojo is not empty!",
					"pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullMapCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNullMap.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "New pojo is not empty!",
					"Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyMapCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNonEmptyMap.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "New pojo is not empty!",
					"Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullKeysCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNullKeys.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "New pojo is not empty!",
					"getKeys() returned null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyKeysCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNonEmptyKeys.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE, "New pojo is not empty!",
					"getKeys() was not empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	/*
	 * Assert Pojo Constructor
	 */
	
	@Test
	public void testAssertPojoConstructorPass()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		assertPojoConstructor(MappedPojoA.class);
	}
	
	@Test
	public void testAssertPojoConstructorMissingMethod()
			throws InvocationTargetException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(BadMappedPojoA2.class);
			fail();
		}catch(NoSuchMethodException e){
			assertEquals("com.github.tadukoo.util.junit.pojo.MappedPojoTestTest$BadMappedPojoA2.<init>" +
					"(com.github.tadukoo.util.pojo.MappedPojo)", e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorPrivateMethod()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException{
		try{
			assertPojoConstructor(BadMappedPojoB.class);
			fail();
		}catch(IllegalAccessException e){
			assertEquals("class " + MappedPojoTest.class.getCanonicalName() + " cannot access a member of " +
					"class " + BadMappedPojoB.class.getCanonicalName() + " with modifiers \"private\"", e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorThrows()
			throws NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(BadMappedPojoC.class);
			fail();
		}catch(InvocationTargetException e){
			assertNull(e.getMessage());
			Throwable t = e.getTargetException();
			assertTrue(t instanceof IllegalArgumentException);
			assertEquals("Me a bad boy", t.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorAbstract()
			throws InvocationTargetException, NoSuchMethodException, IllegalAccessException{
		try{
			assertPojoConstructor(AbstractMappedPojoA.class);
			fail();
		}catch(InstantiationException e){
			assertNull(e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorBadValue1()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoBadValue1.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Double value not right in pojo!",
							buildAssertError(DEFAULT_TEST_DOUBLE, DEFAULT_WRONG_DOUBLE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorBadValue2()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoBadValue2.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("String value not right in pojo!",
							buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorNullMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorNullMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map is null in populated pojo!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorEmptyMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorEmptyMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map is empty in populated pojo!", ASSERT_FALSE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorWrongSizeMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapExtraItem.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map size is wrong in populated pojo!", buildAssertError(2, 3)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorMissingTestKey()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapMissingTestKey.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Double key not found in populated pojo!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorWrongTestValue()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapWrongTestValue.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Double value not right in populated pojo map!",
							buildAssertError(DEFAULT_TEST_DOUBLE, DEFAULT_WRONG_DOUBLE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorMissingTestKey2()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapMissingTestKey2.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("String key not found in populated pojo!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorWrongTest2Value()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapWrongTest2Value.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("String value not right in populated pojo map!",
					buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorNullKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoNullKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Keys are null in populated pojo!", ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorKeysWrongSize()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorKeysWrongSize.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Keys size is wrong in populated pojo!", buildAssertError(2, 1)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorKeysMissingTestKey()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorKeysMissingTestKey.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Keys missing Double key in populated pojo!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorKeysMissingTestKey2()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorKeysMissingTestKey2.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Keys missing String key in populated pojo!", ASSERT_TRUE_ERROR),
					e.getMessage());
		}
	}
	
	/*
	 * Assert Pojo Constructor Custom Message
	 */
	
	@Test
	public void testAssertPojoConstructorPassCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		assertPojoConstructor(MappedPojoA.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertPojoConstructorBadValue1CustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoBadValue1.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Double value not right in pojo!", buildAssertError(DEFAULT_TEST_DOUBLE, DEFAULT_WRONG_DOUBLE)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorBadValue2CustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoBadValue2.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"String value not right in pojo!", buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorNullMapCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorNullMap.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Map is null in populated pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorEmptyMapCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorEmptyMap.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Map is empty in populated pojo!", ASSERT_FALSE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorWrongSizeMapCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapExtraItem.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
							"Map size is wrong in populated pojo!", buildAssertError(2, 3)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorMissingTestKeyCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapMissingTestKey.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
							"Double key not found in populated pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorWrongTestValueCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapWrongTestValue.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Double value not right in populated pojo map!",
					buildAssertError(DEFAULT_TEST_DOUBLE, DEFAULT_WRONG_DOUBLE)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorMissingTestKey2CustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapMissingTestKey2.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
							"String key not found in populated pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorWrongTest2ValueCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorMapWrongTest2Value.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"String value not right in populated pojo map!",
					buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorNullKeysCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoNullKeys.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Keys are null in populated pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorKeysWrongSizeCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorKeysWrongSize.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
							"Keys size is wrong in populated pojo!", buildAssertError(2, 1)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorKeysMissingTestKeyCustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorKeysMissingTestKey.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
							"Keys missing Double key in populated pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorKeysMissingTestKey2CustomMessage()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoConstructorKeysMissingTestKey2.class, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
							"Keys missing String key in populated pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	/*
	 * Assert Empty Pojo
	 */
	
	@Test
	public void testAssertEmptyPojoPass(){
		assertEmptyPojo(pojoA);
	}
	
	@Test
	public void testAssertEmptyPojoFalseIsEmpty(){
		try{
			assertEmptyPojo(pojoBadIsEmpty);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("pojo was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNullMap(){
		try{
			assertEmptyPojo(pojoNullMap);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map was null in empty pojo!",
					ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNonEmptyMap(){
		try{
			assertEmptyPojo(pojoNonEmptyMap);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNullKeys(){
		try{
			assertEmptyPojo(pojoNullKeys);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getKeys() returned null in empty pojo!",
							ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNonEmptyKeys(){
		try{
			assertEmptyPojo(pojoNonEmptyKeys);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getKeys() was not empty in empty pojo!",
							ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	/*
	 * Assert Empty Pojo Custom Message
	 */
	
	@Test
	public void testAssertEmptyPojoPassCustomMessage(){
		assertEmptyPojo(pojoA, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
	}
	
	@Test
	public void testAssertEmptyPojoFalseIsEmptyCustomMessage(){
		try{
			assertEmptyPojo(pojoBadIsEmpty, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"pojo was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNullMapCustomMessage(){
		try{
			assertEmptyPojo(pojoNullMap, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Map was null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNonEmptyMapCustomMessage(){
		try{
			assertEmptyPojo(pojoNonEmptyMap, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"Map was non-empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNullKeysCustomMessage(){
		try{
			assertEmptyPojo(pojoNullKeys, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"getKeys() returned null in empty pojo!", ASSERT_NOT_NULL_ERROR.toString()), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyPojoNonEmptyKeysCustomMessage(){
		try{
			assertEmptyPojo(pojoNonEmptyKeys, DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE,
					"getKeys() was not empty in empty pojo!", ASSERT_TRUE_ERROR.toString()), e.getMessage());
		}
	}
}
