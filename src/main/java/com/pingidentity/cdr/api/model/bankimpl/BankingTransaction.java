package com.pingidentity.cdr.api.model.bankimpl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pingidentity.cdr.api.model.IModel;

public class BankingTransaction implements IModel {

	private String accountId, transactionId;
	
	private String amount, currency, type, status, description, reference;
	
	private String postingDateTime, valueDateTime, executionDateTime, merchantName, merchantCategoryCode, billerCode, billName, crn, apcaNumber;

	private boolean isDetailAvailable;
	private BankingTransaction()
	{
		
		
	}
	private BankingTransaction(String accountId, String transactionId, String type, String status, String description, String reference, String amount, String currency, boolean isDetailAvailable, String postingDateTime)
	{
		this.accountId = accountId;
		this.currency = currency;
		this.type = type;
		this.status = status;
		this.description = description;
		this.isDetailAvailable = isDetailAvailable;
		this.transactionId = transactionId;
		this.reference = reference;
		this.amount = amount;
		this.postingDateTime = postingDateTime;
	}
	
	public static BankingTransaction getInstance(String accountId, String transactionId, String type, String status, String description, String reference, String amount, String currency, boolean isDetailAvailable)
	{		
		return new BankingTransaction(accountId, transactionId, type, status, description, reference, amount, currency, isDetailAvailable, null);
	}
	
	public static BankingTransaction getInstance(String accountId, String transactionId, String type, String status, String description, String reference, String amount, String currency, boolean isDetailAvailable, String postingDateTime)
	{		
		return new BankingTransaction(accountId, transactionId, type, status, description, reference, amount, currency, isDetailAvailable, postingDateTime);
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public void setDetailAvailable(boolean isDetailAvailable) {
		this.isDetailAvailable = isDetailAvailable;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCurrency() {
		return currency;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	@JsonProperty("isDetailAvailable")
	public boolean isDetailAvailable() {
		return isDetailAvailable;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getReference() {
		return reference;
	}

	public String getAmount() {
		return amount;
	}
	
	public String getPostingDateTime() {
		return postingDateTime;
	}
	public void setPostingDateTime(String postingDateTime) {
		this.postingDateTime = postingDateTime;
	}
	public String getValueDateTime() {
		return valueDateTime;
	}
	public void setValueDateTime(String valueDateTime) {
		this.valueDateTime = valueDateTime;
	}
	public String getExecutionDateTime() {
		return executionDateTime;
	}
	public void setExecutionDateTime(String executionDateTime) {
		this.executionDateTime = executionDateTime;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantCategoryCode() {
		return merchantCategoryCode;
	}
	public void setMerchantCategoryCode(String merchantCategoryCode) {
		this.merchantCategoryCode = merchantCategoryCode;
	}
	public String getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(String billerCode) {
		this.billerCode = billerCode;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	public String getApcaNumber() {
		return apcaNumber;
	}
	public void setApcaNumber(String apcaNumber) {
		this.apcaNumber = apcaNumber;
	}
	
	@Override
	public String toString()
	{
		return this.transactionId;
	}
}
