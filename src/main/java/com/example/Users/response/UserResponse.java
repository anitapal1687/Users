package com.example.Users.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserResponse {


	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object results;
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	}

}
