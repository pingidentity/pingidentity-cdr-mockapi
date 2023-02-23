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
import com.pingidentity.cdr.api.model.bankimpl.BankingScheduledPayment;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingScheduledPaymentsList;

@Controller
public class ReadScheduledPaymentsController {

	private static final String URL_ACCOUNT_DIRECTDEBITS = "/cds-au/v{version}/banking/accounts/{accountId}/payments/scheduled";
	private static final String URL_ACCOUNT_BULK_DIRECTDEBITS = "/cds-au/v{version}/banking/payments/scheduled";

	@Autowired
	private String baseUrl;

	@Autowired
	private IBankDataAccess bankDataAccess;

	@GetMapping(URL_ACCOUNT_DIRECTDEBITS)
	public ResponseEntity<ListResponse<ResponseBankingScheduledPaymentsList>> accountScheduledPayments(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@PathVariable(value = "accountId", required = true) String accountId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {
		
		List<String> requestedAccountList = Collections.singletonList(accountId);
		
		return calculateAccountScheduledPayments(version, fapiInteractionId, userId, selectedAccounts, requestedAccountList, request, response);

	}

	@GetMapping(URL_ACCOUNT_BULK_DIRECTDEBITS)
	public ResponseEntity<ListResponse<ResponseBankingScheduledPaymentsList>> accountScheduledPayment(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) {
		
		return calculateAccountScheduledPayments(version, fapiInteractionId, userId, selectedAccounts, null, request, response);

	}

	@PostMapping(URL_ACCOUNT_BULK_DIRECTDEBITS)
	public ResponseEntity<ListResponse<ResponseBankingScheduledPaymentsList>> accountScheduledPaymentPost(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			@RequestBody EntryResponse<RequestAccountIds> requestedAccountIds,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<String> requestedAccountList = requestedAccountIds.getData().getAccountIds();
		
		return calculateAccountScheduledPayments(version, fapiInteractionId, userId, selectedAccounts, requestedAccountList, request, response);

	}
	
	private ResponseEntity<ListResponse<ResponseBankingScheduledPaymentsList>> calculateAccountScheduledPayments(
			String version,
			String fapiInteractionId,
			String userId,
			String[] selectedAccounts,
			List<String> requestedAccountList,
			HttpServletRequest request, HttpServletResponse response) {

		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);
		
		List<BankingScheduledPayment> payments = new ArrayList<BankingScheduledPayment>();
		
		for(String accountId : accountList)
		{
			if(requestedAccountList != null)
			{
				if(!requestedAccountList.contains(accountId))
					continue;
			}

			ResponseBankingScheduledPaymentsList scheduledPayments = bankDataAccess.getAccountScheduledPayments(userId, accountId, accountList);
			
			for(BankingScheduledPayment payment : scheduledPayments.getScheduledPayments())
				payments.add(payment);
		}

		ResponseBankingScheduledPaymentsList accountData = new ResponseBankingScheduledPaymentsList(payments);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseBankingScheduledPaymentsList>>(
				ListResponse.getInstance(accountData, baseUrl + request.getRequestURI(), payments.size()),
				HttpStatus.OK);

	}

}