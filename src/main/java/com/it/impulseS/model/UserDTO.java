package com.it.impulseS.model;

import java.util.Date;

import javax.persistence.Lob;

public class UserDTO {

	private String nation;
	private String name;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private Date creationDate;
	private String imageProfile;
	private String shortMessage;
	private String token;
	private String apiKey;
	private String telephoneNumber;
	private String password;

	public UserDTO() {
		super();
	}

	public UserDTO(String nation, String name, String lastName, Date dateOfBirth, String email, Date creationDate,
			String imageProfile, String shortMessage, String token, String apiKey, String telephoneNumber,
			String password) {
		super();
		this.nation = nation;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.creationDate = creationDate;
		this.imageProfile = imageProfile;
		this.shortMessage = shortMessage;
		this.token = token;
		this.apiKey = apiKey;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
	}
	
	
	public UserDTO(String tn, String photo, String shortMessage) {
		this.telephoneNumber= tn;
		this.imageProfile = photo;
		this.shortMessage = shortMessage;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
