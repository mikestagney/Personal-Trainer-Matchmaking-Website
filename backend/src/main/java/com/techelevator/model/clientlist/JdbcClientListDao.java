package com.techelevator.model.clientlist;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.user.User;

/**
 * JdbcClientListDao Class implements the ClientListDao and searches the database
 * to find the List<User> that are in that Trainer's Client List and match the search criteria
 */
@Component
public class JdbcClientListDao implements ClientListDao{

	private JdbcTemplate jdbcTemplate;
	
	/**
     * Create a new client list dao with the supplied data source
     *
     * @param dataSource an SQL data source
     */
	@Autowired
    public JdbcClientListDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/**
	 * @param user_id the User Id of the Trainer who's Client List is being searched
	 * @param firstName the First Name to search the client list for
	 * @param lastName the Last Name to search the client list for
	 * @param userName the User Name to search the client list for
	 * @return List<User> that are in that Trainer's Client List and match the search criteria
	 */
	@Override
	public List<User> searchClientListOfTrainer(long user_id, String firstName, String lastName, String username) {
		List<User> clientList = new ArrayList<User>();
		List<User> listOfAllClients = getClientListOfTrainer(user_id);
		for (User user: listOfAllClients) {
			if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase()) &&
					user.getLastName().toLowerCase().contains(lastName.toLowerCase()) &&
					user.getUsername().toLowerCase().contains(username.toLowerCase())) {
				clientList.add(user);
			}
		}
        return clientList;
	}
	
	private User mapResultToUser(SqlRowSet results) {
    	User user = new User();
        user.setId(results.getLong("user_id"));
        user.setUsername(results.getString("username"));
        user.setRole(results.getString("role"));
        return user;
    }
	
	private List<User> getClientListOfTrainer(long user_id) {
		String sqlSelectUsersByTrainerId = "SELECT client_id FROM client_list WHERE trainer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUsersByTrainerId, user_id);
        List<User> clientList = new ArrayList<User>();
        while (results.next()) {
        	clientList.add(mapResultToUser(results));
        }
        return clientList;
	}
}