package com.intertecintl.usernamelist.validator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.constants.AppConstants;
import com.intertecintl.usernamelist.commons.exceptions.FormatException;
import com.intertecintl.usernamelist.commons.exceptions.RestrictedException;
import com.intertecintl.usernamelist.commons.exceptions.ValidationException;
import com.intertecintl.usernamelist.commons.properties.AppProperties;
import com.intertecintl.usernamelist.data.dao.impl.UsernameDAOFIleImpl;

/**
 * Class that exposes the data validation methods
 *  
 * @author Leslin Briceno
 * 
 */
@Component
public final class DataValidatorUtils implements Logable {
	
	@Autowired
	UsernameDAOFIleImpl usernameDAO;

	/**
	 * Method to validate the username format
	 * @param value Username entered
	 * @throws ValidationException Exception thrown when the username does not match with the allowed format
	 */
	public void validateUsernameFormat(final String value) throws FormatException{
		String regex = AppProperties.getGenericProp().getProperty(AppConstants.USERNAME_FORMAT_REGEX.getValue());
		LOGGER_DEBUG.debug("Validating the entered value " + value + " with the regular expression " + regex);
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		
		if(!matcher.matches()){
			String exceptionMessage = "The entered username doesn't match with the allowed format, it should be at least 6 characters long";
			LOGGER_ERROR.error(exceptionMessage);
			throw new FormatException(exceptionMessage);
		}
	}
	
	/**
	 * Method to validate if an username is restricted
	 * @param value Username entered
	 * @throws ValidationException Exception thrown when the username is restricted
	 */
	public void validateUsernameIsRestricted(final String username) throws RestrictedException, ValidationException{
		LOGGER_DEBUG.debug("Validating if the entered username is restricted");
		
		if(usernameDAO.searchUsernameInRestrictedList(username)){
			String exceptionMessage = "The entered username is restricted, plese provide a different one";
			LOGGER_ERROR.error(exceptionMessage);
			throw new RestrictedException(exceptionMessage);
		}
	}
	
	/**
	 * Method to validate if an username was used
	 * @param value Username entered
	 * @throws ValidationException Exception thrown when the username was used
	 */
	public void validateUsernameWasUsed(final String username) throws ValidationException{
		LOGGER_DEBUG.debug("Validating if the entered username was used");
		
		if(usernameDAO.searchUsernameInUsedList(username)){
			String exceptionMessage = "The entered username was used, plese provide a different one";
			LOGGER_ERROR.error(exceptionMessage);
			throw new ValidationException(exceptionMessage);
		}
	} 
}
