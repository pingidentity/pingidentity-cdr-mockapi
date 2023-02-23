package com.pingidentity.cdr.api.data.bankimpl;

import java.util.List;

import com.pingidentity.cdr.api.model.bankimpl.BankingAccount;
import com.pingidentity.cdr.api.model.bankimpl.BankingBalance;
import com.pingidentity.cdr.api.model.bankimpl.BankingPayee;
import com.pingidentity.cdr.api.model.bankimpl.BankingTransaction;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingDirectDebitAuthorisationList;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingScheduledPaymentsList;

public interface IBankDataAccess {
	public List<BankingAccount> getAccounts(String userRef, List<String> selectedAccountList, String openStatus, String productCategory, Boolean isOwned);
	public BankingAccount getAccount(String userRef, String accountId, List<String> selectedAccountList);
	public BankingBalance getAccountBalance(String userRef, String accountId, List<String> selectedAccountList);
	public BankingTransaction getAccountTransaction(String userRef, String accountId, String transactionId);
	public List<BankingTransaction> getAccountTransactions(String userRef, String accountId);
	public ResponseBankingDirectDebitAuthorisationList getAccountDirectDebit(String userId, String accountId, List<String> accountList);
	public ResponseBankingScheduledPaymentsList getAccountScheduledPayments(String userId, String accountId,
			List<String> accountList);
	public List<BankingPayee> getPayees(String userId);
	public BankingPayee getPayee(String userId, String payeeId);
}
