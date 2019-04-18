package com.techelevator.model.workout;

import java.util.List;

import com.techelevator.model.user.WorkoutPlan;

public interface WorkoutDao {
	
	public WorkoutPlan getWorkOutPlan(long workoutId);
	
	public WorkoutPlan getWorkOutPlanForId(long userId);
	
	public List<WorkoutPlan> getWorkOutPlansForIds(long trainerId, long clientId);
	
	public void createWorkoutPlan(WorkoutPlan workoutPlan);
	
	public void updateWorkoutPlan(WorkoutPlan workoutPlan);
}
