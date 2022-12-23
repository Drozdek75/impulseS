package com.it.impulseS.model;

import java.util.ArrayList;
import java.util.List;

public class UserRequestContactsDTO {

	private List<String> phoneNumbers = new ArrayList<>();

	public UserRequestContactsDTO() {
		super();
	}

	public UserRequestContactsDTO(List<String> phoneNumbers) {
		super();
		this.phoneNumbers = phoneNumbers;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

}
