package com.intertecintl.usernamelist.commons.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.intertecintl.usernamelist.commons.constants.AppConstants;

/**
 * Class that load configuration framework.
 *  
 * @author Leslin Briceno
 * 
 */
public class FrameworkConfig implements Logable {
	private String homepath = null;

	/**
	 * Gets the file properties.
	 *
	 * @return the file properties
	 */
	public String getFileProperties(final String applId) {
		return getHomePath() + applId + AppConstants.CONFIG_DIR_PROP.getValue() + Configurable.DEFAULT_PROPERTIES.getValue();
	}

	/**
	 * Gets the home path.
	 *
	 * @return the home path
	 */
	public String getHomePath() {
		if (this.homepath == null) {
			try {
				this.homepath = System.getenv(Configurable.HOME_VARIABLE.getValue());
			} catch (Exception e) {
				LOGGER_ERROR.error(FrameworkConfig.class.getName() + " : Could not load the homepath of the propertis from environment variable, we try again");
				LOGGER_ERROR.error(FrameworkConfig.class.getName(), e);
			}

			if (this.homepath == null) {
				final Properties props = getResource();

				if (props != null) {
					this.homepath = props.getProperty("config.homepath");
				}

				else {
					LOGGER_ERROR.error(FrameworkConfig.class.getName() + " : configuration file not found");
				}
			}

			else {
				this.homepath = homepath.replace("\\", "/");
			}

			if(this.homepath == null) {
				this.homepath = Configurable.APP_HOME_DEFAULT.getValue();
			}
		}

		return this.homepath;
	}

	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	private Properties getResource() {
		final ClassLoader loader = FrameworkConfig.class.getClassLoader();
		final URL url = loader.getResource(Configurable.DEFAULT_PROPERTIES.getValue() + Configurable.PROPERTIES_FILE_EXTENSION.getValue());
		Properties localProp = null;

		if (url != null) {
			InputStream is = null;
			Properties props = null;

			try {
				props = new Properties();
				is = loader.getResourceAsStream(Configurable.DEFAULT_PROPERTIES.getValue() + Configurable.PROPERTIES_FILE_EXTENSION.getValue());
				props.load(is);
				localProp = props;
			} catch (IOException e) {
				LOGGER_ERROR.error(FrameworkConfig.class.getName(), e);
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (Exception localException2) {
					LOGGER_ERROR.error(FrameworkConfig.class.getName(), localException2);
				}
			}
		}

		return localProp;
	}
}