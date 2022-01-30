package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import com.github.tadukoo.util.map.MapUtil;
import com.github.tadukoo.util.pojo.AbstractMappedPojo;
import com.github.tadukoo.util.pojo.MappedPojo;
import com.github.tadukoo.util.tuple.Pair;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMessageStart;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Mapped Pojo Test is used to test {@link MappedPojo}s.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public class MappedPojoTest implements DefaultTestValues{
	
	/** Not allowed to create a MappedPojoTest instance */
	private MappedPojoTest(){ }
	
	/*
	 * Constructor Assertions
	 */
	
	/**
	 * Using the given {@link MappedPojo} class, it will create a new instance using no constructor parameters.
	 * Then it'll test that the map and keys are correctly empty.
	 *
	 * @param pojoClass The {@link MappedPojo} class to be tested
	 * @param <P> The {@link MappedPojo} class
	 * @throws NoSuchMethodException If the empty constructor does not exist
	 * @throws InvocationTargetException If the constructor throws an exception
	 * @throws InstantiationException If the class is abstract
	 * @throws IllegalAccessException If the constructor is not public
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <P extends Class<? extends MappedPojo>> void assertEmptyConstructor(P pojoClass)
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
		assertEmptyConstructor(pojoClass, null);
	}
	
	/**
	 * Using the given {@link MappedPojo} class, it will create a new instance using no constructor parameters.
	 * Then it'll test that the map and keys are correctly empty.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param pojoClass The {@link MappedPojo} class to be tested
	 * @param message A message to append to the front of any assertion error messages
	 * @param <P> The {@link MappedPojo} class
	 * @throws NoSuchMethodException If the empty constructor does not exist
	 * @throws InvocationTargetException If the constructor throws an exception
	 * @throws InstantiationException If the class is abstract
	 * @throws IllegalAccessException If the constructor is not public
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <P extends Class<? extends MappedPojo>> void assertEmptyConstructor(P pojoClass, String message)
		throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
		// Create the start of the message
		String msgStart = buildMessageStart(message);
		// Create a new instance of the pojo
		MappedPojo pojo = pojoClass.getDeclaredConstructor().newInstance();
		// Assert the pojo is empty
		assertEmptyPojo(pojo, msgStart + "New pojo is not empty!");
	}
	
	/**
	 * Using the given {@link MappedPojo} class, it will create a new instance using another MappedPojo as a
	 * constructor parameter. Then it will check that the map and keys (and getItem) are correct.
	 *
	 * @param pojoClass The {@link MappedPojo} class to be tested
	 * @param <P> The {@link MappedPojo} class
	 * @throws NoSuchMethodException If the pojo constructor does not exist
	 * @throws InvocationTargetException If the constructor throws an exception
	 * @throws InstantiationException If the class is abstract
	 * @throws IllegalAccessException If the constructor is not public
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <P extends Class<? extends MappedPojo>> void assertPojoConstructor(P pojoClass)
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
		// Create a simple pojo with a couple values
		MappedPojo simplePojo = new AbstractMappedPojo(){
			@Override
			public Map<String, Object> getMap(){
				return MapUtil.createMap(
						Pair.of(DEFAULT_TEST_KEY, DEFAULT_TEST_DOUBLE),
						Pair.of(DEFAULT_TEST_KEY_2, DEFAULT_TEST_STRING));
			}
		};
		
		// Create a new instance of the pojo class
		MappedPojo pojo = pojoClass.getDeclaredConstructor(MappedPojo.class).newInstance(simplePojo);
		// Check the values in the pojo
		assertEquals(DEFAULT_TEST_DOUBLE, pojo.getItem(DEFAULT_TEST_KEY));
		assertEquals(DEFAULT_TEST_STRING, pojo.getItem(DEFAULT_TEST_KEY_2));
		// Check the map is proper
		Map<String, Object> map = pojo.getMap();
		assertNotNull(map);
		assertFalse(map.isEmpty());
		assertEquals(2, map.size());
		assertTrue(map.containsKey(DEFAULT_TEST_KEY));
		assertEquals(DEFAULT_TEST_DOUBLE, map.get(DEFAULT_TEST_KEY));
		assertTrue(map.containsKey(DEFAULT_TEST_KEY_2));
		assertEquals(DEFAULT_TEST_STRING, map.get(DEFAULT_TEST_KEY_2));
		// Check the keys are proper
		Set<String> keys = pojo.getKeys();
		assertNotNull(keys);
		assertEquals(2, keys.size());
		assertTrue(keys.contains(DEFAULT_TEST_KEY));
		assertTrue(keys.contains(DEFAULT_TEST_KEY_2));
	}
	
	/*
	 * Empty Pojo Assertions
	 */
	
	/**
	 * Checks that the given {@link MappedPojo} is empty. Checks that {@link MappedPojo#isEmpty()} is true,
	 * that {@link MappedPojo#getMap()} is not null and is empty, and that {@link MappedPojo#getKeys()} is
	 * not null and is empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static void assertEmptyPojo(MappedPojo pojo){
		assertEmptyPojo(pojo, null);
	}
	
	/**
	 * Checks that the given {@link MappedPojo} is empty. Checks that {@link MappedPojo#isEmpty()} is true,
	 * that {@link MappedPojo#getMap()} is not null and is empty, and that {@link MappedPojo#getKeys()} is
	 * not null and is empty.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param message A message to append to the front of any assertion error messages
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static void assertEmptyPojo(MappedPojo pojo, String message){
		// Build the Message Start
		String msgStart = buildMessageStart(message);
		
		// Test the pojo returns true for its isEmpty method
		assertTrue(pojo.isEmpty(), msgStart + "pojo was non-empty in empty pojo!");
		
		// Test the map is empty
		Map<String, Object> map = pojo.getMap();
		assertNotNull(map, msgStart + "Map was null in empty pojo!");
		assertTrue(map.isEmpty(), msgStart + "Map was non-empty in empty pojo!");
		
		// Test the key set is empty
		Set<String> keys = pojo.getKeys();
		assertNotNull(keys, msgStart + "getKeys() returned null in empty pojo!");
		assertTrue(keys.isEmpty(), msgStart + "getKeys() was not empty in empty pojo!");
	}
	
	/*
	 * GetItem/SetItem Assertions
	 */
	
	/**
	 * Checks {@link MappedPojo#getItem(String)} and {@link MappedPojo#setItem(String, Object)} on the given
	 * pojo, to test that those get and set methods work for the given key and values. The set and retrieve
	 * are done twice (once for each given value) to ensure we're not adding/appending or just always returning
	 * the expected value. We also check that the keys and map are correct on the pojo.
	 * <br><br>
	 * <b>Note: </b> The given pojo should be empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param key The key to be used
	 * @param value The first value to be used for testing
	 * @param value2 The second value to be used for testing
	 * @param <V> The value type being set and retrieved
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <V> void assertValueGetSet(MappedPojo pojo, String key, V value, V value2){
		// Ensure the pojo is empty
		assertEmptyPojo(pojo);
		
		// Set the item
		pojo.setItem(key, value);
		// Test that the has methods return true for the key
		assertTrue(pojo.hasKey(key));
		assertTrue(pojo.hasItem(key));
		// Check that we can retrieve the item
		assertEquals(value, pojo.getItem(key));
		
		// Check keys
		Set<String> keys = pojo.getKeys();
		assertNotNull(keys);
		assertEquals(1, keys.size());
		assertEquals(key, keys.iterator().next());
		
		// Check map
		Map<String, Object> map = pojo.getMap();
		assertNotNull(map);
		assertEquals(1, map.size());
		assertTrue(map.containsKey(key));
		assertEquals(value, map.get(key));
		
		// 2nd run, to ensure we're not adding/appending or just always returning the expected value
		pojo.setItem(key, value2);
		// Test that the has methods return true for the key
		assertTrue(pojo.hasKey(key));
		assertTrue(pojo.hasItem(key));
		// Check that we can retrieve the item
		assertEquals(value2, pojo.getItem(key));
		
		// Check keys
		Set<String> keys2 = pojo.getKeys();
		assertNotNull(keys2);
		assertEquals(1, keys2.size());
		assertEquals(key, keys2.iterator().next());
		
		// Check map
		Map<String, Object> map2 = pojo.getMap();
		assertNotNull(map2);
		assertEquals(1, map2.size());
		assertTrue(map2.containsKey(key));
		assertEquals(value2, map2.get(key));
	}
	
	/**
	 * Checks {@link MappedPojo#getItem(String)} and {@link MappedPojo#setItem(String, Object)} on the given
	 * pojo, to test that those get and set methods work for the given key for a String value. The set and retrieve
	 * are done twice to ensure we're not adding/appending or just always returning the expected value.
	 * We also check that the keys and map are correct on the pojo.
	 * <br><br>
	 * <b>Note: </b> The given pojo should be empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param key The key to be used
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static void assertStringGetSet(MappedPojo pojo, String key){
		assertValueGetSet(pojo, key, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	/**
	 * Checks {@link MappedPojo#getItem(String)} and {@link MappedPojo#setItem(String, Object)} on the given
	 * pojo, to test that those get and set methods work for the given key for a double value. The set and retrieve
	 * are done twice to ensure we're not adding/appending or just always returning the expected value.
	 * We also check that the keys and map are correct on the pojo.
	 * <br><br>
	 * <b>Note: </b> The given pojo should be empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param key The key to be used
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static void assertDoubleGetSet(MappedPojo pojo, String key){
		assertValueGetSet(pojo, key, DEFAULT_TEST_DOUBLE, DEFAULT_TEST_DOUBLE_2);
	}
	
	/*
	 * Custom Get/Set Assertions
	 */
	
	/**
	 * Checks {@link MappedPojo#getItem(String)} and {@link MappedPojo#setItem(String, Object)} on the given pojo,
	 * along with the given custom getter and setter that the methods work for the given key with the given values.
	 * The set and retrieve are done twice per getter/setter combo to ensure we're not adding/appending or just
	 * always returning the expected value. We also check that the keys and map are correct on the pojo.
	 * <br><br>
	 * <b>Note: </b> The given pojo should be empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param getter The getter to be tested
	 * @param setter The setter to be tested
	 * @param key The key to be used
	 * @param value The first value to be used for testing
	 * @param value2 The second value to be used for testing
	 * @param <V> The value type being set and retrieved
	 * @param <T> A {@link Throwable} that the getter may throw
	 * @param <T2> A {@link Throwable} that the setter may throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <V, T extends Throwable, T2 extends Throwable> void assertValueGetSetCustom(
			MappedPojo pojo, ThrowingSupplier<V, T> getter, ThrowingConsumer<V, T2> setter, String key,
			V value, V value2) throws T, T2{
		// Run to check getItem/setItem
		assertValueGetSet(pojo, key, value, value2);
		
		// Clear out the pojo
		pojo.clear();
		assertEmptyPojo(pojo);
		
		// Run to check custom getter/setter
		setter.accept(value);
		assertEquals(value, getter.get());
		// Test that the has methods return true for the key
		assertTrue(pojo.hasKey(key));
		assertTrue(pojo.hasItem(key));
		// Check that we can retrieve the item
		assertEquals(value, pojo.getItem(key));
		
		// Check keys
		Set<String> keys = pojo.getKeys();
		assertNotNull(keys);
		assertEquals(1, keys.size());
		assertEquals(key, keys.iterator().next());
		
		// Check map
		Map<String, Object> map = pojo.getMap();
		assertNotNull(map);
		assertEquals(1, map.size());
		assertTrue(map.containsKey(key));
		assertEquals(value, map.get(key));
		
		// 2nd run, to ensure we're not adding/appending or just always returning the expected value
		setter.accept(value2);
		assertEquals(value2, getter.get());
		// Test that the has methods return true for the key
		assertTrue(pojo.hasKey(key));
		assertTrue(pojo.hasItem(key));
		// Check that we can retrieve the item
		assertEquals(value2, pojo.getItem(key));
		
		// Check keys
		Set<String> keys2 = pojo.getKeys();
		assertNotNull(keys2);
		assertEquals(1, keys2.size());
		assertEquals(key, keys2.iterator().next());
		
		// Check map
		Map<String, Object> map2 = pojo.getMap();
		assertNotNull(map2);
		assertEquals(1, map2.size());
		assertTrue(map2.containsKey(key));
		assertEquals(value2, map2.get(key));
	}
	
	/**
	 * Checks {@link MappedPojo#getItem(String)} and {@link MappedPojo#setItem(String, Object)} on the given pojo,
	 * along with the given custom getter and setter that the methods work for the given key for string values.
	 * The set and retrieve are done twice per getter/setter combo to ensure we're not adding/appending or just
	 * always returning the expected value. We also check that the keys and map are correct on the pojo.
	 * <br><br>
	 * <b>Note: </b> The given pojo should be empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param getter The getter to be tested
	 * @param setter The setter to be tested
	 * @param key The key to be used
	 * @param <T> A {@link Throwable} that the getter may throw
	 * @param <T2> A {@link Throwable} that the setter may throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <T extends Throwable, T2 extends Throwable> void assertStringGetSetCustom(
			MappedPojo pojo, ThrowingSupplier<String, T> getter, ThrowingConsumer<String, T2> setter, String key)
		throws T, T2{
		assertValueGetSetCustom(pojo, getter, setter, key, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	/**
	 * Checks {@link MappedPojo#getItem(String)} and {@link MappedPojo#setItem(String, Object)} on the given pojo,
	 * along with the given custom getter and setter that the methods work for the given key for double values.
	 * The set and retrieve are done twice per getter/setter combo to ensure we're not adding/appending or just
	 * always returning the expected value. We also check that the keys and map are correct on the pojo.
	 * <br><br>
	 * <b>Note: </b> The given pojo should be empty
	 *
	 * @param pojo The {@link MappedPojo} to be tested
	 * @param getter The getter to be tested
	 * @param setter The setter to be tested
	 * @param key The key to be used
	 * @param <T> A {@link Throwable} that the getter may throw
	 * @param <T2> A {@link Throwable} that the setter may throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <T extends Throwable, T2 extends Throwable> void assertDoubleGetSetCustom(
			MappedPojo pojo, ThrowingSupplier<Double, T> getter, ThrowingConsumer<Double, T2> setter, String key)
		throws T, T2{
		assertValueGetSetCustom(pojo, getter, setter, key, DEFAULT_TEST_DOUBLE, DEFAULT_TEST_DOUBLE_2);
	}
}
