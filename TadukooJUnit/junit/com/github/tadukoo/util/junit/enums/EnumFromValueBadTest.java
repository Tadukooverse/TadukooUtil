package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.functional.NoException;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer3;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.junit.DefaultTestValues;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildAssertError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildMultiPartError;
import static com.github.tadukoo.util.junit.AssertionFailedErrors.buildTwoPartError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class EnumFromValueBadTest<V, E extends Enum<?>> implements DefaultTestValues{
	private final ThrowingConsumer<ThrowingFunction<V, E, NoException>, NoException> fromValueBadFunc;
	private final ThrowingConsumer2<ThrowingFunction<V, E, NoException>, V, NoException> fromValueBadFunc2;
	private final ThrowingConsumer3<ThrowingFunction<V, E, NoException>, V, String, NoException> fromValueBadFunc3;
	private final ThrowingFunction<V, E, NoException> fromValueFunc;
	private final ThrowingFunction<V, E, NoException> badFromValueFunc;
	private final E enumVal;
	private final V badValue;
	private final String defaultCustomAssertionFailedMessage = DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE;
	
	protected EnumFromValueBadTest(
			ThrowingConsumer<ThrowingFunction<V, E, NoException>, NoException> fromValueBadFunc,
			ThrowingConsumer2<ThrowingFunction<V, E, NoException>, V, NoException> fromValueBadFunc2,
			ThrowingConsumer3<ThrowingFunction<V, E, NoException>, V, String, NoException> fromValueBadFunc3,
			ThrowingFunction<V, E, NoException> fromValueFunc, ThrowingFunction<V, E, NoException> badFromValueFunc,
			E enumVal, V badValue){
		this.fromValueBadFunc = fromValueBadFunc;
		this.fromValueBadFunc2 = fromValueBadFunc2;
		this.fromValueBadFunc3 = fromValueBadFunc3;
		this.fromValueFunc = fromValueFunc;
		this.badFromValueFunc = badFromValueFunc;
		this.enumVal = enumVal;
		this.badValue = badValue;
	}
	
	@Test
	public void testAssertFromValueBad(){
		fromValueBadFunc.accept(fromValueFunc);
	}
	
	@Test
	public void testAssertFromValueBadFail(){
		try{
			fromValueBadFunc.accept(badFromValueFunc);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum returned non-null for bad value!",
							buildAssertError(null, enumVal.toString())),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertFromValueBadCustomValue(){
		fromValueBadFunc2.accept(fromValueFunc, badValue);
	}
	
	@Test
	public void testAssertFromValueBadCustomValueFail(){
		try{
			fromValueBadFunc2.accept(badFromValueFunc, badValue);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildTwoPartError("enum returned non-null for bad value!",
							buildAssertError(null, enumVal.toString())),
					e.getMessage());
		}
	}
	
	@Test
	public void testAssertFromValueBadCustomValueCustomMessage(){
		fromValueBadFunc3.accept(fromValueFunc, badValue, defaultCustomAssertionFailedMessage);
	}
	
	@Test
	public void testAssertFromValueBadCustomValueCustomMessageFail(){
		try{
			fromValueBadFunc3.accept(badFromValueFunc, badValue, defaultCustomAssertionFailedMessage);
			fail();
		}catch(AssertionFailedError e){
			assertEquals(buildMultiPartError(defaultCustomAssertionFailedMessage,
							"enum returned non-null for bad value!", buildAssertError(null, enumVal.toString())),
					e.getMessage());
		}
	}
}
