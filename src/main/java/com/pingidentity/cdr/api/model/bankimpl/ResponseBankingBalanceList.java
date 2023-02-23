package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseBankingBalanceList implements IData {

	private final List<BankingBalance> balances;
	
	public ResponseBankingBalanceList(List<BankingBalance> balances)
	{
		this.balances = balances;
	}

	public List<BankingBalance> getBalances() {
		return balances;
	}
}
