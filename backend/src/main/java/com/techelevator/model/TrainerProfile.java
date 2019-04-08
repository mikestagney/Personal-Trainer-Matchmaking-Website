package com.techelevator.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * TrainerProfile
 */
public class TrainerProfile {
	
	private long id;
	
	@NotBlank(message="isPublic is required")
	private boolean isPublic;
	
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

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getPrice_per_hour() {
		return price_per_hour;
	}

	public void setPrice_per_hour(int price_per_hour) {
		this.price_per_hour = price_per_hour;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getPhilosophy() {
		return philosophy;
	}

	public void setPhilosophy(String philosophy) {
		this.philosophy = philosophy;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCertifications() {
		return certifications;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	
}
