package com.intertecintl.usernamelist.commons.config;

/**
* Enum that contains framework properties for the configuration
*  
* @author Leslin Briceno
* 
*/
public enum Configurable {
	HOME_VARIABLE("INTERTECINTL_HOME"),
	DEFAULT_PROPERTIES("ApplFramework"),
	PROPERTIES_FILE_EXTENSION(".properties"),
	XML_FILE_EXTENSION(".xml"),
	LOG4J_PROP("/config/log4j.properties"),
	LOG4J_XML("/config/log4j.xml"),
	CONFIG_DIR_PROP("/config/"),
	APP_HOME_DEFAULT("/opt/appl_home");

	private String value;

	/**
	 * Private constructor that initialize the constant
	 *
	 * @param value Constant value
	 */
	private Configurable(final String value) {
		this.value = value;
	}

	/**
	 * Method that returns the constant value.
	 *
	 * @return Constant value
	 */
	public String getValue() {
		return value;
	}
}