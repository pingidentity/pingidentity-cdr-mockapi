package com.pingidentity.cdr.api.model.bankimpl;

public class BankingScheduledPaymentRecurrenceOnceOff {

	private final String paymentDate;
	
	public BankingScheduledPaymentRecurrenceOnceOff(String paymentDate)
	{
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}
}
