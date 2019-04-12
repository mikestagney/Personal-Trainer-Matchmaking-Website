package com.techelevator.model.user;

/**
 * TrainerProfile Class is an object that holds an Id, an isPublic boolen, a First Name,
 * a Last Name, a Price Per Hour, a Rating, a Trainer Philospy, a Trainer Bio,
 * a Trainer's City and State, and the Trainer's Certifications
 */
public class TrainerProfile {
	
	private long id;
	private int price_per_hour;
	private double rating;
	private String philosophy;
	private String bio;
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
