package com.github.tadukoo.util.junit;

import com.github.tadukoo.util.StringUtil;

/**
 * Assertion Failed Errors contains helper methods for creating Assertion Failed Error messages as well as
 * several common error messages.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public enum AssertionFailedErrors{
	
	/** The error you get when an assertTrue fails - {@code expected: <true> but was: <false>} */
	ASSERT_TRUE_ERROR(buildAssertError(true, false)),
	/** The error you get when an assertFalse fails - {@code expected: <false> but was: <true>} */
	ASSERT_FALSE_ERROR(buildAssertError(false, true)),
	/** The error you get when an assertNotNull fails - {@code expected: not <null>} */
	ASSERT_NOT_NULL_ERROR(buildAssertErrorNot(null));
	
	/** The error string */
	private final String error;
	
	/** The separator that appears between errors when there are multiple chained */
	public static final String SEPARATOR = " ==> ";
	
	/**
	 * Creates a new Assertion Failed Error with the given error string
	 *
	 * @param error The error string
	 */
	AssertionFailedErrors(String error){
		this.error = error;
	}
	
	/**
	 * Builds the start of a message when appending a message onto another assertion failed error (helpful to
	 * check if message is blank)
	 *
	 * @param message The message to append (might be blank)
	 * @return An empty string, or the message with the {@link #SEPARATOR} after it
	 */
	public static String buildMessageStart(String message){
		return StringUtil.isBlank(message)?"":message + SEPARATOR;
	}
	
	/**
	 * Builds an Assertion Failed Error string of the form: {@code expected: not <value>}
	 *
	 * @param expectedNot The value we're expecting to be false
	 * @return A string representing an Assertion Failed Error string
	 */
	public static String buildAssertErrorNot(Object expectedNot){
		return "expected: not <" + StringUtil.convertToString(expectedNot) + ">";
	}
	
	/**
	 * Builds an Assertion Failed Error string of the form: {@code expected: <expected> but was: <actual>}
	 *
	 * @param expected The value we're expecting
	 * @param actual The actual value
	 * @return A string representing an Assertion Failed Error string
	 */
	public static String buildAssertError(Object expected, Object actual){
		return "expected: <" + StringUtil.convertToString(expected) + "> but was: " +
				"<" + StringUtil.convertToString(actual) + ">";
	}
	
	/**
	 * Builds an Assertion Failed Error string of the form: {@code <message> ==> <error>}, e.g.
	 * {@code pojo was empty! ==> expected: <true> but was: <false>}
	 * This is useful when using an assert method that includes a custom message before the assert error
	 *
	 * @param message The message that will appear before the assertion error
	 * @param error The {@link AssertionFailedErrors assertion error enum} to use for the assertion error
	 * @return A string representing an Assertion Failed Error string
	 */
	public static String buildTwoPartError(String message, AssertionFailedErrors error){
		return buildTwoPartError(message, error.toString());
	}
	
	/**
	 * Builds an Assertion Failed Error string of the form: {@code <message> ==> <error>}, e.g.
	 * {@code pojo was empty! ==> expected: <true> but was: <false>}
	 * This is useful when using an assert method that includes a custom message before the assert error
	 *
	 * @param message The message that will appear before the assertion error
	 * @param error The assertion error string to use for the assertion error
	 * @return A string representing an Assertion Failed Error string
	 */
	public static String buildTwoPartError(String message, String error){
		return message + SEPARATOR + error;
	}
	
	/**
	 * Builds an Assertion Failed Error string of the form {@code <error> ==> <error> ==> ...}, e.g.
	 * {@code clear() failed! ==> pojo was empty! ==> expected: <true> but was: <false>}
	 * This is useful when using an assert method that includes multiple custom messages in a chain before
	 * the assert error (e.g. outer method failed, because inner method failed, because assertion error)
	 *
	 * @param errors The errors in order for the message
	 * @return A string representing an Assertion Failed Error string
	 */
	public static String buildMultiPartError(String ... errors){
		StringBuilder message = new StringBuilder();
		for(String error: errors){
			message.append(error).append(SEPARATOR);
		}
		message.delete(message.length()-SEPARATOR.length(), message.length());
		return message.toString();
	}
	
	/**
	 * @return The error string
	 */
	@Override
	public String toString(){
		return error;
	}
}
