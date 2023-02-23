package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseBankingTransactionList implements IData {

	private final List<BankingTransaction> transactions;
	
	public ResponseBankingTransactionList(List<BankingTransaction> transactions)
	{
		this.transactions = transactions;
	}

	public List<BankingTransaction> getTransactions() {
		return transactions;
	}
}
