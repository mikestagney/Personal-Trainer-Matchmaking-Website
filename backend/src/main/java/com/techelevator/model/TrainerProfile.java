package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * TrainerProfile
 */
public class TrainerProfile {
	
	/**
	 * long id
	 */
	private long id;
	
	/**
	 * boolean isPublic
	 */
	@NotBlank(message="isPublic is required")
	private boolean isPublic;
	
	/**
	 * String firstName
	 */
	private String firstName;
	
	/**
	 * String lastName
	 */
	private String lastName;
	
	/**
	 * int price_per_hour
	 */
	private int price_per_hour;
	
	/**
	 * double rating
	 */
	private double rating;
	
	/**
	 * String philosophy
	 */
	private String philosophy;
	
	/**
	 * String bio
	 */
	private String bio;
	
	/**
	 * String city
	 */
	private String city;
	
	/**
	 * String state
	 */
	private String state;
	
	/**
	 * String certifications
	 */
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
     * public boolean isPublic()
     */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * public void setPublic(boolean isPublic)
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	/**
	 * public String getFirstName()
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
	 * public int getPrice_per_hour()
	 */
	public int getPrice_per_hour() {
		return price_per_hour;
	}

	/**
	 * public void setPrice_per_hour(int price_per_hour)
	 */
	public void setPrice_per_hour(int price_per_hour) {
		this.price_per_hour = price_per_hour;
	}

	/**
	 * public double getRating()
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * public void setRating(double rating)
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * public String getPhilosophy()
	 */
	public String getPhilosophy() {
		return philosophy;
	}

	/**
	 * public void setPhilosophy(String philosophy)
	 */
	public void setPhilosophy(String philosophy) {
		this.philosophy = philosophy;
	}

	/**
	 * public String getBio()
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * public void setBio(String bio)
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * public String getCity()
	 */
	public String getCity() {
		return city;
	}

	/**
	 * public void setCity(String city)
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * public String getState()
	 */
	public String getState() {
		return state;
	}

	/**
	 * public void setState(String state)
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * public String getCertifications()
	 */
	public String getCertifications() {
		return certifications;
	}

	/**
	 * public void setCertifications(String certifications)
	 */
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
}