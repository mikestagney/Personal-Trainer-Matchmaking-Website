package com.techelevator.model.workout;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class WorkoutPlans {
	
	
	@Min(1)
	@NotBlank(message="Work Out Id is required")
	private long workOutId;
	@Min(1)
	@NotBlank(message="Trainer Id is required")
	private long trainerId;
	@Min(1)
	@NotBlank(message="Client Id is required")
	private long clientId;

	@Size(min = 2, max = 20)
	@NotBlank(message="Message subject line is required")
	private String title;
	@Size(min = 2, max = 250)
	@NotBlank(message="Message body text is required")
	private String message;
	
	private Map<Long,String[]> workOutPlans = new HashMap<Long,String[]>();

	public long getWorkOutId() {
		return workOutId;
	}

	public void setWorkOutId(long workOutId) {
		this.workOutId = workOutId;
	}

	public long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

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

	public Map<Long, String[]> getWorkOutPlans() {
		return workOutPlans;
	}

	public void setWorkOutPlans(Map<Long, String[]> workOutPlans) {
		this.workOutPlans = workOutPlans;
	}
	
	

}
