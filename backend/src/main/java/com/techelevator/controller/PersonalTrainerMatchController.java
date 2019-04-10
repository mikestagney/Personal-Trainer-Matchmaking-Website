package com.techelevator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.PrivateMessage;
import com.techelevator.model.PrivateMessageDao;
import com.techelevator.model.TrainerProfile;
import com.techelevator.model.TrainerProfileDao;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

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
	
	@RequestMapping(path="/", method=RequestMethod.GET)
    public String displayHomePage() {
		return "homepage";
    }
	
	@RequestMapping(path="/trainer", method=RequestMethod.GET)
	public TrainerProfile displayTrainerProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return trainerProfileDao.getTrainerProfileById(authProvider.getCurrentUser().getId());
	}
	
	@RequestMapping(path="/client", method=RequestMethod.GET)
	public User displayClientProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getUserById(authProvider.getCurrentUser().getId());
	}
	
	@RequestMapping(path="/trainerSearch", method=RequestMethod.GET)
	public List<TrainerProfile> displayAllTrainers(@RequestParam(defaultValue="") String city,
													@RequestParam(defaultValue="") String state,
													@RequestParam(defaultValue="0") int price_per_hour,
													@RequestParam(defaultValue="0") double rating,
													@RequestParam(defaultValue="") String certifications) {
		return trainerProfileDao.getTrainerProfilesBySearchCriteria(city,state,price_per_hour,rating,certifications);
	}
	
	@PutMapping ("updateTrainerProfile/{user_id}")
	public void updateTrainerProfile(@PathVariable long user_id, @RequestBody TrainerProfile trainerProfile) {
		TrainerProfile requestedTrainerProfile = trainerProfileDao.getTrainerProfileById(user_id);
		if (requestedTrainerProfile != null) {
			trainerProfile.setId(user_id);
			trainerProfileDao.updateTrainerProfile(trainerProfile);
		}
	}
	
	@RequestMapping(path="/clientList", method=RequestMethod.GET)
	public List<User> displayClientList() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return userDao.getClientList(authProvider.getCurrentUser().getId());
	}
	
	@RequestMapping(path="/messageList/{user_id}", method=RequestMethod.GET)
	public List<PrivateMessage> displayMessageListForUser(@PathVariable long user_id) {
		return privateMessageDao.getPrivateMessagesForUser(user_id);
	}
	
	@RequestMapping(path="/messageListBetweenUsers/{trainerId}/{clientId}", method=RequestMethod.GET)
	public List<PrivateMessage> displayMessageListBetweenUsers(@PathVariable long trainerId, @PathVariable long clientId) {
		return privateMessageDao.getPrivateMessagesBetweenUser(trainerId, clientId);
	}
	
}
