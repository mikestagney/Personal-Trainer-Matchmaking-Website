package com.techelevator.model.privatemessage;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PrivateMessage Class is an object that holds a Trainer Id,
 * Client Id, datePosted, subject, and Message Body of a Private Method
 */
public class Message {
	
	@Min(1)
	@NotBlank(message="Trainer Id is required")
	private long senderId;
	@Min(1)
	@NotBlank(message="Client Id is required")
	private long recipientId;
	@NotBlank(message="Date Posted is required")
	private LocalDate datePosted;
	@Size(min = 2, max = 20)
	@NotBlank(message="Message subject line is required")
	private String subject;
	@Size(min = 2, max = 250)
	@NotBlank(message="Message body text is required")
	private String message;
	private boolean unread;

	/**
	 * @return senderId the Id associated with the Trainer for this message
	 */
	public long getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId to set for this message
	 */
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return recipientId the Id associated with the Client for this message
	 */
	public long getRecipientId() {
		return recipientId;
	}

	/**
	 * @param recipientId to set for this message
	 */
	public void setRecipientId(long recipientId) {
		this.recipientId = recipientId;
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
	 * @return if message war red
	 */
	public boolean isUnread() {
		return unread;
	}

	/**
	 * @param set if message was read
	 */
	public void setUnread(boolean unread) {
		this.unread = unread;
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
	public String getMessage() {
		return message;
	}

	/**
	 * @param messageBody text to set for this message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}