package com.techelevator.model.dao;

import java.util.List;
import com.techelevator.model.PrivateMessage;

/**
 * PrivateMessageDao
 */
public interface PrivateMessageDao {
	
	/**
	 * public List<PrivateMessage> getPrivateMessagesForUser(long user_id)
	 */
	public List<PrivateMessage> getPrivateMessagesForUser(long user_id);
	
	/**
	 * public List<PrivateMessage> getPrivateMessagesBetweenUser(long trainerId, long clientId)
	 */
	public List<PrivateMessage> getPrivateMessagesBetweenUser(long trainerId, long clientId);
}
