package com.techelevator.model.user;

import java.util.Arrays;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class User  {
	//TODO BM -- ask David if this violates security considerations
	public String token;
	
	@MapToDB("user_id")
	protected long id;
	
	@MapToDB
	@Size(min = 2, max = 25, message="Username must be between 2 and 25 letters long")
	@NotBlank(message="Username is required")
	protected String username;
	
	@MapToDB
    private String role;
	
	@MapToDB
	@Size(min = 2, max = 256, message="Password length is invalid")
    @NotBlank(message="Password is required")
	protected String password;
	
	@MapToDB
	protected String confirmPassword;
	
	@MapToDB("first_name")
	@Size(min = 2, max = 25, message="First Name must be between 2 and 25 letters long")
	@NotBlank(message="First Name is required")
    private String firstName;

	@MapToDB("last_name")
	@Size(min = 2, max = 25, message="Last Name must be between 2 and 25 letters long")
	@NotBlank(message="Last Name is required")
	private String lastName;
	
	@MapToDB
	@Size(min = 5, max = 120, message="Address must be between 5 and 120 letters long")
	private String address;

	@MapToDB
	@Size(min = 2, max = 30, message="City must be between 2 and 25 letters long")
	private String city;
	
	@MapToDB
	@Size(min = 2, max = 2, message="State must be exactly 2 letters long")
	private String state;
	
	@MapToDB
	@Size(min = 5, max = 16, message="Zip must be between 5 and 16 letters long")
	private String zip;
	
	private Boolean hasTrainersOrClients;

	public Boolean getHasTrainersOrClients() {
		return hasTrainersOrClients;
	}

	public void setHasTrainersOrClients(Boolean hasTrainersOrClients) {
		this.hasTrainersOrClients = hasTrainersOrClients;
	}

	/**
     * @return True if Password Matches Confirm Password
     */
    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return false;
    }
    
    @AssertTrue(message = "Role must be: Client or Trainer")
    public boolean isRoleValid() {
    	final String[] values = {"Client","Trainer"};
    	return Arrays.asList(values).contains(this.role);
    }
    
    /**
	 * @return First Name of User
	 */
    public String getFirstName() {
		return firstName;
	}

    /**
     * @param First Name to be set for User
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Last Name of User
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param Last Name to be set for User
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return Name of User
	 */
	public String getName() {
		return firstName + " " + lastName;
	}

    /**
     * @return Password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return Confirmation Password of User
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    public long getUserID() {
    	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    /**
     * @param password to set for the User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param confirmation password to set for the User
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}