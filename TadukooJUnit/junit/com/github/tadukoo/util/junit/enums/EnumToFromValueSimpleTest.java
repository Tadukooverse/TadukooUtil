package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer4;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer5;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;
import com.github.tadukoo.util.junit.DefaultTestValues;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Test class used for testing toFromValue methods in {@link EnumTest} that have only versions that require a
 * toValue function (i.e. they do not have a method for a default toValue function, e.g. toString). For functions
 * that <i>do have</i> such a method, use {@link EnumToFromValueTest} instead.
 *
 * @param <V> The type of value we're using
 * @param <E> The type of enum we're using
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public abstract class EnumToFromValueSimpleTest<V, E extends Enum<?>> implements DefaultTestValues{
	/** An assertToFromValue function that takes an enum value, regular value, and fromValue and toValue functions */
	private final ThrowingConsumer4<E, V, ThrowingFunction<V, E, NoException>,
			ThrowingSupplier<V, NoException>, NoException> assertToFromValFunc2;
	/** An assertToFromValue function that takes an enum value, regular value, fromValue and toValue functions, and custom message */
	private final ThrowingConsumer5<E, V, ThrowingFunction<V, E, NoException>,
			ThrowingSupplier<V, NoException>, String, NoException> assertToFromValFunc4;
	/** The regular fromValue function of the enum */
	protected final ThrowingFunction<V, E, NoException> fromValueFunc;
	/** A bad fromValue function of the enum */
	protected final ThrowingFunction<V, E, NoException> badFromValueFunc;
	/** The toValue function for {@link #enumVal} */
	protected final ThrowingSupplier<V, NoException> customToValueFunc;
	/** The first enum test value */
	protected final E enumVal;
	/** The value stored in {@link #enumVal} */
	protected final V value;
	/** The toValue function for {@link #enumVal2} */
	protected final ThrowingSupplier<V, NoException> customToValueFunc2;
	/** The second enum test value */
	protected final E enumVal2;
	/** The value stored in {@link #enumVal2} */
	protected final V value2;
	/** The message to use for custom assertion messages */
	protected static final String defaultCustomAssertionFailedMessage = DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE;
	
	/**
	 * Constructs a new {@link EnumToFromValueSimpleTest} with the given parameters
	 *
	 * @param assertToFromValFunc2 An assertToFromValue function that takes an enum value, regular value,
	 *                             and fromValue and toValue functions
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
	protected EnumToFromValueSimpleTest(
			ThrowingConsumer4<E, V, ThrowingFunction<V, E, NoException>,
					ThrowingSupplier<V, NoException>, NoException> assertToFromValFunc2,
			ThrowingConsumer5<E, V, ThrowingFunction<V, E, NoException>,
					ThrowingSupplier<V, NoException>, String, NoException> assertToFromValFunc4,
			ThrowingFunction<V, E, NoException> fromValueFunc, ThrowingFunction<V, E, NoException> badFromValueFunc,
			ThrowingSupplier<V, NoException> customToValueFunc, E enumVal, V value,
			ThrowingSupplier<V, NoException> customToValueFunc2, E enumVal2, V value2){
		this.assertToFromValFunc2 = assertToFromValFunc2;
		this.assertToFromValFunc4 = assertToFromValFunc4;
		this.fromValueFunc = fromValueFunc;
		this.badFromValueFunc = badFromValueFunc;
		this.customToValueFunc = customToValueFunc;
		this.customToValueFunc2 = customToValueFunc2;
		this.enumVal = enumVal;
		this.enumVal2 = enumVal2;
		this.value = value;
		this.value2 = value2;
	}
	
	@Test
	public void testAssertToFromValueCustomToValue(){
		assertToFromValFunc2.accept(enumVal, value, fromValueFunc, customToValueFunc);
		assertToFromValFunc2.accept(enumVal2, value2, fromValueFunc, customToValueFunc2);
	}
	
	@Test
	public void testAssertToFromValueCustomToValueCustomMessage(){
		assertToFromValFunc4.accept(enumVal, value, fromValueFunc, customToValueFunc,
				defaultCustomAssertionFailedMessage);
		assertToFromValFunc4.accept(enumVal2, value2, fromValueFunc, customToValueFunc2,
				defaultCustomAssertionFailedMessage);
	}
	
	@Test
	public void testAssertToFromValueWrongToCustomToValue(){
		try{
			assertToFromValFunc2.accept(enumVal, value2, fromValueFunc, customToValueFunc);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's toValue failed!", buildAssertError(value2, value)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromValueWrongToCustomToValueCustomMessage(){
		try{
			assertToFromValFunc4.accept(enumVal, value2, fromValueFunc, customToValueFunc,
					defaultCustomAssertionFailedMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomAssertionFailedMessage, "enum's toValue failed!",
					buildAssertError(value2, value)), e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromValueWrongFromCustomToValue(){
		try{
			assertToFromValFunc2.accept(enumVal, value, badFromValueFunc, customToValueFunc);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum's fromValue failed!", buildAssertError(enumVal, enumVal2)),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertToFromValueWrongFromCustomToValueCustomMessage(){
		try{
			assertToFromValFunc4.accept(enumVal, value, badFromValueFunc, customToValueFunc,
					defaultCustomAssertionFailedMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomAssertionFailedMessage, "enum's fromValue failed!",
					buildAssertError(enumVal, enumVal2)), e.getMessage());
		}
	}
}
