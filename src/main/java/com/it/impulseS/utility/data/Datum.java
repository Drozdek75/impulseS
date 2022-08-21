package com.it.impulseS.utility.data;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Datum {

	public String iso2;
    public String iso3;
    public String country;
    public ArrayList<String> cities;
    
    
    
    
	public String getIso2() {
		return iso2;
	}
	public Datum() {
		super();
	}
	public Datum(String iso2, String iso3, String country) {
	super();
	this.iso2 = iso2;
	this.iso3 = iso3;
	this.country = country;
}
	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}
	public String getIso3() {
		return iso3;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<String> getCities() {
		return cities;
	}
	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}
	
	
    
    

}
