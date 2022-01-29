package com.github.tadukoo.util.junit;

/**
 * Default values to use for testing, so we can have them consistent between the JUnit tests and the actual
 * source assertions
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public interface DefaultTestValues{
	/** The default first test string to be used: some_test_string */
	String DEFAULT_TEST_STRING = "some_test_string";
	/** The default second test string to be used when you need 2 distinct strings: another_test_string */
	String DEFAULT_TEST_STRING_2 = "another_test_string";
	/** A string to use for returning wrong values: something_wrong */
	String DEFAULT_WRONG_STRING = "something_wrong";
	
	/** The default first test double to be used: 42.0 */
	double DEFAULT_TEST_DOUBLE = 42.0;
	/** The default second test double to be used when you need 2 distinct doubles: 35.4 */
	double DEFAULT_TEST_DOUBLE_2 = 35.4;
	/** A double to use for returning wrong values: 27.3 */
	double DEFAULT_WRONG_DOUBLE = 27.3;
	
	/** The default string to use for a key: Test */
	String DEFAULT_TEST_KEY = "Test";
	/** The default second string to use for a key: Derp */
	String DEFAULT_TEST_KEY_2 = "Derp";
	/** The default string to use for a wrong key: Wrong */
	String DEFAULT_WRONG_KEY = "Wrong";
	
	/** The default string to use for an assertion failed message: Something went wrong! */
	String DEFAULT_CUSTOM_ASSERTION_FAILED_MESSAGE = "Something went wrong!";
}
