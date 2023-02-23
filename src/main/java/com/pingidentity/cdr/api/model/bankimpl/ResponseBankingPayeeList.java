package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseBankingPayeeList implements IData {

	private final List<BankingPayee> payees;
	
	public ResponseBankingPayeeList(List<BankingPayee> payees)
	{
		this.payees = payees;
	}

	public List<BankingPayee> getPayees() {
		return payees;
	}
}
