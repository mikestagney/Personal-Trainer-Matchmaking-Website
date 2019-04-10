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
import com.techelevator.model.PrivateMessage;
import com.techelevator.model.TrainerProfile;
import com.techelevator.model.User;
import com.techelevator.model.dao.ClientListDao;
import com.techelevator.model.dao.PrivateMessageDao;
import com.techelevator.model.dao.TrainerProfileDao;
import com.techelevator.model.dao.UserDao;

/**
 * PersonalTrainerMatchController
 */
@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	/**
	 * Autowired userDao
	 */
	@Autowired
    private UserDao userDao;
	
	/**
	 * Autowired authProvider
	 */
    @Autowired
    private AuthProvider authProvider;
    
    /**
	 * Autowired trainerProfileDao
	 */
    @Autowired
    private TrainerProfileDao trainerProfileDao;
    
    /**
	 * Autowired privateMessageDao
	 */
    @Autowired
    private PrivateMessageDao privateMessageDao;
    
    /**
	 * Autowired clientListDao
	 */
    @Autowired
    private ClientListDao clientListDao;
	
    /**
	 * GetMapping for displayHomePage
	 */
    @GetMapping("/")
    public void displayHomePage() throws UnauthorizedException {
    	if(authProvider.userHasRole(new String[] {"trainer"})) {
    		displayTrainerProfilePage();
        }
    	else if(authProvider.userHasRole(new String[] {"client"})) {
    		displayClientProfilePage();
    	}
    }
    
    /**
	 * GetMapping for displayTrainerProfilePage
	 */
	@GetMapping("/trainer")
	public TrainerProfile displayTrainerProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return trainerProfileDao.getTrainerProfileById(authProvider.getCurrentUser().getId());
	}
	
	/**
	 * GetMapping for displayClientProfilePage
	 */
	@GetMapping("/client")
	public User displayClientProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getUserById(authProvider.getCurrentUser().getId());
	}
	
	/**
	 * GetMapping for displayAllTrainers
	 */
	@GetMapping("/trainerSearch")
	public List<TrainerProfile> displayAllTrainers(@RequestParam(defaultValue="") String city,
													@RequestParam(defaultValue="") String state,
													@RequestParam(defaultValue="0") int price_per_hour,
													@RequestParam(defaultValue="0") double rating,
													@RequestParam(defaultValue="") String certifications) {
		return trainerProfileDao.getTrainerProfilesBySearchCriteria(city,state,price_per_hour,rating,certifications);
	}
	
	/**
	 * PutMapping for updateTrainerProfile
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
	 * GetMapping for displayClientListSearch
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
	 * GetMapping for displayMessageListForUser
	 */
	@GetMapping("/messageList}")
	public List<PrivateMessage> displayMessageListForUser() {
		return privateMessageDao.getPrivateMessagesForUser(authProvider.getCurrentUser().getId());
	}
	
	/**
	 * GetMapping for displayMessageListBetweenUsers
	 */
	@GetMapping("/messageListBetweenUsers")
	public List<PrivateMessage> displayMessageListBetweenUsers(@RequestParam long user_id) {
		return privateMessageDao.getPrivateMessagesBetweenUser(authProvider.getCurrentUser().getId(), user_id);
	}
}