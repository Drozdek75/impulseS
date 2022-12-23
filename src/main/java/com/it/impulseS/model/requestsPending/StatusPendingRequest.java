package com.it.impulseS.model.requestsPending;

import java.time.LocalDateTime;
import java.util.Date;

public class StatusPendingRequest {

	private int codeResponse;
	private String message;
	private boolean success;
	private LocalDateTime date;
	private String apiKey;
	private String token;

	public StatusPendingRequest() {
		super();
	}

	public StatusPendingRequest(int codeResponse, String message, boolean success, LocalDateTime date, String apiKey, String token) {
		super();
		this.codeResponse = codeResponse;
		this.message = message;
		this.success = success;
		this.date = date;
		this.apiKey = apiKey;
		this.token = token;
	}

	public int getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(int codeResponse) {
		this.codeResponse = codeResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
