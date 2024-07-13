package com.nagarro.customermgmtservice.model;

import lombok.Data;

@Data
public class Creds {
	String username;
	int pin;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
}
