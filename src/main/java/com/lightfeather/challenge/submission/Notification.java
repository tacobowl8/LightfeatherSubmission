package com.lightfeather.challenge.submission;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Notification {

	@NotBlank(message = "Please enter the first name")
	private String firstName;
	
	@NotBlank(message = "Please enter the last name")
	private String lastName;
	
	private boolean receiveEmail;
	private boolean receivePhoneCall;
	
	@Email(message = "Must be a well-formed email address")
	private String email;
	
	@PhoneNumber
	private String phoneNumber;
	
	@NotBlank(message = "Please select a supervisor")
	private String supervisor;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isReceiveEmail() {
		return receiveEmail;
	}
	public void setReceiveEmail(boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}
	public boolean isReceivePhoneCall() {
		return receivePhoneCall;
	}
	public void setReceivePhoneCall(boolean receivePhoneCall) {
		this.receivePhoneCall = receivePhoneCall;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
}
