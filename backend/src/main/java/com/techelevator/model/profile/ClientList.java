package com.techelevator.model.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;

import com.techelevator.model.user.User;

/**
 * ClientList Class is an object that holds a TrainerProfile
 * and a List<User> that are in that Trainer's Client List
 */
public class ClientList {
	
	@NotBlank(message="Trainer Profile is required")
	private UserProfile trainerProfile = new UserProfile();
	private List<User> clientList = new ArrayList<User>();
	private Map<User,String[]> privateNotes = new HashMap<User,String[]>();

	/**
	 * @return TrainerProfile object
	 */
	public UserProfile getTrainerProfile() {
		return trainerProfile;
	}

	/**
	 * @param UserProfile object to be set to
	 */
	public void setTrainerProfile(UserProfile trainerProfile) {
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
	
	public Map<User, String[]> getPrivateNotes() {
		return privateNotes;
	}

	public void setPrivateNotes(Map<User, String[]> privateNotes) {
		this.privateNotes = privateNotes;
	}
	
	public String[] getNotes(User user) {
		return privateNotes.get(user);
	}
}