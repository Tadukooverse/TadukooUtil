package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.functional.consumer.Consumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.integer.IntConsumer;
import com.github.tadukoo.util.functional.integer.ThrowingIntBinaryOperator;
import com.github.tadukoo.util.functional.integer.ThrowingIntConsumer;
import com.github.tadukoo.util.functional.integer.ThrowingIntFunction;
import com.github.tadukoo.util.functional.integer.ThrowingIntPredicate;
import com.github.tadukoo.util.functional.integer.ThrowingIntToDoubleFunction;
import com.github.tadukoo.util.functional.integer.ThrowingIntToLongFunction;
import com.github.tadukoo.util.functional.integer.ThrowingIntUnaryOperator;
import com.github.tadukoo.util.functional.integer.ThrowingObjIntConsumer;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Throwing Stream is a wrapper around {@link IntStream} that allows for the methods to throw {@link Throwable Throwables}
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
public class ThrowingIntStream extends ThrowingBaseStream<Integer, IntStream, ThrowingIntStream>{
	
	/**
	 * Constructs a new {@link ThrowingIntStream} by wrapping the given {@link IntStream} object
	 *
	 * @param stream The {@link IntStream} object being wrapped
	 */
	private ThrowingIntStream(IntStream stream){
		super(stream);
	}
	
	/**
	 * Wraps the given {@link IntStream} as a {@link ThrowingIntStream}
	 *
	 * @param stream The {@link IntStream} to be wrapped
	 * @return The {@link ThrowingIntStream} made by wrapping the {@link IntStream}
	 */
	public static ThrowingIntStream from(IntStream stream){
		return new ThrowingIntStream(stream);
	}
	
	/**
	 * Returns an empty sequential {@code ThrowingIntStream}.
	 *
	 * @return an empty sequential stream
	 * @see IntStream#empty()
	 */
	public static ThrowingIntStream empty(){
		return new ThrowingIntStream(IntStream.empty());
	}
	
	/**
	 * Returns a sequential {@code ThrowingIntStream} containing a single element.
	 *
	 * @param item the single element
	 * @return a singleton sequential stream
	 * @see IntStream#of(int)
	 */
	public static ThrowingIntStream of(int item){
		return new ThrowingIntStream(IntStream.of(item));
	}
	
	/**
	 * Returns a sequential ordered stream whose elements are the specified values.
	 *
	 * @param items the elements of the new stream
	 * @return the new stream
	 * @see IntStream#of(int...)
	 */
	public static ThrowingIntStream of(int ... items){
		return new ThrowingIntStream(IntStream.of(items));
	}
	
	/**
	 * Returns an infinite sequential ordered {@code ThrowingIntStream} produced by iterative
	 * application of a function {@code func} to an initial element {@code seed},
	 * producing a {@code Stream} consisting of {@code seed}, {@code func(seed)},
	 * {@code func(func(seed))}, etc.
	 *
	 * <p>The first element (position {@code 0}) in the {@code ThrowingIntStream} will be
	 * the provided {@code seed}.  For {@code n > 0}, the element at position
	 * {@code n}, will be the result of applying the function {@code func} to the
	 * element at position {@code n - 1}.
	 *
	 * <p>The action of applying {@code func} for one element
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a>
	 * the action of applying {@code func} for subsequent elements.  For any given
	 * element the action may be performed in whatever thread the library
	 * chooses.
	 *
	 * @param seed the initial element
	 * @param func a function to be applied to the previous element to produce a new element
	 * @return a new sequential {@code ThrowingIntStream}
	 * @see IntStream#iterate(int, IntUnaryOperator)
	 */
	public static ThrowingIntStream iterate(final int seed, final IntUnaryOperator func){
		return new ThrowingIntStream(IntStream.iterate(seed, func));
	}
	
	/**
	 * Returns a sequential ordered {@code ThrowingIntStream} produced by iterative
	 * application of the given {@code next} function to an initial element,
	 * conditioned on satisfying the given {@code hasNext} predicate.  The
	 * stream terminates as soon as the {@code hasNext} predicate returns false.
	 *
	 * <p>{@code IntStream.iterate} should produce the same sequence of elements as
	 * produced by the corresponding for-loop:
	 * <pre>{@code
	 *     for (int index=seed; hasNext.test(index); index = next.applyAsInt(index)) {
	 *         ...
	 *     }
	 * }</pre>
	 *
	 * <p>The resulting sequence may be empty if the {@code hasNext} predicate
	 * does not hold on the seed value.  Otherwise, the first element will be the
	 * supplied {@code seed} value, the next element (if present) will be the
	 * result of applying the {@code next} function to the {@code seed} value,
	 * and so on iteratively until the {@code hasNext} predicate indicates that
	 * the stream should terminate.
	 *
	 * <p>The action of applying the {@code hasNext} predicate to an element
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a>
	 * the action of applying the {@code next} function to that element.  The
	 * action of applying the {@code next} function for one element
	 * <i>happens-before</i> the action of applying the {@code hasNext}
	 * predicate for subsequent elements.  For any given element an action may
	 * be performed in whatever thread the library chooses.
	 *
	 * @param seed the initial element
	 * @param hasNext a predicate to apply to elements to determine when the stream must terminate.
	 * @param next a function to be applied to the previous element to produce a new element
	 * @return a new sequential {@code ThrowingIntStream}
	 * @see IntStream#iterate(int, IntPredicate, IntUnaryOperator)
	 */
	public static ThrowingIntStream iterate(int seed, IntPredicate hasNext, IntUnaryOperator next){
		return new ThrowingIntStream(IntStream.iterate(seed, hasNext, next));
	}
	
	/**
	 * Returns an infinite sequential unordered stream where each element is
	 * generated by the provided {@code IntSupplier}.  This is suitable for
	 * generating constant streams, streams of random elements, etc.
	 *
	 * @param supplier the {@code IntSupplier} for generated elements
	 * @return a new infinite sequential unordered {@code ThrowingIntStream}
	 * @see IntStream#generate(IntSupplier)
	 */
	public static ThrowingIntStream generate(IntSupplier supplier){
		return new ThrowingIntStream(IntStream.generate(supplier));
	}
	
	/**
	 * Returns a sequential ordered {@code ThrowingIntStream} from {@code startInclusive}
	 * (inclusive) to {@code endExclusive} (exclusive) by an incremental step of
	 * {@code 1}.
	 * <br><br>
	 * <b>apiNote:</b>
	 * <p>An equivalent sequence of increasing values can be produced
	 * sequentially using a {@code for} loop as follows:
	 * <pre>{@code
	 *     for (int i = startInclusive; i < endExclusive ; i++) { ... }
	 * }</pre>
	 *
	 * @param startInclusive the (inclusive) initial value
	 * @param endExclusive the exclusive upper bound
	 * @return a sequential {@code ThrowingIntStream} for the range of {@code int} elements
	 * @see IntStream#range(int, int)
	 */
	public static ThrowingIntStream range(int startInclusive, int endExclusive){
		return new ThrowingIntStream(IntStream.range(startInclusive, endExclusive));
	}
	
	/**
	 * Returns a sequential ordered {@code ThrowingIntStream} from {@code startInclusive}
	 * (inclusive) to {@code endInclusive} (inclusive) by an incremental step of
	 * {@code 1}.
	 * <br><br>
	 * <b>apiNote:</b>
	 * <p>An equivalent sequence of increasing values can be produced
	 * sequentially using a {@code for} loop as follows:
	 * <pre>{@code
	 *     for (int i = startInclusive; i <= endInclusive ; i++) { ... }
	 * }</pre>
	 *
	 * @param startInclusive the (inclusive) initial value
	 * @param endInclusive the inclusive upper bound
	 * @return a sequential {@code ThrowingIntStream} for the range of {@code int} elements
	 * @see IntStream#rangeClosed(int, int)
	 */
	public static ThrowingIntStream rangeClosed(int startInclusive, int endInclusive){
		return new ThrowingIntStream(IntStream.rangeClosed(startInclusive, endInclusive));
	}
	
	/**
	 * Creates a lazily concatenated stream whose elements are all the
	 * elements of the first stream followed by all the elements of the
	 * second stream.  The resulting stream is ordered if both
	 * of the input streams are ordered, and parallel if either of the input
	 * streams is parallel.  When the resulting stream is closed, the close
	 * handlers for both input streams are invoked.
	 *
	 * <p>This method operates on the two input streams and binds each stream
	 * to its source.  As a result subsequent modifications to an input stream
	 * source may not be reflected in the concatenated stream result.
	 * <br><br>
	 * <b>implNote</b>
	 * Use caution when constructing streams from repeated concatenation.
	 * Accessing an element of a deeply concatenated stream can result in deep
	 * call chains, or even {@code StackOverflowError}.
	 * <br><br>
	 * <b>apiNote:</b>
	 * To preserve optimization opportunities this method binds each stream to
	 * its source and accepts only two streams as parameters.  For example, the
	 * exact size of the concatenated stream source can be computed if the exact
	 * size of each input stream source is known.
	 * To concatenate more streams without binding, or without nested calls to
	 * this method, try creating a stream of streams and flat-mapping with the
	 * identity function, for example:
	 * <pre>{@code
	 *     IntStream concat = Stream.of(s1, s2, s3, s4).flatMapToInt(s -> s);
	 * }</pre>
	 *
	 * @param a the first stream
	 * @param b the second stream
	 * @return the concatenation of the two input streams
	 * @see IntStream#concat(IntStream, IntStream)
	 */
	public static ThrowingIntStream concat(ThrowingIntStream a, ThrowingIntStream b){
		return new ThrowingIntStream(IntStream.concat(a.stream(), b.stream()));
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingIntStream sequential(){
		return ThrowingIntStream.from(stream.sequential());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingIntStream parallel(){
		return ThrowingIntStream.from(stream.parallel());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingIntStream unordered(){
		return ThrowingIntStream.from(stream.unordered());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingIntStream onClose(Runnable closeHandler){
		return ThrowingIntStream.from(stream.onClose(closeHandler));
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream that match
	 * the given predicate.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} the predicate can throw
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to each element to determine if it
	 *                  should be included
	 * @return the new stream
	 * @throws T If the predicate throws it
	 * @see IntStream#filter(IntPredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream filter(ThrowingIntPredicate<T> predicate) throws T{
		return ThrowingIntStream.from(stream.filter(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a stream consisting of the results of applying the given
	 * function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} that the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see IntStream#map(IntUnaryOperator)
	 */
	public <T extends Throwable> ThrowingIntStream map(ThrowingIntUnaryOperator<T> mapper) throws T{
		return ThrowingIntStream.from(stream.map(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns an object-valued {@code Stream} consisting of the results of
	 * applying the given function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">
	 *     intermediate operation</a>.
	 *
	 * @param <U> the element type of the new stream
	 * @param <T> A {@link Throwable} the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see IntStream#mapToObj(IntFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <U, T extends Throwable> ThrowingStream<U> mapToObj(ThrowingIntFunction<? extends U, T> mapper) throws T{
		return ThrowingStream.from(stream.mapToObj(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@code ThrowingLongStream} consisting of the results of applying the
	 * given function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see IntStream#mapToLong(IntToLongFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingLongStream mapToLong(ThrowingIntToLongFunction<T> mapper) throws T{
		return ThrowingLongStream.from(stream.mapToLong(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@code ThrowingDoubleStream} consisting of the results of applying the
	 * given function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the mapper
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see IntStream#mapToDouble(IntToDoubleFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream mapToDouble(ThrowingIntToDoubleFunction<T> mapper) throws T{
		return ThrowingDoubleStream.from(stream.mapToDouble(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a stream consisting of the results of replacing each element of
	 * this stream with the contents of a mapped stream produced by applying
	 * the provided mapping function to each element.  Each mapped stream is
	 * {@link java.util.stream.BaseStream#close() closed} after its contents
	 * have been placed into this stream.  (If a mapped stream is {@code null}
	 * an empty stream is used, instead.)
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the mapper
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element which produces an
	 *               {@code ThrowingIntStream} of new values
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see ThrowingStream#flatMap(ThrowingFunction)
	 * @see IntStream#flatMap(IntFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream flatMap(ThrowingIntFunction<? extends IntStream, T> mapper) throws T{
		return ThrowingIntStream.from(stream.flatMap(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a stream consisting of the results of replacing each element of
	 * this stream with multiple elements, specifically zero or more elements.
	 * Replacement is performed by applying the provided mapping function to each
	 * element in conjunction with a {@linkplain Consumer consumer} argument
	 * that accepts replacement elements. The mapping function calls the consumer
	 * zero or more times to provide the replacement elements.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * <p>If the {@linkplain Consumer} argument is used outside the scope of
	 * its application to the mapping function, the results are undefined.
	 * <br><br>
	 * <b>implSpec:</b>
	 * The default implementation invokes {@link #flatMap flatMap} on this stream,
	 * passing a function that behaves as follows. First, it calls the mapper function
	 * with an {@code IntConsumer} that accumulates replacement elements into a newly created
	 * internal buffer. When the mapper function returns, it creates an {@code IntStream} from the
	 * internal buffer. Finally, it returns this stream to {@code flatMap}.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the mapper
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function that generates replacement elements
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see ThrowingStream#mapMulti ThrowingStream.mapMulti
	 * @see IntStream#mapMulti(IntStream.IntMapMultiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream mapMulti(ThrowingConsumer2<Integer, IntConsumer, T> mapper) throws T{
		return ThrowingIntStream.from(stream.flatMap(item -> {
			try{
				List<Integer> results = new ArrayList<>();
				mapper.accept(item, results::add);
				return results.stream().mapToInt(a -> a);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a stream consisting of the distinct elements of this stream.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 *
	 * @return the new stream
	 * @see IntStream#distinct()
	 */
	public ThrowingIntStream distinct(){
		return ThrowingIntStream.from(stream.distinct());
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream in sorted
	 * order.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 *
	 * @return the new stream
	 * @see IntStream#sorted()
	 */
	public ThrowingIntStream sorted(){
		return ThrowingIntStream.from(stream.sorted());
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream, additionally
	 * performing the provided action on each element as elements are consumed
	 * from the resulting stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * <p>For parallel stream pipelines, the action may be called at
	 * whatever time and in whatever thread the element is made available by the
	 * upstream operation.  If the action modifies shared state,
	 * it is responsible for providing the required synchronization.
	 * <br><br>
	 * <b>apiNote:</b> This method exists mainly to support debugging, where you want
	 * to see the elements as they flow past a certain point in a pipeline:
	 * <pre>{@code
	 *     IntStream.of(1, 2, 3, 4)
	 *         .filter(e -> e > 2)
	 *         .peek(e -> System.out.println("Filtered value: " + e))
	 *         .map(e -> e * e)
	 *         .peek(e -> System.out.println("Mapped value: " + e))
	 *         .sum();
	 * }</pre>
	 *
	 * <p>In cases where the stream implementation is able to optimize away the
	 * production of some or all the elements (such as with short-circuiting
	 * operations like {@code findFirst}, or in the example described in
	 * {@link #count}), the action will not be invoked for those elements.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the action
	 * @param action a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">
	 *               non-interfering</a> action to perform on the elements as
	 *               they are consumed from the stream
	 * @return the new stream
	 * @throws T If the action throws it
	 * @see IntStream#peek(java.util.function.IntConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream peek(ThrowingIntConsumer<T> action) throws T{
		return ThrowingIntStream.from(stream.peek(item -> {
			try{
				action.accept(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream, truncated
	 * to be no longer than {@code maxSize} in length.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * stateful intermediate operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>
	 * While {@code limit()} is generally a cheap operation on sequential
	 * stream pipelines, it can be quite expensive on ordered parallel pipelines,
	 * especially for large values of {@code maxSize}, since {@code limit(n)}
	 * is constrained to return not just any <em>n</em> elements, but the
	 * <em>first n</em> elements in the encounter order.  Using an unordered
	 * stream source (such as {@link #generate(IntSupplier)}) or removing the
	 * ordering constraint with {@link #unordered()} may result in significant
	 * speedups of {@code limit()} in parallel pipelines, if the semantics of
	 * your situation permit.  If consistency with encounter order is required,
	 * and you are experiencing poor performance or memory utilization with
	 * {@code limit()} in parallel pipelines, switching to sequential execution
	 * with {@link #sequential()} may improve performance.
	 *
	 * @param maxSize the number of elements the stream should be limited to
	 * @return the new stream
	 * @throws IllegalArgumentException if {@code maxSize} is negative
	 * @see IntStream#limit(long)
	 */
	public ThrowingIntStream limit(long maxSize){
		return ThrowingIntStream.from(stream.limit(maxSize));
	}
	
	/**
	 * Returns a stream consisting of the remaining elements of this stream
	 * after discarding the first {@code n} elements of the stream.
	 * If this stream contains fewer than {@code n} elements then an
	 * empty stream will be returned.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>>
	 * While {@code skip()} is generally a cheap operation on sequential
	 * stream pipelines, it can be quite expensive on ordered parallel pipelines,
	 * especially for large values of {@code n}, since {@code skip(n)}
	 * is constrained to skip not just any <em>n</em> elements, but the
	 * <em>first n</em> elements in the encounter order.  Using an unordered
	 * stream source (such as {@link #generate(IntSupplier)}) or removing the
	 * ordering constraint with {@link #unordered()} may result in significant
	 * speedups of {@code skip()} in parallel pipelines, if the semantics of
	 * your situation permit.  If consistency with encounter order is required,
	 * and you are experiencing poor performance or memory utilization with
	 * {@code skip()} in parallel pipelines, switching to sequential execution
	 * with {@link #sequential()} may improve performance.
	 *
	 * @param n the number of leading elements to skip
	 * @return the new stream
	 * @throws IllegalArgumentException if {@code n} is negative
	 * @see IntStream#skip(long)
	 */
	public ThrowingIntStream skip(long n){
		return ThrowingIntStream.from(stream.skip(n));
	}
	
	/**
	 * Returns, if this stream is ordered, a stream consisting of the longest
	 * prefix of elements taken from this stream that match the given predicate.
	 * Otherwise, returns, if this stream is unordered, a stream consisting of a
	 * subset of elements taken from this stream that match the given predicate.
	 *
	 * <p>If this stream is ordered then the longest prefix is a contiguous
	 * sequence of elements of this stream that match the given predicate.  The
	 * first element of the sequence is the first element of this stream, and
	 * the element immediately following the last element of the sequence does
	 * not match the given predicate.
	 *
	 * <p>If this stream is unordered, and some (but not all) elements of this
	 * stream match the given predicate, then the behavior of this operation is
	 * nondeterministic; it is free to take any subset of matching elements
	 * (which includes the empty set).
	 *
	 * <p>Independent of whether this stream is ordered or unordered if all
	 * elements of this stream match the given predicate then this operation
	 * takes all elements (the result is the same as the input), or if no
	 * elements of the stream match the given predicate then no elements are
	 * taken (the result is an empty stream).
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * stateful intermediate operation</a>.
	 * <br><br>
	 * <b>implSpec:</b>
	 * The default implementation obtains the {@link #spliterator() spliterator}
	 * of this stream, wraps that spliterator to support the semantics
	 * of this operation on traversal, and returns a new stream associated with
	 * the wrapped spliterator.  The returned stream preserves the execution
	 * characteristics of this stream (namely parallel or sequential execution
	 * as per {@link #isParallel()}) but the wrapped spliterator may choose to
	 * not support splitting.  When the returned stream is closed, the close
	 * handlers for both the returned and this stream are invoked.
	 * <br><br>
	 * <b>apiNote:</b>
	 * While {@code takeWhile()} is generally a cheap operation on sequential
	 * stream pipelines, it can be quite expensive on ordered parallel
	 * pipelines, since the operation is constrained to return not just any
	 * valid prefix, but the longest prefix of elements in the encounter order.
	 * Using an unordered stream source (such as {@link #generate(IntSupplier)})
	 * or removing the ordering constraint with {@link #unordered()} may result
	 * in significant speedups of {@code takeWhile()} in parallel pipelines, if
	 * the semantics of your situation permit.  If consistency with encounter
	 * order is required, and you are experiencing poor performance or memory
	 * utilization with {@code takeWhile()} in parallel pipelines, switching to
	 * sequential execution with {@link #sequential()} may improve performance.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the predicate
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to elements to determine the longest
	 *                  prefix of elements.
	 * @return the new stream
	 * @throws T If the predicate throws it
	 * @see IntStream#takeWhile(IntPredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream takeWhile(ThrowingIntPredicate<T> predicate) throws T{
		return ThrowingIntStream.from(stream.takeWhile(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns, if this stream is ordered, a stream consisting of the remaining
	 * elements of this stream after dropping the longest prefix of elements
	 * that match the given predicate.  Otherwise, returns, if this stream is
	 * unordered, a stream consisting of the remaining elements of this stream
	 * after dropping a subset of elements that match the given predicate.
	 *
	 * <p>If this stream is ordered then the longest prefix is a contiguous
	 * sequence of elements of this stream that match the given predicate.  The
	 * first element of the sequence is the first element of this stream, and
	 * the element immediately following the last element of the sequence does
	 * not match the given predicate.
	 *
	 * <p>If this stream is unordered, and some (but not all) elements of this
	 * stream match the given predicate, then the behavior of this operation is
	 * nondeterministic; it is free to drop any subset of matching elements
	 * (which includes the empty set).
	 *
	 * <p>Independent of whether this stream is ordered or unordered if all
	 * elements of this stream match the given predicate then this operation
	 * drops all elements (the result is an empty stream), or if no elements of
	 * the stream match the given predicate then no elements are dropped (the
	 * result is the same as the input).
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 * <br><br>
	 * <b>implSpec:</b>
	 * The default implementation obtains the {@link #spliterator() spliterator}
	 * of this stream, wraps that spliterator to support the semantics
	 * of this operation on traversal, and returns a new stream associated with
	 * the wrapped spliterator.  The returned stream preserves the execution
	 * characteristics of this stream (namely parallel or sequential execution
	 * as per {@link #isParallel()}) but the wrapped spliterator may choose to
	 * not support splitting.  When the returned stream is closed, the close
	 * handlers for both the returned and this stream are invoked.
	 * <br><br>
	 * <b>apiNote:</b>
	 * While {@code dropWhile()} is generally a cheap operation on sequential
	 * stream pipelines, it can be quite expensive on ordered parallel
	 * pipelines, since the operation is constrained to return not just any
	 * valid prefix, but the longest prefix of elements in the encounter order.
	 * Using an unordered stream source (such as {@link #generate(IntSupplier)})
	 * or removing the ordering constraint with {@link #unordered()} may result
	 * in significant speedups of {@code dropWhile()} in parallel pipelines, if
	 * the semantics of your situation permit.  If consistency with encounter
	 * order is required, and you are experiencing poor performance or memory
	 * utilization with {@code dropWhile()} in parallel pipelines, switching to
	 * sequential execution with {@link #sequential()} may improve performance.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the predicate
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to elements to determine the longest
	 *                  prefix of elements.
	 * @return the new stream
	 * @throws T If the predicate throws it
	 * @see IntStream#dropWhile(IntPredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream dropWhile(ThrowingIntPredicate<T> predicate) throws T{
		return ThrowingIntStream.from(stream.dropWhile(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Performs an action for each element of this stream.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * <p>For parallel stream pipelines, this operation does <em>not</em>
	 * guarantee to respect the encounter order of the stream, as doing so
	 * would sacrifice the benefit of parallelism.  For any given element, the
	 * action may be performed at whatever time and in whatever thread the
	 * library chooses.  If the action accesses shared state, it is
	 * responsible for providing the required synchronization.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the action
	 * @param action a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">
	 *               non-interfering</a> action to perform on the elements
	 * @throws T If the action throws it
	 * @see IntStream#forEach(java.util.function.IntConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> void forEach(ThrowingIntConsumer<T> action) throws T{
		stream.forEach(item -> {
			try{
				action.accept(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs an action for each element of this stream, guaranteeing that
	 * each element is processed in encounter order for streams that have a
	 * defined encounter order.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the action
	 * @param action a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">
	 *               non-interfering</a> action to perform on the elements
	 * @throws T If the action throws it
	 * @see #forEach(ThrowingIntConsumer)
	 * @see IntStream#forEachOrdered(java.util.function.IntConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> void forEachOrdered(ThrowingIntConsumer<T> action) throws T{
		stream.forEachOrdered(item -> {
			try{
				action.accept(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns an array containing the elements of this stream.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an array containing the elements of this stream
	 * @see IntStream#toArray()
	 */
	public int[] toArray(){
		return stream.toArray();
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> on the
	 * elements of this stream, using the provided identity value and an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>
	 * accumulation function, and returns the reduced value.  This is equivalent
	 * to:
	 * <pre>{@code
	 *     int result = identity;
	 *     for (int element : this stream)
	 *         result = accumulator.applyAsInt(result, element)
	 *     return result;
	 * }</pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>The {@code identity} value must be an identity for the accumulator
	 * function. This means that for all {@code x},
	 * {@code accumulator.apply(identity, x)} is equal to {@code x}.
	 * The {@code accumulator} function must be an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a> function.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 * <br><br>
	 * <b>apiNote:</b> Sum, min and max are all special cases of reduction that can be
	 * expressed using this method.
	 * For example, summing a stream can be expressed as:
	 *
	 * <pre>{@code
	 *     int sum = integers.reduce(0, (a, b) -> a+b);
	 * }</pre>
	 *
	 * or more compactly:
	 *
	 * <pre>{@code
	 *     int sum = integers.reduce(0, Integer::sum);
	 * }</pre>
	 *
	 * <p>While this may seem a more roundabout way to perform an aggregation
	 * compared to simply mutating a running total in a loop, reduction
	 * operations parallelize more gracefully, without needing additional
	 * synchronization and with greatly reduced risk of data races.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the op
	 * @param identity the identity value for the accumulating function
	 * @param op an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *           <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *           <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *           function for combining two values
	 * @return the result of the reduction
	 * @throws T If the op throws it
	 * @see #sum()
	 * @see #min()
	 * @see #max()
	 * @see #average()
	 * @see IntStream#reduce(int, IntBinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> int reduce(int identity, ThrowingIntBinaryOperator<T> op) throws T{
		return stream.reduce(identity, (item1, item2) -> {
			try{
				return op.apply(item1, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> on the
	 * elements of this stream, using an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a> accumulation
	 * function, and returns an {@code OptionalInt} describing the reduced value,
	 * if any. This is equivalent to:
	 * <pre>{@code
	 *     boolean foundAny = false;
	 *     int result = null;
	 *     for (int element : this stream) {
	 *         if (!foundAny) {
	 *             foundAny = true;
	 *             result = element;
	 *         }
	 *         else
	 *             result = accumulator.applyAsInt(result, element);
	 *     }
	 *     return foundAny ? OptionalInt.of(result) : OptionalInt.empty();
	 * }</pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>The {@code accumulator} function must be an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a> function.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the op
	 * @param op an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *           <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *           <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *           function for combining two values
	 * @return the result of the reduction
	 * @throws T If the op throws it
	 * @see #reduce(int, ThrowingIntBinaryOperator)
	 * @see IntStream#reduce(IntBinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> OptionalInt reduce(ThrowingIntBinaryOperator<T> op) throws T{
		return stream.reduce((item1, item2) -> {
			try{
				return op.apply(item1, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#MutableReduction">mutable
	 * reduction</a> operation on the elements of this stream.  A mutable
	 * reduction is one in which the reduced value is a mutable result container,
	 * such as an {@code ArrayList}, and elements are incorporated by updating
	 * the state of the result rather than by replacing the result.  This
	 * produces a result equivalent to:
	 * <pre>{@code
	 *     R result = supplier.get();
	 *     for (int element : this stream)
	 *         accumulator.accept(result, element);
	 *     return result;
	 * }</pre>
	 *
	 * <p>Like {@link #reduce(int, ThrowingIntBinaryOperator)}, {@code collect} operations
	 * can be parallelized without requiring additional synchronization.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @param <R> the type of the mutable result container
	 * @param <T> A {@link Throwable} that can be thrown by the supplier
	 * @param <T2> A {@link Throwable} that can be thrown by the accumulator
	 * @param <T3> A {@link Throwable} that can be thrown by the combiner
	 * @param supplier a function that creates a new mutable result container.
	 *                 For a parallel execution, this function may be called
	 *                 multiple times and must return a fresh value each time.
	 * @param accumulator an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                    function that must fold an element into a result
	 *                    container.
	 * @param combiner an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                    function that accepts two partial result containers
	 *                    and merges them, which must be compatible with the
	 *                    accumulator function.  The combiner function must fold
	 *                    the elements from the second result container into the
	 *                    first result container.
	 * @return the result of the reduction
	 * @throws T If the supplier throws it
	 * @throws T2 If the accumulator throws it
	 * @throws T3 If the combiner throws it
	 * @see Stream#collect(Supplier, BiConsumer, BiConsumer)
	 * @see IntStream#collect(Supplier, ObjIntConsumer, BiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <R, T extends Throwable, T2 extends Throwable, T3 extends Throwable> R collect(
			ThrowingSupplier<R, T> supplier, ThrowingObjIntConsumer<R, T2> accumulator,
			ThrowingConsumer2<R, R, T3> combiner) throws T, T2, T3{
		return stream.collect(() -> {
			try{
				return supplier.get();
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}, (item, item2) -> {
			try{
				accumulator.accept(item, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}, (item, item2) -> {
			try{
				combiner.accept(item, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns the sum of elements in this stream.  This is a special case
	 * of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>
	 * and is equivalent to:
	 * <pre>{@code
	 *     return reduce(0, Integer::sum);
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return the sum of elements in this stream
	 * @see IntStream#sum()
	 */
	public int sum(){
		return stream.sum();
	}
	
	/**
	 * Returns an {@code OptionalInt} describing the minimum element of this
	 * stream, or an empty optional if this stream is empty.  This is a special
	 * case of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>
	 * and is equivalent to:
	 * <pre>{@code
	 *     return reduce(Integer::min);
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @return an {@code OptionalInt} containing the minimum element of this
	 * stream, or an empty {@code OptionalInt} if the stream is empty
	 * @see IntStream#min()
	 */
	public OptionalInt min(){
		return stream.min();
	}
	
	/**
	 * Returns an {@code OptionalInt} describing the maximum element of this
	 * stream, or an empty optional if this stream is empty.  This is a special
	 * case of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>
	 * and is equivalent to:
	 * <pre>{@code
	 *     return reduce(Integer::max);
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an {@code OptionalInt} containing the maximum element of this
	 * stream, or an empty {@code OptionalInt} if the stream is empty
	 * @see IntStream#max()
	 */
	public OptionalInt max(){
		return stream.max();
	}
	
	/**
	 * Returns the count of elements in this stream.  This is a special case of
	 * a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> and is
	 * equivalent to:
	 * <pre>{@code
	 *     return mapToLong(e -> 1L).sum();
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>
	 * An implementation may choose to not execute the stream pipeline (either
	 * sequentially or in parallel) if it is capable of computing the count
	 * directly from the stream source.  In such cases no source elements will
	 * be traversed and no intermediate operations will be evaluated.
	 * Behavioral parameters with side effects, which are strongly discouraged
	 * except for harmless cases such as debugging, may be affected.  For
	 * example, consider the following stream:
	 * <pre>{@code
	 *     IntStream s = IntStream.of(1, 2, 3, 4);
	 *     long count = s.peek(System.out::println).count();
	 * }</pre>
	 * The number of elements covered by the stream source is known and the
	 * intermediate operation, {@code peek}, does not inject into or remove
	 * elements from the stream (as may be the case for {@code flatMap} or
	 * {@code filter} operations).  Thus, the count is 4 and there is no need to
	 * execute the pipeline and, as a side effect, print out the elements.
	 *
	 * @return the count of elements in this stream
	 * @see IntStream#count()
	 */
	public long count(){
		return stream.count();
	}
	
	/**
	 * Returns an {@code OptionalDouble} describing the arithmetic mean of elements of
	 * this stream, or an empty optional if this stream is empty.  This is a
	 * special case of a
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an {@code OptionalDouble} containing the average element of this
	 * stream, or an empty optional if the stream is empty
	 * @see IntStream#average()
	 */
	public OptionalDouble average(){
		return stream.average();
	}
	
	/**
	 * Returns an {@code IntSummaryStatistics} describing various
	 * summary data about the elements of this stream.  This is a special
	 * case of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an {@code IntSummaryStatistics} describing various summary data
	 * about the elements of this stream
	 * @see IntStream#summaryStatistics()
	 */
	public IntSummaryStatistics summaryStatistics(){
		return stream.summaryStatistics();
	}
	
	/**
	 * Returns whether any elements of this stream match the provided
	 * predicate.  May not evaluate the predicate on all elements if not
	 * necessary for determining the result.  If the stream is empty then
	 * {@code false} is returned and the predicate is not evaluated.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>
	 * This method evaluates the <em>existential quantification</em> of the
	 * predicate over the elements of the stream (for some x P(x)).
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the predicate
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to elements of this stream
	 * @return {@code true} if any elements of the stream match the provided
	 * predicate, otherwise {@code false}
	 * @throws T If the predicate throws it
	 * @see IntStream#anyMatch(IntPredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean anyMatch(ThrowingIntPredicate<T> predicate) throws T{
		return stream.anyMatch(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns whether all elements of this stream match the provided predicate.
	 * May not evaluate the predicate on all elements if not necessary for
	 * determining the result.  If the stream is empty then {@code true} is
	 * returned and the predicate is not evaluated.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>
	 * This method evaluates the <em>universal quantification</em> of the
	 * predicate over the elements of the stream (for all x P(x)).  If the
	 * stream is empty, the quantification is said to be <em>vacuously
	 * satisfied</em> and is always {@code true} (regardless of P(x)).
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the predicate
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to elements of this stream
	 * @return {@code true} if either all elements of the stream match the
	 * provided predicate or the stream is empty, otherwise {@code false}
	 * @throws T If the predicate throws it
	 * @see IntStream#allMatch(IntPredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean allMatch(ThrowingIntPredicate<T> predicate) throws T{
		return stream.allMatch(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns whether no elements of this stream match the provided predicate.
	 * May not evaluate the predicate on all elements if not necessary for
	 * determining the result.  If the stream is empty then {@code true} is
	 * returned and the predicate is not evaluated.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>
	 * This method evaluates the <em>universal quantification</em> of the
	 * negated predicate over the elements of the stream (for all x ~P(x)).  If
	 * the stream is empty, the quantification is said to be vacuously satisfied
	 * and is always {@code true}, regardless of P(x).
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the predicate
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to elements of this stream
	 * @return {@code true} if either no elements of the stream match the
	 * provided predicate or the stream is empty, otherwise {@code false}
	 * @throws T If the predicate throws it
	 * @see IntStream#noneMatch(IntPredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean noneMatch(ThrowingIntPredicate<T> predicate) throws T{
		return stream.noneMatch(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns an {@link OptionalInt} describing the first element of this
	 * stream, or an empty {@code OptionalInt} if the stream is empty.  If the
	 * stream has no encounter order, then any element may be returned.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 *
	 * @return an {@code OptionalInt} describing the first element of this stream,
	 * or an empty {@code OptionalInt} if the stream is empty
	 * @see IntStream#findFirst()
	 */
	public OptionalInt findFirst(){
		return stream.findFirst();
	}
	
	/**
	 * Returns an {@link OptionalInt} describing some element of the stream, or
	 * an empty {@code OptionalInt} if the stream is empty.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 *
	 * <p>The behavior of this operation is explicitly nondeterministic; it is
	 * free to select any element in the stream.  This is to allow for maximal
	 * performance in parallel operations; the cost is that multiple invocations
	 * on the same source may not return the same result.  (If a stable result
	 * is desired, use {@link #findFirst()} instead.)
	 *
	 * @return an {@code OptionalInt} describing some element of this stream, or
	 * an empty {@code OptionalInt} if the stream is empty
	 * @see #findFirst()
	 * @see IntStream#findAny()
	 */
	public OptionalInt findAny(){
		return stream.findAny();
	}
	
	/**
	 * Returns a {@code ThrowingLongStream} consisting of the elements of this stream,
	 * converted to {@code long}.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @return a {@code ThrowingLongStream} consisting of the elements of this stream,
	 * converted to {@code long}
	 * @see IntStream#asLongStream()
	 */
	public ThrowingLongStream asLongStream(){
		return ThrowingLongStream.from(stream.asLongStream());
	}
	
	/**
	 * Returns a {@code ThrowingDoubleStream} consisting of the elements of this stream,
	 * converted to {@code double}.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @return a {@code ThrowingDoubleStream} consisting of the elements of this stream,
	 * converted to {@code double}
	 * @see IntStream#asDoubleStream()
	 */
	public ThrowingDoubleStream asDoubleStream(){
		return ThrowingDoubleStream.from(stream.asDoubleStream());
	}
	
	/**
	 * Returns a {@code ThrowingStream} consisting of the elements of this stream,
	 * each boxed to an {@code Integer}.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @return a {@code ThrowingStream} consistent of the elements of this stream,
	 * each boxed to an {@code Integer}
	 * @see IntStream#boxed()
	 */
	public ThrowingStream<Integer> boxed(){
		return ThrowingStream.from(stream.boxed());
	}
}
