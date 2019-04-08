package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	@RequestMapping(path="/", method=RequestMethod.GET)
    public void displayHomePage() {
        
    }
	
	
}
