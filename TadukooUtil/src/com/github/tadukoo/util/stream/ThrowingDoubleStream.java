package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.functional.consumer.Consumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.function.ThrowingFunction2;
import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Throwing Stream is a wrapper around {@link DoubleStream} that allows for the methods to throw {@link Throwable Throwables}
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
public class ThrowingDoubleStream extends ThrowingBaseStream<Double, DoubleStream, ThrowingDoubleStream>{
	
	/**
	 * Constructs a new {@link ThrowingDoubleStream} by wrapping the given {@link DoubleStream} object
	 *
	 * @param stream The {@link DoubleStream} object being wrapped
	 */
	private ThrowingDoubleStream(DoubleStream stream){
		super(stream);
	}
	
	/**
	 * Wraps the given {@link DoubleStream} as a {@link ThrowingDoubleStream}
	 *
	 * @param stream The {@link DoubleStream} to be wrapped
	 * @return The {@link ThrowingDoubleStream} made by wrapping the {@link DoubleStream}
	 */
	public static ThrowingDoubleStream from(DoubleStream stream){
		return new ThrowingDoubleStream(stream);
	}
	
	/**
	 * Returns an empty sequential {@code ThrowingDoubleStream}.
	 *
	 * @return an empty sequential stream
	 * @see DoubleStream#empty()
	 */
	public static ThrowingDoubleStream empty(){
		return new ThrowingDoubleStream(DoubleStream.empty());
	}
	
	/**
	 * Returns a sequential {@code ThrowingDoubleStream} containing a single element.
	 *
	 * @param item the single element
	 * @return a singleton sequential stream
	 * @see DoubleStream#of(double)
	 */
	public static ThrowingDoubleStream of(double item){
		return new ThrowingDoubleStream(DoubleStream.of(item));
	}
	
	/**
	 * Returns a sequential ordered stream whose elements are the specified values.
	 *
	 * @param items the elements of the new stream
	 * @return the new stream
	 * @see DoubleStream#of(double...)
	 */
	public static ThrowingDoubleStream of(double ... items){
		return new ThrowingDoubleStream(DoubleStream.of(items));
	}
	
	/**
	 * Returns an infinite sequential ordered {@code ThrowingDoubleStream} produced by iterative
	 * application of a function {@code func} to an initial element {@code seed},
	 * producing a {@code Stream} consisting of {@code seed}, {@code func(seed)},
	 * {@code func(func(seed))}, etc.
	 *
	 * <p>The first element (position {@code 0}) in the {@code ThrowingDoubleStream} will be
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
	 * @return a new sequential {@code ThrowingDoubleStream}
	 * @see DoubleStream#iterate(double, DoubleUnaryOperator)
	 */
	public static ThrowingDoubleStream iterate(final double seed, final DoubleUnaryOperator func){
		return new ThrowingDoubleStream(DoubleStream.iterate(seed, func));
	}
	
	/**
	 * Returns a sequential ordered {@code ThrowingDoubleStream} produced by iterative
	 * application of the given {@code next} function to an initial element,
	 * conditioned on satisfying the given {@code hasNext} predicate.  The
	 * stream terminates as soon as the {@code hasNext} predicate returns false.
	 *
	 * <p>{@code DoubleStream.iterate} should produce the same sequence of elements as
	 * produced by the corresponding for-loop:
	 * <pre>{@code
	 *     for (double index=seed; hasNext.test(index); index = next.applyAsDouble(index)) {
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
	 * @return a new sequential {@code ThrowingDoubleStream}
	 * @see DoubleStream#iterate(double, DoublePredicate, DoubleUnaryOperator)
	 */
	public static ThrowingDoubleStream iterate(double seed, DoublePredicate hasNext, DoubleUnaryOperator next){
		return new ThrowingDoubleStream(DoubleStream.iterate(seed, hasNext, next));
	}
	
	/**
	 * Returns an infinite sequential unordered stream where each element is
	 * generated by the provided {@code DoubleSupplier}.  This is suitable for
	 * generating constant streams, streams of random elements, etc.
	 *
	 * @param supplier the {@code DoubleSupplier} for generated elements
	 * @return a new infinite sequential unordered {@code ThrowingDoubleStream}
	 * @see DoubleStream#generate(DoubleSupplier)
	 */
	public static ThrowingDoubleStream generate(DoubleSupplier supplier){
		return new ThrowingDoubleStream(DoubleStream.generate(supplier));
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
	 *     doubleStream concat = Stream.of(s1, s2, s3, s4).flatMapToDouble(s -> s);
	 * }</pre>
	 *
	 * @param a the first stream
	 * @param b the second stream
	 * @return the concatenation of the two input streams
	 * @see DoubleStream#concat(DoubleStream, DoubleStream)
	 */
	public static ThrowingDoubleStream concat(ThrowingDoubleStream a, ThrowingDoubleStream b){
		return new ThrowingDoubleStream(DoubleStream.concat(a.stream(), b.stream()));
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingDoubleStream sequential(){
		return ThrowingDoubleStream.from(stream.sequential());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingDoubleStream parallel(){
		return ThrowingDoubleStream.from(stream.parallel());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingDoubleStream unordered(){
		return ThrowingDoubleStream.from(stream.unordered());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingDoubleStream onClose(Runnable closeHandler){
		return ThrowingDoubleStream.from(stream.onClose(closeHandler));
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
	 * @see DoubleStream#filter(DoublePredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream filter(ThrowingPredicate<Double, T> predicate) throws T{
		return ThrowingDoubleStream.from(stream.filter(item -> {
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
	 * @see DoubleStream#map(DoubleUnaryOperator)
	 */
	public <T extends Throwable> ThrowingDoubleStream map(ThrowingFunction<Double, Double, T> mapper) throws T{
		return ThrowingDoubleStream.from(stream.map(item -> {
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
	 * @see DoubleStream#mapToObj(DoubleFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <U, T extends Throwable> ThrowingStream<U> mapToObj(ThrowingFunction<Double, ? extends U, T> mapper) throws T{
		return ThrowingStream.from(stream.mapToObj(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@code ThrowingIntegerStream} consisting of the results of applying the
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
	 * @see DoubleStream#mapToInt(DoubleToIntFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream mapToInt(ThrowingFunction<Double, Integer, T> mapper) throws T{
		return ThrowingIntStream.from(stream.mapToInt(item -> {
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
	 * @param <T> A {@link Throwable} that can be thrown by the mapper
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see DoubleStream#mapToLong(DoubleToLongFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingLongStream mapToLong(ThrowingFunction<Double, Long, T> mapper) throws T{
		return ThrowingLongStream.from(stream.mapToLong(item -> {
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
	 *               {@code ThrowingDoubleStream} of new values
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see ThrowingStream#flatMap(ThrowingFunction)
	 * @see DoubleStream#flatMap(DoubleFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream flatMap(ThrowingFunction<Double, ? extends DoubleStream, T> mapper) throws T{
		return ThrowingDoubleStream.from(stream.flatMap(item -> {
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
	 * with an {@code DoubleConsumer} that accumulates replacement elements into a newly created
	 * internal buffer. When the mapper function returns, it creates an {@code doubleStream} from the
	 * internal buffer. Finally, it returns this stream to {@code flatMap}.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the mapper
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function that generates replacement elements
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see ThrowingStream#mapMulti ThrowingStream.mapMulti
	 * @see DoubleStream#mapMulti(DoubleStream.DoubleMapMultiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream mapMulti(ThrowingConsumer2<Double, Consumer<Double>, T> mapper) throws T{
		return ThrowingDoubleStream.from(stream.flatMap(item -> {
			try{
				List<Double> results = new ArrayList<>();
				mapper.accept(item, results::add);
				return results.stream().mapToDouble(a -> a);
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
	 * @see DoubleStream#distinct()
	 */
	public ThrowingDoubleStream distinct(){
		return ThrowingDoubleStream.from(stream.distinct());
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream in sorted
	 * order.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 *
	 * @return the new stream
	 * @see DoubleStream#sorted()
	 */
	public ThrowingDoubleStream sorted(){
		return ThrowingDoubleStream.from(stream.sorted());
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
	 *     DoubleStream.of(1, 2, 3, 4)
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
	 * @see DoubleStream#peek(DoubleConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream peek(ThrowingConsumer<Double, T> action) throws T{
		return ThrowingDoubleStream.from(stream.peek(item -> {
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
	 * stream source (such as {@link #generate(DoubleSupplier)}) or removing the
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
	 * @see DoubleStream#limit(long)
	 */
	public ThrowingDoubleStream limit(long maxSize){
		return ThrowingDoubleStream.from(stream.limit(maxSize));
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
	 * stream source (such as {@link #generate(DoubleSupplier)}) or removing the
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
	 * @see DoubleStream#skip(long)
	 */
	public ThrowingDoubleStream skip(long n){
		return ThrowingDoubleStream.from(stream.skip(n));
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
	 * Using an unordered stream source (such as {@link #generate(DoubleSupplier)})
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
	 * @see DoubleStream#takeWhile(DoublePredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream takeWhile(ThrowingPredicate<Double, T> predicate) throws T{
		return ThrowingDoubleStream.from(stream.takeWhile(item -> {
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
	 * Using an unordered stream source (such as {@link #generate(DoubleSupplier)})
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
	 * @see DoubleStream#dropWhile(DoublePredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream dropWhile(ThrowingPredicate<Double, T> predicate) throws T{
		return ThrowingDoubleStream.from(stream.dropWhile(item -> {
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
	 * @see DoubleStream#forEach(DoubleConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> void forEach(ThrowingConsumer<Double, T> action) throws T{
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
	 * @see #forEach(ThrowingConsumer)
	 * @see DoubleStream#forEachOrdered(DoubleConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> void forEachOrdered(ThrowingConsumer<Double, T> action) throws T{
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
	 * @see DoubleStream#toArray()
	 */
	public double[] toArray(){
		return stream.toArray();
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> on the
	 * elements of this stream, using the provided identity value and an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>
	 * accumulation function, and returns the reduced value.  This is equivalent
	 * to:
	 * <pre>{@code
	 *     double result = identity;
	 *     for (double element : this stream)
	 *         result = accumulator.applyAsDouble(result, element)
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
	 *     double sum = Doubles.reduce(0, (a, b) -> a+b);
	 * }</pre>
	 *
	 * or more compactly:
	 *
	 * <pre>{@code
	 *     double sum = Doubles.reduce(0, Double::sum);
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
	 * @see DoubleStream#reduce(double, DoubleBinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> double reduce(double identity, ThrowingFunction2<Double, Double, Double, T> op) throws T{
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
	 * function, and returns an {@code OptionalDouble} describing the reduced value,
	 * if any. This is equivalent to:
	 * <pre>{@code
	 *     boolean foundAny = false;
	 *     double result = null;
	 *     for (double element : this stream) {
	 *         if (!foundAny) {
	 *             foundAny = true;
	 *             result = element;
	 *         }
	 *         else
	 *             result = accumulator.applyAsDouble(result, element);
	 *     }
	 *     return foundAny ? OptionalDouble.of(result) : OptionalDouble.empty();
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
	 * @see #reduce(double, ThrowingFunction2)
	 * @see DoubleStream#reduce(DoubleBinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> OptionalDouble reduce(ThrowingFunction2<Double, Double, Double, T> op) throws T{
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
	 *     for (double element : this stream)
	 *         accumulator.accept(result, element);
	 *     return result;
	 * }</pre>
	 *
	 * <p>Like {@link #reduce(double, ThrowingFunction2)}, {@code collect} operations
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
	 * @see DoubleStream#collect(Supplier, ObjDoubleConsumer, BiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <R, T extends Throwable, T2 extends Throwable, T3 extends Throwable> R collect(
			ThrowingSupplier<R, T> supplier, ThrowingConsumer2<R, Double, T2> accumulator,
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
	 *     return reduce(0, Double::sum);
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return the sum of elements in this stream
	 * @see DoubleStream#sum()
	 */
	public double sum(){
		return stream.sum();
	}
	
	/**
	 * Returns an {@code OptionalDouble} describing the minimum element of this
	 * stream, or an empty optional if this stream is empty.  This is a special
	 * case of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>
	 * and is equivalent to:
	 * <pre>{@code
	 *     return reduce(Double::min);
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @return an {@code OptionalDouble} containing the minimum element of this
	 * stream, or an empty {@code OptionalDouble} if the stream is empty
	 * @see DoubleStream#min()
	 */
	public OptionalDouble min(){
		return stream.min();
	}
	
	/**
	 * Returns an {@code OptionalDouble} describing the maximum element of this
	 * stream, or an empty optional if this stream is empty.  This is a special
	 * case of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>
	 * and is equivalent to:
	 * <pre>{@code
	 *     return reduce(Double::max);
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an {@code OptionalDouble} containing the maximum element of this
	 * stream, or an empty {@code OptionalDouble} if the stream is empty
	 * @see DoubleStream#max()
	 */
	public OptionalDouble max(){
		return stream.max();
	}
	
	/**
	 * Returns the count of elements in this stream.  This is a special case of
	 * a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> and is
	 * equivalent to:
	 * <pre>{@code
	 *     return mapToInteger(e -> 1L).sum();
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
	 *     doubleStream s = DoubleStream.of(1, 2, 3, 4);
	 *     Integer count = s.peek(System.out::println).count();
	 * }</pre>
	 * The number of elements covered by the stream source is known and the
	 * intermediate operation, {@code peek}, does not inject into or remove
	 * elements from the stream (as may be the case for {@code flatMap} or
	 * {@code filter} operations).  Thus, the count is 4 and there is no need to
	 * execute the pipeline and, as a side effect, print out the elements.
	 *
	 * @return the count of elements in this stream
	 * @see DoubleStream#count()
	 */
	public long count(){
		return stream.count();
	}
	
	/**
	 * Returns an {@code OptionalLong} describing the arithmetic mean of elements of
	 * this stream, or an empty optional if this stream is empty.  This is a
	 * special case of a
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an {@code OptionalLong} containing the average element of this
	 * stream, or an empty optional if the stream is empty
	 * @see DoubleStream#average()
	 */
	public OptionalDouble average(){
		return stream.average();
	}
	
	/**
	 * Returns an {@code doubleSummaryStatistics} describing various
	 * summary data about the elements of this stream.  This is a special
	 * case of a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @return an {@code doubleSummaryStatistics} describing various summary data
	 * about the elements of this stream
	 * @see DoubleStream#summaryStatistics()
	 */
	public DoubleSummaryStatistics summaryStatistics(){
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
	 * @see DoubleStream#anyMatch(DoublePredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean anyMatch(ThrowingPredicate<Double, T> predicate) throws T{
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
	 * @see DoubleStream#allMatch(DoublePredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean allMatch(ThrowingPredicate<Double, T> predicate) throws T{
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
	 * @see DoubleStream#noneMatch(DoublePredicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean noneMatch(ThrowingPredicate<Double, T> predicate) throws T{
		return stream.noneMatch(item -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns an {@link OptionalDouble} describing the first element of this
	 * stream, or an empty {@code OptionalDouble} if the stream is empty.  If the
	 * stream has no encounter order, then any element may be returned.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 *
	 * @return an {@code OptionalDouble} describing the first element of this stream,
	 * or an empty {@code OptionalDouble} if the stream is empty
	 * @see DoubleStream#findFirst()
	 */
	public OptionalDouble findFirst(){
		return stream.findFirst();
	}
	
	/**
	 * Returns an {@link OptionalDouble} describing some element of the stream, or
	 * an empty {@code OptionalDouble} if the stream is empty.
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
	 * @return an {@code OptionalDouble} describing some element of this stream, or
	 * an empty {@code OptionalDouble} if the stream is empty
	 * @see #findFirst()
	 * @see DoubleStream#findAny()
	 */
	public OptionalDouble findAny(){
		return stream.findAny();
	}
	
	/**
	 * Returns a {@code ThrowingStream} consisting of the elements of this stream,
	 * each boxed to an {@code Double}.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @return a {@code ThrowingStream} consistent of the elements of this stream,
	 * each boxed to an {@code Double}
	 * @see DoubleStream#boxed()
	 */
	public ThrowingStream<Double> boxed(){
		return ThrowingStream.from(stream.boxed());
	}
}
