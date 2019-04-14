package com.techelevator.model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.authentication.PasswordHasher;
import com.techelevator.model.user.ClientList;

/**
 * 
 */
@Component
public class JdbcUserDao implements UserDao {
	
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
    public void saveUser(String username, String firstName, String lastName, String password, String role) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        long newId = jdbcTemplate.queryForObject(
        		"INSERT INTO users(	username, first_name, last_name, password, salt, role) VALUES (?, ?, ?, ?, ?, ?) "
        		+ "RETURNING user_id", Long.class,
        							username, firstName,  lastName,  hashedPassword, saltString, role);
        
        switch( role ) {
        case "Trainer":
        	jdbcTemplate.update("INSERT INTO trainer(user_id) VALUES (?)", newId);
        	break;
        case "Client":
        	jdbcTemplate.update("INSERT INTO client(user_id) VALUES (?)", newId);
        	break;
    	default:
    		throw new IllegalArgumentException("User has illegal role: " + role + ". Must be: Client, Trainer.");
        }
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
        String sqlSearchForUser = "SELECT * FROM users WHERE username = ?";

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
    	User user = new User();
    	user.setId(results.getLong("user_id"));
    	user.setUsername(results.getString("username"));
    	user.setFirstName(results.getString("first_name"));
    	user.setLastName(results.getString("last_name"));
    	user.setRole(results.getString("role"));
    	user.setCity(results.getString("city"));
    	user.setState(results.getString("state"));
    	return user;
    }

    /**
     * @param username the user name of the user requested
     * @return the User requested
     */
    @Override
    public User getUserByUsername(String username) {
    	String sqlSelectUserByUsername = "SELECT * FROM users WHERE username = ?";
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
    	String sqlSelectUserById = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return createUser(results);
        } else {
            return null;
        }
	}
	
	@Override
	public Trainer getTrainerByID(Long id) {

    	String sql = "SELECT user_id, username, is_public, first_name, last_name, city, state, hourly_rate, rating, philosophy, biography, certifications\n" + 
    			     "FROM users JOIN trainer USING(user_id) WHERE user_id = ?";
    	return new Trainer(jdbcTemplate.queryForRowSet(sql, id));
	}
	
	@Override
	public User getClientById(Long id) {
    	String sqlSelectUserById = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, id);
        if(results.next()) {
            return createUser(results);
        } else {
            return null;
        }
	}
	
	@Override
	public void updateTrainer(Trainer trainer) {
		//TODO update trainer via multi query
		jdbcTemplate.update("UPDATE trainer SET hourly_rate=?, rating=?, philosophy=?, bio_info=?, is_public=?, certifications=?  WHERE user_id=?",
				trainer.getHourlyRate(), trainer.getRating(), trainer.getPhilosophy(), trainer.getBiography(), trainer.isPublic(), trainer.getCertifications(), trainer.getTrainerID());
	}

	@Override
	public void updateUser(User user) {
		jdbcTemplate.update("UPDATE user_profile SET username=?, first_name=?, last_name=?, city=?, state=? WHERE user_id=?",
				user.getUsername(), user.getFirstName(), user.getLastName(), user.getCity(), user.getState(), user.getId());
		
	}

	//TODO BM -- either use join or return entire result set
	@Override
	public List<Trainer> getTrainersSearch(String name, String city, String state, int minHourlyRate, int maxHourlyRate, double rating) {
		
		String sql = "SELECT * FROM users WHERE first_name ILIKE ? AND city ILIKE ? AND state ILIKE ? AND role = 'Trainer'";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + name + "%", "%" + city + "%", "%" + state + "%");

		List<Trainer> results = new ArrayList<Trainer>();
		while (rowSet.next()) {
        	results.add(new Trainer(rowSet));
        }
        return results;
	}

	@Override
	public ClientList searchClientList(long id, String name, String username) {
		ClientList clientList = new ClientList();
		clientList.setClientList(searchClientListOfTrainer(id, name, username));
		//clientList.setTrainer(getTrainerById(id));
		clientList.setPrivateNotes(getPrivateNotes(id, clientList.getClientList()));
		return clientList;
	}
	
	private List<User> searchClientListOfTrainer(long user_id, String name, String username) {
		List<User> clientList = new ArrayList<User>();
		List<User> listOfAllClients = getClientListOfTrainer(user_id);
		for (User user: listOfAllClients) {
			if ((user.getFirstName() + " " + user.getLastName()).contains(name) &&
					user.getUsername().contains(username)) {
				clientList.add(user);
			}
		}
        return clientList;
	}
	
	private List<User> getClientListOfTrainer(long user_id) {
		String sqlSelectUsersByTrainerId = "SELECT client_id FROM client_list WHERE trainer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUsersByTrainerId, user_id);
        List<User> clientList = new ArrayList<User>();
        while (results.next()) {
        	clientList.add(getUserById(results.getLong("client_id")));
        }
        return clientList;
	}
	
	
	private Map<User,String[]> getPrivateNotes(long id, List<User> clientList) {
		Map<User,String[]> privateNotes = new HashMap<User,String[]>();
		for (User user: clientList) {
			privateNotes.put(user,getPrivateNotesStringArr(id,user.getId()));
		}
        return privateNotes;
	}
	
	private String[] getPrivateNotesStringArr(long user_id, long client_id) {
		String[] privateNotes = null;
		String sqlSelectPrivateNotes = "SELECT private_notes FROM client_list WHERE trainer_id = ? and client_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectPrivateNotes, user_id, client_id);
		if (results.next()) {
			privateNotes = results.getObject("private_notes", String[].class);
        }
        return privateNotes;
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
	public void addPrivateNote(long trainer_id, long client_id, String privateNote) {
		String[] privateNotesOld = getPrivateNotesStringArr(trainer_id, client_id);
		String[] privateNotesNew = new String[privateNotesOld.length + 1];
		for (int i = 0; i < privateNotesOld.length; i++) {
			privateNotesNew[i] = privateNotesOld[i];
		}
		privateNotesNew[privateNotesOld.length] = privateNote;
		jdbcTemplate.update("UPDATE client_list SET private_notes=?  WHERE trainer_id=? AND client_id=?",
				privateNotesNew,trainer_id,client_id);
	}
	
	@Override
	public void removePrivateNote(long trainer_id, long client_id, String privateNote) {
		String[] privateNotesOld = getPrivateNotesStringArr(trainer_id, client_id);
		if (privateNotesOld.length - 1 == 0) {
			jdbcTemplate.update("UPDATE client_list SET private_notes=?  WHERE trainer_id=? AND client_id=?",
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
			jdbcTemplate.update("UPDATE client_list SET private_notes=?  WHERE trainer_id=? AND client_id=?",
					privateNotesNew,trainer_id,client_id);
		}
	}

	
}
