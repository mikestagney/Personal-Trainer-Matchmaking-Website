package com.techelevator.model.privatemessage;

import java.util.List;

/**
 * PrivateMessageDao Interface has methods to find all of the messages for a User
 * and to all of the Messages between two Users
 */
public interface MessageDao {
	
	/**
	 * @param user_id The ID of the user receiving the message
	 * @return List<PrivateMessage> list of messages
	 */
	public List<Message> getMessagesForUser(long user_id);
	
	/**
	 * @param userId1 the User Id of one the User who's Messages are being displayed
	 * @param userId2 the User Id of one the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved between the two Users
	 */
	public List<Message> getMessagesBetweenUsers(long trainerId, long clientId);
	
	public Message getMessage(long message_id);
	
	public void sendMessage(Message message);
	
	public void deleteMessage(long user_id, long messageId);
}