package com.intertecintl.usernamelist.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.constants.AppConstants;
import com.intertecintl.usernamelist.commons.exceptions.ValidationException;
import com.intertecintl.usernamelist.commons.properties.AppProperties;
import com.intertecintl.usernamelist.validator.utils.DataValidatorUtils;

/**
 * Utility class of the core component of the application
 * 
 * @author Leslin Briceno
 *
 */
@Component
public class CoreUtils implements Logable{
	
	@Autowired
	DataValidatorUtils dataValidatorUtils;
	
	/**
	 * Method to generate suggested usernames based on username provided by the final user
	 * @param providedUserName Provided username
	 * @return Suggested username in a list of strings
	 */
	public List<String> generateSuggestedUsernames(final String providedUserName){
		List<String> suggestedUsernamesList = new ArrayList<>();
		
		while(suggestedUsernamesList.size() < Integer.valueOf(AppProperties.getGenericProp().getProperty(AppConstants.SUGGESTED_LIST_LENGTH.getValue()))){
			boolean avoidIt = Boolean.FALSE;
			String generatedString = RandomStringUtils.randomAlphanumeric(2);
			String suggestedUsername = providedUserName.concat(generatedString);
			
			try {
				dataValidatorUtils.validateUsernameWasUsed(suggestedUsername);
			} catch (ValidationException e) {
				LOGGER_DEBUG.debug("Username " + suggestedUsername + " was unavailable, we proceed to generate a new one");
				avoidIt = Boolean.TRUE;
			}
			
			if(!avoidIt && !suggestedUsernamesList.contains(suggestedUsername)){
				suggestedUsernamesList.add(suggestedUsername);
			}			
		}
		
		return suggestedUsernamesList;		
	}

}
