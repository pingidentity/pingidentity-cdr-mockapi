package com.pingidentity.cdr.api.controller.bank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pingidentity.cdr.api.controller.response.EntryResponse;
import com.pingidentity.cdr.api.controller.response.ListResponse;
import com.pingidentity.cdr.api.controller.utils.ControllerHelper;
import com.pingidentity.cdr.api.data.bankimpl.IBankDataAccess;
import com.pingidentity.cdr.api.exception.AccountNotFoundException;
import com.pingidentity.cdr.api.model.bankimpl.BankingTransaction;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingTransactionList;

@Controller
public class ReadTransactionsController {

	private static final String URL_ACCOUNT_TRANSACTIONS = "/cds-au/v{version}/banking/accounts/{accountId}/transactions";
	private static final String URL_ACCOUNT_TRANSACTIONS_DETAIL = "/cds-au/v{version}/banking/accounts/{accountId}/transactions/{transactionId}";

	@Autowired
	private String baseUrl;

	@Autowired
	private IBankDataAccess bankDataAccess;

	@GetMapping(URL_ACCOUNT_TRANSACTIONS)
	public ResponseEntity<ListResponse<ResponseBankingTransactionList>> transactions(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			@PathVariable(value = "accountId", required = true) String accountId,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);
		
		if(!accountList.contains(accountId))
			throw new AccountNotFoundException("Account not found");
		
		List<BankingTransaction> transactions = bankDataAccess.getAccountTransactions(userId, accountId);

		ResponseBankingTransactionList accountData = new ResponseBankingTransactionList(transactions);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseBankingTransactionList>>(
				ListResponse.getInstance(accountData, baseUrl + request.getRequestURI(), transactions.size()),
				HttpStatus.OK);

	}

	@GetMapping(URL_ACCOUNT_TRANSACTIONS_DETAIL)
	public ResponseEntity<EntryResponse<BankingTransaction>> transactionDetail(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			@PathVariable(value = "accountId", required = true) String accountId,
			@PathVariable(value = "transactionId", required = true) String transactionId,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {
		
		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);
		
		if(!accountList.contains(accountId))
			throw new AccountNotFoundException("Account not found");
		
		BankingTransaction transaction = bankDataAccess.getAccountTransaction(userId, accountId, transactionId);

		EntryResponse<BankingTransaction> responsePayload = EntryResponse.getInstance(transaction, baseUrl + request.getRequestURI());

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<EntryResponse<BankingTransaction>>(responsePayload, HttpStatus.OK);

	}

}