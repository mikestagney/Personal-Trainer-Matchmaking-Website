package com.techelevator.model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClientList Class is an object that holds a TrainerProfile
 * and a List<User> that are in that Trainer's Client List
 */
public class ClientList {
	
	//@NotBlank(message="Trainer Profile is required")
	//private Trainer trainer = new Trainer();
	
	private List<User> listOfClients = new ArrayList<User>();
	private Map<User,String[]> privateNotes = new HashMap<User,String[]>();



	/**
	 * @return List<User> for all Users in Trainer's Client List
	 */
	public List<User> getListOfClients() {
		return listOfClients;
	}

	/**
	 * @param List<User> for all Users in Trainer's Client List to be set to
	 */
	public void setListOfClients(List<User> listOfClients) {
		this.listOfClients = listOfClients;
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