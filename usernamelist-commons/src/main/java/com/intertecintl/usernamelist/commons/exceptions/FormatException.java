package com.intertecintl.usernamelist.commons.exceptions;

/**
 * Class that encapsulate a format validation error to be thrown through an exception.
 * 
 * @author Leslin Briceno
 * 
 */
public class FormatException extends Exception {
	private static final long serialVersionUID = 4170766642741292528L;
	
	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 */
	public FormatException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new action exception.
	 *
	 * @param cause the cause
	 */
	public FormatException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public FormatException(final String message, final Throwable cause) {
		super(message, cause);
	}
}