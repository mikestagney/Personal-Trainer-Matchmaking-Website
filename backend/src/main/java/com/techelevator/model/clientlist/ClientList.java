package com.techelevator.model.clientlist;

import java.util.ArrayList;
import java.util.List;
import com.techelevator.model.trainerprofile.TrainerProfile;
import com.techelevator.model.user.User;

/**
 * ClientList Class is an object that holds a TrainerProfile
 * and a List<User> that are in that Trainer's Client List
 */
public class ClientList {
	
	private TrainerProfile trainerProfile = new TrainerProfile();
	private List<User> clientList = new ArrayList<User>();
	
	/**
	 * @return TrainerProfile object
	 */
	public TrainerProfile getTrainerProfile() {
		return trainerProfile;
	}

	/**
	 * @param TrainerProfile object to be set to
	 */
	public void setTrainerProfile(TrainerProfile trainerProfile) {
		this.trainerProfile = trainerProfile;
	}

	/**
	 * @return List<User> for all Users in Trainer's Client List
	 */
	public List<User> getClientList() {
		return clientList;
	}

	/**
	 * @param List<User> for all Users in Trainer's Client List to be set to
	 */
	public void setClientList(List<User> clientList) {
		this.clientList = clientList;
	}
}