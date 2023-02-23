package com.pingidentity.cdr.api.model.energyimpl;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pingidentity.cdr.api.model.IModel;

public class EnergyAccount implements IModel {

	private String accountId;
	private String accountNumber;
	private String displayName;
	private String creationDate;

    @JsonInclude(Include.NON_NULL)
	private String openStatus;

	private List<Map<String, Object>> plans;

	private EnergyAccount()
	{
		
	}
	
	private EnergyAccount(String accountId, String openStatus, String accountNumber, String displayName, String creationDate, List<Map<String, Object>> plans)
	{
		this.accountId = accountId;
		this.displayName = displayName;
		this.accountNumber = accountNumber;
		this.creationDate = creationDate;
		this.plans = plans;
		this.openStatus = openStatus;
	}
	
	public static EnergyAccount getInstance(String accountId, String openStatus, String accountNumber, String displayName, String creationDate, List<Map<String, Object>> plans)
	{
		return new EnergyAccount(accountId, openStatus, accountNumber, displayName, creationDate, plans);
	}
	
	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setPlans(List<Map<String, Object>> plans) {
		this.plans = plans;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public List<Map<String, Object>> getPlans() {
		return plans;
	}
	
	@Override
	public String toString()
	{
		return this.accountId;
	}
}
