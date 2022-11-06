package com.github.tadukoo.util.parallel;

import com.github.tadukoo.util.logger.EasyLogger;

/**
 * Parallel Worker is the class involved in parallel programming that actually does the work involved in the
 * parallel operation.
 * <br><br>
 * Subclasses you make of this class should have a constructor matching this class's constructor, because of
 * how {@link ParallelRunner} works for instantiating an instance of this class.
 *
 * @param <E> The type of the work object
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public abstract class ParallelWorker<E> implements Runnable{
	/** The {@link EasyLogger logger} to use for logging */
	protected final EasyLogger logger;
	/** The {@link Queue} containing work that needs to be executed yet */
	private final Queue<E> todoQueue;
	/** The {@link Queue} to put finished work into */
	private final Queue<E> doneQueue;
	
	/**
	 * Constructs a new {@link ParallelWorker} with the given parameters
	 *
	 * @param logger The {@link EasyLogger logger} to use for logging
	 * @param todoQueue The {@link Queue} containing work that needs to be executed yet
	 * @param doneQueue The {@link Queue} to put finished work into
	 */
	public ParallelWorker(EasyLogger logger, Queue<E> todoQueue, Queue<E> doneQueue){
		this.logger = logger;
		this.todoQueue = todoQueue;
		this.doneQueue = doneQueue;
	}
	
	/**
	 * Used to run the actual worker. Runs forever until {@link #checkToContinueWork(Object)} returns false.
	 * Each iteration it grabs work off the {@link #todoQueue}, checks if it is an object to terminate work,
	 * and if not will call {@link #doWork(Object)} with the work object, then add that work to the {@link #doneQueue}
	 */
	@Override
	public void run(){
		boolean cont = true;
		while(cont){
			try{
				E work = todoQueue.dequeue();
				if(checkToContinueWork(work)){
					// Do the actual work
					doWork(work);
					doneQueue.enqueue(work);
				}else{
					cont = false;
				}
			}catch(InterruptedException e){
				logger.logError(e);
			}
		}
	}
	
	/**
	 * Checks if the passed in work object is a terminate work object and returns {@code false} if it is
	 *
	 * @param work The work object to check
	 * @return {@code true} if the object is a valid work object, {@code false} if it is a terminate work object
	 */
	protected abstract boolean checkToContinueWork(E work);
	
	/**
	 * Do the work involved with the given work object
	 *
	 * @param work The work object to use to do the work
	 */
	protected abstract void doWork(E work);
}
