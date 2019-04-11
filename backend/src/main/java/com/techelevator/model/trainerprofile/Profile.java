package com.techelevator.model.trainerprofile;

import org.hibernate.validator.constraints.NotBlank;

public class Profile {
	
	private long id;
	@NotBlank(message="isPublic is required")
	private boolean isPublic;
	private String firstName;
	private String lastName;
	private int price_per_hour;
	private double rating;
	private String philosophy;
	private String bio;
	private String city;
	private String state;
	private String certifications;
	
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
	 * @return the Price Per Hour
	 */
	public int getPrice_per_hour() {
		return price_per_hour;
	}

	/**
	 * @param the Price Per Hour to set
	 */
	public void setPrice_per_hour(int price_per_hour) {
		this.price_per_hour = price_per_hour;
	}

	/**
	 * @return the Rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param the Rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @return the Philosophy of the Trainer
	 */
	public String getPhilosophy() {
		return philosophy;
	}

	/**
	 * @param the Philosophy of the Trainer to set
	 */
	public void setPhilosophy(String philosophy) {
		this.philosophy = philosophy;
	}

	/**
	 * @return the Bio of the Trainer
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param the Bio of the Trainer to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
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

	/**
	 * @return the Certifications of the Trainer
	 */
	public String getCertifications() {
		return certifications;
	}

	/**
	 * @param the Certifications of the Trainer to set
	 */
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
}
