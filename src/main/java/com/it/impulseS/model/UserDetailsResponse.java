package com.it.impulseS.model;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsResponse {

	private Set<UserDTO> userList = new HashSet<>();

	public UserDetailsResponse() {
		super();
	}

	public UserDetailsResponse(Set<UserDTO> userList) {
		super();
		this.userList = userList;
	}

	public Set<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(Set<UserDTO> userList) {
		this.userList = userList;
	}

}
