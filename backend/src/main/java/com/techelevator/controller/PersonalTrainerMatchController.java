package com.techelevator.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.RegistrationResult;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.privatemessage.Message;
import com.techelevator.model.privatemessage.MessageDao;
import com.techelevator.model.user.UserDao;
import com.techelevator.model.user.Client;
import com.techelevator.model.user.ClientList;
import com.techelevator.model.user.Trainer;
import com.techelevator.model.user.User;

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
    private AuthProvider authProvider;
    @Autowired
    private MessageDao privateMessageDao;
    @Autowired
    private UserDao userDao;
	
    
    @GetMapping("/")
    public void displayHomePage() {
    	
    }
    
    @GetMapping("/search")
	public List<Trainer> trainersSearch(@RequestParam(defaultValue="") String name,
											@RequestParam(defaultValue="") String city,
											@RequestParam(defaultValue="") String state,
											@RequestParam(defaultValue="0") int minHourlyRate,
											@RequestParam(defaultValue="999") int maxHourlyRate,
											@RequestParam(defaultValue="0") double rating) {
		return userDao.getTrainersSearch(name,city,state,minHourlyRate,maxHourlyRate,rating);
	}
    
    
    @GetMapping("/trainer/profile/{trainerId}")
	public Trainer trainerProfilePage(@PathVariable long trainerId) throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Trainer"})) {
            throw new UnauthorizedException();
        }
		return userDao.getTrainerById(trainerId);
	}
    
    @GetMapping("/client/profile/{clientId}")
	public Client clientProfilePage(@PathVariable long clientId) throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Trainer"})) {
            throw new UnauthorizedException();
        }
		return userDao.getClientById(clientId);
	}
    
    @GetMapping("/profile")
	public User profilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Trainer", "Client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getClientById(authProvider.getCurrentUser().getId());
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
	public ClientList displayClientListSearch(@RequestParam(defaultValue="") String name,
												@RequestParam(defaultValue="") String username)
												throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return userDao.searchClientList(authProvider.getCurrentUser().getId(), name, username);
	}
	
	@GetMapping("/inbox")
	public List<Message> displayMessages() throws UnauthorizedException {
		return privateMessageDao.getMessages(authProvider.getCurrentUser().getId());
	}
	
	@PostMapping("/send")
	public void sendMessage(@Valid @RequestBody Message message, BindingResult result) throws UnauthorizedException {
		RegistrationResult registrationResult = new RegistrationResult();
		if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                registrationResult.addError(error.getDefaultMessage());
            }
        }
    	else {
    		privateMessageDao.sendMessage(message);
    		
    	}
	}
    
    @GetMapping("inbox/{messageId}")
	public Message displayMessage(@PathVariable long messageId) throws UnauthorizedException {
		return privateMessageDao.getMessage(messageId);
	}
    
    
    /**
	 * Method displayMessageListBetweenUsers() takes one parameters
	 * <p>
	 * Displays Message List for only the messages between a user and the logged in User
	 * @param user_id this is the id of the user that the logged in User wants to see messages to and from
	 * @return List<PrivateMessage> list of all Private Messages between user and User
	 */
	@GetMapping("/inbox/{userId}")
	public List<Message> displayMessagesBetweenUsers(@PathVariable long userId) {
		return privateMessageDao.getMessagesBetweenUsers(authProvider.getCurrentUser().getId(), userId);
	}
}