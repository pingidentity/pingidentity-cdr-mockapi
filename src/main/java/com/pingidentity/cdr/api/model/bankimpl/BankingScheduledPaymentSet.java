package com.pingidentity.cdr.api.model.bankimpl;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankingScheduledPaymentSet {

	private final BankingScheduledPaymentTo to;
	private final boolean isAmountCalculated;
	private final String amount;
	
	public BankingScheduledPaymentSet(BankingScheduledPaymentTo to, boolean isAmountCalculated, String amount)
	{
		this.to = to;
		this.isAmountCalculated = isAmountCalculated;
		this.amount = amount;
	}

	public BankingScheduledPaymentTo getTo() {
		return to;
	}

	@JsonProperty("isAmountCalculated")
	public boolean isAmountCalculated() {
		return isAmountCalculated;
	}

	public String getAmount() {
		return amount;
	}
}
