package com.pingidentity.cdr.api.model.bankimpl;

public class BankingScheduledPaymentTo {

	private final String toUType, accountId;
	
	public BankingScheduledPaymentTo(String toUType, String accountId)
	{
		this.toUType = toUType;
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getToUType() {
		return toUType;
	}
}
