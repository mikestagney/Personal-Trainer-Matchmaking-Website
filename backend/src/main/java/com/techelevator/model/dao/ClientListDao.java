package com.techelevator.model.dao;

import java.util.List;
import com.techelevator.model.User;

public interface ClientListDao {
			
	public List<User> searchClientListOfTrainer(long user_id, String firstName, String lastName, String userName);
}
