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
import java.util.Map;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_NOT_NULL_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.ASSERT_TRUE_ERROR;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
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
			assertEquals(buildTwoPartError("pojo was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNullMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map was null in empty pojo!",
					ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyMap()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNonEmptyMap.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("Map was non-empty in empty pojo!",
					ASSERT_TRUE_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNullKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNullKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getKeys() returned null in empty pojo!",
							ASSERT_NOT_NULL_ERROR), e.getMessage());
		}
	}
	
	@Test
	public void testAssertEmptyConstructorNonEmptyKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertEmptyConstructor(MappedPojoNonEmptyKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("getKeys() was not empty in empty pojo!",
							ASSERT_TRUE_ERROR), e.getMessage());
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
			assertEquals(buildAssertError(DEFAULT_TEST_DOUBLE, DEFAULT_WRONG_DOUBLE),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorBadValue2()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoBadValue2.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildAssertError(DEFAULT_TEST_STRING, DEFAULT_WRONG_STRING),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertPojoConstructorNullKeys()
			throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
		try{
			assertPojoConstructor(MappedPojoNullKeys.class);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(ASSERT_NOT_NULL_ERROR.toString(), e.getMessage());
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
}
