package com.pingidentity.cdr.api.model.energyimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseEnergyAccountList implements IData {

	private final List<EnergyAccount> accounts;
	
	public ResponseEnergyAccountList(List<EnergyAccount> accounts)
	{
		this.accounts = accounts;
	}

	public List<EnergyAccount> getAccounts() {
		return accounts;
	}
}
