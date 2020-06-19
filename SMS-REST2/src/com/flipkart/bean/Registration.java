package com.flipkart.bean;

import java.sql.Timestamp;


public class Registration {
	Timestamp registrationDate;
	int regId;
	int userId;
	int paymentId;
	
	public Registration()
	{
		this.regId = -1;
		this.userId = -1;
		this.registrationDate = null;
		this.paymentId = -1;
	}
	public Timestamp getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	
	
	
}
