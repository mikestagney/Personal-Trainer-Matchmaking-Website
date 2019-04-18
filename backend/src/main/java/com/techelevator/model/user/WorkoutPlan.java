package com.techelevator.model.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class WorkoutPlan {
	
	@MapToDB("workoutplan_id")
	@Min(1)
	@NotBlank(message="Work Out Id is required")
	private Integer workoutId;
	@MapToDB("trainer_id")
	@Min(1)
	@NotBlank(message="Trainer Id is required")
	private Integer trainerId;
	@MapToDB("client_id")
	@Min(1)
	@NotBlank(message="Client Id is required")
	private Integer clientId;
	@MapToDB("completed")
	private Boolean completed;
	
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Integer getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(Integer workoutId) {
		this.workoutId = workoutId;
	}

	public Integer getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@MapToDB
	@Size(min = 2, max = 20)
	@NotBlank(message="Message subject line is required")
	private String title;
	@MapToDB("body")
	@Size(min = 2, max = 250)
	@NotBlank(message="Message body text is required")
	private String message;
	@MapToDB("days_of_week")
	private String daysOfWeek;
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	
	

}
