package com.it.impulseS.model;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_nation", nullable = false)
	private Nations nation;

	@Column(nullable = false)
	private String name;

	private String lastName;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(nullable = false)
	private String email;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date CreationDate;

	private String image;

	private String shortMessage;

	private String token;

	private String publicKey;

	public User() {
		super();
	}

	public User(Nations nation, String name, String lastName, Date dateOfBirth, String email, Date creationDate,
			String image, String shortMessage, String token, String publicKey) {
		super();
		this.nation = nation;
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		CreationDate = creationDate;
		this.image = image;
		this.shortMessage = shortMessage;
		this.token = token;
		this.publicKey = publicKey;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
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

}
