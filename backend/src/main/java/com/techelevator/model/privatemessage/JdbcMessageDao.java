package com.techelevator.model.privatemessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 * JdbcPrivateMessageDao Class implements the PrivateMessageDao and searches the database
 * to find all of the messages for a User and all of the Messages between two Users
 */
@Component
public class JdbcMessageDao implements MessageDao{

	private JdbcTemplate jdbcTemplate;
	
	/**
     * Create a new private message dao with the supplied data source
     *
     * @param dataSource an SQL data source
     */
	@Autowired
    public JdbcMessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/**
	 * @param user_id the User Id of the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved by the User
	 */
	@Override
	public List<Message> getMessagesForUser(long user_id) {
		List<Message> messageList = new ArrayList<Message>();
		String sqlSearchForUsersMessages = "SELECT * FROM message RIGHT JOIN app_user ON message.sender_id = app_user.user_id WHERE recipient_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUsersMessages, user_id);
        while (results.next()) {
            messageList.add(mapResultToMessage(results));
        }
		return messageList;
	}
	
	/**
	 * @param userId1 the User Id of one the User who's Messages are being displayed
	 * @param userId2 the User Id of one the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved between the two Users
	 */
	@Override
	public List<Message> getMessagesBetweenUsers(long userId1, long userId2) {
		List<Message> messageList = new ArrayList<Message>();
		String sqlSearchForMessagesBetweenUsers = "SELECT * FROM private_message WHERE (sender_id = ? OR recipient_id = ?) AND (sender_id = ? OR recipient_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForMessagesBetweenUsers, userId1, userId1, userId2, userId2);
        while (results.next()) {
            messageList.add(mapResultToMessage(results));
        }
		return messageList;
	}
	
	private Message mapResultToMessage(SqlRowSet results) {
		Message message = new Message();
		message.setMessageId(results.getLong("message_id"));
		message.setSenderId(results.getLong("sender_id"));
		message.setRecipientId(results.getLong("recipient_id"));
		message.setPostDate(results.getDate("post_date"));
		message.setUnread(results.getBoolean("unread"));
		message.setSubject(results.getString("subject"));
		message.setMessage(results.getString("message"));
		
		String firstName = results.getString("first_name");
		String lastName = results.getString("last_name");
		message.setSenderName(firstName + " " + lastName);
		//message.setRecipientDelete(results.getBoolean("recipient_delete"));
		return message;
	}

	@Override
	public Message getMessage(long messageId) {
		String sqlSearchForMessagesBetweenUsers = "SELECT * FROM message RIGHT JOIN app_user ON message.sender_id = app_user.user_id WHERE message_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForMessagesBetweenUsers, messageId);
        if (results.next()) {
        	Message message = mapResultToMessage(results);
        	message.setUnread(false);
        	//String changeToRead = "INSERT INTO message (unread) VALUES (false) WHERE message_id = ?";
			//jdbcTemplate.update(changeToRead, messageId);
        	return message;
        }
		return null;
	}

	@Override
	public void sendMessage(Message message) {
        jdbcTemplate.update("INSERT INTO message( sender_id, recipient_id, post_date, subject, message) VALUES (?,?,?,?,?)",
        		message.getSenderId(), message.getRecipientId(), new Date(), message.getSubject(), message.getMessage());
	}
	
	@Override
	public void deleteMessage(long messageId) {  // long user_id, parameter removed
		Message message = getMessage(messageId);
		//if (messageId == message.getSenderId()) {
			//jdbcTemplate.update("INSERT INTO privateMessage( sender_delete ) VALUES (true) WHERE message_id = ?", messageId);
			message.setSenderDelete(true);
		//}
		//else if (messageId == message.getRecipientId()) {
			//jdbcTemplate.update("INSERT INTO privateMessage( recipient_delete ) VALUES (true) WHERE message_id = ?", messageId);
			//message.setRecipientDelete(true);
		//}
		//if (message.isSenderDelete() && message.isRecipientDelete()) {
			String sqlMessageDelete = "DELETE FROM message WHERE message_id = " + messageId;
			jdbcTemplate.execute(sqlMessageDelete);
			//jdbcTemplate.execute("DELETE FROM message WHERE AND message_id = ?"); //original code

		//}
	}
}