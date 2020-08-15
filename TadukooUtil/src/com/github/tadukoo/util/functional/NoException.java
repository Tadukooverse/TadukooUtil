package com.github.tadukoo.util.functional;

/**
 * To be used as a {@link Throwable} parameter in Throwing Functional Interfaces
 * that are not meant to throw anything.
 */
public final class NoException extends RuntimeException{
	
	// Can't actually create a NoException, as you're not supposed to
	private NoException(){ }
}
