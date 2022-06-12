package user;

import java.util.regex.Pattern;

import databaseManager.DBManager;
import models.User;

public class UserManager {

	// Signs a user in, returns an empty string if successful or an error if any
	public static String Signin(User user) {

		// Check email validity
		
		// Create regex and pattern
		var regex = "^(.+)@(.+)$";
		var pattern = Pattern.compile(regex);
		
		// Check if email matches
		var match = pattern.matcher(user.email).matches();
		
		// If the email address did not match
		if(!match)
			// Return error
			return "invalid email address";
		
		// Add the user to the database and return any errors that might exist
		return DBManager.AddUser(user);

			
	}
}
