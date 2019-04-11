package com.techelevator.model.trainerprofile;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

/**
 * JdbcTrainerProfileDao implements the TrainerProfileDao and has methods for
 * getting a Trainer's Trainer Profile, updating a Trainer's Trainer Profile,
 * and for finding all Trainer Profiles that meet the search criteria
 */
@Component
public class JdbcTrainerProfileDao implements TrainerProfileDao{
	
	private JdbcTemplate jdbcTemplate;
	
	/**
     * Create a new trainer profile dao with the supplied data source
     *
     * @param dataSource an SQL data source
     */
	@Autowired
    public JdbcTrainerProfileDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	/**
	 * @param id the User Id for the Trainer
	 * @return TrainerProfile for the Trainer for the given Id
	 */
	@Override
	public TrainerProfile getTrainerProfileById(Long id) {
		String sqlSelectUserById = "SELECT * FROM trainer_profile WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return mapResultToTrainerProfile(results, results.getString("first_name"), results.getString("last_name"));
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
	public List<TrainerProfile> getTrainerProfilesBySearchCriteria(String city, String state, int min_price_per_hour, int max_price_per_hour, double rating, String certifications) {
		List<TrainerProfile> trainerProfileList = new ArrayList<TrainerProfile>();
		String sqlSelectTrainersBySearchCriteria = "SELECT * FROM trainer_profile WHERE city ILIKE ?"
					+ "AND state ILIKE ? AND price_per_hour >= ? AND price_per_hour <= ? AND rating >= ? AND certifications ILIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTrainersBySearchCriteria, "%" + city + "%",
        									"%" + state + "%", min_price_per_hour, max_price_per_hour, rating, "%" + certifications + "%");
        while (results.next()) {
        	trainerProfileList.add(getTrainerProfileById(results.getLong("user_id")));
        }
        return trainerProfileList;
	}
	
	/**
	 * @param TrainerProfile to update the logged in Trainer's Trainer Profile to
	 */
	@Override
	public void updateTrainerProfile(TrainerProfile trainerProfile) {
		jdbcTemplate.update("UPDATE trainer_profile SET is_public=?, price_per_hour=?, philosphy=?, bio=?, "
				+ "city=?, state=?, certifications=? WHERE user_id=?", trainerProfile.isPublic(),
				trainerProfile.getPrice_per_hour(), trainerProfile.getPhilosophy(), trainerProfile.getBio(),
				trainerProfile.getCity(),trainerProfile.getState(), trainerProfile.getCertifications(), trainerProfile.getId());		
	}
	
	private TrainerProfile mapResultToTrainerProfile(SqlRowSet results, String firstName, String lastName) {
    	TrainerProfile trainerProfile = new TrainerProfile();
    	trainerProfile.setFirstName(firstName);
    	trainerProfile.setLastName(lastName);
    	trainerProfile.setId(results.getLong("user_id"));
    	trainerProfile.setPublic(results.getBoolean("isPublic"));
    	trainerProfile.setPrice_per_hour(results.getInt("price_per_hour"));
    	trainerProfile.setRating(results.getDouble("rating"));
    	trainerProfile.setPhilosophy(results.getString("philosophy"));
    	trainerProfile.setBio(results.getString("bio"));
    	trainerProfile.setCity(results.getString("city"));
    	trainerProfile.setState(results.getString("state"));
    	trainerProfile.setCertifications(results.getString("certifications"));
        return trainerProfile;
    }
}