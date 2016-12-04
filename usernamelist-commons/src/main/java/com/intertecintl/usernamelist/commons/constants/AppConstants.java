package com.intertecintl.usernamelist.commons.constants;

import java.nio.file.Path;
import com.intertecintl.usernamelist.commons.config.FrameworkConfig;

/**
 * Enum class that contains the applicacation's contants
 * 
 * @author Leslin Briceno
 */
public enum AppConstants {
	
	ID_APP("USERNAMELIST"),
	CONFIG_DIR_PROP("/config/"),
	APP_HOME_VAR_DEFINITION(new FrameworkConfig().getHomePath()),
	APP_CONFIG_PATH(APP_HOME_VAR_DEFINITION.getValue() + "/" + ID_APP.getValue() + CONFIG_DIR_PROP.getValue()),
	APP_CONFIG_FILE_NAME(ID_APP.getValue() + ".xml"),
	PASSWORD_KEY("password"),
	TIME_VERIFY_CHANGE_CONF_FILE("TIME_VERIFY_CHANGE_CONF_FILE"),
	USERNAME_FORMAT_REGEX("USERNAME_FORMAT_REGEX"),
	RESTRICTED_NAMES_FILE("RESTRICTED_NAMES_FILE"),
	USED_NAMES_FILE("USED_NAMES_FILE"),
	RESTRICTED_OPTION("RESTRICTED_OPTION"),
	USED_OPTION("USED_OPTION"),
	SUGGESTED_LIST_LENGTH("SUGGESTED_LIST_LENGTH");
	
	private String value;
	
	/**
	 * Private constructor of the enum
	 * @param value Constant value
	 */
	private AppConstants(final String value){
		this.value = value;
	}
	
	/**
	 * Private constructor of the enum
	 * @param path Constant value
	 */
	private AppConstants(final Path path){
		this.value = path.toString();
	}

	/**
	 * Method which returns the value of a constant
	 * @return Constant value
	 */
	public String getValue() {
		return value;
	}

}
