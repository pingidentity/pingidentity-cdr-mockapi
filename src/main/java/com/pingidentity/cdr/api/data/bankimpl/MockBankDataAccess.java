package com.pingidentity.cdr.api.data.bankimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pingidentity.cdr.api.exception.AccountNotFoundException;
import com.pingidentity.cdr.api.exception.bank.PayeeNotFoundException;
import com.pingidentity.cdr.api.exception.bank.TransactionNotFoundException;
import com.pingidentity.cdr.api.model.bankimpl.BankingAccount;
import com.pingidentity.cdr.api.model.bankimpl.BankingAuthorisedEntity;
import com.pingidentity.cdr.api.model.bankimpl.BankingBalance;
import com.pingidentity.cdr.api.model.bankimpl.BankingDirectDebit;
import com.pingidentity.cdr.api.model.bankimpl.BankingPayee;
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPayment;
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPaymentFrom;
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPaymentRecurrence;
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPaymentRecurrenceOnceOff;
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPaymentSet;
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPaymentTo;
import com.pingidentity.cdr.api.model.bankimpl.BankingTransaction;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingDirectDebitAuthorisationList;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingScheduledPaymentsList;

public class MockBankDataAccess implements IBankDataAccess {
	private static final int NUM_ACCOUNTS = 3;
	private static final int NUM_TRANSACTIONS = 30;
	private static final int NUM_PAYMENTSET = 5;
	private static final int NUM_SCHEDULEDPAYMENTS = 10;
	private static final int NUM_PAYEES = 8;

	public List<BankingAccount> getAccounts(String userRef, List<String> selectedAccountList, String openStatus, String productCategory, Boolean isOwned) {
		List<BankingAccount> accountList = BankMockUtils.getAccountList(userRef, NUM_ACCOUNTS);
		
		List<BankingAccount> returnList = new ArrayList<BankingAccount>();
		
		if(openStatus != null && (openStatus.isEmpty() || openStatus.equalsIgnoreCase("ALL")))
			openStatus = null;
		
		if(productCategory != null && productCategory.isEmpty())
			productCategory = null;
		
		for(BankingAccount currentAccount : accountList)
		{
			if(openStatus != null && !currentAccount.getOpenStatus().equalsIgnoreCase(openStatus))
			{
				continue;
			}
			if(productCategory != null && !currentAccount.getProductCategory().equalsIgnoreCase(productCategory))
			{
				continue;
			}
			if(isOwned != null && currentAccount.isOwned() != isOwned)
			{
				continue;
			}
			
			if(selectedAccountList != null)
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
			else
			{
				returnList.add(currentAccount);
			}
		}
		
		return returnList;
	}

	public BankingAccount getAccount(String userRef, String accountId, List<String> selectedAccountList) {
		List<BankingAccount> accounts = getAccounts(userRef, selectedAccountList, null, null, null);

		BankingAccount returnAccount = null;

		for (BankingAccount account : accounts) {
			if (account.getAccountId().equals(accountId)) {
				returnAccount = account;
				break;
			}
		}
		
		if (returnAccount == null)
			throw new AccountNotFoundException("Account does not exist");
		
		return returnAccount;
	}

	public BankingBalance getAccountBalance(String userRef, String accountId, List<String> selectedAccountList) {
		
		BankingAccount account = getAccount(userRef, accountId, selectedAccountList);
		
		if (account == null)
			throw new AccountNotFoundException("Account does not exist");
		
		int amount = 0;
		for(Character character : accountId.toCharArray())
		{
			amount = amount + character;
		}
		
		String currentBalance = amount + ".00";		
		String availableBalance = amount*2 + ".00";
		String creditLimit = "10000.00";
		String amortisedLimit = "100000000.00";
		String currency = "AUD";
		
		return BankingBalance.getInstance(accountId, currentBalance, availableBalance, creditLimit, amortisedLimit, currency);
	}

	public BankingTransaction getAccountTransaction(String userRef, String accountId, String transactionId) {
		List<BankingTransaction> transactions = getAccountTransactions(userRef, accountId);

		BankingTransaction returnTransaction = null;

		for (BankingTransaction transaction : transactions) {
			if (transaction.getTransactionId().equals(transactionId)) {
				returnTransaction = transaction;
				break;
			}
		}
		
		if (returnTransaction == null)
			throw new TransactionNotFoundException("Account Transaction does not exist");
		
		return returnTransaction;
	}

	public List<BankingTransaction> getAccountTransactions(String userRef, String accountId) {
		
		List<BankingTransaction> returnList = BankMockUtils.getAccountTransactionList(userRef, accountId, NUM_TRANSACTIONS);
		
		return returnList;
	}

	public ResponseBankingDirectDebitAuthorisationList getAccountDirectDebit(String userId, String accountId, List<String> accountList) {
		
		List<BankingAuthorisedEntity> authorisedEntities = getMockAuthorisedEntities();

		List<BankingDirectDebit> accountDirectDebitAuthorisations = new ArrayList<BankingDirectDebit>();
		
		for(BankingAuthorisedEntity authorisedEntity: authorisedEntities)
		{
			BankingDirectDebit auth = BankingDirectDebit.getInstance(accountId, authorisedEntity);
			accountDirectDebitAuthorisations.add(auth);
		}
		
		ResponseBankingDirectDebitAuthorisationList returnDirectDebit = ResponseBankingDirectDebitAuthorisationList.getInstance(accountDirectDebitAuthorisations);
		
		return returnDirectDebit;
	}
	
	private List<BankingAuthorisedEntity> getMockAuthorisedEntities()
	{
		BankingAuthorisedEntity entity1 = new BankingAuthorisedEntity();
		entity1.setAbn("ABN-123-123");
		entity1.setAcn("ACN-123-123");
		entity1.setArbn("ARBN-123-123");
		entity1.setDescription("123-123 entity");
		entity1.setFinancialInstitution("123-123 Financial Institute");
		

		BankingAuthorisedEntity entity2 = new BankingAuthorisedEntity();
		entity2.setAbn("ABN-789-789");
		entity2.setAcn("ACN-789-789");
		entity2.setArbn("ARBN-789-789");
		entity2.setDescription("789-789 entity");
		entity2.setFinancialInstitution("789-789 Financial Institute");
		
		List<BankingAuthorisedEntity> returnList = new ArrayList<BankingAuthorisedEntity>();
		returnList.add(entity1);
		returnList.add(entity2);
		
		return returnList;
	}

	public ResponseBankingScheduledPaymentsList getAccountScheduledPayments(String userId, String accountId,
			List<String> accountList) {
		
		List<BankingScheduledPayment> scheduledPayments = new ArrayList<BankingScheduledPayment>();
		
		for(int count = 0; count < NUM_SCHEDULEDPAYMENTS; count++)
		{
			String scheduledPaymentId = UUID.nameUUIDFromBytes(("scheduledpayment_" + accountId + count).getBytes()).toString();
			String payerReference = UUID.nameUUIDFromBytes(("payerReference_" + accountId + count).getBytes()).toString();
			String payeeReference = UUID.nameUUIDFromBytes(("payeeReference_" + accountId + count).getBytes()).toString();
			String status = "ACTIVE";
			
			BankingScheduledPaymentFrom from = new BankingScheduledPaymentFrom(accountId);
			
			List<BankingScheduledPaymentSet> paymentSetList = new ArrayList<BankingScheduledPaymentSet>();
			
			for(int countSet = 0; countSet < NUM_PAYMENTSET; countSet++)
			{
				String toUType = "accountId";
				String toAccountId = UUID.nameUUIDFromBytes(("toAccountId_" + accountId + countSet).getBytes()).toString();
				
				BankingScheduledPaymentTo to = new BankingScheduledPaymentTo(toUType, toAccountId);
				boolean isAmountCalculated = true;
				String amount = "30.00";
				
				BankingScheduledPaymentSet paymentSet = new BankingScheduledPaymentSet(to, isAmountCalculated, amount);
				
				paymentSetList.add(paymentSet);
			}
			
			String recurrenceUType = "onceOff";
			BankingScheduledPaymentRecurrenceOnceOff onceOff = new BankingScheduledPaymentRecurrenceOnceOff("2022-12-25T15:43:00-08:00");
			
			BankingScheduledPaymentRecurrence recurrence = new BankingScheduledPaymentRecurrence(recurrenceUType, onceOff);
			
			BankingScheduledPayment payment = BankingScheduledPayment.getInstance(scheduledPaymentId, payerReference, payeeReference, status, from, paymentSetList, recurrence);
			
			scheduledPayments.add(payment);
		}

		ResponseBankingScheduledPaymentsList returnPayments = ResponseBankingScheduledPaymentsList.getInstance(scheduledPayments);
				
		return returnPayments;
	}

	public List<BankingPayee> getPayees(String userId) {
		
		List<BankingPayee> returnList = new ArrayList<BankingPayee>();
		
		for(int count = 0; count < NUM_PAYEES; count++)
		{
			String payeeId = UUID.nameUUIDFromBytes(("payeeId_" + count).getBytes()).toString();
			String nickname = "payee " + count;
			String type = "DOMESTIC";
			
			BankingPayee payee = new BankingPayee(payeeId, nickname, type);
			
			returnList.add(payee);
		}
		
		return returnList;
	}

	public BankingPayee getPayee(String userId, String payeeId) {
		
		List<BankingPayee> payees = getPayees(userId);
		
		for(BankingPayee payee : payees)
		{
			if(payee.getPayeeId().equals(payeeId))
				return payee;
		}
		
		throw new PayeeNotFoundException("Payee not found");
	}

}
