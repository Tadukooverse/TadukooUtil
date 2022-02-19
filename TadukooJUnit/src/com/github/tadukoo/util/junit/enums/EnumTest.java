package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import com.github.tadukoo.util.junit.DefaultTestValues;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMessageStart;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Enum Test is used for some regular Enum JUnit tests.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public class EnumTest implements DefaultTestValues{
	
	/** Not allowed to instantiate an {@link EnumTest} */
	private EnumTest(){ }
	
	/**
	 * Assert that an enum's fromValue method returns {@code null} when given a bad value
	 *
	 * @param fromValueFunc The fromValue function of the enum
	 * @param badValue The value to use as the "bad" value for the fromValue method to not recognize
	 * @param <V> The type of value we're using
	 * @param <E> The type of enum we're using
	 */
	public static <V, E extends Enum<?>> void assertFromValueBad(
			ThrowingFunction<V, E, NoException> fromValueFunc, V badValue){
		assertFromValueBad(fromValueFunc, badValue, null);
	}
	
	/**
	 * Assert that an enum's fromValue method returns {@code null} when given a bad value
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param fromValueFunc The fromValue function of the enum
	 * @param badValue The value to use as the "bad" value for the fromValue method to not recognize
	 * @param message A message to append to the front of any assertion error messages
	 * @param <V> The type of value we're using
	 * @param <E> The type of enum we're using
	 */
	public static <V, E extends Enum<?>> void assertFromValueBad(
		ThrowingFunction<V, E, NoException> fromValueFunc, V badValue, String message){
		String msgStart = buildMessageStart(message);
		assertNull(fromValueFunc.apply(badValue), msgStart + "enum returned non-null for bad value!");
	}
	
	/**
	 * Assert that an enum's fromString method returns {@code null} when given a bad string.
	 * This version uses {@link #DEFAULT_WRONG_STRING} as the bad string
	 *
	 * @param fromStringFunc The fromString function of the enum
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertFromStringBad(
			ThrowingFunction<String, E, NoException> fromStringFunc){
		assertFromStringBad(fromStringFunc, DEFAULT_WRONG_STRING);
	}
	
	/**
	 * Assert that an enum's fromString method returns {@code null} when given the given bad string.
	 *
	 * @param fromStringFunc The fromString function of the enum
	 * @param badString The string to use as the "bad" string for the fromString method to not recognize
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertFromStringBad(
			ThrowingFunction<String, E, NoException> fromStringFunc, String badString){
		assertFromStringBad(fromStringFunc, badString, null);
	}
	
	/**
	 * Assert that an enum's fromString method returns {@code null} when given the given bad string.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param fromStringFunc The fromString function of the enum
	 * @param badString The string to use as the "bad" string for the fromString method to not recognize
	 * @param message A message to append to the front of any assertion error messages
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertFromStringBad(
			ThrowingFunction<String, E, NoException> fromStringFunc, String badString, String message){
		assertFromValueBad(fromStringFunc, badString, message);
	}
	
	/**
	 * Assert that an enum's fromInt method returns {@code null} when given a bad int.
	 * This version uses {@link #DEFAULT_WRONG_INT} as the bad int
	 *
	 * @param fromIntFunc The fromInt function of the enum
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertFromIntBad(
			ThrowingFunction<Integer, E, NoException> fromIntFunc){
		assertFromIntBad(fromIntFunc, DEFAULT_WRONG_INT);
	}
	
	/**
	 * Assert that an enum's fromInt method returns {@code null} when given the given bad int.
	 *
	 * @param fromIntFunc The fromInt function of the enum
	 * @param badInt The int to use as the "bad" int for the fromInt method to not recognize
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertFromIntBad(
			ThrowingFunction<Integer, E, NoException> fromIntFunc, int badInt){
		assertFromIntBad(fromIntFunc, badInt, null);
	}
	
	/**
	 * Assert that an enum's fromInt method returns {@code null} when given the given bad int.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param fromIntFunc The fromInt function of the enum
	 * @param badInt The int to use as the "bad" int for the fromInt method to not recognize
	 * @param message A message to append to the front of any assertion error messages
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertFromIntBad(
			ThrowingFunction<Integer, E, NoException> fromIntFunc, int badInt, String message){
		assertFromValueBad(fromIntFunc, badInt, message);
	}
	
	/**
	 * Assert that an enum's toValue and fromValue methods work properly with the given enumValue and value
	 * for the given fromValue and toValue functions
	 *
	 * @param enumVal The enum value to be tested
	 * @param value The enum value's value to be tested
	 * @param fromValueFunc The function to use to get the enum value from the enum value's value
	 * @param toValueFunc The function to use to get the enum value's value from the enum value
	 * @param <V> The type of value we're using
	 * @param <E> The type of enum we're using
	 */
	public static <V, E extends Enum<?>> void assertToFromValue(
			E enumVal, V value, ThrowingFunction<V, E, NoException> fromValueFunc,
			ThrowingSupplier<V, NoException> toValueFunc){
		assertToFromValue(enumVal, value, fromValueFunc, toValueFunc, null);
	}
	
	/**
	 * Assert that an enum's toValue and fromValue methods work properly with the given enumValue and value
	 * for the given fromValue and toValue functions.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param enumVal The enum value to be tested
	 * @param value The enum value's value to be tested
	 * @param fromValueFunc The function to use to get the enum value from the enum value's value
	 * @param toValueFunc The function to use to get the enum value's value from the enum value
	 * @param message A message to append to the front of any assertion error messages
	 * @param <V> The type of value we're using
	 * @param <E> The type of enum we're using
	 */
	public static <V, E extends Enum<?>> void assertToFromValue(
			E enumVal, V value, ThrowingFunction<V, E, NoException> fromValueFunc,
			ThrowingSupplier<V, NoException> toValueFunc, String message){
		String msgStart = buildMessageStart(message);
		assertEquals(value, toValueFunc.get(), msgStart + "enum's toValue failed!");
		assertEquals(enumVal, fromValueFunc.apply(value), msgStart + "enum's fromValue failed!");
	}
	
	/**
	 * Assert that an enum's toString and fromString methods work properly with the given enumValue and enumString
	 * for the given fromString function. Uses the built-in toString function from {@link Object}
	 *
	 * @param enumVal The enum value to be tested
	 * @param enumStr The enum value's string to be tested
	 * @param fromStringFunc The function to use to get the enum value from the enum string
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertToFromString(
			E enumVal, String enumStr, ThrowingFunction<String, E, NoException> fromStringFunc){
		assertToFromString(enumVal, enumStr, fromStringFunc, "");
	}
	
	/**
	 * Assert that an enum's toString and fromString methods work properly with the given enumValue and enumString
	 * for the given fromString function. This version allows you to specify a custom toString function
	 *
	 * @param enumVal The enum value to be tested
	 * @param enumStr The enum value's string to be tested
	 * @param fromStringFunc The function to use to get the enum value from the enum string
	 * @param toStringFunc The function to use to get the enum string from the enum value
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertToFromString(
			E enumVal, String enumStr, ThrowingFunction<String, E, NoException> fromStringFunc,
			ThrowingSupplier<String, NoException> toStringFunc){
		assertToFromValue(enumVal, enumStr, fromStringFunc, toStringFunc);
	}
	
	/**
	 * Assert that an enum's toString and fromString methods work properly with the given enumValue and enumString
	 * for the given fromString function. Uses the built-in toString function from {@link Object}.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param enumVal The enum value to be tested
	 * @param enumStr The enum value's string to be tested
	 * @param fromStringFunc The function to use to get the enum value from the enum string
	 * @param message A message to append to the front of any assertion error messages
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertToFromString(
			E enumVal, String enumStr, ThrowingFunction<String, E, NoException> fromStringFunc,
			String message){
		assertToFromValue(enumVal, enumStr, fromStringFunc, enumVal::toString, message);
	}
	
	/**
	 * Assert that an enum's toString and fromString methods work properly with the given enumValue and enumString
	 * for the given fromString function. This version allows you to specify a custom toString function.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param enumVal The enum value to be tested
	 * @param enumStr The enum value's string to be tested
	 * @param fromStringFunc The function to use to get the enum value from the enum string
	 * @param toStringFunc The function to use to get the enum string from the enum value
	 * @param message A message to append to the front of any assertion error messages
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertToFromString(
			E enumVal, String enumStr, ThrowingFunction<String, E, NoException> fromStringFunc,
			ThrowingSupplier<String, NoException> toStringFunc, String message){
		assertToFromValue(enumVal, enumStr, fromStringFunc, toStringFunc, message);
	}
	
	/**
	 * Assert that an enum's toInt and fromInt methods work properly with the given enumValue and enumInt
	 * for the given fromInt and toInt functions.
	 *
	 * @param enumVal The enum value to be tested
	 * @param enumInt The enum value's int to be tested
	 * @param fromIntFunc The function to use to get the enum value from the enum int
	 * @param toIntFunc The function to use to get the enum int from the enum value
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertToFromInt(
			E enumVal, int enumInt, ThrowingFunction<Integer, E, NoException> fromIntFunc,
			ThrowingSupplier<Integer, NoException> toIntFunc){
		assertToFromValue(enumVal, enumInt, fromIntFunc, toIntFunc);
	}
	
	/**
	 * Assert that an enum's toInt and fromInt methods work properly with the given enumValue and enumInt
	 * for the given fromInt and toInt functions.
	 * This version allows for specifying a custom message to append to the start of any assertion errors.
	 *
	 * @param enumVal The enum value to be tested
	 * @param enumInt The enum value's int to be tested
	 * @param fromIntFunc The function to use to get the enum value from the enum int
	 * @param toIntFunc The function to use to get the enum int from the enum value
	 * @param message A message to append to the front of any assertion error messages
	 * @param <E> The type of enum we're using
	 */
	public static <E extends Enum<?>> void assertToFromInt(
			E enumVal, int enumInt, ThrowingFunction<Integer, E, NoException> fromIntFunc,
			ThrowingSupplier<Integer, NoException> toIntFunc, String message){
		assertToFromValue(enumVal, enumInt, fromIntFunc, toIntFunc, message);
	}
}
