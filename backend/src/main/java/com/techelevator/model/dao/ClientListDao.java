package com.techelevator.model.dao;

import java.util.List;
import com.techelevator.model.ClientList;
import com.techelevator.model.TrainerProfile;
import com.techelevator.model.User;

public interface ClientListDao {
	
	public List<User> getClientListOfTrainer(long user_id);
	
	public TrainerProfile getTrainerProfile(ClientList clientList);
	
	public List<User> searchClientListOfTrainer(long user_id, String firstName, String lastName, String userName);
}
