package com.techelevator.model;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTrainerProfileDao implements TrainerProfileDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcTrainerProfileDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public TrainerProfile getTrainerProfile(Long id) {
		String sqlSelectUserById = "SELECT * FROM trainer_profile WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return mapResultToTrainerProfile(results);
        } else {
            return null;
        }
	}
	
	@Override
	public void update(TrainerProfile trainerProfile) {
		jdbcTemplate.update("UPDATE trainer_profile SET is_public=?, price_per_hour=?, philosphy=?, bio=?, "
				+ "city=?, state=?, certifications=? WHERE user_id=?", trainerProfile.isPublic(),
				trainerProfile.getPrice_per_hour(), trainerProfile.getPhilosophy(), trainerProfile.getBio(),
				trainerProfile.getCity(),trainerProfile.getState(), trainerProfile.getCertifications(), trainerProfile.getId());		
	}
	
	private TrainerProfile mapResultToTrainerProfile(SqlRowSet results) {
    	TrainerProfile trainerProfile = new TrainerProfile();
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
