package com.techelevator.model.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Trainer extends User {
	
	@Size(min = 2, max = 30)
	private String city;
	@Size(min = 2, max = 2)
	private String state;
	@Min(0)
	private Integer hourlyRate;
	@Min(0)
	@Max(5)
	private Double rating;
	@Size(min = 2, max = 50)
	private String philosophy;
	@Size(min = 2, max = 250)
	private String bioInfo;
	private String[] certifications;
	@NotNull(message="isPublic is required")
	private Boolean isPublic;

	/**
	 * @return if Trainer Profile is Public
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param set if Trainer Profile is Public
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
	 * @return the Hourly Rate
	 */
	public int getHourlyRate() {
		return hourlyRate;
	}

	/**
	 * @param the Hourly Rate to set
	 */
	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
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
	 * @return the Bio Info of the Trainer
	 */
	public String getBioInfo() {
		return bioInfo;
	}

	/**
	 * @param the Bio Info of the Trainer to set
	 */
	public void setBioInfo(String bioInfo) {
		this.bioInfo = bioInfo;
	}

	/**
	 * @return the Certifications of the Trainer
	 */
	public String[] getCertifications() {
		return certifications;
	}

	/**
	 * @param the Certifications of the Trainer to set
	 */
	public void setCertifications(String[] certifications) {
		this.certifications = certifications;
	}
}
