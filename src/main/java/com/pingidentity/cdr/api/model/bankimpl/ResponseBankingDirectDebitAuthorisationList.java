package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IModel;

public class ResponseBankingDirectDebitAuthorisationList implements IModel {

	private final List<BankingDirectDebit> directDebitAuthorisations;
	
	private ResponseBankingDirectDebitAuthorisationList(List<BankingDirectDebit> directDebitAuthorisations)
	{
		this.directDebitAuthorisations = directDebitAuthorisations;
	}
	
	public static ResponseBankingDirectDebitAuthorisationList getInstance(List<BankingDirectDebit> directDebitAuthorisations)
	{		
		return new ResponseBankingDirectDebitAuthorisationList(directDebitAuthorisations);
	}

	public List<BankingDirectDebit> getDirectDebitAuthorisations() {
		return directDebitAuthorisations;
	}
}
