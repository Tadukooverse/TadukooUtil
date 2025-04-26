package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;
import com.github.tadukoo.util.functional.function.Function2;

/**
 * Represents a {@link Function} that takes two inputs and outputs a long
 *
 * @param <A> The 1st input type
 * @param <B> The 2nd input type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ToLongFunction2<A, B> extends Function2<A, B, Long>{
}
