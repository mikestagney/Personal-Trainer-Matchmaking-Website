package com.techelevator.model.profile;

import java.util.List;
import javax.sql.DataSource;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.authentication.PasswordHasher;
import com.techelevator.model.profile.ClientList;
import com.techelevator.model.profile.UserProfile;
import com.techelevator.model.user.Client;
import com.techelevator.model.user.Trainer;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDao;

/**
 * 
 */
@Component
public class JdbcNewUserDao implements UserDao{
	
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
    public JdbcNewUserDao(DataSource dataSource, PasswordHasher passwordHasher) {
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
    public User saveUser(String username, String firstName, String lastName, String password, String role) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        long newId = jdbcTemplate.queryForObject("INSERT INTO user(username, first_name, last_name, password, salt, role) VALUES (?, ?, ?, ?, ?, ?) "
        		+ "RETURNING user_id", Long.class, username,  lastName, hashedPassword, saltString, role);
        return createUser(newId, username, firstName, lastName, password, role);
    }
    

    /**
	 * @param User object of the user to change password for
     * @param newPassword the new password
	 */
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
        String sqlSearchForUser = "SELECT * FROM user WHERE UPPER(username) = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
        if (results.next()) {
            String storedSalt = results.getString("salt");
            String storedPassword = results.getString("password");
            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
            if(storedPassword.equals(hashedPassword)) {
                return createUser(results);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private User createUser(SqlRowSet results) {
    	switch (results.getString("role")) {
    	case "Trainer":
    		return createNewTrainer(results);
    	default:
    		return createNewClient(results);
    	}
    }
    
    private User createUser(Long id, String username, String firstName, String lastName, String password, String role) {
    	switch (role) {
    	case "Trainer":
    		Trainer trainer = new Trainer();
    		trainer.setId(id);
    		trainer.setUsername(username);
    		trainer.setFirstName(firstName);
    		trainer.setLastName(lastName);
    		trainer.setRole(role);
        	return trainer;
    	default:
    		Client client = new Client();
        	client.setId(id);
        	client.setUsername(username);
        	client.setFirstName(firstName);
        	client.setLastName(lastName);
        	client.setRole(role);
        	return client;
    	}
    }
    
    private User createNewTrainer(SqlRowSet results) {
    	Trainer trainer = new Trainer();
    	trainer.setId(results.getLong("user_id"));
    	trainer.setUsername(results.getString("username"));
    	trainer.setFirstName(results.getString("first_mame"));
    	trainer.setLastName(results.getString("last_name"));
    	trainer.setRole(results.getString("role"));
    	trainer.setHourlyRate(results.getInt("hourly_rate"));
    	trainer.setRating(results.getDouble("rating"));
    	trainer.setPhilosophy(results.getString("philosophy"));
    	trainer.setBioInfo(results.getString("bio_info"));
    	trainer.setCertifications(results.getObject("certifications", String[].class));
    	trainer.setCity(results.getString("city"));
    	trainer.setState(results.getString("state"));
    	trainer.setPublic(results.getBoolean("is_public"));
    	return trainer;
    }
    
    private User createNewClient(SqlRowSet results) {
    	Client client = new Client();
    	client.setId(results.getLong("user_id"));
    	client.setUsername(results.getString("username"));
    	client.setFirstName(results.getString("first_mame"));
    	client.setLastName(results.getString("last_name"));
    	client.setRole(results.getString("role"));
    	client.setCity(results.getString("city"));
    	client.setState(results.getString("state"));
    	return client;
    }
    
    private String getRole(String username) {
    	String sqlSelectUsersRole = "SELECT role FROM users WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUsersRole, username);
        if(results.next()) {
            return results.getString("role");
        } else {
            return null;
        }
    }
    private String getRole(long id) {
    	String sqlSelectUsersRole = "SELECT role FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUsersRole, id);
        if(results.next()) {
            return results.getString("role");
        } else {
            return null;
        }
    }

    /**
     * @param username the user name of the user requested
     * @return the User requested
     */
    @Override
    public User getUserByUsername(String username) {
    	String role = getRole(username).toLowerCase();
    	String sqlSelectUserByUsername = "SELECT * FROM " + role + " WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserByUsername, username);
        if(results.next()) {
            return createUser(results);
        } else {
            return null;
        }
    }

    /**
     * @param id the id of the user requested
     * @return the User requested
     */
	@Override
	public User getUserById(Long id) {
		String role = getRole(id).toLowerCase();
    	String sqlSelectUserById = "SELECT * FROM " + role + " WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return createUser(results);
        } else {
            return null;
        }
	}
	
	
	@Override
	public void updateClient(Client client) {
		updateUser(client);
	}
	
	@Override
	public void updateTrainer(Trainer trainer) {
		updateUser(trainer);
		jdbcTemplate.update("UPDATE trainer SET hourly_rate=?, rating=?, philosophy=?, bio_info=?, is_public=?, certifications=?  WHERE user_id=?",
				trainer.getHourlyRate(), trainer.getRating(), trainer.getPhilosophy(), trainer.getBioInfo(), trainer.isPublic(), trainer.getCertifications(), trainer.getId());
	}

	
	private void updateUser(User user) {
		jdbcTemplate.update("UPDATE user_profile SET username=?, first_name=?, last_name=?, city=?, state=? WHERE user_id=?",
				user.getUsername(), user.getFirstName(), user.getLastName(), user.getCity(), user.getState(), user.getId());
		
	}

	@Override
	public List<Trainer> getTrainersSearch(String name, String city, String state, int minHourlyRate, int maxHourlyRate, double rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> searchClientListOfTrainer(long user_id, String firstName, String lastName, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientList getClientListForTrainer(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientList searchClientList(long user_id, String firstName, String lastName, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addClientToClientList(long trainer_id, long client_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeClientFromClientList(long trainer_id, long client_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPrivateNoteToClientList(long trainer_id, long client_id, String privateNote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePrivateNoteFromClientList(long trainer_id, long client_id, String privateNote) {
		// TODO Auto-generated method stub
		
	}
	
	private NewUser mapResultToForClient(SqlRowSet results) {
		switch (results.getString("role")) {
			case ("Trainer"):
				NewUser user = new Trainer();
			default:
				NewUser user = new Client();
		}
        user.setId(results.getLong("user_id"));
        user.setUsername(results.getString("username"));
        return user;
    }
	
	private User mapResultToForTrainer(SqlRowSet results) {
		User user = new User();
        user.setId(results.getLong("user_id"));
        user.setUsername(results.getString("username"));
        user.setRole(results.getString("role"));
        return user;
    }


	@Override
	public void createTrainerProfile(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createClientProfile(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserProfile(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Trainer> getTrainersSearch(String city, String state, int min_price_per_hour, int max_price_per_hour,
			double rating, String certifications) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientList searchClientList(String name, String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
