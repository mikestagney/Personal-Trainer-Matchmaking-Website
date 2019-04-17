package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController Class uses AuthProvider and has a method, public String authorizedOnly(),
 * for checking if the currently logged in User has either role of "Trainer" or "Client"
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    @Autowired
    private AuthProvider authProvider;

    /**
	 * 
	 * Method authorizedOnly() takes no parameters
	 * <p>
	 * This methods returns a String of "Success" if user has role of "Trainer" or "Client"
	 * <p>
	 * If no role or any other role then an UnauthorizedException is thrown
	 * @return "Success" if currently logged in User has either role of "Trainer" or "Client"
	 */
    @GetMapping("/")
    public String authorizedOnly() throws UnauthorizedException {
        /*
        You can lock down which roles are allowed by checking
        if the current user has a role.

        In this example, if the user does not have the admin role
        we send back an unauthorized error.
        */
        if( ! authProvider.userHasRole(new String[] {"Trainer", "Client"})) {
            throw new UnauthorizedException();
        }
        return "Success";
    }
}