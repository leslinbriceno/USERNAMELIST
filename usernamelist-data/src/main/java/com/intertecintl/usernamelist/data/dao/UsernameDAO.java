package com.intertecintl.usernamelist.data.dao;

import com.intertecintl.usernamelist.commons.exceptions.RestrictedException;
import com.intertecintl.usernamelist.commons.exceptions.ValidationException;

/**
 * Interface that contains the DAO methods for the application
 * 
 * @author Leslin Briceno
 *
 */
public interface UsernameDAO {
	
	/**
	 * Method that search a provided username into the username restricted list
	 * @param username Username provided
	 * @return True if the method found the username into the list, false otherwise
	 * @throws RestrictedException Exception thrown when a validation exception ocurred
	 * @throws ValidationException Exception thrown when a validation exception ocurred
	 */
	boolean searchUsernameInRestrictedList(String username) throws RestrictedException, ValidationException;
	
	/**
	 * Method that search a provided username into the username used list
	 * @param username Username provided
	 * @return True if the method found the username into the list, false otherwise
	 * @throws ValidationException Exception thrown when a validation exception ocurred
	 */
	boolean searchUsernameInUsedList(String username) throws ValidationException;

}
