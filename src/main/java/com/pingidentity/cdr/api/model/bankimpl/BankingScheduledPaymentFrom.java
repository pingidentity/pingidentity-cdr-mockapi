package com.pingidentity.cdr.api.model.bankimpl;

public class BankingScheduledPaymentFrom {

	private final String accountId;
	
	public BankingScheduledPaymentFrom(String accountId)
	{
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}
}
