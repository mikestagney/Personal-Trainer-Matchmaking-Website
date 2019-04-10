package com.techelevator.model.dao;

import java.util.List;
import com.techelevator.model.TrainerProfile;

/**
 * TrainerProfileDao
 */
public interface TrainerProfileDao {
	
	/**
	 * public TrainerProfile getTrainerProfileById(Long id)
	 */
	public TrainerProfile getTrainerProfileById(Long id);
	
	/**
	 * public void updateTrainerProfile(TrainerProfile trainerProfile)
	 */
	public void updateTrainerProfile(TrainerProfile trainerProfile);
	
	/**
	 * public List<TrainerProfile> getTrainerProfilesBySearchCriteria(String city, String state, int price_per_hour, double rating, String certifications)
	 */
	public List<TrainerProfile> getTrainerProfilesBySearchCriteria(String city, String state, int price_per_hour, double rating, String certifications);
}
