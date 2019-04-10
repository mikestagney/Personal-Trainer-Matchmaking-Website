package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.PrivateMessage;
import com.techelevator.model.dao.PrivateMessageDao;

@Component
public class JdbcPrivateMessageDao implements PrivateMessageDao{

private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcPrivateMessageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public List<PrivateMessage> getPrivateMessagesForUser(long user_id) {
		List<PrivateMessage> messageList = new ArrayList<PrivateMessage>();
		String sqlSearchForUsersMessages = "SELECT * FROM private_message WHERE trainerId = ? OR clientId = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUsersMessages, user_id, user_id);
        while (results.next()) {
            messageList.add(mapResultToPrivateMessage(results));
        }
		return messageList;
	}
	
	@Override
	public List<PrivateMessage> getPrivateMessagesBetweenUser(long userId1, long userId2) {
		List<PrivateMessage> messageList = new ArrayList<PrivateMessage>();
		String sqlSearchForMessagesBetweenUsers = "SELECT * FROM private_message WHERE (trainerId = ? OR clientId = ?) AND (trainerId = ? OR clientId = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForMessagesBetweenUsers, userId1, userId1, userId2, userId2);
        while (results.next()) {
            messageList.add(mapResultToPrivateMessage(results));
        }
		return messageList;
	}
	
	private PrivateMessage mapResultToPrivateMessage(SqlRowSet results) {
		PrivateMessage message = new PrivateMessage();
		message.setTrainerId(results.getLong("clientId"));
		message.setClientId(results.getLong("clientId"));
		message.setDatePosted(results.getDate("datePosted").toLocalDate());
		message.setSubject(results.getString("subject"));
		message.setMessageBody(results.getString("messageBody"));
		return message;
	}
}