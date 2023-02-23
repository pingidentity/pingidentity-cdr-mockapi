package com.pingidentity.cdr.api.data.bankimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pingidentity.cdr.api.model.bankimpl.BankingAccount;
import com.pingidentity.cdr.api.model.bankimpl.BankingTransaction;
import com.pingidentity.cdr.api.utils.CacheHelper;

public class BankMockUtils {

	private final static CacheHelper<BankingAccount> bankingAccountCacheHelper = new CacheHelper<BankingAccount>(BankingAccount.class);
	private final static CacheHelper<BankingTransaction> bankingAccountTransactionHelper = new CacheHelper<BankingTransaction>(BankingTransaction.class);

	public static List<BankingAccount> getAccountList(String userId, int number) {
		
		
		if(bankingAccountCacheHelper.isCached(userId))
		{
			return bankingAccountCacheHelper.getCachedObjects(userId);
		}
		
		List<BankingAccount> returnList = new ArrayList<BankingAccount>();

		String accountPrefix = userId.substring(0, 3) + " - ";

		for (int count = 1; count < number + 1; count++) {
			String accountId = UUID.nameUUIDFromBytes((userId + "_" + count).getBytes()).toString();
			String accountNumber = UUID.nameUUIDFromBytes((userId + "_accountNumber_" + count).getBytes()).toString();
			String displayName = accountPrefix + "Account Display " + count;
			String nickname = accountPrefix + "Account Nickname " + count;
			String openStatus = "OPEN";
			String maskedNumber = getMaskedNumber(accountId);
			String productCategory = "TRANS_AND_SAVINGS_ACCOUNTS";
			String productName = "Anybank Transaction";
			boolean isOwned = true;

			returnList.add(BankingAccount.getInstance(accountId, accountNumber, displayName, nickname, openStatus,
					maskedNumber, productCategory, productName, isOwned, getSimpleAddress()));
		}

		String accountId = UUID.nameUUIDFromBytes((userId + "_joint").getBytes()).toString();
		String accountNumber = UUID.nameUUIDFromBytes((userId + "_accountNumber_joint").getBytes()).toString();
		String displayName = accountPrefix + "Joint Account Display ";
		String nickname = accountPrefix + "Joint Account Nickname ";
		String openStatus = "OPEN";
		String maskedNumber = getMaskedNumber(accountId);
		String productCategory = "BUSINESS_LOANS";
		String productName = "Anybank Transaction";
		boolean isOwned = false;

		returnList.add(BankingAccount.getInstance(accountId, accountNumber, displayName, nickname, openStatus,
				maskedNumber, productCategory, productName, isOwned, getSimpleAddress()));

		String accountIdClosed = UUID.nameUUIDFromBytes((userId + "_closed").getBytes()).toString();
		String accountNumberClosed = UUID.nameUUIDFromBytes((userId + "_accountNumber_closed").getBytes()).toString();
		String displayNameClosed = accountPrefix + "Old Account Display ";
		String nicknameClosed = accountPrefix + "Old Account Nickname ";
		String openStatusClosed = "CLOSED";
		String maskedNumberClosed = getMaskedNumber(accountIdClosed);
		String productCategoryClosed = "RESIDENTIAL_MORTGAGES";
		String productNameClosed = "Anybank Transaction";
		boolean isOwnedClosed = true;

		returnList.add(BankingAccount.getInstance(accountIdClosed, accountNumberClosed, displayNameClosed, nicknameClosed, openStatusClosed,
				maskedNumberClosed, productCategoryClosed, productNameClosed, isOwnedClosed, getSimpleAddress()));

		bankingAccountCacheHelper.cacheObjects(userId, returnList);
		
		return returnList;
	}

	public static List<BankingTransaction> getAccountTransactionList(String userRef, String accountId,
			int numTransactions) {
		
		if(bankingAccountTransactionHelper.isCached(userRef))
		{
			return bankingAccountTransactionHelper.getCachedObjects(userRef);
		}

		List<BankingTransaction> returnList = new ArrayList<BankingTransaction>();

		for (int count = 1; count < numTransactions + 1; count++) {
			String transactionId = UUID.nameUUIDFromBytes((accountId + count).getBytes()).toString();
			String currency = "AUD";
			String type = "DIRECT_DEBIT";
			String status = "PENDING";
			String reference = "tx ref";
			String description = "tx desc";
			boolean isDetailAvailable = true;

			int intAmount = 0;
			for (Character character : transactionId.substring(0, 5).toCharArray()) {
				intAmount = intAmount + character;
			}

			String amount = intAmount + ".00";

			BankingTransaction accountTransaction = BankingTransaction.getInstance(accountId, transactionId, type,
					status, description, reference, amount, currency, isDetailAvailable);

			returnList.add(accountTransaction);
		}

		bankingAccountTransactionHelper.cacheObjects(userRef, returnList);

		return returnList;
	}
	
	private static List<Map<String, Object>> getSimpleAddress() {
		String mailingName = "Home Address";
		String addressLine1 = "Level 27/101 Collins St";
		String addressLine2 = null;
		String addressLine3 = null;
		String postCode = "3000";
		String city = "Melbourne";
		String state = "VIC";
		String country = "AUS";

		SimpleAddress simple = new SimpleAddress(mailingName, addressLine1, addressLine2, addressLine3,
				postCode, city, state, country);
		
		Map<String, Object> simpleAddress = new HashMap<String, Object>();
		simpleAddress.put("addressUType", "simple");
		simpleAddress.put("simple", simple);
		
		return Collections.singletonList(simpleAddress);
	}

	private static String getMaskedNumber(String value) {

		int number = 0;
		for (Character character : value.toCharArray())
			number = number + character;

		String numberString = String.valueOf(number);

		while (numberString.length() < 4)
			numberString = '0' + numberString;

		return "xxxxx-xxxxx-x" + numberString.substring(numberString.length() - 4);
	}

	static class SimpleAddress
	{
		private SimpleAddress()
		{
			
		}
		
		String mailingName;
		public String getMailingName() {
			return mailingName;
		}

		public String getAddressLine1() {
			return addressLine1;
		}

		public String getAddressLine2() {
			return addressLine2;
		}

		public String getAddressLine3() {
			return addressLine3;
		}

		public String getPostCode() {
			return postCode;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getCountry() {
			return country;
		}

		String addressLine1;
		String addressLine2;
		String addressLine3;
		String postCode;
		String city;
		String state;
		String country;
		
		SimpleAddress(String mailingName, String addressLine1, String addressLine2, String addressLine3,
				String postCode, String city, String state, String country)
		{
			this.mailingName = mailingName;
			this.addressLine1 = addressLine1;
			this.addressLine2 = addressLine2;
			this.addressLine3 = addressLine3;
			this.postCode = postCode;
			this.city = city;
			this.state = state;
			this.country = country;
		}
	}
}
