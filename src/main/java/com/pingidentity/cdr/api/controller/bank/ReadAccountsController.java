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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.pingidentity.cdr.api.controller.response.EntryResponse;
import com.pingidentity.cdr.api.controller.response.ListResponse;
import com.pingidentity.cdr.api.controller.utils.ControllerHelper;
import com.pingidentity.cdr.api.data.bankimpl.IBankDataAccess;
import com.pingidentity.cdr.api.exception.AccountNotFoundException;
import com.pingidentity.cdr.api.model.RuntimeConfiguration;
import com.pingidentity.cdr.api.model.bankimpl.BankingAccount;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingAccountList;

@Controller
public class ReadAccountsController {

	private static final String URL_ACCOUNT_LIST = "/cds-au/v{version}/banking/accounts";
	private static final String URL_ACCOUNT_DETAIL = "/cds-au/v{version}/banking/accounts/{accountId:^.*(?!balances)}";

	@Autowired
	private String baseUrl;

	@Autowired
	private IBankDataAccess bankDataAccess;
	
	private Boolean isIgnoreAccountHeaders = false;

	@PostMapping("/misc/configure/bank")
	public ResponseEntity<HttpStatus> configureController(
			@RequestBody RuntimeConfiguration runtimeConfiguration) {

		this.isIgnoreAccountHeaders = runtimeConfiguration.getIsIgnoreAccountHeaders();

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping(URL_ACCOUNT_LIST)
	public ResponseEntity<ListResponse<ResponseBankingAccountList>> accounts(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			@RequestParam(value = "open-status", required = false) String openStatus,
			@RequestParam(value = "product-category", required = false) String productCategory,
			@RequestParam(value = "is-owned", required = false) Boolean isOwned,
			HttpServletRequest request, HttpServletResponse response) {

		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);

		List<BankingAccount> accounts = this.isIgnoreAccountHeaders ? bankDataAccess.getAccounts(userId, null, openStatus, productCategory, isOwned)
				: bankDataAccess.getAccounts(userId, accountList, openStatus, productCategory, isOwned);

		ResponseBankingAccountList accountData = new ResponseBankingAccountList(accounts);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseBankingAccountList>>(
				ListResponse.getInstance(accountData, baseUrl + request.getRequestURI(), accounts.size()),
				HttpStatus.OK);

	}

	@GetMapping(URL_ACCOUNT_DETAIL)
	public ResponseEntity<EntryResponse<BankingAccount>> accountDetail(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@PathVariable(value = "accountId", required = true) String accountId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {
		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);

		BankingAccount account = bankDataAccess.getAccount(userId, accountId, accountList);

		EntryResponse<BankingAccount> responsePayload = EntryResponse.getInstance(account,
				baseUrl + request.getRequestURI());

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<EntryResponse<BankingAccount>>(responsePayload, HttpStatus.OK);

	}

}