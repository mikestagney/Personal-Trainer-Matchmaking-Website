package com.techelevator.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	@RequestMapping(path="/", method=RequestMethod.GET)
    public void displayHomePage(ModelMap modelMap) {
		populateModelMap(modelMap);
    }
	
	@RequestMapping(path="/trainer", method=RequestMethod.GET)
	public String displayTrainerProfilePage(@RequestParam String trainer_id, ModelMap modelMap) {
		
		return "trainerProfile";
	}
	
	@RequestMapping(path="/client", method=RequestMethod.GET)
	public String displayClientProfilePage(@RequestParam String client_id, ModelMap modelMap) {
		
		return "clientProfile";
	}
	
	private void populateModelMap(ModelMap modelMap) {
		
	}
	
}
