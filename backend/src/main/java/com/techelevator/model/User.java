package com.techelevator.model;

public interface User {
	
	boolean isPasswordMatching();
	
	String getPassword();
	
	String getConfirmPassword();
	
	String getUsername();
	
	long getId();
	
	void setId(long id);
	
	String getRole();
	
	void setRole(String role);
	
	void setUsername(String username);
	
	void setPassword(String password);
	
	void setConfirmPassword(String confirmPassword);
}
