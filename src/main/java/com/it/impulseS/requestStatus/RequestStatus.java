package com.it.impulseS.requestStatus;

import java.time.LocalDateTime;

public class RequestStatus {

	private String message;
	private int statusCode;
	private LocalDateTime date;
	private String token;

	public RequestStatus() {
		super();
	}

	public RequestStatus(String message, int statusCode, LocalDateTime date, String token) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.date = date;
		this.token = token;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
