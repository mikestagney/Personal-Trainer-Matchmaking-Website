package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.TrainerProfile;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	@Autowired
    private UserDao userDao;
    @Autowired
    private AuthProvider authProvider;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
    public String displayHomePage(ModelMap modelMap) {
		return "homepage";
    }
	
	@RequestMapping(path="/trainer", method=RequestMethod.GET)
	public List<Object> displayTrainerProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		List<Object> trainerInfo = new ArrayList<Object>();
		trainerInfo.add(userDao.getUserById(authProvider.getCurrentUser().getId()));
		trainerInfo.add(userDao.getTrainerProfile(authProvider.getCurrentUser().getId()));
		return trainerInfo;
	}
	
	@RequestMapping(path="/client", method=RequestMethod.GET)
	public User displayClientProfilePage() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getUserById(authProvider.getCurrentUser().getId());
	}
	
	@RequestMapping(path="/trainerSearch", method=RequestMethod.GET)
	public Map<User,TrainerProfile> displayAllTrainers(@RequestParam(defaultValue="") String city,
										 @RequestParam(defaultValue="") String state) {
		return userDao.getMapOfTrainers(city, state);
	}
	
	@RequestMapping(path="/clientList", method=RequestMethod.GET)
	public List<User> displayClientList() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"trainer"})) {
            throw new UnauthorizedException();
        }
		return userDao.getClientList(authProvider.getCurrentUser().getId());
	}
	
}
