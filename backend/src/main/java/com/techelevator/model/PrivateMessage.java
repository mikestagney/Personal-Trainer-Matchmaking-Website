package com.techelevator.model;

import java.time.LocalDate;

/**
 * PrivateMessage
 */
public class PrivateMessage {
	
	/**
	 * long trainerId
	 */
	private long trainerId;
	
	/**
	 * long clientId
	 */
	private long clientId;
	
	/**
	 * LocalDate datePosted
	 */
	private LocalDate datePosted;
	
	/**
	 * String subject
	 */
	private String subject;
	
	/**
	 * String messageBody
	 */
	private String messageBody;

	/**
	 * public long getTrainerId()
	 */
	public long getTrainerId() {
		return trainerId;
	}

	/**
	 * public void setTrainerId(long trainerId)
	 */
	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * public long getClientId()
	 */
	public long getClientId() {
		return clientId;
	}

	/**
	 * public void setClientId(long clientId)
	 */
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	/**
	 * public LocalDate getDatePosted()
	 */
	public LocalDate getDatePosted() {
		return datePosted;
	}

	/**
	 * public void setDatePosted(LocalDate datePosted)
	 */
	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	/**
	 * public String getSubject()
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * public void setSubject(String subject)
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * public String getMessageBody()
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * public void setMessageBody(String messageBody)
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
}
