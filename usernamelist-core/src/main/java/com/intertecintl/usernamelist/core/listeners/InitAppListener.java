package com.intertecintl.usernamelist.core.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.intertecintl.usernamelist.commons.config.Configurable;
import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.constants.AppConstants;
import com.intertecintl.usernamelist.commons.utils.LoadConfigurationUtils;

/**
* Listener that start application
* 
* @author Leslin Briceno
* 
*/
public class InitAppListener implements Logable {
	
	@Autowired
	private LoadConfigurationUtils loadUtils;
	
	/**
	 * Constructor method of the class
	 * @param loadUtils The loadUtils object
	 * @param watcherListener The watcherListener object
	 */
	public InitAppListener(LoadConfigurationUtils loadUtils){
		this.loadUtils = loadUtils;
	}

	/**
	 * Method which initialize the appplication context
	 */
	public void initializeContext() {
		System.setProperty(Configurable.HOME_VARIABLE.getValue(), AppConstants.APP_HOME_VAR_DEFINITION.getValue()); 
		loadUtils.initLog4j(AppConstants.ID_APP.getValue());
		LOGGER_INFO.info("Initializing the application");
		loadUtils.loadFrameworkConfig();
		LOGGER_INFO.info("Application succesfully started");
	}
	
	
}