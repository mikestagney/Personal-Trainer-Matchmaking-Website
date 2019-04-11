package com.techelevator.model.profile;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.user.User;

/**
 * JdbcTrainerProfileDao implements the TrainerProfileDao and has methods for
 * getting a Trainer's Trainer Profile, updating a Trainer's Trainer Profile,
 * and for finding all Trainer Profiles that meet the search criteria
 */
@Component
public class JdbcProfileDao implements ProfileDao{
	
	private JdbcTemplate jdbcTemplate;
	
	/**
     * Create a new trainer profile dao with the supplied data source
     *
     * @param dataSource an SQL data source
     */
	@Autowired
    public JdbcProfileDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/**
	 * @param id the User Id for the Trainer
	 * @return TrainerProfile for the Trainer for the given Id
	 */
	@Override
	public UserProfile getUserProfileById(Long id) {
		String sqlSelectUserById = "SELECT * FROM user_profile WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return mapResultToUserProfile(results);
        } else {
            return null;
        }
	}
	
	/**
	 * @param city this is the city to search for a trainer in
	 * @param state this is the state to search for a trainer in
	 * @param price_per_hour this is the minimum range price to search for a trainer in
	 * @param rating this is the minimum rating to search for a trainer in
	 * @param certifications these are the certifications a trainer must have
	 * @return List<TrainerProfile> for all Trainer's that fall within the search criteria
	 */
	@Override
	public List<UserProfile> getTrainerProfilesBySearchCriteria(String city, String state, int min_price_per_hour, int max_price_per_hour, double rating, String certifications) {
		List<UserProfile> trainerProfileList = new ArrayList<UserProfile>();
		String sqlSelectTrainersBySearchCriteria = "SELECT * FROM user_profile WHERE city ILIKE ? "
					+ "AND state ILIKE ? AND price_per_hour >= ? AND price_per_hour <= ? AND rating >= ? "
					+ "AND certifications ILIKE ? AND is_public = true AND role = 'Trainer'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTrainersBySearchCriteria, "%" + city + "%", "%" + state + "%",
        											min_price_per_hour, max_price_per_hour, rating, "%" + certifications + "%");
        while (results.next()) {
        	trainerProfileList.add(mapResultToUserProfile(results));
        }
        return trainerProfileList;
	}
	
	/**
	 * @param UserProfile to update the logged in Trainer's Trainer Profile to
	 */
	@Override
	public void updateUserProfile(UserProfile userProfile) {
		jdbcTemplate.update("UPDATE user_profile SET first_name=?, last_name=?, is_public=?, city=?, state=? WHERE user_id=?",
				userProfile.getFirstName(), userProfile.getLastName(), userProfile.isPublic(),
				userProfile.getCity(), userProfile.getState(), userProfile.getId());
		if (userProfile.getRole().equals("Trainer")) {
			TrainerProfile trainerProfile = userProfile.getTrainerProfile();
			jdbcTemplate.update("UPDATE trainer_profile SET price_per_hour=?, rating=?, philosphy=?, bio=?, certifications=?  WHERE user_id=?",
					trainerProfile.getPrice_per_hour(), trainerProfile.getRating(), trainerProfile.getPhilosophy(),
					trainerProfile.getBio(), trainerProfile.getCertifications(), trainerProfile.getId());
		}
		
	}
	
	/**
	 * 
	 */
	@Override
	public void createUserProfile(UserProfile userProfile, User user) {
		jdbcTemplate.update("INSERT INTO user_profile (user_id, first_name, last_name, is_public, role, city, state)"
				+ " VALUES (?,?,?,?,?,?)", user.getId(), userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.isPublic(), userProfile.getRole(), userProfile.getCity(), userProfile.getState());
		if (userProfile.getRole().equals("Trainer")) {
			TrainerProfile trainerProfile = userProfile.getTrainerProfile();
			jdbcTemplate.update("INSERT INTO trainer_profile (user_id, price_per_hour, rating, philosphy, bio, certifications)"
					+ " VALUES (?,?,?,?,?,?)", user.getId(), trainerProfile.getPrice_per_hour(), trainerProfile.getRating(),
					trainerProfile.getPhilosophy(), trainerProfile.getBio(), trainerProfile.getCertifications());
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void createUserProfile(User user) {
		jdbcTemplate.update("INSERT INTO user_profile (user_id, first_name, last_name, is_public, role, city, state)"
				+ " VALUES (?,?,?,?,?,?)", user.getId(), user.getFirstName(), user.getLastName(),
				false, user.getRole(), "", "");
		if (user.getRole().equals("Trainer")) {
			UserProfile userProfile = getUserProfileById(user.getId());
			TrainerProfile trainerProfile = userProfile.getTrainerProfile();
			try {
				if (trainerProfile.getPrice_per_hour() < 0) {
					trainerProfile.setPrice_per_hour(0);
				}
			}
			catch (Exception e) {
				trainerProfile.setPrice_per_hour(0);
			}
			jdbcTemplate.update("INSERT INTO trainer_profile (user_id, price_per_hour, rating, philosphy, bio, certifications)"
					+ " VALUES (?,?,?,?,?,?)", user.getId(), trainerProfile.getPrice_per_hour(), trainerProfile.getRating(),
					trainerProfile.getPhilosophy(), trainerProfile.getBio(), trainerProfile.getCertifications());
		}
	}
	
	private UserProfile mapResultToUserProfile(SqlRowSet results) {
    	UserProfile userProfile = new UserProfile();
    	userProfile.setId(results.getLong("user_id"));
    	userProfile.setFirstName(results.getString("first_name"));
    	userProfile.setLastName(results.getString("last_name"));
    	userProfile.setPublic(results.getBoolean("is_public"));
    	userProfile.setCity(results.getString("city"));
    	userProfile.setState(results.getString("state"));
    	userProfile.setRole(results.getString("role"));
    	if (userProfile.getRole().contentEquals("Trainer")) {
    		TrainerProfile trainerProfile = new TrainerProfile();
    		trainerProfile.setPrice_per_hour(results.getInt("price_per_hour"));
        	trainerProfile.setRating(results.getDouble("rating"));
        	trainerProfile.setPhilosophy(results.getString("philosphy"));
        	trainerProfile.setBio(results.getString("bio"));
        	trainerProfile.setCertifications(results.getString("certifications"));
        	userProfile.setTrainerProfile(trainerProfile);
    	}
        return userProfile;
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