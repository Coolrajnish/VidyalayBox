package com.ms.shared.util.util;

import java.io.PrintWriter;
import java.io.StringWriter;

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
public class FatalException extends NestedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for FatalException. Create a fatal exception with no message
	 * description.
	 */
	public FatalException() {
		super();
	}

	/**
	 * Constructor for FatalException. Create a fatal exception with the given
	 * message description.
	 * 
	 * @param str String
	 */
	public FatalException(String str) {
		super(str);
	}

	/**
	 * Create a fatal exception with the given message description, and chain it to
	 * the given exception.
	 * 
	 * @param str       String
	 * @param throwable Throwable
	 */
	public FatalException(String str, Throwable throwable) {
		super(str, throwable);
	}

	/**
	 * Create a fatal exception with the given message description, and chain it to
	 * the d=given exception.
	 * 
	 * @param str   String
	 * @param exSQL java.sql.SQLException
	 */
	public FatalException(String str, java.sql.SQLException exSQL) {
		super(((new StringBuffer(str).append("\nSQLDetails:\n").append(exSQL.getMessage()).append("\nSQLState: ")
				.append(exSQL.getSQLState()).append("\nSQLCode: ").append(exSQL.getErrorCode())).toString()));
	}

	public static String convertStackTraceToString(Throwable pThrowable) {
		if (pThrowable == null) {
			return null;
		} else {
			StringWriter sw = new StringWriter();
			pThrowable.printStackTrace(new PrintWriter(sw));
			return sw.toString();
		}
	}
}
