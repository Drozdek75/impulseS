package com.it.impulseS.configure.jwt;

public class UserClaim {

	private String telephoneNumber;
	private String name;

	public UserClaim() {
		super();
	}

	public UserClaim(String telephoneNumber, String name) {
		super();
		this.telephoneNumber = telephoneNumber;
		this.name = name;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
