package com.intertecintl.usernamelist.commons.config;

import org.apache.log4j.Logger;

/**
* Interface that controls the application logs
* 
* @author Leslin Briceno
* 
*/
public interface Logable {
	public static final Logger LOGGER_ERROR = Logger.getLogger("ErrorLogging");
	public static final Logger LOGGER_INFO 	= Logger.getLogger("InfoLogging");
	public static final Logger LOGGER_DEBUG = Logger.getLogger("DebugLogging");
}
