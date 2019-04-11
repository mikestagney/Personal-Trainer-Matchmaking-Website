package com.techelevator.model.clientlist;

import java.util.List;
import com.techelevator.model.user.User;

/**
 * ClientListDao Interface has a method for Search the Trainer's Client List
 */
public interface ClientListDao {
		
	/**
	 * @param user_id the User Id of the Trainer who's Client List is being searched
	 * @param firstName the First Name to search the client list for
	 * @param lastName the Last Name to search the client list for
	 * @param userName the User Name to search the client list for
	 * @return List<User> that are in that Trainer's Client List and match the search criteria
	 */
	public List<User> searchClientListOfTrainer(long user_id, String firstName, String lastName, String userName);
}