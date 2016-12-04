package com.intertecintl.usernamelist.core.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.exceptions.FormatException;
import com.intertecintl.usernamelist.commons.exceptions.RestrictedException;
import com.intertecintl.usernamelist.commons.exceptions.ValidationException;
import com.intertecintl.usernamelist.core.utils.CoreUtils;
import com.intertecintl.usernamelist.validator.utils.DataValidatorUtils;

/**
 * Class that manage the interactions between UI and data managemet layer
 * 
 * @author Leslin Briceno
 * 
 */
@Component
public class UsernameListManager implements Logable{
	
	@Autowired
	DataValidatorUtils dataValidatorUtils;
	@Autowired
	CoreUtils coreUtils;
	
	/**
	 * Method that vaidates an entered username
	 * @param username Username entered
	 * @return Result of username validation
	 * @throws FormatException Exception thrown when an username hasn't a correct format
	 * @throws RestrictedException 
	 * @throws ValidationException 
	 */
	public Map<Boolean, List<String>> verifyUsername(final String username) throws FormatException, RestrictedException{
		Map<Boolean, List<String>> result = new HashMap<>();
		List<String> suggestedUsernames = null;
		
		try{
			dataValidatorUtils.validateUsernameFormat(username.trim());
			dataValidatorUtils.validateUsernameIsRestricted(username.trim());
			dataValidatorUtils.validateUsernameWasUsed(username.trim());
			result.put(Boolean.TRUE, new ArrayList<>());
		}catch(ValidationException e){
			suggestedUsernames = coreUtils.generateSuggestedUsernames(username.trim()); 
			result.put(Boolean.FALSE, suggestedUsernames);
		}
		
		return result; 
	}

}
