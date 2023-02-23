package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseBankingDirectDebit implements IData {

	private final List<BankingDirectDebit> directDebitAuthorisations;
	
	public ResponseBankingDirectDebit(List<BankingDirectDebit> directDebitAuthorisations)
	{
		this.directDebitAuthorisations = directDebitAuthorisations;
	}

	public List<BankingDirectDebit> getDirectDebitAuthorisations() {
		return directDebitAuthorisations;
	}
}
