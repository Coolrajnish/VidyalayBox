package com.ms.shared.util.util;

/**
 * A type of exception that allows for nesting of exceptions. A printstacktrace
 * method has been supplied that will iterate through the chain of exceptions
 * and dump the information for each one. Generally this exception is not used
 * directly. but rather serves as a superclass for our other standard exception.
 * 
 *
 * Company: mithlaSoftech  Creation Date: 2024
 *
 * @author sumit kumar
 * @version 1.0
 */
public class NestedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable nestedThrowable;

	public NestedException() {
		super();
	}

	/**
	 * Constructor for NestedException.
	 * 
	 * @param str String
	 */
	public NestedException(String str) {
		super(str);
	}

	/**
	 * Constructor for NestedException.
	 * 
	 * @param str       String
	 * @param throwable Throwable
	 */
	public NestedException(String str, Throwable throwable) {
		super(str);
		nestedThrowable = throwable;
	}

	@Override
	public void printStackTrace() {
		synchronized (System.err) {
			super.printStackTrace();
			if (nestedThrowable != null) {
				System.err.println("Nested Exception : ");
				nestedThrowable.printStackTrace();
			}
		}
	}

	/**
	 * Method printStackTrace.
	 * 
	 * @param s java.io.PrintStream
	 */
	public void printStackTrace(java.io.PrintStream s) {
		synchronized (s) {
			super.printStackTrace(s);
			if (nestedThrowable != null) {
				System.err.println("Nested Exception : ");
				nestedThrowable.printStackTrace(s);
			}
		}
	}

	/**
	 * Method printStackTrace.
	 * 
	 * @param s java.io.PrintWriter
	 */
	public void printStackTrace(java.io.PrintWriter s) {
		synchronized (s) {
			super.printStackTrace(s);
			if (nestedThrowable != null) {
				System.err.println("Nested Exception : ");
				nestedThrowable.printStackTrace(s);
			}
		}
	}
}
