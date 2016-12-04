package com.intertecintl.usernamelist.commons.utils;

import com.intertecintl.usernamelist.commons.config.CoreProperties;
import com.intertecintl.usernamelist.commons.config.Log4JConfig;
import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.constants.AppConstants;
import com.intertecintl.usernamelist.commons.exceptions.ActionException;
import com.intertecintl.usernamelist.commons.properties.AppProperties;

/**
 * Class that expose the functionalities to load configuration files
 *  
 * @author Leslin Briceno
 * 
 */
public final class LoadConfigurationUtils implements Logable {
	private CoreProperties core;
	private Log4JConfig log;

	/**
	 * Instantiates a new load configuration utils.
	 */
	public LoadConfigurationUtils() {
		try {
			core = new CoreProperties();
			log = new Log4JConfig();
		} catch(ActionException e) {
			LOGGER_ERROR.error(LoadConfigurationUtils.class.getName(), e);
		}
	}

	/**
	 * Load framework config.
	 */
	public synchronized void loadFrameworkConfig() {
		try {
			AppProperties.setGenericProp(core.getProperties(AppConstants.ID_APP.getValue(), "generic"));
			AppProperties.printProperties();
		} catch(Exception e) {
			LOGGER_ERROR.error("Error loading the configuration", e);
		}
	}

	/**
	 * Metodo que inicializa el componente Log4J.
	 *
	 * @param appId the app id
	 */
	public synchronized void initLog4j(final String appId) {
		if(log != null) {
			log.configure(appId, false);
		}
		
		else {
			LOGGER_ERROR.error("Log4J should be reviewed, because it couldn't be initialized");
		}
	}
}