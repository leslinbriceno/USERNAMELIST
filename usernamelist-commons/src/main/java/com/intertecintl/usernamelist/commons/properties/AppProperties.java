package com.intertecintl.usernamelist.commons.properties;

import java.util.Enumeration;
import java.util.Properties;

import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.constants.AppConstants;

/**
 * Class that stores all related properties with the application after it's initialization
 * 
 * @author Leslin Briceno
 */
public class AppProperties implements Logable {
	
	private static Properties genericProp;
	
	/**
	 * Declaration of private constructor of the class to hide the default constructor of this utility class
	 */
	private AppProperties(){}

	/**
	 * @return the genericProp
	 */
	public static Properties getGenericProp() {
		return genericProp;
	}

	/**
	 * @param genericProp the genericProp to set
	 */
	public static void setGenericProp(Properties genericProp) {
		AppProperties.genericProp = genericProp;
	}
	
	/**
	 * Method which print all defined properties in the class
	 * @return True if successful, flase otherwise
	 */
	public static boolean printProperties(){
		printProps(genericProp);
		return Boolean.TRUE;
	}

	/**
	 * Method that print the content of the specified properties
	 * @param prop Propeties to print
	 */
	private static void printProps(final Properties prop){
		String aux;
		final Enumeration<?> e = prop.propertyNames();
		
		while(e.hasMoreElements()){
			aux = e.nextElement().toString();
			
			if(!aux.toLowerCase().contains(AppConstants.PASSWORD_KEY.getValue().toLowerCase())){
				LOGGER_INFO.info(aux + ": " + prop.getProperty(aux));
			}else{
				LOGGER_INFO.info(aux + ": XXXXXX");
			}
		}
	}

}
