package com.gmail.realtadukoo.util.database;

import java.sql.SQLException;

import com.gmail.realtadukoo.util.functional.function.ExceptionFunction;

/**
 * A Function that takes a single argument, returns a result, 
 * and may throw a {@link SQLException}. 
 * This is an extension of {@link ExceptionFunction} 
 * that only allows for throwing a {@link SQLException}.
 * 
 * @param <S> The input argument type for the function
 * @param <R> The output result type for the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface SQLExceptionFunction<S, R> extends ExceptionFunction<S, R>{
	
	/**
	 * Takes an argument and returns a result.
	 * 
	 * @param s The argument
	 * @return A result
	 * @throws SQLException
	 */
	@Override
	R apply(S s) throws SQLException;
}
