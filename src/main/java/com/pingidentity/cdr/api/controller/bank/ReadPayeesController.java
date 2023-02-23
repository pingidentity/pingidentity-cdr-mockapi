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
import com.pingidentity.cdr.api.model.bankimpl.BankingPayee;
import com.pingidentity.cdr.api.model.bankimpl.ResponseBankingPayeeList;

@Controller
public class ReadPayeesController {

	private static final String URL_PAYEES = "/cds-au/v{version}/banking/payees";
	private static final String URL_PAYEE_DETAIL = "/cds-au/v{version}/banking/payees/{payeeId}";

	@Autowired
	private String baseUrl;

	@Autowired
	private IBankDataAccess bankDataAccess;

	@GetMapping(URL_PAYEES)
	public ResponseEntity<ListResponse<ResponseBankingPayeeList>> transactions(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			HttpServletRequest request, HttpServletResponse response) {
		
		List<BankingPayee> payees = bankDataAccess.getPayees(userId);

		ResponseBankingPayeeList payeeData = new ResponseBankingPayeeList(payees);

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<ListResponse<ResponseBankingPayeeList>>(
				ListResponse.getInstance(payeeData, baseUrl + request.getRequestURI(), payees.size()),
				HttpStatus.OK);

	}

	@GetMapping(URL_PAYEE_DETAIL)
	public ResponseEntity<EntryResponse<BankingPayee>> transactionDetail(
			@PathVariable(value = "version", required = true) String version,
			@RequestHeader(value = "x-fapi-interaction-id", required = false) String fapiInteractionId,
			@RequestHeader(value = "X-USER", required = true) String userId,
			@PathVariable(value = "payeeId", required = true) String payeeId,
			HttpServletRequest request, HttpServletResponse response) throws AccountNotFoundException {
		
		BankingPayee payee = bankDataAccess.getPayee(userId, payeeId);

		EntryResponse<BankingPayee> responsePayload = EntryResponse.getInstance(payee, baseUrl + request.getRequestURI());

		ControllerHelper.setResponseHeaders(response, version, fapiInteractionId);

		return new ResponseEntity<EntryResponse<BankingPayee>>(responsePayload, HttpStatus.OK);

	}

}