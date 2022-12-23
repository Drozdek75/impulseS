package com.it.impulseS.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_nation", nullable = true)
	private Nations nation;

	@Column(nullable = false)
	private String name;

	private String lastName;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(nullable = false, unique = true)
	private String email;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date CreationDate;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	private String shortMessage;

	private String token;

	private String publicKey;

	private String telephoneNumber;

	private String password;

	/*@ManyToMany
	@JsonManagedReference
	@JoinTable(name = "userRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private Set<Roles> RoleList = new HashSet<>();*/
	
	private long dateLastModify = 0l;

	public User() {
		super();
	}

	public User(Nations nation, String name, String lastName, Date dateOfBirth, String email, Date creationDate,
			byte[] image, String shortMessage, String token, String publicKey, String telephoneNumber,
			String password, long lastModify) {
		super();
		this.nation = nation;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.CreationDate = creationDate;
		this.image = image;
		this.shortMessage = shortMessage;
		this.token = token;
		this.publicKey = publicKey;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
		this.dateLastModify = lastModify;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Nations getNation() {
		return nation;
	}

	public void setNation(Nations nation) {
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
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/*public Set<Roles> getRoleList() {
		return RoleList;
	}

	public void setRoleList(Set<Roles> roleList) {
		RoleList = roleList;
	}*/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getDateLastModify() {
		return dateLastModify;
	}

	public void setDateLastModify(long dateLastModify) {
		this.dateLastModify = dateLastModify;
	}
	
	

}
