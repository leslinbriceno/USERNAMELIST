package com.intertecintl.usernamelist.data.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.constants.AppConstants;
import com.intertecintl.usernamelist.commons.exceptions.RestrictedException;
import com.intertecintl.usernamelist.commons.exceptions.ValidationException;
import com.intertecintl.usernamelist.commons.properties.AppProperties;
import com.intertecintl.usernamelist.data.dao.UsernameDAO;

/**
 * Implementation class of the username dao that find the data into files
 * 
 * @author Leslin Briceno
 *
 */
@Service
public class UsernameDAOFIleImpl implements UsernameDAO, Logable {

	/*
	 * (non-Javadoc)
	 * @see com.intertecintl.usernamelist.data.dao.UsernameDAO#searchUsernameInRestrictedList(java.lang.String)
	 */
	public boolean searchUsernameInRestrictedList(final String username) throws RestrictedException, ValidationException {
		return this.searchUsername(username, AppConstants.RESTRICTED_OPTION.getValue());
	}

	/*
	 * (non-Javadoc)
	 * @see com.intertecintl.usernamelist.data.dao.UsernameDAO#searchUsernameInUsedList(java.lang.String)
	 */
	public boolean searchUsernameInUsedList(final String username) throws ValidationException {
		return this.searchUsername(username, AppConstants.USED_OPTION.getValue());
	}
	
	/**
	 * Generic method to search an username into a file 
	 * @param option Search option
	 * @return True if found, false otherwise
	 * @throws ValidationException Exception thrown when a validation exception ocurred
	 */
	private boolean searchUsername(final String username, final String option) throws ValidationException{
		boolean found = Boolean.FALSE;
		Scanner scanner = null;
		String fileName = getFileName(option);
		
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			String exceptionMessage = "File " + fileName + " not found into config folder"; 
			LOGGER_ERROR.error(exceptionMessage);
			throw new ValidationException(exceptionMessage);
		}
		
		while(scanner.hasNextLine() && !found){
			String line = scanner.nextLine();
			
			if(doComparison(line, username, option)){
				found = Boolean.TRUE;
			}
		}
		
		scanner.close();
		
		return found;
	}
	
	/**
	 * Method that provide the file name to be used
	 * @param option Option of the searching method
	 * @return File name to be used
	 */
	private String getFileName(final String option){
		String fileName;
		
		if(AppConstants.RESTRICTED_OPTION.getValue().equals(option)){
			fileName = AppConstants.APP_CONFIG_PATH.getValue().concat(AppProperties.getGenericProp().getProperty(AppConstants.RESTRICTED_NAMES_FILE.getValue()));
		}else{
			fileName = AppConstants.APP_CONFIG_PATH.getValue().concat(AppProperties.getGenericProp().getProperty(AppConstants.USED_NAMES_FILE.getValue()));
		}
		
		return fileName;
	}
	
	/**
	 * Method that compare two provided string values depends on file used
	 * @param fileValue Value of the file line
	 * @param usernameValue Username value
	 * @param option Option of the comparison
	 * @return True if the comparison is successful, false if otherwise
	 */
	private boolean doComparison(final String fileValue, final String usernameValue, final String option){
		boolean areEquals;
		
		if(AppConstants.RESTRICTED_OPTION.getValue().equals(option)){
			areEquals = usernameValue.toLowerCase().contains(fileValue.toLowerCase());
		}else{
			areEquals = fileValue.equalsIgnoreCase(usernameValue);
		}
		
		return areEquals;
	}

}
