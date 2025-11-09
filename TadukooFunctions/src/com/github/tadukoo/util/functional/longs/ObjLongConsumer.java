package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.consumer.Consumer2;

/**
 * A {@link Consumer2} that consumes something and a long
 *
 * @param <A> The 1st input type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ObjLongConsumer<A> extends Consumer2<A, Long>{
}
