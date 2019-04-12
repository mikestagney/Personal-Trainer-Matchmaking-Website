package com.techelevator.model.user;

import java.util.List;

/**
 * UserDao
 */
public interface UserDao {
	
	/**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     * @param userName the user name to give the new user
     * @param password the user's password
     * @param role the user's role
     * @return the new user
     */
	public User saveUser(String userName, String firstName, String lastName, String password, String role);

	/**
	 * @param User object of the user to change password for
     * @param newPassword the new password
	 */
    public void changePassword(User user, String newPassword);

    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    public User getValidUserWithPassword(String userName, String password);
    
    /**
     * @param username the user name of the user requested
     * @return the User requested
     */
    public User getUserByUsername(String username);
    
    /**
     * @param id the id of the user requested
     * @return the User requested
     */
    public User getUserById(Long id);
    
    public Trainer getTrainerById(Long id);
    
    public Client getClientById(Long id);
    
	public void updateClient(Client user);
	
	public void updateTrainer(Trainer trainer);
	
	/**
	 * @param city this is the city to search for a trainer in
	 * @param state this is the state to search for a trainer in
	 * @param price_per_hour this is the minimum range price to search for a trainer in
	 * @param rating this is the minimum rating to search for a trainer in
	 * @param certifications these are the certifications a trainer must have
	 * @return List<TrainerProfile> for all Trainer's that fall within the search criteria
	 */
	public List<Trainer> getTrainersSearch(String name, String city, String state, int minHourlyRate, int maxHourlyRate, double rating);	
	
	/**
	 * @param user_id the User Id of the Trainer who's Client List is being searched
	 * @param name the Name to search the client list for
	 * @param userName the User Name to search the client list for
	 * @return ClientList for Clients in the Trainer's Client List that match the search criteria
	 */
	public ClientList searchClientList(long id, String name, String userName);
			
	public void addClientToClientList(long trainer_id, long client_id);
	
	public void removeClientFromClientList(long trainer_id, long client_id);
	
	public void addPrivateNoteToClientList(long trainer_id, long client_id, String privateNote);
	
	public void removePrivateNoteFromClientList(long trainer_id, long client_id, String privateNote);

}