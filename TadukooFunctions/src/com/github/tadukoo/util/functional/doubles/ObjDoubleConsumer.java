package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.Consumer2;

/**
 * A {@link Consumer2} that consumes something and a double
 *
 * @param <A> The 1st input type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ObjDoubleConsumer<A> extends Consumer2<A, Double>{
}
