package com.gmail.realtadukoo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Util functions for dealing with {@link Throwable Throwables} and {@link Exception Exceptions}. 
 * <br><br>
 * It is named ExceptionUtil because it's more common to think of the term Exception than Throwable, 
 * though it does work for both.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
public final class ExceptionUtil{
	
	// Not allowed to create an ExceptionUtil
	private ExceptionUtil(){ }
	
	/**
	 * Returns a String of the stack trace for the given {@link Throwable}. 
	 * The String will be the equivalent of calling {@link Throwable#printStackTrace()}.
	 * 
	 * @param t The Throwable to get the stack trace of
	 * @return A String of the stack trace
	 */
	public static String getStackTraceAsString(Throwable t){
		// Setup a StringWriter to write the stack trace to
		StringWriter sw = new StringWriter();
		// Create PrintWriter for the StringWriter so it can actually be written to
		PrintWriter pw = new PrintWriter(sw);
		// Print the stack trace to (ultimately) the string writer
		t.printStackTrace(pw);
		
		// Return the written stack trace string
		return sw.toString();
	}
}
