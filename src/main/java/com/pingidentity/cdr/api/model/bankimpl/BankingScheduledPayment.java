package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IModel;

public class BankingScheduledPayment implements IModel {

	private final String scheduledPaymentId, payerReference, payeeReference, status;
	private final BankingScheduledPaymentFrom from;
	private final List<BankingScheduledPaymentSet> paymentSet;
	private final BankingScheduledPaymentRecurrence recurrence;
	
	private BankingScheduledPayment(String scheduledPaymentId, String payerReference, String payeeReference, String status, BankingScheduledPaymentFrom from, List<BankingScheduledPaymentSet> paymentSet, BankingScheduledPaymentRecurrence recurrence)
	{
		this.scheduledPaymentId = scheduledPaymentId;
		this.payerReference = payerReference;
		this.payeeReference = payeeReference;
		this.status = status;
		this.from = from;
		this.paymentSet = paymentSet;
		this.recurrence = recurrence;
	}
	
	public static BankingScheduledPayment getInstance(String scheduledPaymentId, String payerReference, String payeeReference, String status, BankingScheduledPaymentFrom from, List<BankingScheduledPaymentSet> paymentSet, BankingScheduledPaymentRecurrence recurrence)
	{		
		return new BankingScheduledPayment(scheduledPaymentId, payerReference, payeeReference, status, from, paymentSet, recurrence);
	}

	public String getScheduledPaymentId() {
		return scheduledPaymentId;
	}

	public String getPayerReference() {
		return payerReference;
	}

	public String getPayeeReference() {
		return payeeReference;
	}

	public String getStatus() {
		return status;
	}

	public BankingScheduledPaymentFrom getFrom() {
		return from;
	}

	public List<BankingScheduledPaymentSet> getPaymentSet() {
		return paymentSet;
	}

	public BankingScheduledPaymentRecurrence getRecurrence() {
		return recurrence;
	}
}
