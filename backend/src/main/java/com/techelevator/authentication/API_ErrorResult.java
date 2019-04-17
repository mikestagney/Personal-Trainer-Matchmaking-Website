package com.techelevator.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * API Result has errors or if it was Successful.
 */
public class API_ErrorResult {
	
	private boolean success;
	private List<String> errors = new ArrayList<String>();

	public void addError(String error) {
		errors.add(error);
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}