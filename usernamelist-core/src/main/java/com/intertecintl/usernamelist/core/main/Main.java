package com.intertecintl.usernamelist.core.main;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intertecintl.usernamelist.commons.config.Logable;
import com.intertecintl.usernamelist.commons.exceptions.FormatException;
import com.intertecintl.usernamelist.commons.exceptions.RestrictedException;
import com.intertecintl.usernamelist.commons.exceptions.ValidationException;
import com.intertecintl.usernamelist.core.listeners.InitAppListener;
import com.intertecintl.usernamelist.core.managers.UsernameListManager;

/**
 * Main class of the application
 * 
 * @author Leslin Briceno
 *
 */
public class Main implements Logable {
	
	/**
	 * Main method of the application
	 * @param args Application arguments
	 * @throws FormatException Exception thrown when a username format is not valid
	 * @throws ValidationException 
	 */
	public static void main(String[] args) throws FormatException, ValidationException {
		if(args.length == 0 || args.length > 2){
			System.out.println();
			System.out.println("\tMode of use:");
			System.out.println();
			System.out.println("\t\tExecute: java -jar UserNameList.jar \"<username_to_check>\"");
			System.out.println();
			System.out.println("\t\tWhere <username_to_check> is the username you want to check");
			System.out.println();
			System.exit(0);
		}
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		InitAppListener initAppListener = (InitAppListener) applicationContext.getBean("initAppListener");
		initAppListener.initializeContext();
		
		UsernameListManager usernameListManager = (UsernameListManager) applicationContext.getBean("usernameListManager");
		Map<Boolean, List<String>> result;
		
		try {
			result = usernameListManager.verifyUsername(args[0]);
			
			if(result.containsKey(Boolean.TRUE)){
				System.out.println();
				System.out.println("\t\tThe entered username " + args[0] + " is valid!");
			}else{
				List<String> suggestedUsernames = result.get(Boolean.FALSE);
				System.out.println();
				System.out.println("\t\tThe entered username " + args[0] + " was used!");
				System.out.println();
				System.out.println("\t\tWe suggest the following usernames:");
				System.out.println();
				
				for(String suggestion : suggestedUsernames){
					System.out.println("\t\t\t" + suggestion);
				}
			}
		} catch (RestrictedException e) {
			System.out.println();
			System.out.println("\t\tThe entered username " + args[0] + " is restricted, please provide a new one!");
		}
	}

}
