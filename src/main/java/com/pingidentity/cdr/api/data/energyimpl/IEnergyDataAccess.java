package com.pingidentity.cdr.api.data.energyimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.energyimpl.EnergyAccount;
import com.pingidentity.cdr.api.model.energyimpl.EnergyBalance;

public interface IEnergyDataAccess {
	public List<EnergyAccount> getAccounts(String userRef, List<String> selectedAccountList);
	public EnergyAccount getAccount(String userRef, String accountId, List<String> selectedAccountList);
	public EnergyBalance getAccountBalance(String userRef, String accountId, List<String> selectedAccountList);
}
