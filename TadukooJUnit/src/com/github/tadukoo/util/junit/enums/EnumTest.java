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
		String msgStart = buildMessageStart(message);
		assertNull(fromStringFunc.apply(badString), msgStart + "enum returned non-null for bad string!");
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
		assertToFromString(enumVal, enumStr, fromStringFunc, toStringFunc, null);
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
		assertToFromString(enumVal, enumStr, fromStringFunc, enumVal::toString, message);
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
		String msgStart = buildMessageStart(message);
		assertEquals(enumStr, toStringFunc.get(), msgStart + "enum's toString failed!");
		assertEquals(enumVal, fromStringFunc.apply(enumStr), msgStart + "enum's fromString failed!");
	}
}
