package com.pingidentity.cdr.api.model.energyimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.IData;

public class ResponseEnergyBalanceList implements IData {

	private final List<EnergyBalance> balances;
	
	public ResponseEnergyBalanceList(List<EnergyBalance> balances)
	{
		this.balances = balances;
	}

	public List<EnergyBalance> getBalances() {
		return balances;
	}
}
