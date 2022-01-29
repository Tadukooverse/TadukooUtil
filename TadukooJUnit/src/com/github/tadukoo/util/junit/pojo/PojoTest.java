package com.github.tadukoo.util.junit.pojo;

import com.github.tadukoo.util.StringUtil;
import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pojo Test is used to test regular POJOs.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public class PojoTest implements DefaultTestValues{
	
	/** Not allowed to create a PojoTest instance */
	private PojoTest(){ }
	
	/**
	 * Method to test a getter and setter for a Pojo, that a value can be set and retrieved. It is set
	 * and retrieved twice in a row to check for adding/appending or just always returning the expected value.
	 *
	 * @param getter The getter to be tested
	 * @param setter The setter to be tested
	 * @param value The first value to be used for testing
	 * @param value2 The second value to be used for testing
	 * @param <V> The value type being set and retrieved
	 * @param <T> A {@link Throwable} that the getter can throw
	 * @param <T2> A {@link Throwable} that the setter can throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <V, T extends Throwable, T2 extends Throwable> void assertValueGetSet(
			ThrowingSupplier<V, T> getter, ThrowingConsumer<V, T2> setter, V value, V value2) throws T, T2{
		assertValueGetSet(getter, setter, value, value2, "");
	}
	
	/**
	 * Method to test a getter and setter for a Pojo, that a value can be set and retrieved. It is set
	 * and retrieved twice in a row to check for adding/appending or just always returning the expected value.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param getter The getter to be tested
	 * @param setter The setter to be tested
	 * @param value The first value to be used for testing
	 * @param value2 The second value to be used for testing
	 * @param message A message to append to the front of any assertion error messages
	 * @param <V> The value type being set and retrieved
	 * @param <T> A {@link Throwable} that the getter can throw
	 * @param <T2> A {@link Throwable} that the setter can throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <V, T extends Throwable, T2 extends Throwable> void assertValueGetSet(
			ThrowingSupplier<V, T> getter, ThrowingConsumer<V, T2> setter, V value, V value2, String message)
		throws T, T2{
		// Setup message start
		String msgStart = StringUtil.isBlank(message)?"":message + " ==> ";
		
		// Run a set and try to get the value we set
		setter.accept(value);
		assertEquals(value, getter.get(), msgStart + "getter failed on first set!");
		
		// 2nd run, to ensure we're not adding/appending or just always returning the expected value
		setter.accept(value2);
		assertEquals(value2, getter.get(), msgStart + "getter failed on second set!");
	}
	
	/**
	 * Method to test a String getter and setter for a Pojo, that a value can be set and retrieved. It is set
	 * and retrieved twice in a row to check for adding/appending or just always returning the expected value.
	 *
	 * @param getter The String getter to be tested
	 * @param setter The String setter to be tested
	 * @param <T> A {@link Throwable} that the getter can throw
	 * @param <T2> A {@link Throwable} that the setter can throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <T extends Throwable, T2 extends Throwable> void assertStringGetSet(
			ThrowingSupplier<String, T> getter, ThrowingConsumer<String, T2> setter) throws T, T2{
		assertValueGetSet(getter, setter, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2);
	}
	
	/**
	 * Method to test a String getter and setter for a Pojo, that a value can be set and retrieved. It is set
	 * and retrieved twice in a row to check for adding/appending or just always returning the expected value.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param getter The String getter to be tested
	 * @param setter The String setter to be tested
	 * @param message A message to append to the front of any assertion error messages
	 * @param <T> A {@link Throwable} that the getter can throw
	 * @param <T2> A {@link Throwable} that the setter can throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <T extends Throwable, T2 extends Throwable> void assertStringGetSet(
			ThrowingSupplier<String, T> getter, ThrowingConsumer<String, T2> setter, String message) throws T, T2{
		assertValueGetSet(getter, setter, DEFAULT_TEST_STRING, DEFAULT_TEST_STRING_2, message);
	}
	
	/**
	 * Method to test a double getter and setter for a Pojo, that a value can be set and retrieved. It is set
	 * and retrieved twice in a row to check for adding/appending or just always returning the expected value.
	 *
	 * @param getter The double getter to be tested
	 * @param setter The double setter to be tested
	 * @param <T> A {@link Throwable} that the getter can throw
	 * @param <T2> A {@link Throwable} that the setter can throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <T extends Throwable, T2 extends Throwable> void assertDoubleGetSet(
			ThrowingSupplier<Double, T> getter, ThrowingConsumer<Double, T2> setter) throws T, T2{
		assertValueGetSet(getter, setter, DEFAULT_TEST_DOUBLE, DEFAULT_TEST_DOUBLE_2);
	}
	
	/**
	 * Method to test a double getter and setter for a Pojo, that a value can be set and retrieved. It is set
	 * and retrieved twice in a row to check for adding/appending or just always returning the expected value.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param getter The double getter to be tested
	 * @param setter The double setter to be tested
	 * @param message A message to append to the front of any assertion error messages
	 * @param <T> A {@link Throwable} that the getter can throw
	 * @param <T2> A {@link Throwable} that the setter can throw
	 * @throws T If the getter throws it
	 * @throws T2 If the setter throws it
	 * @throws AssertionFailedError If an assertion fails
	 */
	public static <T extends Throwable, T2 extends Throwable> void assertDoubleGetSet(
			ThrowingSupplier<Double, T> getter, ThrowingConsumer<Double, T2> setter, String message) throws T, T2{
		assertValueGetSet(getter, setter, DEFAULT_TEST_DOUBLE, DEFAULT_TEST_DOUBLE_2, message);
	}
}
