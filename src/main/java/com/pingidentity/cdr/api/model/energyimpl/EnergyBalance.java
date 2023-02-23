package com.pingidentity.cdr.api.model.energyimpl;

import com.pingidentity.cdr.api.model.IModel;

public class EnergyBalance implements IModel {

	private final String accountId;
	private final String balance;
	
	private EnergyBalance(String accountId, String balance)
	{
		this.balance = balance;
		this.accountId = accountId;
	}
	
	public static EnergyBalance getInstance(String accountId, String balance)
	{		
		return new EnergyBalance(accountId, balance);
	}

	public String getBalance() {
		return balance;
	}

	public String getAccountId() {
		return accountId;
	}
}
