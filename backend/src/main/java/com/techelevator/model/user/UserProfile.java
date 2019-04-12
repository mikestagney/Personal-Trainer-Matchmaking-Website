package com.techelevator.model.user;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotBlank;

/**
 * UserProfile Class is an object that holds an Id, an isPublic boolen, a First Name,
 * a Last Name, and a User's City and State
 */
public class UserProfile {
	
	private long id;
	@NotBlank(message="isPublic is required")
	private boolean isPublic;
	@NotBlank(message="isPublic is required")
	private String role;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private TrainerProfile trainerProfile = new TrainerProfile();
	
	/**
     * @return True if Trainer has TrainerProfile or Client does not have TrainerProfile
     */
    @AssertTrue(message = "Trainer must have TrainerProfile")
    public boolean isPasswordMatching() {
        if ((role.contentEquals("Trainer") && trainerProfile != null) || (role.contentEquals("Client") && trainerProfile == null)) {
            return true;
        }
        return false;
    }
    
	public TrainerProfile getTrainerProfile() {
		return trainerProfile;
	}

	public void setTrainerProfile(TrainerProfile trainerProfile) {
		this.trainerProfile = trainerProfile;
	}

	/**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    /**
     * @return the boolean for is Trainer Profile Public 
     */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param the boolean to set for is Trainer Profile Public
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	/**
	 * @return the First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param ithe First Name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param the Last Name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the City of the Trainer
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param the City of the Trainer to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the State of the Trainer
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param the State of the Trainer to set
	 */
	public void setState(String state) {
		this.state = state;
	}
}