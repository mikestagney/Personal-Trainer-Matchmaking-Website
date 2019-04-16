package com.techelevator.model.workout;

import java.util.List;

public interface WorkoutDao {
	
	public WorkoutPlans getWorkOutPlan(long workoutId);
	
	public WorkoutPlans getWorkOutPlanForId(long userId);
	
	public List<WorkoutPlans> getWorkOutPlansForIds(long trainerId, long clientId);
}
