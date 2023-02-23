package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseBankingAccountList implements IData {

	private final List<BankingAccount> accounts;
	
	public ResponseBankingAccountList(List<BankingAccount> accounts)
	{
		this.accounts = accounts;
	}

	public List<BankingAccount> getAccounts() {
		return accounts;
	}
}
