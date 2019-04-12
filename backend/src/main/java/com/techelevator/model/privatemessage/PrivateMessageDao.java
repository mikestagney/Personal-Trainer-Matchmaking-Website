package com.techelevator.model.privatemessage;

import java.util.List;

/**
 * PrivateMessageDao Interface has methods to find all of the messages for a User
 * and to all of the Messages between two Users
 */
public interface PrivateMessageDao {
	
	/**
	 * @param user_id the User Id of the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved by the User
	 */
	public List<PrivateMessage> getPrivateMessagesForUser(long user_id);
	
	/**
	 * @param userId1 the User Id of one the User who's Messages are being displayed
	 * @param userId2 the User Id of one the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved between the two Users
	 */
	public List<PrivateMessage> getPrivateMessagesBetweenUser(long trainerId, long clientId);
	
	public PrivateMessage getPrivateMessage(long message_id);
	
	public void sendPrivateMessage(PrivateMessage message);
	
	public void deletePrivateMessage(PrivateMessage message);
}