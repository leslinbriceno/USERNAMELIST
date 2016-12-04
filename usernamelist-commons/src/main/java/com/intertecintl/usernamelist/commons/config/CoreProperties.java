package com.intertecintl.usernamelist.commons.config;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

import org.apache.commons.digester.Digester;

import com.intertecintl.usernamelist.commons.constants.AppConstants;

/**
 * Class which load the configuration file of the application
 *  
 * @author Leslin Briceno
 * 
 */
public class CoreProperties implements Logable {
	private static final String STR_APP = "intertecintl/";
	private static final String STR_PARAM = "/param";
	private final Map<Object, Object> cache = new WeakHashMap<>();
	private final Map<String, String> namespaces = new WeakHashMap<>();
	private final FrameworkConfig fwconfig = new FrameworkConfig();
	private String applId = "";
	private int namespace = 0;
	
	/**
	 * Gets the properties.
	 *
	 * @param applId the appl id
	 * @param property the property
	 * @return the properties
	 */
	public Properties getProperties(final String applId, final String property) {
		this.applId = applId;
		final StringBuilder path = new StringBuilder(this.fwconfig.getHomePath());
		Properties response = null;

		if (path != null) {
			path.append("/").append(this.applId).append(AppConstants.CONFIG_DIR_PROP.getValue());
			response = getPropertiesWNS(property, getPrefix(path.toString()), path.toString());
		}

		return response;
	}

	/**
	 * Gets the prefix.
	 *
	 * @param path the path
	 * @return the prefix
	 */
	private String getPrefix(final String path) {
		String prefix = this.namespaces.get(path);

		if (prefix == null) {
			prefix = (this.namespace++) + ".";
			this.namespaces.put(path, prefix);
		}

		return prefix;
	}

	/**
	 * Gets the properties wns.
	 *
	 * @param property the property
	 * @param prefix the prefix
	 * @param path the path
	 * @return the properties wns
	 */
	private Properties getPropertiesWNS(final String property, final String prefix, final String path) {
		Properties result = (Properties)this.cache.get(prefix + property);

		if(result == null || result.isEmpty()) {
			try {
				this.cache.put(prefix + property, getDigester(property).parse(new File(path + this.applId + Configurable.XML_FILE_EXTENSION.getValue())));
				result = (Properties)this.cache.get(prefix + property);
			} catch (Exception e) {
				LOGGER_ERROR.error(CoreProperties.class.getName(), e);
			}
		}

		return result;
	}

	/**
	 * Gets the digester.
	 *
	 * @param property the property
	 * @return the digester
	 */
	private Digester getDigester(final String property) {
		final Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate(STR_APP + property, "java.util.Properties", Properties.class);
		digester.addCallMethod(STR_APP + property + STR_PARAM, "setProperty", 2);
		digester.addCallParam(STR_APP + property + STR_PARAM + "/name", 0);
		digester.addCallParam(STR_APP + property + STR_PARAM + "/value", 1);
		return digester;
	}
}