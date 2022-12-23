package com.it.impulseS.model.websocket;

public class StompMessage {
	private String to;
	private String from;
	private String message;

	public StompMessage() {
		super();
	}

	public StompMessage(String to, String from, String message) {
		super();
		this.to = to;
		this.from = from;
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
