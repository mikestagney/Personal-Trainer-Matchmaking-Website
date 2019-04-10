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
	
    @GetMapping("/")
    public void displayHomePage() {}
	
	@GetMapping("/trainer")
	public TrainerProfile displayTrainerProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return trainerProfileDao.getTrainerProfileById(authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/client")
	public User displayClientProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getUserById(authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/trainerSearch")
	public List<TrainerProfile> displayAllTrainers(@RequestParam(defaultValue="") String city,
													@RequestParam(defaultValue="") String state,
													@RequestParam(defaultValue="0") int price_per_hour,
													@RequestParam(defaultValue="0") double rating,
													@RequestParam(defaultValue="") String certifications) {
		return trainerProfileDao.getTrainerProfilesBySearchCriteria(city,state,price_per_hour,rating,certifications);
	}
	
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
	
	@GetMapping("/clientList")
	public List<User> displayClientList() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return clientListDao.getClientListOfTrainer(authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/clientListSearch")
	public List<User> displayClientListSearch(@RequestParam(defaultValue="") String firstName,
												@RequestParam(defaultValue="") String lastName,
												@RequestParam(defaultValue="") String username)
												throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return clientListDao.searchClientListOfTrainer(authProvider.getCurrentUser().getId(), firstName, lastName, username);
	}
	
	@GetMapping("/messageList}")
	public List<PrivateMessage> displayMessageListForUser() {
		return privateMessageDao.getPrivateMessagesForUser(authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/messageListBetweenUsers")
	public List<PrivateMessage> displayMessageListBetweenUsers(@RequestParam long user_id) {
		return privateMessageDao.getPrivateMessagesBetweenUser(authProvider.getCurrentUser().getId(), user_id);
	}
}