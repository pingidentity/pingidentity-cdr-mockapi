package com.pingidentity.cdr.api.data.energyimpl;

import java.util.ArrayList;
import java.util.List;

import com.pingidentity.cdr.api.exception.AccountNotFoundException;
import com.pingidentity.cdr.api.model.energyimpl.EnergyAccount;
import com.pingidentity.cdr.api.model.energyimpl.EnergyBalance;

public class MockEnergyDataAccess implements IEnergyDataAccess {
	private static final int NUM_ACCOUNTS = 3;

	public List<EnergyAccount> getAccounts(String userRef, List<String> selectedAccountList) {
		List<EnergyAccount> accountList = EnergyMockUtils.getAccountList(userRef, NUM_ACCOUNTS);
		
		if(selectedAccountList == null || accountList == null)
			return accountList;
		
		List<EnergyAccount> returnList = new ArrayList<EnergyAccount>();
		
		for(EnergyAccount currentAccount : accountList)
		{
			for(String selectedAccount : selectedAccountList)
			{
				if(currentAccount.getAccountId().equals(selectedAccount))
				{
					returnList.add(currentAccount);
					break;
				}
			}
		}
		
		return returnList;
	}

	public EnergyAccount getAccount(String userRef, String accountId, List<String> selectedAccountList) {
		List<EnergyAccount> accounts = getAccounts(userRef, selectedAccountList);

		EnergyAccount returnAccount = null;

		for (EnergyAccount account : accounts) {
			if (account.getAccountId().equals(accountId)) {
				returnAccount = account;
				break;
			}
		}
		
		if (returnAccount == null)
			throw new AccountNotFoundException("Account does not exist");
		
		return returnAccount;
	}

	public EnergyBalance getAccountBalance(String userRef, String accountId, List<String> selectedAccountList) {
		
		EnergyAccount account = getAccount(userRef, accountId, selectedAccountList);
		
		if (account == null)
			throw new AccountNotFoundException("Account does not exist");
		
		int amount = 0;
		for(Character character : accountId.toCharArray())
		{
			amount = amount + character;
		}
		
		String balance = amount + ".00";		

		return EnergyBalance.getInstance(accountId, balance);
	}

}
