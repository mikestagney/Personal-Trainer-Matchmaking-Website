package com.techelevator.model.workout;

public interface WorkOutDao {
	
	public WorkOut getWorkOutPlan(long workoutId);
	
	public WorkOut getWorkOutPlanForId(long userId);
}
