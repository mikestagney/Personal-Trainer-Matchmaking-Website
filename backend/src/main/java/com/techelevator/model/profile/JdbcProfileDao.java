package com.techelevator.model.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	//TODO -- not called at all
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
		jdbcTemplate.update(
			"INSERT INTO user_profile (user_id, first_name, last_name, is_public, role, city, state) VALUES (?,?,?,?,?,?,?);", 
			user.getId(), user.getFirstName(), user.getLastName(), false, user.getRole(), "", "");
		if (user.getRole().equals("Trainer")) {
			UserProfile userProfile = getUserProfileById(user.getId());
			TrainerProfile trainerProfile = userProfile.getTrainerProfile();
			//FIXME --- BM -- What is the intent behind this?
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
	
	@Override
	public void addClientToClientList(long trainer_id, long client_id) {
		jdbcTemplate.update("INSERT INTO client_list (trainer_id, client_id) VALUES (?,?)", trainer_id, client_id);
	}
	
	@Override
	public void removeClientFromClientList(long trainer_id, long client_id) {
		jdbcTemplate.execute("DELETE FROM client_list WHERE trainer_id=? AND client_id=?");
	}
	
	@Override
	public void addPrivateNoteToClientList(long trainer_id, long client_id, String privateNote) {
		String[] privateNotesOld = getPrivateNotesStringArr(trainer_id, client_id);
		String[] privateNotesNew = new String[privateNotesOld.length + 1];
		for (int i = 0; i < privateNotesOld.length; i++) {
			privateNotesNew[i] = privateNotesOld[i];
		}
		privateNotesNew[privateNotesOld.length] = privateNote;
		jdbcTemplate.update("UPDATE client_list SET privateNotes=?  WHERE trainer_id=? AND client_id=?",
				privateNotesNew,trainer_id,client_id);
	}
	
	@Override
	public void removePrivateNoteFromClientList(long trainer_id, long client_id, String privateNote) {
		String[] privateNotesOld = getPrivateNotesStringArr(trainer_id, client_id);
		if (privateNotesOld.length - 1 == 0) {
			jdbcTemplate.update("UPDATE client_list SET privateNotes=?  WHERE trainer_id=? AND client_id=?",
					null,trainer_id,client_id);
		}
		else {
			String[] privateNotesNew = new String[privateNotesOld.length - 1];
			int counter = 0;
			for (int i = 0; i < privateNotesOld.length; i++) {
				if (!privateNotesOld[i].equals(privateNote)) {
					privateNotesNew[counter] = privateNotesOld[i];
					counter++;
				}
			}
			jdbcTemplate.update("UPDATE client_list SET privateNotes=?  WHERE trainer_id=? AND client_id=?",
					privateNotesNew,trainer_id,client_id);
		}
	}
	
	@Override
	public ClientList getEntireClientListForTrainer(long user_id) {
		ClientList clientList = new ClientList();
		clientList.setClientList(getClientListOfTrainer(user_id));
		clientList.setTrainerProfile(getUserProfileById(user_id));
		clientList.setPrivateNotes(getPrivateNotesMap(user_id));
		return clientList;
	}
	
	@Override
	public ClientList searchClientList(long user_id, String firstName, String lastName, String username) {
		ClientList clientList = new ClientList();
		clientList.setClientList(searchClientListOfTrainer(user_id, firstName, lastName, username));
		clientList.setTrainerProfile(getUserProfileById(user_id));
		clientList.setPrivateNotes(searchClientListForPrivateNotes(user_id, firstName, lastName, username));
		return clientList;
	}
	
	@Override
	public Map<User,String[]> searchClientListForPrivateNotes(long user_id, String firstName, String lastName, String username) {
		List<User> clientList = searchClientListOfTrainer(user_id, firstName, lastName, username);
		Map<User,String[]> privateNotes = new HashMap<User,String[]>();
		for (User user: clientList) {
			privateNotes.put(user,getPrivateNotesStringArr(user_id,user.getId()));
		}
        return privateNotes;
	}
	
	private Map<User,String[]> getPrivateNotesMap(long user_id) {
		List<User> listOfAllClients = getClientListOfTrainer(user_id);
		Map<User,String[]> privateNotes = new HashMap<User,String[]>();
		for (User user: listOfAllClients) {
			privateNotes.put(user,getPrivateNotesStringArr(user_id, user.getId()));
		}
        return privateNotes;
	}
	
	private String[] getPrivateNotesStringArr(long user_id, long client_id) {
		String[] privateNotes = null;
		String sqlSelectPrivateNotes = "SELECT privateNotes FROM client_list WHERE trainer_id = ? and client_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectPrivateNotes, user_id, client_id);
		if (results.next()) {
			privateNotes = results.getObject("privateNotes", String[].class);
        }
        return privateNotes;
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