package com.techelevator.controller;

import javax.validation.Valid;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.RegistrationResult;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AccountController Class uses AuthProvider, JwtTokenHandler, UserDao,
 * and has methods for logging in a User, registering a User, and updating a User's password
 * <p>
 * Methods: public String login(@RequestBody User user, RedirectAttributes flash),
 * public RegistrationResult register(@Valid @RequestBody User user, BindingResult result),
 * and public void updatePassword(@PathVariable long user_id, @RequestBody String newPassword)
 */
@RestController
@CrossOrigin
public class AccountController {
	
    @Autowired
    private AuthProvider auth;
    @Autowired
    private JwtTokenHandler tokenHandler;
    @Autowired
    private UserDao userDao;

  
    /**
	 * Generates login credentials and returns a user object.
	 *
	 * If AuthProvider cannot authorize the login then an UnauthorizedException is thrown
	 * @param user User object for person trying to login
	 * @param flash RedirectAttributes to hold temporary data on screen if login is not successful
	 * 
	 * @return CreatedToken if login is successful
	 */
    @PostMapping("/login")
    public User login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
        if(auth.signIn(user.getUsername(), user.getPassword())) {
        	User currentUser = auth.getCurrentUser();
        	currentUser.token = tokenHandler.createToken(user.getUsername(), currentUser.getRole()); 
            return currentUser;
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
	 * Creates a new user in the database and returns JSON indication success status.
	 * 
	 * If AuthProvider cannot authorize the registration then an UnauthorizedException is thrown
	 * @param user User object for person trying to login
	 * @param result BindingResult to check for errors when recieving the entered information
	 * @return RegistrationResult if registration is successful
	 */
    @PostMapping("/register")
    public RegistrationResult register(@Valid @RequestBody User user, BindingResult result) {
    	RegistrationResult registrationResult = new RegistrationResult();
    	if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                registrationResult.addError(error.getDefaultMessage());
            }
        }
    	else {
    		auth.register(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole());
    		registrationResult.setSuccess(true);
    	}
    	return registrationResult;
    }
    
    /**
	 * Method updatePassword() takes two parameters
	 * <p>
	 * This methods returns a updates the User's password
	 * using the new password String entered by the user
	 * @param user_id the user_id of the User changing their password
	 * @param newPassword String of the new password
	 */
    @PutMapping ("/updatePassword/{newPassword}")
	public void updatePassword(@PathVariable String newPassword) {
    	userDao.changePassword(userDao.getUserById(auth.getCurrentUser().getId()), newPassword);
	}
}