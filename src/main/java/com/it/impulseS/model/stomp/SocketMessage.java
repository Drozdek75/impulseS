package com.it.impulseS.model.stomp;

public class SocketMessage {

	private String message;
	private String to;
	private String from;
	private long date;

	public SocketMessage(String message, String to, String from, long date) {
		super();
		this.message = message;
		this.to = to;
		this.from = from;
		this.date = date;
	}

	public SocketMessage() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
