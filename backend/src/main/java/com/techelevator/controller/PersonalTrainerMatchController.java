package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.UserDao;


@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	@Autowired
    private UserDao clientDao;
	
	@Autowired
	private UserDao trainerDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
    public String displayHomePage(ModelMap modelMap) {
		return "homepage";
    }
	
	@RequestMapping(path="/trainer", method=RequestMethod.GET)
	public String displayTrainerProfilePage(@RequestParam String trainer_id, ModelMap modelMap) {
		
		return "trainerProfile";
	}
	
	@RequestMapping(path="/client", method=RequestMethod.GET)
	public String displayClientProfilePage(@RequestParam String client_id, ModelMap modelMap) {
		
		return "clientProfile";
	}
	
}
