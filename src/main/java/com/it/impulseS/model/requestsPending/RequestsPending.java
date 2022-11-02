package com.it.impulseS.model.requestsPending;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestsPending {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String telephoneNumber;

	@Column(nullable = false, unique = true)
	private int activactionCode;

	@Column(nullable = false, unique = true)
	private String apiKey;

	private String emailAddress;

	private boolean ctrl = false;

	public RequestsPending() {
		super();
	}

	public RequestsPending(String telephoneNumber, int activactionCode, String apiKey, String emailAddress,
			boolean ctrl) {
		super();
		this.telephoneNumber = telephoneNumber;
		this.activactionCode = activactionCode;
		this.apiKey = apiKey;
		this.emailAddress = emailAddress;
		this.ctrl = ctrl;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public int getActivactionCode() {
		return activactionCode;
	}

	public void setActivactionCode(int activactionCode) {
		this.activactionCode = activactionCode;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isCtrl() {
		return ctrl;
	}

	public void setCtrl(boolean ctrl) {
		this.ctrl = ctrl;
	}

}
