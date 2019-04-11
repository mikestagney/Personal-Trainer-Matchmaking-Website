package com.techelevator.model.trainerprofile;

import java.util.List;

/**
 * TrainerProfileDao Interface has methods for getting a Trainer's Trainer Profile, updating a
 * Trainer's Trainer Profile, and for finding all Trainer Profiles that meet the search criteria
 */
public interface TrainerProfileDao {
	
	/**
	 * @param id the User Id for the Trainer
	 * @return TrainerProfile for the Trainer for the given Id
	 */
	public TrainerProfile getTrainerProfileById(Long id);
	
	/**
	 * @param TrainerProfile to update the logged in Trainer's Trainer Profile to
	 */
	public void updateTrainerProfile(TrainerProfile trainerProfile);
	
	/**
	 * @param city this is the city to search for a trainer in
	 * @param state this is the state to search for a trainer in
	 * @param price_per_hour this is the minimum range price to search for a trainer in
	 * @param rating this is the minimum rating to search for a trainer in
	 * @param certifications these are the certifications a trainer must have
	 * @return List<TrainerProfile> for all Trainer's that fall within the search criteria
	 */
	public List<TrainerProfile> getTrainerProfilesBySearchCriteria(String city, String state, int min_price_per_hour, int max_price_per_hour, double rating, String certifications);
}