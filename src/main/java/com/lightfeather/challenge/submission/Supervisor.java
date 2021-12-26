package com.lightfeather.challenge.submission;

public class Supervisor implements Comparable<Supervisor> {
	private int id;
	private String phone;
	private String jurisdiction;
	private String identificationNumber;
	private String firstName;
	private String lastName;
	
	public Supervisor(int id, String phone, String jurisdiction, String identificationNumber, String firstName, String lastName) {
		this.id = id;
		this.phone = phone;
		this.jurisdiction = jurisdiction;
		this.identificationNumber = identificationNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
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

	/**
	 * Compares this supervisor object with teh passed in supervisor object
	 * This will check first the jurisdiction between the two and if the calling object has a jurisdiction that comes before the passed in object's jurisdiction, -1 will be returned, else, 1.
	 * If the jurisdictions are the same, this will then check the last names, applying the same logic.
	 * If the last names are the same, this will then check the first names, applying the same logic.
	 * If the jurisdiction, last name, and first name are all the same, 0 will be returned.
	 * 
	 * @param anotherSupervisor
	 * @return
	 */
	@Override
	public int compareTo(Supervisor anotherSupervisor) {
		if(this.jurisdiction.equals(anotherSupervisor.getJurisdiction())) {
			if(this.lastName.equals(anotherSupervisor.getLastName())) {
				if(this.firstName.equals(anotherSupervisor.getFirstName())) {
					return 0;
				} else {
					return this.firstName.compareTo(anotherSupervisor.getFirstName());
				}
			} else {
				return this.lastName.compareTo(anotherSupervisor.getLastName());
			}
		} else {
			return this.jurisdiction.compareTo(anotherSupervisor.getJurisdiction());
		}		
	}
	
	public String toString() {
		return "id = "+id+" ; phone = "+phone+" ; jurisdiction = "+jurisdiction+" ; identificationNumber = "+identificationNumber+" ; firstName = "+firstName+" ; lastName = "+lastName;
	}


}
