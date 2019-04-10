package com.techelevator.model;

import java.util.List;

public interface PrivateMessageDao {
	
	public List<PrivateMessage> getPrivateMessagesForUser(long user_id);
	
	public List<PrivateMessage> getPrivateMessagesBetweenUser(long trainerId, long clientId);

}
