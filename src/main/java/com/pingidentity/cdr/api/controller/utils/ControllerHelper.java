package com.pingidentity.cdr.api.controller.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

public class ControllerHelper {

	public static List<String> getSelectedAccountList(String[] selectedAccounts) {
		
		if(selectedAccounts == null)
			return null;

		List<String> returnList = new ArrayList<String>();

		for (String selectedAccount : selectedAccounts) {
			String[] selectedAccountSplit = selectedAccount.split(",");

			for (String selectedAccountSplitItem : selectedAccountSplit)
				returnList.add(selectedAccountSplitItem);
		}
		return returnList;
	}

	public static void setResponseHeaders(HttpServletResponse response, String version, String fapiInteractionId) {
		response.setHeader("x-v", version);
		
		if(fapiInteractionId == null || fapiInteractionId.trim().equals(""))
			fapiInteractionId = UUID.randomUUID().toString();
		
		response.setHeader("x-fapi-interaction-id", fapiInteractionId);
	}
}
