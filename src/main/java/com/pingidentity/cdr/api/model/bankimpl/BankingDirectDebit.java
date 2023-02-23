package com.pingidentity.cdr.api.model.bankimpl;

import com.pingidentity.cdr.api.model.IModel;

public class BankingDirectDebit implements IModel {

	private final String accountId;
	private final BankingAuthorisedEntity authorisedEntity;
	
	private BankingDirectDebit(String accountId, BankingAuthorisedEntity authorisedEntity)
	{
		this.accountId = accountId;
		this.authorisedEntity = authorisedEntity;
	}
	
	public static BankingDirectDebit getInstance(String accountId, BankingAuthorisedEntity authorisedEntity)
	{		
		return new BankingDirectDebit(accountId, authorisedEntity);
	}

	public String getAccountId() {
		return accountId;
	}

	public BankingAuthorisedEntity getAuthorisedEntity() {
		return authorisedEntity;
	}
}
