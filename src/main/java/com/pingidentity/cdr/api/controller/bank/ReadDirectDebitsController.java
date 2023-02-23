package com.pingidentity.cdr.api.controller.bank;

import java.util.ArrayList;
import java.util.Collections;
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

import com.pingidentity.cdr.api.controller.response.EntryResponse;
import com.pingidentity.cdr.api.controller.response.ListResponse;
import com.pingidentity.cdr.api.controller.utils.ControllerHelper;
import com.pingidentity.cdr.api.data.bankimpl.IBankDataAccess;
import com.pingidentity.cdr.api.exception.AccountNotFoundException;
import com.pingidentity.cdr.api.model.RequestAccountIds;
import com.pingidentity.cdr.api.model.bankimpl.BankingDirectDebit;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingDirectDebit;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingDirectDebitAuthorisationList;

@Controller
public class ReadDirectDebitsController {

	private static final String URL_ACCOUNT_DIRECTDEBITS = "/cds-au/v{version}/banking/accounts/{accountId}/direct-debits";
	private static final String URL_ACCOUNT_BULK_DIRECTDEBITS = "/cds-au/v{version}/banking/accounts/direct-debits";

	@Autowired
	private String baseUrl;

	@Autowired
	private IBankDataAccess bankDataAccess;

	@GetMapping(URL_ACCOUNT_DIRECTDEBITS)
	public ResponseEntity<ListResponse<ResponseBankingDirectDebit>> accountDirectDebits(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@PathVariable(value = "accountId", required = true) String accountId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {

		List<String> requestedAccountList = Collections.singletonList(accountId);
		
		return calculateAccountDirectDebits(version, fapiInteractionId, userId, selectedAccounts, requestedAccountList, request, response);

	}

	@GetMapping(URL_ACCOUNT_BULK_DIRECTDEBITS)
	public ResponseEntity<ListResponse<ResponseBankingDirectDebit>> accountBalances(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) {
		
		return calculateAccountDirectDebits(version, fapiInteractionId, userId, selectedAccounts, null, request, response);

	}

	@PostMapping(URL_ACCOUNT_BULK_DIRECTDEBITS)
	public ResponseEntity<ListResponse<ResponseBankingDirectDebit>> accountBalancesPost(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			@RequestBody EntryResponse<RequestAccountIds> requestedAccountIds,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<String> requestedAccountList = requestedAccountIds.getData().getAccountIds();
		
		return calculateAccountDirectDebits(version, fapiInteractionId, userId, selectedAccounts, requestedAccountList, request, response);

	}
	
	private ResponseEntity<ListResponse<ResponseBankingDirectDebit>> calculateAccountDirectDebits(
			String version,
			String fapiInteractionId,
			String userId,
			String[] selectedAccounts,
			List<String> requestedAccountList,
			HttpServletRequest request, HttpServletResponse response) {

		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);
		
		List<BankingDirectDebit> authorisations = new ArrayList<BankingDirectDebit>();
		
		for(String accountId : accountList)
		{
			if(requestedAccountList != null)
			{
				if(!requestedAccountList.contains(accountId))
					continue;
			}

			ResponseBankingDirectDebitAuthorisationList directDebit = bankDataAccess.getAccountDirectDebit(userId, accountId, accountList);
			
			for(BankingDirectDebit auth : directDebit.getDirectDebitAuthorisations())
				authorisations.add(auth);
		}

		ResponseBankingDirectDebit accountData = new ResponseBankingDirectDebit(authorisations);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseBankingDirectDebit>>(
				ListResponse.getInstance(accountData, baseUrl + request.getRequestURI(), authorisations.size()),
				HttpStatus.OK);

	}

}