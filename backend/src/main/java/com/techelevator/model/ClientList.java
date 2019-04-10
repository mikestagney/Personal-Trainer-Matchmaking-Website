package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class ClientList {
	
	private TrainerProfile trainerProfile = new TrainerProfile();
	
	private List<User> clientList = new ArrayList<User>();

	public TrainerProfile getTrainerProfile() {
		return trainerProfile;
	}

	public void setTrainerProfile(TrainerProfile trainerProfile) {
		this.trainerProfile = trainerProfile;
	}

	public List<User> getClientList() {
		return clientList;
	}

	public void setClientList(List<User> clientList) {
		this.clientList = clientList;
	}
}
