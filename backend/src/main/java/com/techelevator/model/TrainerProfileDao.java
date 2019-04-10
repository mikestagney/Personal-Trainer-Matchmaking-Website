package com.techelevator.model;

public interface TrainerProfileDao {
	
	public TrainerProfile getTrainerProfile(Long id);
	
	public void update(TrainerProfile trainerProfile);
}
