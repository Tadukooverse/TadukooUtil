package com.gmail.realtadukoo.util.functional;

/**
 * To be used as a {@link Throwable} parameter in Throwing Functional Interfaces
 * that are not meant to throw anything.
 */
public final class NoException extends RuntimeException{
	private NoException(){ }
}
