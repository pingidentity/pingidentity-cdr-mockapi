package com.pingidentity.cdr.api.model.energyimpl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnergyAccountPlanDetail {
	private String fuelType;
	private boolean isContingentPlan;
	private List<EnergyAccountPlanDetailAuthorisedContacts> authorisedContacts;
	
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public void setContingentPlan(boolean isContingentPlan) {
		this.isContingentPlan = isContingentPlan;
	}

	public void setAuthorisedContacts(List<EnergyAccountPlanDetailAuthorisedContacts> authorisedContacts) {
		this.authorisedContacts = authorisedContacts;
	}

	private EnergyAccountPlanDetail()
	{
		
	}
	
	private EnergyAccountPlanDetail(String fuelType, boolean isContingentPlan, List<EnergyAccountPlanDetailAuthorisedContacts> authorisedContacts)
	{
		this.fuelType = fuelType;
		this.isContingentPlan = isContingentPlan;
		this.authorisedContacts = authorisedContacts;
	}
	
	public static EnergyAccountPlanDetail getInstance(String fuelType, boolean isContingentPlan, List<EnergyAccountPlanDetailAuthorisedContacts> authorisedContacts)
	{
		return new EnergyAccountPlanDetail(fuelType, isContingentPlan, authorisedContacts);
	}

	public String getFuelType() {
		return fuelType;
	}

	@JsonProperty("isContingentPlan")
	public boolean isContingentPlan() {
		return isContingentPlan;
	}

	public List<EnergyAccountPlanDetailAuthorisedContacts> getAuthorisedContacts() {
		return authorisedContacts;
	}
	
	
	
	
	
}
