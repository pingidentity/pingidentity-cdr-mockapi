package com.pingidentity.cdr.api.model.energyimpl;

public class EnergyAccountPlanDetailAuthorisedContacts {
	private String firstName;
	private String lastName;
	private String prefix;
	private String suffix;

	private EnergyAccountPlanDetailAuthorisedContacts()
	{
		
	}
	
	private EnergyAccountPlanDetailAuthorisedContacts(String firstName, String lastName, String prefix, String suffix)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	public static EnergyAccountPlanDetailAuthorisedContacts getInstance(String firstName, String lastName, String prefix, String suffix)
	{
		return new EnergyAccountPlanDetailAuthorisedContacts(firstName, lastName, prefix, suffix);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
}
