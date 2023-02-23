package com.pingidentity.cdr.api.model.energyimpl;

public class EnergyAccountPlanOverview {
	private String displayName;
	private String startDate;
	private String endDate;
	
	private EnergyAccountPlanOverview()
	{
		
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private EnergyAccountPlanOverview(String displayName, String startDate, String endDate)
	{
		this.displayName = displayName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public static EnergyAccountPlanOverview getInstance(String displayName, String startDate, String endDate)
	{
		return new EnergyAccountPlanOverview(displayName, startDate, endDate);
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	
	
}
