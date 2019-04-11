package com.techelevator.model.profile;

import java.util.List;
import java.util.Map;

import com.techelevator.model.user.User;

/**
 * TrainerProfileDao Interface has methods for getting a Trainer's Trainer Profile, updating a
 * Trainer's Trainer Profile, and for finding all Trainer Profiles that meet the search criteria
 */
public interface ProfileDao {
	
	public void createUserProfile(UserProfile userProfile, User user);
	
	public void createUserProfile(User user);
	
	/**
	 * @param id the User Id for the Trainer
	 * @return TrainerProfile for the Trainer for the given Id
	 */
	public UserProfile getUserProfileById(Long id);
	
	/**
	 * @param UserProfile to update the logged in Trainer's Trainer Profile to
	 */
	public void updateUserProfile(UserProfile trainerProfile);
	
	/**
	 * @param city this is the city to search for a trainer in
	 * @param state this is the state to search for a trainer in
	 * @param price_per_hour this is the minimum range price to search for a trainer in
	 * @param rating this is the minimum rating to search for a trainer in
	 * @param certifications these are the certifications a trainer must have
	 * @return List<TrainerProfile> for all Trainer's that fall within the search criteria
	 */
	public List<UserProfile> getTrainerProfilesBySearchCriteria(String city, String state, int min_price_per_hour, int max_price_per_hour, double rating, String certifications);
	
	/**
	 * @param user_id the User Id of the Trainer who's Client List is being searched
	 * @param firstName the First Name to search the client list for
	 * @param lastName the Last Name to search the client list for
	 * @param userName the User Name to search the client list for
	 * @return List<User> that are in that Trainer's Client List and match the search criteria
	 */
	public List<User> searchClientListOfTrainer(long user_id, String firstName, String lastName, String userName);
	
	public ClientList getEntireClientListForTrainer(long user_id);
	
	public ClientList searchClientList(long user_id, String firstName, String lastName, String username);
	
	public Map<User,String[]> searchClientListForPrivateNotes(long user_id, String firstName, String lastName, String username);
	
	public void addClientToClientList(long trainer_id, long client_id);
	
	public void removeClientFromClientList(long trainer_id, long client_id);
	
	public void addPrivateNoteToClientList(long trainer_id, long client_id, String privateNote);
	
	public void removePrivateNoteFromClientList(long trainer_id, long client_id, String privateNote);
	
}