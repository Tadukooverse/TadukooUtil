package com.github.tadukoo.util.stream;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.BaseStream;

/**
 * Throwing Base Stream is an abstract class used to wrap around a {@link BaseStream} to allow for throwing
 * {@link Throwable Throwables} from the various stream methods.
 *
 * @param <A> The type contained in the {@link BaseStream}
 * @param <Stream> The type of {@link BaseStream} being wrapped
 * @param <ThrowingStream> The actual {@link ThrowingBaseStream} implementation class being used
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
public abstract class ThrowingBaseStream<A, Stream extends BaseStream<A, Stream>,
		ThrowingStream extends ThrowingBaseStream<A, Stream, ThrowingStream>>{
	/** The {@link BaseStream} that this {@link ThrowingBaseStream} is wrapping */
	protected Stream stream;
	
	/**
	 * Constructs a new {@link ThrowingBaseStream} by wrapping the given {@link BaseStream} object
	 *
	 * @param stream The {@link BaseStream} object being wrapped
	 */
	protected ThrowingBaseStream(Stream stream){
		this.stream = stream;
	}
	
	/**
	 * @return The {@link BaseStream} that this {@link ThrowingBaseStream} is wrapping
	 */
	public Stream stream(){
		return stream;
	}
	
	/**
	 * Returns an iterator for the elements of this stream.
	 *
	 * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * @return The element iterator for this stream
	 * @see BaseStream#iterator()
	 */
	public Iterator<A> iterator(){
		return stream.iterator();
	}
	
	/**
	 * Returns a spliterator for the elements of this stream.
	 *
	 * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
	 *
	 * <p>
	 * The returned spliterator should report the set of characteristics derived
	 * from the stream pipeline (namely the characteristics derived from the
	 * stream source spliterator and the intermediate operations).
	 * Implementations may report a sub-set of those characteristics.  For
	 * example, it may be too expensive to compute the entire set for some or
	 * all possible stream pipelines.
	 *
	 * @return the element spliterator for this stream
	 * @see BaseStream#spliterator()
	 */
	public Spliterator<A> spliterator(){
		return stream.spliterator();
	}
	
	/**
	 * Returns whether this stream, if a terminal operation were to be executed,
	 * would execute in parallel.  Calling this method after invoking a
	 * terminal stream operation method may yield unpredictable results.
	 *
	 * @return {@code true} if this stream would execute in parallel if executed
	 * @see BaseStream#isParallel()
	 */
	public boolean isParallel(){
		return stream.isParallel();
	}
	
	/**
	 * Returns an equivalent stream that is sequential.  May return
	 * itself, either because the stream was already sequential, or because
	 * the underlying stream state was modified to be sequential.
	 *
	 * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @return a sequential stream
	 * @see BaseStream#sequential()
	 */
	public abstract ThrowingStream sequential();
	
	/**
	 * Returns an equivalent stream that is parallel.  May return
	 * itself, either because the stream was already parallel, or because
	 * the underlying stream state was modified to be parallel.
	 *
	 * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @return a parallel stream
	 * @see BaseStream#parallel()
	 */
	public abstract ThrowingStream parallel();
	
	/**
	 * Returns an equivalent stream that is
	 * <a href="package-summary.html#Ordering">unordered</a>.  May return
	 * itself, either because the stream was already unordered, or because
	 * the underlying stream state was modified to be unordered.
	 *
	 * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
	 *
	 * @return an unordered stream
	 * @see BaseStream#unordered()
	 */
	public abstract ThrowingStream unordered();
	
	/**
	 * Returns an equivalent stream with an additional close handler.  Close
	 * handlers are run when the {@link #close()} method
	 * is called on the stream, and are executed in the order they were
	 * added.  All close handlers are run, even if earlier close handlers throw
	 * exceptions.  If any close handler throws an exception, the first
	 * exception thrown will be relayed to the caller of {@code close()}, with
	 * any remaining exceptions added to that exception as suppressed exceptions
	 * (unless one of the remaining exceptions is the same exception as the
	 * first exception, since an exception cannot suppress itself.)  May
	 * return itself.
	 *
	 * <p>This is an <a href="package-summary.html#StreamOps">intermediate
	 * operation</a>.
	 *
	 * @param closeHandler A task to execute when the stream is closed
	 * @return a stream with a handler that is run if the stream is closed
	 * @see BaseStream#onClose(Runnable)
	 */
	public abstract ThrowingStream onClose(Runnable closeHandler);
	
	/**
	 * Closes this stream, causing all close handlers for this stream pipeline
	 * to be called.
	 *
	 * @see AutoCloseable#close()
	 * @see BaseStream#close()
	 */
	public void close(){
		stream.close();
	}
	
	/**
	 * A function used to sneakily throw a {@link Throwable} in other methods where it typically isn't possible.
	 *
	 * @param throwable The {@link Throwable} being thrown
	 * @return Nothing, but we say it's a RuntimeException to hide the true intent
	 * @param <T> The type of the {@link Throwable} being thrown
	 * @throws T The {@link Throwable} we receive, cast to T
	 */
	@SuppressWarnings("unchecked")
	protected static <T extends Throwable> RuntimeException sneakyThrow(Throwable throwable) throws T{
		throw (T) throwable; // Erased cast to trick the compiler
	}
}
