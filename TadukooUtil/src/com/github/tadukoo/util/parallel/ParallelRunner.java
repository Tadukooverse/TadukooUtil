package com.github.tadukoo.util.parallel;

import com.github.tadukoo.util.logger.EasyLogger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parallel Runner is a generic class for running parallel code using the {@link ParallelWorker} and {@link Queue}
 * classes to assist in the parallel operation
 *
 * @param <E> The type argument for the work object to be used in the {@link Queue queues}
 * and used by the {@link ParallelWorker}
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public abstract class ParallelRunner<E>{
	/** The {@link EasyLogger logger} to use for logging */
	private final EasyLogger logger;
	/** The maximum number of items that can be in the queues */
	private final int maxQueueItems;
	/** The number of threads to use to run the parallel operation */
	private final int numThreads;
	/** The {@link ParallelWorker} class to use for doing the actual work */
	private final Class<? extends ParallelWorker<E>> workerClass;
	
	/**
	 * Constructs a new {@link ParallelRunner} with the given parameters
	 *
	 * @param logger The {@link EasyLogger logger} to use for logging
	 * @param maxQueueItems The maximum number of items that can be in the queues
	 * @param numThreads The number of threads to use to run the parallel operation
	 * @param workerClass The {@link ParallelWorker} class to use for doing the actual work
	 */
	protected ParallelRunner(
			EasyLogger logger, int maxQueueItems, int numThreads, Class<? extends ParallelWorker<E>> workerClass){
		this.logger = logger;
		this.maxQueueItems = maxQueueItems;
		this.numThreads = numThreads;
		this.workerClass = workerClass;
	}
	
	/**
	 * This method runs the parallel operation as a whole and handles setting up and joining the threads
	 *
	 * @throws NoSuchMethodException If your {@link #workerClass} doesn't have a constructor matching the base class
	 * @throws InvocationTargetException If your {@link #workerClass} constructor throws an exception
	 * @throws InstantiationException If your {@link #workerClass} fails to instantiate
	 * @throws IllegalAccessException If your {@link #workerClass} constructor is not public
	 * @throws InterruptedException If something goes wrong in enqueue/dequeue calls to the {@link Queue queues}
	 */
	public void runParallelWork()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException,
			IllegalAccessException, InterruptedException{
		Queue<E> todoQueue = new Queue<>(maxQueueItems), doneQueue = new Queue<>(maxQueueItems);
		
		// Start worker threads
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < numThreads; i++){
			ParallelWorker<E> worker = workerClass.getConstructor(EasyLogger.class, Queue.class, Queue.class)
					.newInstance(logger, todoQueue, doneQueue);
			threads.add(new Thread(worker));
			threads.get(i).start();
		}
		
		// Do the work
		doWork(todoQueue, doneQueue);
		
		// Send terminate infos
		for(int i = 0; i < numThreads; i++){
			sendTerminateInfo(todoQueue);
		}
		
		// End worker threads
		for(int i = 0; i < numThreads; i++){
			threads.get(i).join();
		}
	}
	
	/**
	 * This method is where the work is performed. In this method, you should add the necessary work to the
	 * todoQueue, receive the finished work from the doneQueue, and process that completed work.
	 * <br><br>
	 * <b>Note: DO NOT</b> send the work to terminate the processes in this code,
	 * that is handled in {@link #sendTerminateInfo(Queue)}
	 *
	 * @param todoQueue The {@link Queue} to send work to that needs to be executed
	 * @param doneQueue The {@link Queue} to receive work from once it's completed
	 * @throws InterruptedException If anything goes wrong in enqueue/dequeue calls to the {@link Queue queues}
	 */
	protected abstract void doWork(Queue<E> todoQueue, Queue<E> doneQueue) throws InterruptedException;
	
	/**
	 * This method is where you send out the work that tells the threads to stop running their loops. It should line
	 * up with what's defined in {@link ParallelWorker#checkToContinueWork(Object)} on your worker class
	 *
	 * @param todoQueue The {@link Queue} to send work to that tells the threads to stop running their loops
	 * @throws InterruptedException If anything goes wrong in enqueue calls to the {@link Queue}
	 */
	protected abstract void sendTerminateInfo(Queue<E> todoQueue) throws InterruptedException;
}
