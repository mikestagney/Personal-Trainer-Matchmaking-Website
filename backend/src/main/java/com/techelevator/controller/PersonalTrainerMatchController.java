package com.techelevator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.clientlist.ClientListDao;
import com.techelevator.model.privatemessage.PrivateMessage;
import com.techelevator.model.privatemessage.PrivateMessageDao;
import com.techelevator.model.trainerprofile.TrainerProfile;
import com.techelevator.model.trainerprofile.TrainerProfileDao;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDao;

/**
 * PersonalTrainerMatchController Class uses AuthProvider, UserDao,
 * TrainerProfileDao, PrivateMessageDao, clientListDao, and
 * has methods for mapping the needed data for displaying the pages for the website
 * <p>
 * Methods: public void displayHomePage(), public TrainerProfile displayTrainerProfilePage(), public User displayClientProfilePage(),
 * public List<TrainerProfile> displayAllTrainers(String city,String state,int price_per_hour,double rating,String certifications),
 * public TrainerProfile displayTrainerDetailPage(@RequestParam long trainer_id),
 * public void updateTrainerProfile(@RequestBody TrainerProfile trainerProfile),
 * public List<User> displayClientListSearch(String firstName,String lastName,String username),
 * public List<PrivateMessage> displayMessageListForUser(),
 * and public List<PrivateMessage> displayMessageListBetweenUsers(@RequestParam long user_id)
 */
@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	@Autowired
    private UserDao userDao;
	@Autowired
    private AuthProvider authProvider;
    @Autowired
    private TrainerProfileDao trainerProfileDao;
    @Autowired
    private PrivateMessageDao privateMessageDao;
    @Autowired
    private ClientListDao clientListDao;
	
    /**
	 * Method displayHomePage() does not take any parameters
	 * <p>
	 * Displays User's Profile Page
	 * <p>
	 * If AuthProvider cannot authorize that the User has a valid role then an UnauthorizedException is thrown
	 */
    @GetMapping("/")
    public void displayHomePage() throws UnauthorizedException {
    	if(authProvider.userHasRole(new String[] {"Trainer"})) {
    		displayTrainerProfilePage();
        }
    	else if(authProvider.userHasRole(new String[] {"Client"})) {
    		displayClientProfilePage();
    	}
    }
    
    /**
	 * Method displayTrainerProfilePage() does not take any parameters
	 * <p>
	 * Displays Trainer's Profile Page
	 * <p>
	 * If AuthProvider cannot authorize that the User has the role "Trainer" then an UnauthorizedException is thrown
	 * @return TrainerProfile object for the currently loggin in User with role "Trainer"
	 */
	@GetMapping("/trainer")
	public TrainerProfile displayTrainerProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Trainer"})) {
            throw new UnauthorizedException();
        }
		return trainerProfileDao.getTrainerProfileById(authProvider.getCurrentUser().getId());
	}
	
	/**
	 * Method displayClientProfilePage() does not take any parameters
	 * <p>
	 * Displays Client's Profile Page
	 * <p>
	 * If AuthProvider cannot authorize that the User has the role "Client" then an UnauthorizedException is thrown
	 * @return User object for the currently loggin in User with role "Client"
	 */
	@GetMapping("/client")
	public User displayClientProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getUserById(authProvider.getCurrentUser().getId());
	}
	
	/**
	 * Method displayAllTrainers() takes five parameters
	 * <p>
	 * Displays All Trainers that meet the search criteria
	 * <p>
	 * If AuthProvider cannot authorize that the User has the role "Client" then an UnauthorizedException is thrown
	 * @param city this is the city to search for a trainer in
	 * @param state this is the state to search for a trainer in
	 * @param price_per_hour this is the minimum range price to search for a trainer in
	 * @param rating this is the minimum rating to search for a trainer in
	 * @param certifications these are the certifications a trainer must have
	 * @return List<TrainerProfile> for all Trainer's that fall within the search criteria
	 */
	@GetMapping("/trainerSearch")
	public List<TrainerProfile> displayAllTrainers(@RequestParam(defaultValue="") String city,
													@RequestParam(defaultValue="") String state,
													@RequestParam(defaultValue="0") int min_price_per_hour,
													@RequestParam(defaultValue="200") int max_price_per_hour,
													@RequestParam(defaultValue="0") double rating,
													@RequestParam(defaultValue="") String certifications) {
		return trainerProfileDao.getTrainerProfilesBySearchCriteria(city,state,min_price_per_hour,max_price_per_hour,rating,certifications);
	}
	
	/**
	 * Method displayTrainerDetailPage() takes one parameters
	 * <p>
	 * Displays the Trainer Detail page for a specific TrainerProfile
	 * <p>
	 * @param trainer_id this is the trainer_id for the Trainer Profile to be displayed
	 * @return TrainerProfile for the Trainer Detail Page that is being displayed
	 */
	@GetMapping("/trainerDetailPage")
	public TrainerProfile displayTrainerDetailPage(@RequestParam long trainer_id) {
		return trainerProfileDao.getTrainerProfileById(trainer_id);
	}
	
	/**
	 * Method updateTrainerProfile() takes one parameters
	 * <p>
	 * This methods uses the passed in Trainer Profile object to update the currently
	 * loggin in Trainer User's profile
	 * <p>
	 * If AuthProvider cannot authorize that the User has the role "Trainer" then an UnauthorizedException is thrown
	 * @param TrainerProfile object for the Trainer Profile that is being updated
	 */
	@PutMapping("updateTrainerProfile")
	public void updateTrainerProfile(@RequestBody TrainerProfile trainerProfile) throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		else {
			trainerProfile.setId(authProvider.getCurrentUser().getId());
			trainerProfileDao.updateTrainerProfile(trainerProfile);
		}
	}
	
	/**
	 * Method displayClientListSearch() takes three parameters
	 * <p>
	 * Displays All Trainers that meet the search criteria
	 * <p>
	 * If AuthProvider cannot authorize that the User has the role "Trainer" then an UnauthorizedException is thrown
	 * @param firstName this is the first name to search for client in client list of trainer
	 * @param lastName this is the last name to search for client in client list of trainer
	 * @param username this is the username to search for client in client list of trainer
	 * @return List<User> for all Client's that fall within the search criteria
	 */
	@GetMapping("/clientList")
	public List<User> displayClientListSearch(@RequestParam(defaultValue="") String firstName,
												@RequestParam(defaultValue="") String lastName,
												@RequestParam(defaultValue="") String username)
												throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return clientListDao.searchClientListOfTrainer(authProvider.getCurrentUser().getId(), firstName, lastName, username);
	}
	
	/**
	 * Method displayMessageListForUser() does not take any parameters
	 * <p>
	 * Displays Message List for User
	 * @return List<PrivateMessage> list of all Private Messages for user
	 */
	@GetMapping("/messageList}")
	public List<PrivateMessage> displayMessageListForUser() {
		return privateMessageDao.getPrivateMessagesForUser(authProvider.getCurrentUser().getId());
	}
	
	/**
	 * Method displayMessageListBetweenUsers() takes one parameters
	 * <p>
	 * Displays Message List for only the messages between a user and the logged in User
	 * @param user_id this is the id of the user that the logged in User wants to see messages to and from
	 * @return List<PrivateMessage> list of all Private Messages between user and User
	 */
	@GetMapping("/messageListBetweenUsers")
	public List<PrivateMessage> displayMessageListBetweenUsers(@RequestParam long user_id) {
		return privateMessageDao.getPrivateMessagesBetweenUser(authProvider.getCurrentUser().getId(), user_id);
	}
}