package com.techelevator.model.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Trainer extends AbstractUser<Trainer> {

	public Trainer(SqlRowSet rowSet) {
		super(Trainer.class);
		try {
			//load(rowSet);
		} catch(Exception e) {}
	}

	
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
	public Integer getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(Integer hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getPhilosophy() {
		return philosophy;
	}
	public void setPhilosophy(String philosophy) {
		this.philosophy = philosophy;
	}
	public String getBioInfo() {
		return bioInfo;
	}
	public void setBioInfo(String bioInfo) {
		this.bioInfo = bioInfo;
	}
	public String[] getCertifications() {
		return certifications;
	}
	public void setCertifications(String[] certifications) {
		this.certifications = certifications;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
}
