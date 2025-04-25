package com.github.tadukoo.util.stream;

import com.github.tadukoo.util.functional.consumer.Consumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;
import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;
import com.github.tadukoo.util.functional.function.ThrowingFunction;
import com.github.tadukoo.util.functional.function.ThrowingFunction2;
import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;
import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Throwing Stream is a wrapper around {@link Stream} that allows for the methods to throw {@link Throwable Throwables}
 *
 * @param <A> The type used in the {@link Stream}
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
public class ThrowingStream<A> extends ThrowingBaseStream<A, Stream<A>, ThrowingStream<A>>{
	
	/**
	 * Constructs a new {@link ThrowingStream} by wrapping the given {@link Stream} object
	 *
	 * @param stream The {@link Stream} object being wrapped
	 */
	private ThrowingStream(Stream<A> stream){
		super(stream);
	}
	
	/**
	 * Wraps the given {@link Stream} as a {@link ThrowingStream}
	 *
	 * @param stream The {@link Stream} to be wrapped
	 * @param <A>    The type used in the {@link Stream}
	 * @return The {@link ThrowingStream} made by wrapping the {@link Stream}
	 */
	public static <A> ThrowingStream<A> from(Stream<A> stream){
		return new ThrowingStream<>(stream);
	}
	
	/**
	 * Returns an empty sequential {@code ThrowingStream}.
	 *
	 * @param <A> the type of stream elements
	 * @return an empty sequential stream
	 * @see Stream#empty()
	 */
	public static <A> ThrowingStream<A> empty(){
		return new ThrowingStream<>(Stream.empty());
	}
	
	/**
	 * Returns a sequential {@code ThrowingStream} containing a single element.
	 *
	 * @param item the single element
	 * @param <A> the type of stream elements
	 * @return a singleton sequential stream
	 * @see Stream#of(Object)
	 */
	public static <A> ThrowingStream<A> of(A item){
		return new ThrowingStream<>(Stream.of(item));
	}
	
	/**
	 * Returns a sequential {@code ThrowingStream} containing a single element, if
	 * non-null, otherwise returns an empty {@code ThrowingStream}.
	 *
	 * @param item the single element
	 * @param <A> the type of stream elements
	 * @return a stream with a single element if the specified element
	 *         is non-null, otherwise an empty stream
	 * @see Stream#ofNullable(Object)
	 */
	public static <A> ThrowingStream<A> ofNullable(A item){
		return new ThrowingStream<>(Stream.ofNullable(item));
	}
	
	/**
	 * Returns a sequential ordered stream whose elements are the specified values.
	 *
	 * @param <A> the type of stream elements
	 * @param items the elements of the new stream
	 * @return the new stream
	 * @see Stream#of(Object[])
	 */
	@SafeVarargs
	public static <A> ThrowingStream<A> of(A ... items){
		return new ThrowingStream<>(Stream.of(items));
	}
	
	/**
	 * Returns an infinite sequential ordered {@code ThrowingStream} produced by iterative
	 * application of a function {@code func} to an initial element {@code seed},
	 * producing a {@code ThrowingStream} consisting of {@code seed}, {@code func(seed)},
	 * {@code func(func(seed))}, etc.
	 *
	 * <p>The first element (position {@code 0}) in the {@code ThrowingStream} will be
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
	 * @param <A> the type of stream elements
	 * @param seed the initial element
	 * @param func a function to be applied to the previous element to produce
	 *          a new element
	 * @return a new sequential {@code ThrowingStream}
	 */
	public static<A> ThrowingStream<A> iterate(final A seed, final UnaryOperator<A> func){
		return new ThrowingStream<>(Stream.iterate(seed, func));
	}
	
	/**
	 * Returns a sequential ordered {@code ThrowingStream} produced by iterative
	 * application of the given {@code next} function to an initial element,
	 * conditioned on satisfying the given {@code hasNext} predicate.  The
	 * stream terminates as soon as the {@code hasNext} predicate returns false.
	 *
	 * <p>{@code Stream.iterate} should produce the same sequence of elements as
	 * produced by the corresponding for-loop:
	 * <pre>{@code
	 *     for (T index=seed; hasNext.test(index); index = next.apply(index)) {
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
	 * @param <A> the type of stream elements
	 * @param seed the initial element
	 * @param hasNext a predicate to apply to elements to determine when the
	 *                stream must terminate.
	 * @param next a function to be applied to the previous element to produce
	 *             a new element
	 * @return a new sequential {@code Stream}
	 * @see Stream#iterate(Object, Predicate, UnaryOperator)
	 */
	public static<A> ThrowingStream<A> iterate(A seed, Predicate<? super A> hasNext, UnaryOperator<A> next){
		return new ThrowingStream<>(Stream.iterate(seed, hasNext, next));
	}
	
	/**
	 * Returns an infinite sequential unordered stream where each element is
	 * generated by the provided {@code Supplier}.  This is suitable for
	 * generating constant streams, streams of random elements, etc.
	 *
	 * @param <A> the type of stream elements
	 * @param supplier the {@code Supplier} of generated elements
	 * @return a new infinite sequential unordered {@code Stream}
	 * @see Stream#generate(Supplier)
	 */
	public static<A> ThrowingStream<A> generate(Supplier<? extends A> supplier){
		return new ThrowingStream<>(Stream.generate(supplier));
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
	 * <b>implNote:</b>
	 * Use caution when constructing streams from repeated concatenation.
	 * Accessing an element of a deeply concatenated stream can result in deep
	 * call chains, or even {@code StackOverflowError}.
	 *
	 * <p>Subsequent changes to the sequential/parallel execution mode of the
	 * returned stream are not guaranteed to be propagated to the input streams.
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
	 *     Stream<T> concat = Stream.of(s1, s2, s3, s4).flatMap(s -> s);
	 * }</pre>
	 *
	 * @param <A> The type of stream elements
	 * @param a the first stream
	 * @param b the second stream
	 * @return the concatenation of the two input streams
	 * @see Stream#concat(Stream, Stream)
	 */
	public static <A> ThrowingStream<A> concat(ThrowingStream<? extends A> a, ThrowingStream<? extends A> b){
		return new ThrowingStream<>(Stream.concat(a.stream(), b.stream()));
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingStream<A> sequential(){
		return ThrowingStream.from(stream.sequential());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingStream<A> parallel(){
		return ThrowingStream.from(stream.parallel());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingStream<A> unordered(){
		return ThrowingStream.from(stream.unordered());
	}
	
	/** {@inheritDoc} */
	@Override
	public ThrowingStream<A> onClose(Runnable closeHandler){
		return ThrowingStream.from(stream.onClose(closeHandler));
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream that match
	 * the given predicate.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T>       A {@link Throwable} that the predicate can throw
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  {@link ThrowingPredicate throwing predicate} to apply to each element to determine if it
	 *                  should be included
	 * @return the new stream
	 * @throws T If the predicate throws it
	 * @see Stream#filter(Predicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingStream<A> filter(ThrowingPredicate<? super A, T> predicate) throws T{
		return new ThrowingStream<>(stream.filter(item -> {
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
	 * @param <R>    The element type of the new stream
	 * @param <T>    A {@link Throwable} that the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               {@link ThrowingFunction throwing function} to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see Stream#map(Function)
	 */
	public <R, T extends Throwable> ThrowingStream<R> map(ThrowingFunction<A, R, T> mapper) throws T{
		return new ThrowingStream<>(stream.map(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns an {@link ThrowingIntStream} consisting of the results of applying the
	 * given function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">
	 * intermediate operation</a>.
	 *
	 * @param <T>    A {@link Throwable} that the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see Stream#mapToInt(ToIntFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream mapToInt(ThrowingFunction<? super A, Integer, T> mapper) throws T{
		return ThrowingIntStream.from(stream.mapToInt(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@link ThrowingLongStream} consisting of the results of applying the
	 * given function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T>    A {@link Throwable} that the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see Stream#mapToLong(ToLongFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingLongStream mapToLong(ThrowingFunction<? super A, Long, T> mapper) throws T{
		return ThrowingLongStream.from(stream.mapToLong(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@link ThrowingDoubleStream} consisting of the results of applying the
	 * given function to the elements of this stream.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T>    {@link Throwable} that the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see Stream#mapToDouble(ToDoubleFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream mapToDouble(ThrowingFunction<? super A, Double, T> mapper) throws T{
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
	 * <br><br>
	 * <b>apiNote:</b> The {@code flatMap()} operation has the effect of applying a one-to-many
	 * transformation to the elements of the stream, and then flattening the
	 * resulting elements into a new stream.
	 *
	 * <p><b>Examples.</b>
	 *
	 * <p>If {@code orders} is a stream of purchase orders, and each purchase
	 * order contains a collection of line items, then the following produces a
	 * stream containing all the line items in all the orders:
	 * <pre>{@code
	 *     orders.flatMap(order -> order.getLineItems().stream())...
	 * }</pre>
	 *
	 * <p>If {@code path} is the path to a file, then the following produces a
	 * stream of the {@code words} contained in that file:
	 * <pre>{@code
	 *     Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
	 *     Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
	 * }</pre>
	 * The {@code mapper} function passed to {@code flatMap} splits a line,
	 * using a simple regular expression, into an array of words, and then
	 * creates a stream of words from that array.
	 *
	 * @param <R>    The element type of the new stream
	 * @param <T>    A {@link Throwable} that the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element which produces a stream
	 *               of new values
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #mapMulti
	 * @see Stream#flatMap(Function)
	 */
	@SuppressWarnings("RedundantThrows")
	public <R, T extends Throwable> ThrowingStream<R> flatMap(
			ThrowingFunction<? super A, ? extends Stream<? extends R>, T> mapper) throws T{
		return ThrowingStream.from(stream.flatMap(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns an {@link ThrowingIntStream} consisting of the results of replacing each
	 * element of this stream with the contents of a mapped stream produced by
	 * applying the provided mapping function to each element.  Each mapped
	 * stream is {@link java.util.stream.BaseStream#close() closed} after its
	 * contents have been placed into this stream.  (If a mapped stream is
	 * {@code null} an empty stream is used, instead.)
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} that the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element which produces a stream
	 *               of new values
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #flatMap(ThrowingFunction)
	 * @see Stream#flatMapToInt(Function)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream flatMapToInt(
			ThrowingFunction<? super A, ? extends IntStream, T> mapper) throws T{
		return ThrowingIntStream.from(stream.flatMapToInt(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns an {@link ThrowingLongStream} consisting of the results of replacing each
	 * element of this stream with the contents of a mapped stream produced by
	 * applying the provided mapping function to each element.  Each mapped
	 * stream is {@link java.util.stream.BaseStream#close() closed} after its
	 * contents have been placed into this stream.  (If a mapped stream is
	 * {@code null} an empty stream is used, instead.)
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T>    A {@link Throwable} the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element which produces a stream
	 *               of new values
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #flatMap(ThrowingFunction)
	 * @see Stream#flatMapToLong(Function)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingLongStream flatMapToLong(
			ThrowingFunction<? super A, ? extends LongStream, T> mapper) throws T{
		return ThrowingLongStream.from(stream.flatMapToLong(item -> {
			try{
				return mapper.apply(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns an {@link ThrowingDoubleStream} consisting of the results of replacing
	 * each element of this stream with the contents of a mapped stream produced
	 * by applying the provided mapping function to each element.  Each mapped
	 * stream is {@link java.util.stream.BaseStream#close() closed} after its
	 * contents have placed been into this stream.  (If a mapped stream is
	 * {@code null} an empty stream is used, instead.)
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param <T> A {@link Throwable} the function can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function to apply to each element which produces a stream
	 *               of new values
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #flatMap(ThrowingFunction)
	 * @see Stream#flatMapToDouble(Function)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream flatMapToDouble(
			ThrowingFunction<? super A, ? extends DoubleStream, T> mapper) throws T{
		return ThrowingDoubleStream.from(stream.flatMapToDouble(item -> {
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
	 * element in conjunction with a {@linkplain java.util.function.Consumer consumer} argument
	 * that accepts replacement elements. The mapping function calls the consumer
	 * zero or more times to provide the replacement elements.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * <p>If the {@linkplain java.util.function.Consumer consumer} argument is used outside the scope of
	 * its application to the mapping function, the results are undefined.
	 * <br><br>
	 * <b>implSpec:</b> The default implementation invokes {@link #flatMap flatMap} on this stream,
	 * passing a function that behaves as follows. First, it calls the mapper function
	 * with a {@code Consumer} that accumulates replacement elements into a newly created
	 * internal buffer. When the mapper function returns, it creates a stream from the
	 * internal buffer. Finally, it returns this stream to {@code flatMap}.
	 * <br><br>
	 * <b>apiNote:</b> This method is similar to {@link #flatMap flatMap} in that it applies a one-to-many
	 * transformation to the elements of the stream and flattens the result elements
	 * into a new stream. This method is preferable to {@code flatMap} in the following
	 * circumstances:
	 * <ul>
	 * <li>When replacing each stream element with a small (possibly zero) number of
	 * elements. Using this method avoids the overhead of creating a new Stream instance
	 * for every group of result elements, as required by {@code flatMap}.</li>
	 * <li>When it is easier to use an imperative approach for generating result
	 * elements than it is to return them in the form of a Stream.</li>
	 * </ul>
	 *
	 * <p>If a lambda expression is provided as the mapper function argument, additional type
	 * information may be necessary for proper inference of the element type {@code <R>} of
	 * the returned stream. This can be provided in the form of explicit type declarations for
	 * the lambda parameters or as an explicit type argument to the {@code mapMulti} call.
	 *
	 * <p><b>Examples</b>
	 *
	 * <p>Given a stream of {@code Number} objects, the following
	 * produces a list containing only the {@code Integer} objects:
	 * <pre>{@code
	 *     Stream<Number> numbers = ... ;
	 *     List<Integer> integers = numbers.<Integer>mapMulti((number, consumer) -> {
	 *             if (number instanceof Integer i)
	 *                 consumer.accept(i);
	 *         })
	 *         .collect(Collectors.toList());
	 * }</pre>
	 *
	 * <p>If we have an {@code Iterable<Object>} and need to recursively expand its elements
	 * that are themselves of type {@code Iterable}, we can use {@code mapMulti} as follows:
	 * <pre>{@code
	 * class C {
	 *     static void expandIterable(Object e, Consumer<Object> c) {
	 *         if (e instanceof Iterable<?> elements) {
	 *             for (Object ie : elements) {
	 *                 expandIterable(ie, c);
	 *             }
	 *         } else if (e != null) {
	 *             c.accept(e);
	 *         }
	 *     }
	 *
	 *     public static void main(String[] args) {
	 *         var nestedList = List.of(1, List.of(2, List.of(3, 4)), 5);
	 *         Stream<Object> expandedStream = nestedList.stream().mapMulti(C::expandIterable);
	 *     }
	 * }
	 * }</pre>
	 *
	 * @param <R> The element type of the new stream
	 * @param <T> A {@link Throwable} the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function that generates replacement elements
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #flatMap(ThrowingFunction)
	 * @see Stream#mapMulti(BiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <R, T extends Throwable> ThrowingStream<R> mapMulti(
			ThrowingConsumer2<? super A, Consumer<R>, T> mapper) throws T{
		return ThrowingStream.from(stream.flatMap(item -> {
			try{
				List<R> results = new ArrayList<>();
				mapper.accept(item, results::add);
				return results.stream();
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@link ThrowingIntStream} consisting of the results of replacing each
	 * element of this stream with multiple elements, specifically zero or more
	 * elements.
	 * Replacement is performed by applying the provided mapping function to each
	 * element in conjunction with a {@linkplain IntConsumer consumer} argument
	 * that accepts replacement elements. The mapping function calls the consumer
	 * zero or more times to provide the replacement elements.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * <p>If the {@linkplain IntConsumer consumer} argument is used outside the scope of
	 * its application to the mapping function, the results are undefined.
	 * <br><br>
	 * <b>implSpec:</b> The default implementation invokes {@link #flatMapToInt flatMapToInt} on this stream,
	 * passing a function that behaves as follows. First, it calls the mapper function
	 * with an {@code IntConsumer} that accumulates replacement elements into a newly created
	 * internal buffer. When the mapper function returns, it creates an {@code IntStream} from
	 * the internal buffer. Finally, it returns this stream to {@code flatMapToInt}.
	 *
	 * @param <T> A {@link Throwable} the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function that generates replacement elements
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #mapMulti(ThrowingConsumer2)
	 * @see Stream#mapMultiToInt(BiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingIntStream mapMultiToInt(
			ThrowingConsumer2<? super A, Consumer<Integer>, T> mapper) throws T{
		return ThrowingIntStream.from(stream.flatMapToInt(item -> {
			try{
				List<Integer> results = new ArrayList<>();
				mapper.accept(item, results::add);
				return results.stream().mapToInt(Integer::intValue);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@link ThrowingLongStream} consisting of the results of replacing each
	 * element of this stream with multiple elements, specifically zero or more
	 * elements.
	 * Replacement is performed by applying the provided mapping function to each
	 * element in conjunction with a {@linkplain LongConsumer consumer} argument
	 * that accepts replacement elements. The mapping function calls the consumer
	 * zero or more times to provide the replacement elements.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * <p>If the {@linkplain LongConsumer consumer} argument is used outside the scope of
	 * its application to the mapping function, the results are undefined.
	 * <br><br>
	 * <b>implSpec:</b> The default implementation invokes {@link #flatMapToLong flatMapToLong} on this stream,
	 * passing a function that behaves as follows. First, it calls the mapper function
	 * with a {@code LongConsumer} that accumulates replacement elements into a newly created
	 * internal buffer. When the mapper function returns, it creates a {@code LongStream} from
	 * the internal buffer. Finally, it returns this stream to {@code flatMapToLong}.
	 *
	 * @param <T> A {@link Throwable} the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function that generates replacement elements
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #mapMulti(ThrowingConsumer2)
	 * @see Stream#mapMultiToLong(BiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingLongStream mapMultiToLong(
			ThrowingConsumer2<? super A, Consumer<Long>, T> mapper) throws T{
		return ThrowingLongStream.from(stream.flatMapToLong(item -> {
			try{
				List<Long> results = new ArrayList<>();
				mapper.accept(item, results::add);
				return results.stream().mapToLong(Long::longValue);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a {@link ThrowingDoubleStream} consisting of the results of replacing each
	 * element of this stream with multiple elements, specifically zero or more
	 * elements.
	 * Replacement is performed by applying the provided mapping function to each
	 * element in conjunction with a {@linkplain DoubleConsumer consumer} argument
	 * that accepts replacement elements. The mapping function calls the consumer
	 * zero or more times to provide the replacement elements.
	 *
	 * <p>This is an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * <p>If the {@linkplain DoubleConsumer consumer} argument is used outside the scope of
	 * its application to the mapping function, the results are undefined.
	 * <br><br>
	 * <b>implSpec:</b> The default implementation invokes {@link #flatMapToDouble flatMapToDouble} on this stream,
	 * passing a function that behaves as follows. First, it calls the mapper function
	 * with an {@code DoubleConsumer} that accumulates replacement elements into a newly created
	 * internal buffer. When the mapper function returns, it creates a {@code DoubleStream} from
	 * the internal buffer. Finally, it returns this stream to {@code flatMapToDouble}.
	 *
	 * @param <T> A {@link Throwable} the mapper can throw
	 * @param mapper a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *               <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *               function that generates replacement elements
	 * @return the new stream
	 * @throws T If the mapper throws it
	 * @see #mapMulti(ThrowingConsumer2)
	 * @see Stream#mapMultiToDouble(BiConsumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingDoubleStream mapMultiToDouble(
			ThrowingConsumer2<? super A, Consumer<Double>, T> mapper) throws T{
		return ThrowingDoubleStream.from(stream.flatMapToDouble(item -> {
			try{
				List<Double> results = new ArrayList<>();
				mapper.accept(item, results::add);
				return results.stream().mapToDouble(Double::doubleValue);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}));
	}
	
	/**
	 * Returns a stream consisting of the distinct elements (according to
	 * {@link Object#equals(Object)}) of this stream.
	 *
	 * <p>For ordered streams, the selection of distinct elements is stable
	 * (for duplicated elements, the element appearing first in the encounter
	 * order is preserved.)  For unordered streams, no stability guarantees
	 * are made.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 * <br><br>
	 * <b>apiNote:</b> Preserving stability for {@code distinct()} in parallel pipelines is
	 * relatively expensive (requires that the operation act as a full barrier,
	 * with substantial buffering overhead), and stability is often not needed.
	 * Using an unordered stream source (such as {@link Stream#generate(Supplier)})
	 * or removing the ordering constraint with {@link #unordered()} may result
	 * in significantly more efficient execution for {@code distinct()} in parallel
	 * pipelines, if the semantics of your situation permit.  If consistency
	 * with encounter order is required, and you are experiencing poor performance
	 * or memory utilization with {@code distinct()} in parallel pipelines,
	 * switching to sequential execution with {@link #sequential()} may improve
	 * performance.
	 *
	 * @return the new stream
	 * @see Stream#distinct()
	 */
	public ThrowingStream<A> distinct(){
		return ThrowingStream.from(stream.distinct());
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream, sorted
	 * according to natural order.  If the elements of this stream are not
	 * {@code Comparable}, a {@code java.lang.ClassCastException} may be thrown
	 * when the terminal operation is executed.
	 *
	 * <p>For ordered streams, the sort is stable.  For unordered streams, no
	 * stability guarantees are made.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 *
	 * @return the new stream
	 * @see Stream#sorted()
	 */
	public ThrowingStream<A> sorted(){
		return ThrowingStream.from(stream.sorted());
	}
	
	/**
	 * Returns a stream consisting of the elements of this stream, sorted
	 * according to the provided {@code Comparator}.
	 *
	 * <p>For ordered streams, the sort is stable. For unordered streams, no
	 * stability guarantees are made.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">stateful
	 * intermediate operation</a>.
	 *
	 * @param comparator a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                   <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                   {@code Comparator} to be used to compare stream elements
	 * @return the new stream
	 * @see Stream#sorted(Comparator)
	 */
	public ThrowingStream<A> sorted(Comparator<? super A> comparator){
		return ThrowingStream.from(stream.sorted(comparator));
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
	 *     Stream.of("one", "two", "three", "four")
	 *         .filter(e -> e.length() > 3)
	 *         .peek(e -> System.out.println("Filtered value: " + e))
	 *         .map(String::toUpperCase)
	 *         .peek(e -> System.out.println("Mapped value: " + e))
	 *         .collect(Collectors.toList());
	 * }</pre>
	 *
	 * <p>In cases where the stream implementation is able to optimize away the
	 * production of some or all the elements (such as with short-circuiting
	 * operations like {@code findFirst}, or in the example described in
	 * {@link #count}), the action will not be invoked for those elements.
	 *
	 * @param <T> A {@link Throwable} the action can throw
	 * @param action a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">
	 *                 non-interfering</a> action to perform on the elements as
	 *                 they are consumed from the stream
	 * @return the new stream
	 * @throws T If the action throws it
	 * @see Stream#peek(java.util.function.Consumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingStream<A> peek(ThrowingConsumer<? super A, T> action) throws T{
		return ThrowingStream.from(stream.peek(item -> {
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
	 * stream source (such as {@link Stream#generate(Supplier)}) or removing the
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
	 * @see Stream#limit(long)
	 */
	public ThrowingStream<A> limit(long maxSize){
		return ThrowingStream.from(stream.limit(maxSize));
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
	 * <b>apiNote:</b>
	 * While {@code skip()} is generally a cheap operation on sequential
	 * stream pipelines, it can be quite expensive on ordered parallel pipelines,
	 * especially for large values of {@code n}, since {@code skip(n)}
	 * is constrained to skip not just any <em>n</em> elements, but the
	 * <em>first n</em> elements in the encounter order.  Using an unordered
	 * stream source (such as {@link Stream#generate(Supplier)}) or removing the
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
	 * @see Stream#skip(long)
	 */
	public ThrowingStream<A> skip(long n){
		return ThrowingStream.from(stream.skip(n));
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
	 * Using an unordered stream source (such as {@link Stream#generate(Supplier)}) or
	 * removing the ordering constraint with {@link #unordered()} may result in
	 * significant speedups of {@code takeWhile()} in parallel pipelines, if the
	 * semantics of your situation permit.  If consistency with encounter order
	 * is required, and you are experiencing poor performance or memory
	 * utilization with {@code takeWhile()} in parallel pipelines, switching to
	 * sequential execution with {@link #sequential()} may improve performance.
	 *
	 * @param <T> A {@link Throwable} that can be thrown by the predicate
	 * @param predicate a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                  <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                  predicate to apply to elements to determine the longest
	 *                  prefix of elements.
	 * @return the new stream
	 * @throws T If thrown by the predicate
	 * @see Stream#takeWhile(Predicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingStream<A> takeWhile(ThrowingPredicate<? super A, T> predicate) throws T{
		return ThrowingStream.from(stream.takeWhile(item -> {
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
	 * Using an unordered stream source (such as {@link Stream#generate(Supplier)}) or
	 * removing the ordering constraint with {@link #unordered()} may result in
	 * significant speedups of {@code dropWhile()} in parallel pipelines, if the
	 * semantics of your situation permit.  If consistency with encounter order
	 * is required, and you are experiencing poor performance or memory
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
	 * @see Stream#dropWhile(Predicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> ThrowingStream<A> dropWhile(ThrowingPredicate<? super A, T> predicate) throws T{
		return ThrowingStream.from(stream.dropWhile(item -> {
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
	 * <p>The behavior of this operation is explicitly nondeterministic.
	 * For parallel stream pipelines, this operation does <em>not</em>
	 * guarantee to respect the encounter order of the stream, as doing so
	 * would sacrifice the benefit of parallelism.  For any given element, the
	 * action may be performed at whatever time and in whatever thread the
	 * library chooses.  If the action accesses shared state, it is
	 * responsible for providing the required synchronization.
	 *
	 * @param <T> A {@link Throwable} the action can throw
	 * @param action a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">
	 *               non-interfering</a> action to perform on the elements
	 * @throws T If the action throws it
	 * @see Stream#forEach(java.util.function.Consumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> void forEach(ThrowingConsumer<? super A, T> action) throws T{
		stream.forEach(item -> {
			try{
				action.accept(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs an action for each element of this stream, in the encounter
	 * order of the stream if the stream has a defined encounter order.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * <p>This operation processes the elements one at a time, in encounter
	 * order if one exists.  Performing the action for one element
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a>
	 * performing the action for subsequent elements, but for any given element,
	 * the action may be performed in whatever thread the library chooses.
	 *
	 * @param <T> A {@link Throwable} the action can throw
	 * @param action a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">
	 *               non-interfering</a> action to perform on the elements
	 * @throws T If the action throws it
	 * @see #forEach(ThrowingConsumer)
	 * @see Stream#forEachOrdered(java.util.function.Consumer)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> void forEachOrdered(ThrowingConsumer<? super A, T> action) throws T{
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
	 * @return an array, whose {@linkplain Class#getComponentType runtime component
	 * type} is {@code Object}, containing the elements of this stream
	 * @see Stream#toArray()
	 */
	public Object[] toArray(){
		return stream.toArray();
	}
	
	/**
	 * Returns an array containing the elements of this stream, using the
	 * provided {@code generator} function to allocate the returned array, as
	 * well as any additional arrays that might be required for a partitioned
	 * execution or for resizing.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 * <br><br>
	 * <b>apiNote:</b>
	 * The generator function takes an integer, which is the size of the
	 * desired array, and produces an array of the desired size.  This can be
	 * concisely expressed with an array constructor reference:
	 * <pre>{@code
	 *     Person[] men = people.stream()
	 *                          .filter(p -> p.getGender() == MALE)
	 *                          .toArray(Person[]::new);
	 * }</pre>
	 *
	 * @param <B> the component type of the resulting array
	 * @param <T> A {@link Throwable} the generator can throw
	 * @param generator a function which produces a new array of the desired
	 *                  type and the provided length
	 * @return an array containing the elements in this stream
	 * @throws ArrayStoreException if the runtime type of any element of this
	 *         stream is not assignable to the {@linkplain Class#getComponentType
	 *         runtime component type} of the generated array
	 * @throws T If the generator throws it
	 * @see Stream#toArray(IntFunction)
	 */
	@SuppressWarnings("RedundantThrows")
	public <B, T extends Throwable> B[] toArray(ThrowingFunction<Integer, B[], T> generator) throws T{
		return stream.toArray(value -> {
			try{
				return generator.apply(value);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> on the
	 * elements of this stream, using the provided identity value and an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>
	 * accumulation function, and returns the reduced value.  This is equivalent
	 * to:
	 * <pre>{@code
	 *     T result = identity;
	 *     for (T element : this stream)
	 *         result = accumulator.apply(result, element)
	 *     return result;
	 * }</pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>The {@code identity} value must be an identity for the accumulator
	 * function. This means that for all {@code t},
	 * {@code accumulator.apply(identity, t)} is equal to {@code t}.
	 * The {@code accumulator} function must be an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a> function.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 * <br><br>
	 * <b>apiNote:</b> Sum, min, max, average, and string concatenation are all special
	 * cases of reduction. Summing a stream of numbers can be expressed as:
	 *
	 * <pre>{@code
	 *     Integer sum = integers.reduce(0, (a, b) -> a+b);
	 * }</pre>
	 *
	 * or:
	 *
	 * <pre>{@code
	 *     Integer sum = integers.reduce(0, Integer::sum);
	 * }</pre>
	 *
	 * <p>While this may seem a more roundabout way to perform an aggregation
	 * compared to simply mutating a running total in a loop, reduction
	 * operations parallelize more gracefully, without needing additional
	 * synchronization and with greatly reduced risk of data races.
	 *
	 * @param <T> A {@link Throwable} the accumulator can throw
	 * @param identity the identity value for the accumulating function
	 * @param accumulator an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                    function for combining two values
	 * @return the result of the reduction
	 * @throws T If the accumulator throws it
	 * @see Stream#reduce(Object, BinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> A reduce(A identity, ThrowingFunction2<A, A, A, T> accumulator) throws T{
		return stream.reduce(identity, (item, item2) -> {
			try{
				return accumulator.apply(item, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> on the
	 * elements of this stream, using an
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a> accumulation
	 * function, and returns an {@code Optional} describing the reduced value,
	 * if any. This is equivalent to:
	 * <pre>{@code
	 *     boolean foundAny = false;
	 *     T result = null;
	 *     for (T element : this stream) {
	 *         if (!foundAny) {
	 *             foundAny = true;
	 *             result = element;
	 *         }
	 *         else
	 *             result = accumulator.apply(result, element);
	 *     }
	 *     return foundAny ? Optional.of(result) : Optional.empty();
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
	 * @param <T> A {@link Throwable} that the accumulator can throw
	 * @param accumulator an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                    function for combining two values
	 * @return an {@link Optional} describing the result of the reduction
	 * @throws NullPointerException if the result of the reduction is null
	 * @throws T If the accumulator throws it
	 * @see #reduce(Object, ThrowingFunction2)
	 * @see #min(Comparator)
	 * @see #max(Comparator)
	 * @see Stream#reduce(BinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> Optional<A> reduce(ThrowingFunction2<A, A, A, T> accumulator) throws T{
		return stream.reduce((item, item2) -> {
			try{
				return accumulator.apply(item, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a> on the
	 * elements of this stream, using the provided identity, accumulation and
	 * combining functions.  This is equivalent to:
	 * <pre>{@code
	 *     U result = identity;
	 *     for (T element : this stream)
	 *         result = accumulator.apply(result, element)
	 *     return result;
	 * }</pre>
	 *
	 * but is not constrained to execute sequentially.
	 *
	 * <p>The {@code identity} value must be an identity for the combiner
	 * function.  This means that for all {@code u}, {@code combiner(identity, u)}
	 * is equal to {@code u}.  Additionally, the {@code combiner} function
	 * must be compatible with the {@code accumulator} function; for all
	 * {@code u} and {@code t}, the following must hold:
	 * <pre>{@code
	 *     combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
	 * }</pre>
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 * <br><br>
	 * <b>apiNote:</b> Many reductions using this form can be represented more simply
	 * by an explicit combination of {@code map} and {@code reduce} operations.
	 * The {@code accumulator} function acts as a fused mapper and accumulator,
	 * which can sometimes be more efficient than separate mapping and reduction,
	 * such as when knowing the previously reduced value allows you to avoid
	 * some computation.
	 *
	 * @param <U> The type of the result
	 * @param <T> A {@link Throwable} that can be thrown by the accumulator
	 * @param <T2> A {@link Throwable} that can be thrown by the combiner
	 * @param identity the identity value for the combiner function
	 * @param accumulator an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                    function for incorporating an additional element into a result
	 * @param combiner an <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Associativity">associative</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                    <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                    function for combining two values, which must be
	 *                    compatible with the accumulator function
	 * @return the result of the reduction
	 * @throws T If the accumulator throws it
	 * @throws T2 If the combiner throws it
	 * @see #reduce(ThrowingFunction2)
	 * @see #reduce(Object, ThrowingFunction2)
	 * @see Stream#reduce(Object, BiFunction, BinaryOperator)
	 */
	@SuppressWarnings("RedundantThrows")
	public <U, T extends Throwable, T2 extends Throwable> U reduce(
			U identity, ThrowingFunction2<U, ? super A, U, T> accumulator, ThrowingFunction2<U, U, U, T2> combiner) throws T, T2{
		return stream.reduce(identity, (item, item2) -> {
			try{
				return accumulator.apply(item, item2);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		}, (item, item2) -> {
			try{
				return combiner.apply(item, item2);
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
	 *     for (T element : this stream)
	 *         accumulator.accept(result, element);
	 *     return result;
	 * }</pre>
	 *
	 * <p>Like {@link #reduce(Object, ThrowingFunction2)}, {@code collect} operations
	 * can be parallelized without requiring additional synchronization.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 * <br><br>
	 * <b>apiNote:</b> There are many existing classes in the JDK whose signatures are
	 * well-suited for use with method references as arguments to {@code collect()}.
	 * For example, the following will accumulate strings into an {@code ArrayList}:
	 * <pre>{@code
	 *     List<String> asList = stringStream.collect(ArrayList::new, ArrayList::add,
	 *                                                ArrayList::addAll);
	 * }</pre>
	 *
	 * <p>The following will take a stream of strings and concatenates them into a
	 * single string:
	 * <pre>{@code
	 *     String concat = stringStream.collect(StringBuilder::new, StringBuilder::append,
	 *                                          StringBuilder::append)
	 *                                 .toString();
	 * }</pre>
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
	 */
	@SuppressWarnings("RedundantThrows")
	public <R, T extends Throwable, T2 extends Throwable, T3 extends Throwable> R collect(
			ThrowingSupplier<R, T> supplier, ThrowingConsumer2<R, ? super A, T2> accumulator,
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
	 * Performs a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#MutableReduction">mutable
	 * reduction</a> operation on the elements of this stream using a
	 * {@code Collector}.  A {@code Collector}
	 * encapsulates the functions used as arguments to
	 * {@link #collect(ThrowingSupplier, ThrowingConsumer2, ThrowingConsumer2)}, allowing for reuse of
	 * collection strategies and composition of collect operations such as
	 * multiple-level grouping or partitioning.
	 *
	 * <p>If the stream is parallel, and the {@code Collector}
	 * is {@link Collector.Characteristics#CONCURRENT concurrent}, and
	 * either the stream is unordered or the collector is
	 * {@link Collector.Characteristics#UNORDERED unordered},
	 * then a concurrent reduction will be performed (see {@link Collector} for
	 * details on concurrent reduction.)
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * <p>When executed in parallel, multiple intermediate results may be
	 * instantiated, populated, and merged to maintain isolation of
	 * mutable data structures.  Therefore, even when executed in parallel
	 * with non-thread-safe data structures (such as {@code ArrayList}), no
	 * additional synchronization is needed for a parallel reduction.
	 * <br><br>
	 * <b>apiNote:</b>
	 * The following will accumulate strings into a List:
	 * <pre>{@code
	 *     List<String> asList = stringStream.collect(Collectors.toList());
	 * }</pre>
	 *
	 * <p>The following will classify {@code Person} objects by city:
	 * <pre>{@code
	 *     Map<String, List<Person>> peopleByCity
	 *         = personStream.collect(Collectors.groupingBy(Person::getCity));
	 * }</pre>
	 *
	 * <p>The following will classify {@code Person} objects by state and city,
	 * cascading two {@code Collector}s together:
	 * <pre>{@code
	 *     Map<String, Map<String, List<Person>>> peopleByStateAndCity
	 *         = personStream.collect(Collectors.groupingBy(Person::getState,
	 *                                                      Collectors.groupingBy(Person::getCity)));
	 * }</pre>
	 *
	 * @param <R> the type of the result
	 * @param <B> the intermediate accumulation type of the {@code Collector}
	 * @param collector the {@code Collector} describing the reduction
	 * @return the result of the reduction
	 * @see #collect(ThrowingSupplier, ThrowingConsumer2, ThrowingConsumer2)
	 * @see Collectors
	 * @see Stream#collect(Collector)
	 */
	public <R, B> R collect(Collector<? super A, B, R> collector){
		return stream.collect(collector);
	}
	
	/**
	 * Accumulates the elements of this stream into a {@code List}. The elements in
	 * the list will be in this stream's encounter order, if one exists. The returned List
	 * is unmodifiable; calls to any mutator method will always cause
	 * {@code UnsupportedOperationException} to be thrown. There are no
	 * guarantees on the implementation type or serializability of the returned List.
	 *
	 * <p>The returned instance may be <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
	 * Callers should make no assumptions about the identity of the returned instances.
	 * Identity-sensitive operations on these instances (reference equality ({@code ==}),
	 * identity hash code, and synchronization) are unreliable and should be avoided.
	 *
	 * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 * <br><br>
	 * <b>apiNote:</b> If more control over the returned object is required, use
	 * {@link Collectors#toCollection(Supplier)}.
	 * <br><br>
	 * <b>implSpec:</b> The implementation in this interface returns a List produced as if by the following:
	 * <pre>{@code
	 * Collections.unmodifiableList(new ArrayList<>(Arrays.asList(this.toArray())))
	 * }</pre>
	 * <br><br>
	 * <b>implNote:</b> Most instances of Stream will override this method and provide an implementation
	 * that is highly optimized compared to the implementation in this interface.
	 *
	 * @return a List containing the stream elements
	 * @see Stream#toList()
	 */
	public List<A> toList(){
		return stream.toList();
	}
	
	/**
	 * Returns the minimum element of this stream according to the provided
	 * {@code Comparator}.  This is a special case of a
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @param comparator a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                   <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                   {@code Comparator} to compare elements of this stream
	 * @return an {@code Optional} describing the minimum element of this stream,
	 * or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException if the minimum element is null
	 * @see Stream#min(Comparator)
	 */
	public Optional<A> min(Comparator<? super A> comparator){
		return stream.min(comparator);
	}
	
	/**
	 * Returns the maximum element of this stream according to the provided
	 * {@code Comparator}.  This is a special case of a
	 * <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Reduction">reduction</a>.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">terminal
	 * operation</a>.
	 *
	 * @param comparator a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#NonInterference">non-interfering</a>,
	 *                   <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#Statelessness">stateless</a>
	 *                   {@code Comparator} to compare elements of this stream
	 * @return an {@code Optional} describing the maximum element of this stream,
	 * or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException if the maximum element is null
	 * @see Stream#max(Comparator)
	 */
	public Optional<A> max(Comparator<? super A> comparator){
		return stream.max(comparator);
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
	 *     List<String> l = Arrays.asList("A", "B", "C", "D");
	 *     long count = l.stream().peek(System.out::println).count();
	 * }</pre>
	 * The number of elements covered by the stream source, a {@code List}, is
	 * known and the intermediate operation, {@code peek}, does not inject into
	 * or remove elements from the stream (as may be the case for
	 * {@code flatMap} or {@code filter} operations).  Thus, the count is the
	 * size of the {@code List} and there is no need to execute the pipeline
	 * and, as a side effect, print out the list elements.
	 *
	 * @return the count of elements in this stream
	 * @see Stream#count()
	 */
	public long count(){
		return stream.count();
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
	 * @see Stream#anyMatch(Predicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean anyMatch(ThrowingPredicate<? super A, T> predicate) throws T{
		return stream.anyMatch((item) -> {
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
	 * @see Stream#allMatch(Predicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean allMatch(ThrowingPredicate<? super A, T> predicate) throws T{
		return stream.allMatch((item) -> {
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
	 * @see Stream#noneMatch(Predicate)
	 */
	@SuppressWarnings("RedundantThrows")
	public <T extends Throwable> boolean noneMatch(ThrowingPredicate<? super A, T> predicate) throws T{
		return stream.noneMatch((item) -> {
			try{
				return predicate.test(item);
			}catch(Throwable t){
				throw sneakyThrow(t);
			}
		});
	}
	
	/**
	 * Returns an {@link Optional} describing the first element of this stream,
	 * or an empty {@code Optional} if the stream is empty.  If the stream has
	 * no encounter order, then any element may be returned.
	 *
	 * <p>This is a <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html#StreamOps">short-circuiting
	 * terminal operation</a>.
	 *
	 * @return an {@code Optional} describing the first element of this stream,
	 * or an empty {@code Optional} if the stream is empty
	 * @throws NullPointerException if the element selected is null
	 * @see Stream#findFirst()
	 */
	public Optional<A> findFirst(){
		return stream.findFirst();
	}
	
	/**
	 * Returns an {@link Optional} describing some element of the stream, or an
	 * empty {@code Optional} if the stream is empty.
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
	 * @return an {@code Optional} describing some element of this stream, or an
	 * empty {@code Optional} if the stream is empty
	 * @throws NullPointerException if the element selected is null
	 * @see #findFirst()
	 * @see Stream#findAny()
	 */
	public Optional<A> findAny(){
		return stream.findAny();
	}
}
