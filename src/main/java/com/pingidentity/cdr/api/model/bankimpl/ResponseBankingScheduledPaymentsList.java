package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseBankingScheduledPaymentsList implements IData {

	private final List<BankingScheduledPayment> scheduledPayments;
	
	public ResponseBankingScheduledPaymentsList(List<BankingScheduledPayment> scheduledPayments)
	{
		this.scheduledPayments = scheduledPayments;
	}
	
	public static ResponseBankingScheduledPaymentsList getInstance(List<BankingScheduledPayment> scheduledPayments)
	{		
		return new ResponseBankingScheduledPaymentsList(scheduledPayments);
	}

	public List<BankingScheduledPayment> getScheduledPayments() {
		return scheduledPayments;
	}
}
