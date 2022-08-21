package com.it.impulseS.utility.data;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NationsOBJ {

	public boolean error;
    public String msg;
    public ArrayList<Datum> data;
    
    
    
	public NationsOBJ() {
		super();
	}
	public NationsOBJ(boolean error, String msg, ArrayList<Datum> data) {
		super();
		this.error = error;
		this.msg = msg;
		this.data = data;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<Datum> getData() {
		return data;
	}
	public void setData(ArrayList<Datum> data) {
		this.data = data;
	}
    
    
}
