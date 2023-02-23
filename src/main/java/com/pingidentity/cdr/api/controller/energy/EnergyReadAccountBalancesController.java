package com.pingidentity.cdr.api.controller.energy;

import java.util.ArrayList;
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
import com.pingidentity.cdr.api.data.energyimpl.IEnergyDataAccess;
import com.pingidentity.cdr.api.exception.AccountNotFoundException;
import com.pingidentity.cdr.api.model.energyimpl.EnergyBalance;
import com.pingidentity.cdr.api.model.RequestAccountIds;
import com.pingidentity.cdr.api.model.energyimpl.ResponseEnergyBalanceList;

@Controller
public class EnergyReadAccountBalancesController {

	private static final String URL_ACCOUNT_BALANCE = "/cds-au/v{version}/energy/accounts/{accountId}/balance";
	private static final String URL_ACCOUNT_BALANCES = "/cds-au/v{version}/energy/accounts/balances";

	@Autowired
	private String baseUrl;

	@Autowired
	private IEnergyDataAccess energyDataAccess;

	@GetMapping(URL_ACCOUNT_BALANCE)
	public ResponseEntity<EntryResponse<EnergyBalance>> accountBalance(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@PathVariable(value = "accountId", required = true) String accountId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {
		
		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);

		EnergyBalance balance = energyDataAccess.getAccountBalance(userId, accountId, accountList);

		EntryResponse<EnergyBalance> responsePayload = EntryResponse.getInstance(balance, baseUrl + request.getRequestURI());

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<EntryResponse<EnergyBalance>>(responsePayload, HttpStatus.OK);

	}

	@GetMapping(URL_ACCOUNT_BALANCES)
	public ResponseEntity<ListResponse<ResponseEnergyBalanceList>> accountBalances(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) {
		
		return calculateAccountBalances(version, fapiInteractionId, userId, selectedAccounts, null, request, response);

	}

	@PostMapping(URL_ACCOUNT_BALANCES)
	public ResponseEntity<ListResponse<ResponseEnergyBalanceList>> accountBalancesPost(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			@RequestBody EntryResponse<RequestAccountIds> requestedAccountIds,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<String> requestedAccountList = requestedAccountIds.getData().getAccountIds();
		
		return calculateAccountBalances(version, fapiInteractionId, userId, selectedAccounts, requestedAccountList, request, response);

	}
	
	private ResponseEntity<ListResponse<ResponseEnergyBalanceList>> calculateAccountBalances(
			String version,
			String fapiInteractionId,
			String userId,
			String[] selectedAccounts,
			List<String> requestedAccountList,
			HttpServletRequest request, HttpServletResponse response) {

		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);
		
		List<EnergyBalance> balances = new ArrayList<EnergyBalance>();
		
		for(String accountId : accountList)
		{
			if(requestedAccountList != null)
			{
				if(!requestedAccountList.contains(accountId))
					continue;
			}
			
			balances.add(energyDataAccess.getAccountBalance(userId, accountId, accountList));
		}

		ResponseEnergyBalanceList accountData = new ResponseEnergyBalanceList(balances);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseEnergyBalanceList>>(
				ListResponse.getInstance(accountData, baseUrl + request.getRequestURI(), balances.size()),
				HttpStatus.OK);

	}

}