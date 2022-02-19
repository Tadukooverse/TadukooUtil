package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer3;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer4;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer5;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Test class used for testing toFromValue methods in {@link EnumTest} that have a total of 4 versions, including
 * ones that don't require a toValue function (i.e. they have a method for a default toValue function, e.g. toString).
 * For functions that <i>don't have</i> such a method, use {@link EnumToFromValueSimpleTest} instead.
 *
 * @param <V> The type of value we're using
 * @param <E> The type of enum we're using
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public abstract class EnumToFromValueTest<V, E extends Enum<?>> extends EnumToFromValueSimpleTest<V, E>{
	/** An assertToFromValue function that takes an enum value, regular value, and fromValue function */
	private final ThrowingConsumer3<E, V, ThrowingFunction<V, E, NoException>, NoException> assertToFromValFunc;
	/** An assertToFromValue function that takes an enum value, regular value, fromValue function, and custom message */
	private final ThrowingConsumer4<E, V, ThrowingFunction<V, E, NoException>, String, NoException> assertToFromValFunc3;
	
	/**
	 * Constructs a new {@link EnumToFromValueTest} with the given parameters
	 *
	 * @param assertToFromValFunc An assertToFromValue function that takes an enum value, regular value,
	 *                            and fromValue function
	 * @param assertToFromValFunc2 An assertToFromValue function that takes an enum value, regular value,
	 *                             and fromValue and toValue functions
	 * @param assertToFromValFunc3 An assertToFromValue function that takes an enum value, regular value,
	 *                             fromValue function, and custom message
	 * @param assertToFromValFunc4 An assertToFromValue function that takes an enum value, regular value,
	 *                             fromValue and toValue functions, and custom message
	 * @param fromValueFunc The regular fromValue function of the enum
	 * @param badFromValueFunc A bad fromValue function of the enum
	 * @param customToValueFunc The toValue function for {@link #enumVal}
	 * @param enumVal The first enum test value
	 * @param value The value stored in {@link #enumVal}
	 * @param customToValueFunc2 The toValue function for {@link #enumVal2}
	 * @param enumVal2 The second enum test value
	 * @param value2 The value stored in {@link #enumVal2}
	 */
	protected EnumToFromValueTest(
			ThrowingConsumer3<E, V, ThrowingFunction<V, E, NoException>, NoException> assertToFromValFunc,
			ThrowingConsumer4<E, V, ThrowingFunction<V, E, NoException>,
					ThrowingSupplier<V, NoException>, NoException> assertToFromValFunc2,
			ThrowingConsumer4<E, V, ThrowingFunction<V, E, NoException>, String, NoException> assertToFromValFunc3,
			ThrowingConsumer5<E, V, ThrowingFunction<V, E, NoException>,
					ThrowingSupplier<V, NoException>, String, NoException> assertToFromValFunc4,
			ThrowingFunction<V, E, NoException> fromValueFunc, ThrowingFunction<V, E, NoException> badFromValueFunc,
			ThrowingSupplier<V, NoException> customToValueFunc, E enumVal, V value,
			ThrowingSupplier<V, NoException> customToValueFunc2, E enumVal2, V value2){
		super(assertToFromValFunc2, assertToFromValFunc4, fromValueFunc, badFromValueFunc,
				customToValueFunc, enumVal, value, customToValueFunc2, enumVal2, value2);
		this.assertToFromValFunc = assertToFromValFunc;
		this.assertToFromValFunc3 = assertToFromValFunc3;
	}
	
	@Test
	public void testAssertToFromValue(){
		assertToFromValFunc.accept(enumVal, value, fromValueFunc);
		assertToFromValFunc.accept(enumVal2, value2, fromValueFunc);
	}
	
	@Test
	public void testAssertToFromValueCustomMessage(){
		assertToFromValFunc3.accept(enumVal, value, fromValueFunc, defaultCustomAssertionFailedMessage);
		assertToFromValFunc3.accept(enumVal2, value2, fromValueFunc, defaultCustomAssertionFailedMessage);
	}
	
	@Test
	public void testAssertToFromValueWrongTo(){
		try{
			assertToFromValFunc.accept(enumVal, value2, fromValueFunc);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's toValue failed!", buildAssertError(value2, value)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromValueWrongToCustomMessage(){
		try{
			assertToFromValFunc3.accept(enumVal, value2, fromValueFunc, defaultCustomAssertionFailedMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomAssertionFailedMessage, "enum's toValue failed!",
					buildAssertError(value2, value)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromValueWrongFrom(){
		try{
			assertToFromValFunc.accept(enumVal, value, badFromValueFunc);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's fromValue failed!", buildAssertError(enumVal, enumVal2)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromValueWrongFromCustomMessage(){
		try{
			assertToFromValFunc3.accept(enumVal, value, badFromValueFunc, defaultCustomAssertionFailedMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomAssertionFailedMessage, "enum's fromValue failed!",
					buildAssertError(enumVal, enumVal2)), e.getMessage());
		}
	}
}
