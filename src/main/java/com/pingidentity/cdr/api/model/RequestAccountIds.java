package com.pingidentity.cdr.api.model;

import java.util.List;

public class RequestAccountIds implements IModel {

	private List<String> accountIds;
	
	public RequestAccountIds()
	{
	}
	
	public RequestAccountIds(List<String> accountIds)
	{
		this.setAccountIds(accountIds);
	}

	public List<String> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<String> accountIds) {
		this.accountIds = accountIds;
	}
}
