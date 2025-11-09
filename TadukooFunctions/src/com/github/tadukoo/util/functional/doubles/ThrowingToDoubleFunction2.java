package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.function.ThrowingFunction2;

/**
 * Represents a {@link ThrowingFunction} that takes two inputs and returns a double
 *
 * @param <A> The 1st input type
 * @param <B> The 2nd input type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingToDoubleFunction2<A, B, T extends Throwable> extends ThrowingFunction2<A, B, Double, T>{
}
