package com.techelevator.model.privatemessage;

import java.time.LocalDate;

/**
 * PrivateMessage Class is an object that holds a Trainer Id,
 * Client Id, datePosted, subject, and Message Body of a Private Method
 */
public class PrivateMessage {
	
	private long trainerId;
	private long clientId;
	private LocalDate datePosted;
	private String subject;
	private String messageBody;

	/**
	 * @return trainerId the Id associated with the Trainer for this message
	 */
	public long getTrainerId() {
		return trainerId;
	}

	/**
	 * @param trainerId to set for this message
	 */
	public void setTrainerId(long trainerId) {
		this.trainerId = trainerId;
	}

	/**
	 * @return clientId the Id associated with the Client for this message
	 */
	public long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId to set for this message
	 */
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return LocalDate the date associated when the Message was Sent
	 */
	public LocalDate getDatePosted() {
		return datePosted;
	}

	/**
	 * @param LocalDate to set for this message's datePosted
	 */
	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	/**
	 * @return subject the String associated with the subject line for this message
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject line text to set for this message
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return messageBody the String associated with the content of the messages body
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * @param messageBody text to set for this message
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
}