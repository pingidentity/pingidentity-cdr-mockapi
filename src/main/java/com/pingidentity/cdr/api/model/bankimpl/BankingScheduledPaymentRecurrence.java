package com.pingidentity.cdr.api.model.bankimpl;

public class BankingScheduledPaymentRecurrence {

	private final String recurrenceUType;
	private final BankingScheduledPaymentRecurrenceOnceOff onceOff;
	
	public BankingScheduledPaymentRecurrence(String recurrenceUType, BankingScheduledPaymentRecurrenceOnceOff onceOff)
	{
		this.recurrenceUType = recurrenceUType;
		this.onceOff = onceOff;
	}

	public String getRecurrenceUType() {
		return recurrenceUType;
	}

	public BankingScheduledPaymentRecurrenceOnceOff getOnceOff() {
		return onceOff;
	}
}
