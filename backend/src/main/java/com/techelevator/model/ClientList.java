package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientList
 */
public class ClientList {
	
	/**
	 * TrainerProfile trainerProfile
	 */
	private TrainerProfile trainerProfile = new TrainerProfile();
	
	/**
	 * List<User> clientList
	 */
	private List<User> clientList = new ArrayList<User>();
	
	/**
	 * public TrainerProfile getTrainerProfile()
	 */
	public TrainerProfile getTrainerProfile() {
		return trainerProfile;
	}

	/**
	 * public void setTrainerProfile(TrainerProfile trainerProfile)
	 */
	public void setTrainerProfile(TrainerProfile trainerProfile) {
		this.trainerProfile = trainerProfile;
	}

	/**
	 * public List<User> getClientList()
	 */
	public List<User> getClientList() {
		return clientList;
	}

	/**
	 * public void setClientList(List<User> clientList)
	 */
	public void setClientList(List<User> clientList) {
		this.clientList = clientList;
	}
}
