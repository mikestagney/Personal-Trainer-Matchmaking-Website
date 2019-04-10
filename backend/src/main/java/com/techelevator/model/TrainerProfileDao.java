package com.techelevator.model;

import java.util.List;

public interface TrainerProfileDao {
	
	public TrainerProfile getTrainerProfileById(Long id);
	
	public void updateTrainerProfile(TrainerProfile trainerProfile);
	
	public List<TrainerProfile> getTrainerProfilesBySearchCriteria(String city, String state, int price_per_hour, double rating, String certifications);
}
