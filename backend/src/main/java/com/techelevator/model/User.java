package com.techelevator.model;

import javax.validation.constraints.AssertTrue;
import org.hibernate.validator.constraints.NotBlank;

/**
 * User
 */
public class User {
	
	/**
	 * String username
	 */
	@NotBlank(message="Username is required")
    private String username;
	
	/**
	 * String firstName
	 */
	@NotBlank(message="First Name is required")
    private String firstName;
	
	/**
	 * String lastName
	 */
	@NotBlank(message="Last Name is required")
    private String lastName;

	/**
	 * public String getFirstName() {
	 */
    public String getFirstName() {
		return firstName;
	}

    /**
     * public void setFirstName(String firstName)
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * public String getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * public void setLastName(String lastName)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * String role
	 */
	@NotBlank(message="Role is required")
    private String role;
	
	/**
     * long id
     */
    private long id;

    /**
     * String password
     */
    @NotBlank(message="Password is required")
    private String password;
    
    /**
     * String confirmPassword
     */
    private String confirmPassword;

    /**
     * boolean passwordMatching
     */
    private boolean passwordMatching;
    
    /**
     * public boolean isPasswordMatching()
     */
    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return true;
    }

    /**
     * public String getPassword()
     */
    public String getPassword() {
        return password;
    }

    /**
     * public String getConfirmPassword()
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
        this.username = username;
    }

    /**
     * public void setPassword(String password)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * public void setConfirmPassword(String confirmPassword)
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
