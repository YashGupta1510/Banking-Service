package com.nagarro.accmgmtservice.model;

import com.nagarro.accmgmtservice.validator.ValidateAction;

public class Txn {

	Creds creds;
	
	@ValidateAction
	String action; 
	double amount;
	public Creds getCreds() {
		return creds;
	}
	public void setCreds(Creds creds) {
		this.creds = creds;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
