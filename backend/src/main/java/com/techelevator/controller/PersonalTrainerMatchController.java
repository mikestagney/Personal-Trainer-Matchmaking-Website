package com.techelevator.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.API_ErrorResult;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.privatemessage.Message;
import com.techelevator.model.privatemessage.MessageDao;
import com.techelevator.model.user.UserDao;
import com.techelevator.model.user.WorkoutPlan;
import com.techelevator.model.workout.WorkoutDao;
import com.techelevator.model.user.Trainer;
import com.techelevator.model.user.User;


//TODO BM -- split this into TrainerController, ClientController, AccountController
@RestController
@CrossOrigin
public class PersonalTrainerMatchController {
	
	@Autowired
    private AuthProvider authProvider;
    @Autowired
    private MessageDao privateMessageDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private WorkoutDao workoutDao;
	
    @GetMapping("/dir/trainers")
	public List<Trainer> trainersList() {
    	return userDao.getTrainers();
    }
    
    @GetMapping("/trainer/profile/{trainerId}")
	public Trainer trainerProfilePage(@PathVariable long trainerId) throws UnauthorizedException {
		return userDao.getTrainerByID(trainerId);
	}
    
    @GetMapping("/trainer/profile/edit/{trainerId}")
	public Trainer editTrainerProfilePage(@PathVariable long trainerId) throws UnauthorizedException {
    	if(!authProvider.userHasRole(new String[] {"Trainer"})) {
    		throw new UnauthorizedException();
    	}
    	return userDao.getTrainerByID(trainerId);
	}

    @PutMapping("/trainer/profile/edit/{trainerId}")
	public void updateTrainerProfilePage(
			@PathVariable long trainerId,
			@Valid @RequestBody Trainer trainer, BindingResult result
			) throws UnauthorizedException 
    {
		if(!authProvider.userHasRole(new String[] {"Trainer"})) {
            throw new UnauthorizedException();
        }
		//TODO ?? -- How do i send bindingresult errors to frontend?
		if(!result.hasErrors()) {
			userDao.putTrainerByID(trainerId, trainer);
        }
	}

    @GetMapping("/client/profile/{clientId}")
	public User clientProfilePage(@PathVariable long clientId) throws UnauthorizedException {
		return userDao.getClientById(clientId);
	}
    
    @PutMapping("/client/updateProfile")
	public void updateClientProfilePage(@Valid @RequestBody User user, BindingResult result) throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Client"})) {
            throw new UnauthorizedException();
        }
		if(!result.hasErrors()) {
			userDao.updateUser(user);
		}
	}
    
    /**
	 * Method displayClientListSearch() takes three parameters
	 * <p>
	 * Displays All Trainers that meet the search criteria
	 * <p>
	 * If AuthProvider cannot authorize that the User has the role "Trainer" then an UnauthorizedException is thrown
	 * @param firstName this is the first name to search for client in client list of trainer
	 * @param lastName this is the last name to search for client in client list of trainer
	 * @param username this is the username to search for client in client list of trainer
	 * @return List<User> for all Client's that fall within the search criteria
	 */
	@GetMapping("/clientList")
	public List<User> displayClientList() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Trainer"})) {
            throw new UnauthorizedException();
        }
		return userDao.getClientList(authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/trainerList")
	public List<Trainer> displayTrainerList() throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Client"})) {
            throw new UnauthorizedException();
        }
		return userDao.getTrainerList(authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/inbox")
	public List<Message> displayMessages() {
		return privateMessageDao.getMessagesForUser(authProvider.getCurrentUser().getId());
	}
	
	@PostMapping("/send")
	public void sendMessage(@Valid @RequestBody Message message, BindingResult result) {
		API_ErrorResult registrationResult = new API_ErrorResult();
		if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                registrationResult.addError(error.getDefaultMessage());
            }
        }
    	else {
    		privateMessageDao.sendMessage(message);
    	}
	}
    
    
    /**
	 * Method displayMessageListBetweenUsers() takes one parameters
	 * 
	 * Displays Message List for only the messages between a user and the logged in User
	 * @param user_id this is the id of the user that the logged in User wants to see messages to and from
	 * @return List<PrivateMessage> list of all Private Messages between user and User
	 */
	@GetMapping("/inbox/{userId}")
	public List<Message> displayMessagesBetweenUsers(@PathVariable long userId) {
		return privateMessageDao.getMessagesBetweenUsers(authProvider.getCurrentUser().getId(), userId);
	}
	
    @GetMapping("/inbox/{messageId}")
	public Message displayMessage(@PathVariable long messageId) {
		return privateMessageDao.getMessage(messageId);
	}
    

	@PutMapping("/inbox/{messageId}")
	public void deleteMessage(@PathVariable long messageId) {
		privateMessageDao.deleteMessage(authProvider.getCurrentUser().getId(), messageId);
	}
	
	@PostMapping("/addTrainer/{trainerId}")
	public void addClientToClientList(@PathVariable long trainerId) {
		userDao.addClientToClientList(trainerId,authProvider.getCurrentUser().getId());
	}
	
	@GetMapping("/workoutPlans/{userId}")
	public List<WorkoutPlan> getWorkoutPlans(@PathVariable long userId) throws UnauthorizedException {
		if (authProvider.userHasRole(new String[] {"Client"})) {
			return workoutDao.getWorkOutPlansForIds(userId,authProvider.getCurrentUser().getId());
        }
		else if (authProvider.userHasRole(new String[] {"Trainer"})) {
			return workoutDao.getWorkOutPlansForIds(authProvider.getCurrentUser().getId(),userId);
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@GetMapping("/workoutPlan/{workoutPlanId}")
	public WorkoutPlan getWorkoutPlan(@PathVariable long workoutPlanId) {
		return workoutDao.getWorkOutPlan(workoutPlanId);
	}
	
	@GetMapping("/createWorkoutPlan/{clientId}")
	public void getIdForCreateWorkoutPlan(@PathVariable long clientId) {
	}
	
	@PostMapping("/createWorkoutPlan")
	public void createWorkoutPlan(@Valid @RequestBody WorkoutPlan workoutPlan, BindingResult result) throws UnauthorizedException {
		if(!authProvider.userHasRole(new String[] {"Trainer"})) {
            throw new UnauthorizedException();
        }
		if(!result.hasErrors()) {
    		workoutDao.createWorkoutPlan(workoutPlan);
    	}
	}
}
