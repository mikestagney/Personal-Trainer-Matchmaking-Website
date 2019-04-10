package com.techelevator.model;

import java.time.LocalDate;

public class PrivateMessage {
	
	private long trainerId;
	
	private long clientId;
	
	private LocalDate datePosted;
	
	private String subject;
	
	private String messageBody;

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

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
