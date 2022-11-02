package com.it.impulseS.model;

public class NationDTO {

	private String description;
	private String abbreviation;
	private String countryCode;

	public NationDTO() {
		super();
	}

	public NationDTO(String description, String abbreviation, String countryCode) {
		super();
		this.description = description;
		this.abbreviation = abbreviation;
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
