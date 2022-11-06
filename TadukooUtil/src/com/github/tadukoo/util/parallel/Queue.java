package com.github.tadukoo.util.parallel;

import java.util.LinkedList;

/**
 * This Queue is used to run parallel code with thread-safe methods for grabbing items off the queue and
 * putting items on the queue.
 *
 * @param <E> The type of item stored in this {@link Queue}
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public class Queue<E>{
	/** An object to use as a lock for synchronizing while running in parallel */
	private final Object lock = new Object();
	/** The actual data of the {@link Queue} */
	private final LinkedList<E> data;
	/** The maximum number of items that can be in the {@link Queue} */
	private final int maxItems;
	
	/**
	 * Creates a new {@link Queue}
	 *
	 * @param maxItems The maximum number of items that can be in the {@link Queue}
	 */
	public Queue(int maxItems){
		data = new LinkedList<>();
		this.maxItems = maxItems;
	}
	
	/**
	 * Adds the given item to the {@link Queue}
	 *
	 * @param item The item to add to the {@link Queue}
	 * @throws InterruptedException If something goes wrong in waiting for the lock
	 */
	public void enqueue(E item) throws InterruptedException{
		synchronized(lock){
			// Wait until we can add more to the queue
			while(data.size() == maxItems){
				lock.wait();
			}
			// Add the item to the queue and release the lock
			data.addLast(item);
			lock.notifyAll();
		}
	}
	
	/**
	 * Takes an item off the {@link Queue}
	 *
	 * @return The item taken off the {@link Queue}
	 * @throws InterruptedException If something goes wrong in waiting for the lock
	 */
	public E dequeue() throws InterruptedException{
		synchronized(lock){
			// Wait until there's something in the queue to take out
			while(data.size() == 0){
				lock.wait();
			}
			// Grab an item off the queue, release the lock, and return the grabbed item
			E item = data.removeFirst();
			lock.notifyAll();
			return item;
		}
	}
}
