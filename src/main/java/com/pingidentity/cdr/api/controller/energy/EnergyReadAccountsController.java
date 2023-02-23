package com.pingidentity.cdr.api.controller.energy;

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
import com.pingidentity.cdr.api.model.RuntimeConfiguration;
import com.pingidentity.cdr.api.model.energyimpl.EnergyAccount;
import com.pingidentity.cdr.api.model.energyimpl.ResponseEnergyAccountList;

@Controller
public class EnergyReadAccountsController {

	private static final String URL_ACCOUNT_LIST = "/cds-au/v{version}/energy/accounts";
	private static final String URL_ACCOUNT_DETAIL = "/cds-au/v{version}/energy/accounts/{accountId:^.*(?!balances)}";

	@Autowired
	private String baseUrl;

	@Autowired
	private IEnergyDataAccess energyDataAccess;
	
	private Boolean isIgnoreAccountHeaders = false;

	@PostMapping("/misc/configure/energy")
	public ResponseEntity<HttpStatus> configureController(
			@RequestBody RuntimeConfiguration runtimeConfiguration) {

		this.isIgnoreAccountHeaders = runtimeConfiguration.getIsIgnoreAccountHeaders();

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping(URL_ACCOUNT_LIST)
	public ResponseEntity<ListResponse<ResponseEnergyAccountList>> accounts(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) {

		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);

		List<EnergyAccount> accounts = this.isIgnoreAccountHeaders ? energyDataAccess.getAccounts(userId, null)
				: energyDataAccess.getAccounts(userId, accountList);

		ResponseEnergyAccountList accountData = new ResponseEnergyAccountList(accounts);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseEnergyAccountList>>(
				ListResponse.getInstance(accountData, baseUrl + request.getRequestURI(), accounts.size()),
				HttpStatus.OK);

	}

	@GetMapping(URL_ACCOUNT_DETAIL)
	public ResponseEntity<EntryResponse<EnergyAccount>> accountDetail(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@PathVariable(value = "accountId", required = true) String accountId,
			@RequestHeader(value = "X-ACCOUNTS", required = false) String[] selectedAccounts,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {
		List<String> accountList = ControllerHelper.getSelectedAccountList(selectedAccounts);

		EnergyAccount account = energyDataAccess.getAccount(userId, accountId, accountList);

		EntryResponse<EnergyAccount> responsePayload = EntryResponse.getInstance(account,
				baseUrl + request.getRequestURI());

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<EntryResponse<EnergyAccount>>(responsePayload, HttpStatus.OK);

	}

}