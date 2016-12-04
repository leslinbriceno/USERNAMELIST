package com.intertecintl.usernamelist.commons.config;

import org.apache.log4j.PropertyConfigurator;

import com.intertecintl.usernamelist.commons.exceptions.ActionException;

/**
 * Class that load the log configuration of the application
 *  
 * @author Leslin Briceno
 * 
 */
public class Log4JConfig {
	private String home;

	/**
	 * Instantiates a new log4 j config.
	 *
	 * @throws Exception the exception
	 */
	public Log4JConfig() throws ActionException {
		this.home = System.getenv(Configurable.HOME_VARIABLE.getValue());
		
		if (this.home == null) {
			this.home = Configurable.APP_HOME_DEFAULT.getValue();
		}
	}

	/**
	 * Configure.
	 *
	 * @param idAplicacion the id aplicacion
	 * @param isXML the is xml
	 */
	public void configure(String idAplicacion, boolean isXML) {
		PropertyConfigurator.configure(this.home + "/" + idAplicacion + (isXML ? Configurable.LOG4J_XML.getValue() : Configurable.LOG4J_PROP.getValue()));
	}
}
