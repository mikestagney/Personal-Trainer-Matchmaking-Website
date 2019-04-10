package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.authentication.PasswordHasher;

@Component
public class JdbcUserDao implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    /**
     * Create a new user dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for users.
     *
     * @param dataSource an SQL data source
     * @param passwordHasher an object to salt and hash passwords
     */
    @Autowired
    public JdbcUserDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }

    /**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     * @param userName the user name to give the new user
     * @param password the user's password
     * @param role the user's role
     * @return the new user
     */
    @Override
    public User saveUser(String userName, String password, String role) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        long newId = jdbcTemplate.queryForObject("INSERT INTO users(username, password, salt, role) VALUES (?, ?, ?, ?) RETURNING user_id", Long.class, userName,
                hashedPassword, saltString, role);

        User newUser = new User();
        newUser.setId(newId);
        newUser.setUsername(userName);
        newUser.setRole(role);

        return newUser;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(newPassword, salt);
        String saltString = new String(Base64.encode(salt));

        jdbcTemplate.update("UPDATE users SET password=?, salt=? WHERE user_id=?",
                hashedPassword, saltString, user.getId());
    }

    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    @Override
    public User getValidUserWithPassword(String userName, String password) {
        String sqlSearchForUser = "SELECT * FROM users WHERE UPPER(username) = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
        if (results.next()) {
            String storedSalt = results.getString("salt");
            String storedPassword = results.getString("password");
            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
            if(storedPassword.equals(hashedPassword)) {
                return mapResultToUser(results);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private User mapResultToUser(SqlRowSet results) {
    	User user = new User();
        user.setId(results.getLong("user_id"));
        user.setUsername(results.getString("username"));
        user.setRole(results.getString("role"));
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        String sqlSelectUserByUsername = "SELECT user_id, username, role FROM users WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserByUsername, username);

        if(results.next()) {
            return mapResultToUser(results);
        } else {
            return null;
        }
    }

	@Override
	public User getUserById(Long id) {
		String sqlSelectUserById = "SELECT user_id, username, role FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return mapResultToUser(results);
        } else {
            return null;
        }
	}
	
	@Override
	public List<User> getUserInfoForTrainer(String city, String state, int price_per_hour, double rating, String certifications) {
		String sqlSelectTrainers = "SELECT user_id, username FROM users JOIN trainer_profile "
									   + "WHERE role = ? AND city.trainer_profile ILIKE ? AND state.trainer_profile ILIKE ? "
									   + "AND price_per_hour.trainer_profile >= ? AND rating.trainer_profile >= ? "
									   + "AND certifications.trainer_profile ILIKE ? ORDER BY username";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTrainers, "trainer",
        		"%" + city + "%", "%" + state + "%", price_per_hour, rating, "%" + certifications + "%");
        List<User> trainerList = new ArrayList<User>();
        while (results.next()) {
        	User user = mapResultToUser(results);
        	trainerList.add(user);
        }
        return trainerList;
	}
	
	@Override
	public List<User> getClientList(Long id) {
		String sqlSelectUsersByTrainerId = "SELECT client_id FROM client_list WHERE trainer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUsersByTrainerId, id);
        List<User> clientList = new ArrayList<User>();
        while (results.next()) {
        	clientList.add(mapResultToUser(results));
        }
        return clientList;
	}
}
