package com.pingidentity.cdr.api.model.bankimpl;

import com.pingidentity.cdr.api.model.IModel;

public class BankingBalance implements IModel {

	private final String accountId;

	private final String currentBalance;
	private final String availableBalance;
	private final String creditLimit;

	private final String amortisedLimit;
	private final String currency;
	
	private BankingBalance(String accountId, String currentBalance, String availableBalance, String creditLimit, String amortisedLimit, String currency)
	{
		this.accountId = accountId;
		this.currentBalance = currentBalance;
		this.availableBalance = availableBalance;
		this.creditLimit = creditLimit;
		this.amortisedLimit = amortisedLimit;
		this.currency = currency;
	}
	
	public static BankingBalance getInstance(String accountId, String currentBalance, String availableBalance, String creditLimit, String amortisedLimit, String currency)
	{		
		return new BankingBalance(accountId, currentBalance, availableBalance, creditLimit, amortisedLimit, currency);
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}
	
	public String getAvailableBalance() {
		return availableBalance;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public String getAmortisedLimit() {
		return amortisedLimit;
	}

	public String getCurrency() {
		return currency;
	}
}
