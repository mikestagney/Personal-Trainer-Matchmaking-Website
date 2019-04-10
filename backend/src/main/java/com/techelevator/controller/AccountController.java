package com.techelevator.controller;

import javax.validation.Valid;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.RegistrationResult;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.User;
import com.techelevator.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AccountController
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

    @PostMapping("/login")
    public String login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
        if(auth.signIn(user.getUsername(), user.getPassword())) {
        	User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(user.getUsername(), currentUser.getRole());
        } else {
            throw new UnauthorizedException();
        }
    }


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
    
    @PutMapping ("updatePassword/{user_id}")
	public void updatePassword(@PathVariable long user_id, @RequestBody String newPassword) {
    	userDao.changePassword(userDao.getUserById(user_id), newPassword);
	}
}