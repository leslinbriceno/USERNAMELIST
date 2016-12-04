package com.intertecintl.usernamelist.commons.exceptions;

/**
 * Class that encapsulate an action error to be thrown through an exception.
 *  
 * @author Leslin Briceno
 * 
 */
public class ActionException extends Exception {
	
	private static final long serialVersionUID = 861687798517798736L;

	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 */
	public ActionException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new action exception.
	 *
	 * @param cause the cause
	 */
	public ActionException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ActionException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
