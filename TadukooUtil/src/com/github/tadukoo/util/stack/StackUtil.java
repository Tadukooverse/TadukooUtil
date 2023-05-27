package com.github.tadukoo.util.stack;

/**
 * Stack Util is used for figuring out information from the stack
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public final class StackUtil{
	
	/** Not allowed to instantiate StackUtil */
	private StackUtil(){ }
	
	/**
	 * Figures out the canonical class name of the class that called the method that called this method
	 * (so e.g. if StackUtilTest.testGetCallingClassName calls this method, it'll return whatever called
	 * StackUtilTest's method)
	 *
	 * @return The canonical class name of the calling class
	 */
	public static String getCallingClassName(){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[3];
		return element.getClassName();
	}
	
	/**
	 * Figures out the class that called the method that called this method
	 * (so e.g. if StackUtilTest.testGetCallingClassName calls this method, it'll return whatever called
	 * StackUtilTest's method)
	 *
	 * @return The calling class
	 */
	public static Class<?> getCallingClass() throws ClassNotFoundException{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[3];
		return StackUtil.class.getClassLoader().loadClass(element.getClassName());
	}
	
	/**
	 * Figures out the method name of the class that called the method that called this method
	 * (so e.g. if StackUtilTest.testGetCallingClassName calls this method, it'll return whatever called
	 * StackUtilTest's method)
	 *
	 * @return The method name of the calling method
	 */
	public static String getCallingMethodName(){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[3];
		return element.getMethodName();
	}
}
