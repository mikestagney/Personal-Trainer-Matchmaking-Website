package com.techelevator.model.privatemessage;

import java.util.ArrayList;
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
public class JdbcPrivateMessageDao implements PrivateMessageDao{

	private JdbcTemplate jdbcTemplate;
	
	/**
     * Create a new private message dao with the supplied data source
     *
     * @param dataSource an SQL data source
     */
	@Autowired
    public JdbcPrivateMessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/**
	 * @param user_id the User Id of the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved by the User
	 */
	@Override
	public List<PrivateMessage> getPrivateMessagesForUser(long user_id) {
		List<PrivateMessage> messageList = new ArrayList<PrivateMessage>();
		String sqlSearchForUsersMessages = "SELECT * FROM private_message WHERE sender_id = ? OR recipient_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUsersMessages, user_id, user_id);
        while (results.next()) {
            messageList.add(mapResultToPrivateMessage(results));
        }
		return messageList;
	}
	
	/**
	 * @param userId1 the User Id of one the User who's Messages are being displayed
	 * @param userId2 the User Id of one the User who's Messages are being displayed
	 * @return List<PrivateMessage> that have been sent or recieved between the two Users
	 */
	@Override
	public List<PrivateMessage> getPrivateMessagesBetweenUser(long userId1, long userId2) {
		List<PrivateMessage> messageList = new ArrayList<PrivateMessage>();
		String sqlSearchForMessagesBetweenUsers = "SELECT * FROM private_message WHERE (sender_id = ? OR recipient_id = ?) AND (sender_id = ? OR recipient_id = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForMessagesBetweenUsers, userId1, userId1, userId2, userId2);
        while (results.next()) {
            messageList.add(mapResultToPrivateMessage(results));
        }
		return messageList;
	}
	
	private PrivateMessage mapResultToPrivateMessage(SqlRowSet results) {
		PrivateMessage message = new PrivateMessage();
		message.setSenderId(results.getLong("sender_id"));
		message.setRecipientId(results.getLong("recipient_id"));
		message.setDatePosted(results.getDate("date_sent").toLocalDate());
		message.setUnread(results.getBoolean("read"));
		message.setSubject(results.getString("subject"));
		message.setMessage(results.getString("message"));
		return message;
	}
}