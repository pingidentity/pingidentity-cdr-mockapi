package com.pingidentity.cdr.api.data.energyimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pingidentity.cdr.api.model.energyimpl.EnergyAccount;
import com.pingidentity.cdr.api.model.energyimpl.EnergyAccountPlanDetail;
import com.pingidentity.cdr.api.model.energyimpl.EnergyAccountPlanDetailAuthorisedContacts;
import com.pingidentity.cdr.api.model.energyimpl.EnergyAccountPlanOverview;
import com.pingidentity.cdr.api.utils.CacheHelper;

public class EnergyMockUtils {
	private static final int NUM_PLANS = 3;

	private final static CacheHelper<EnergyAccount> accountCacheHelper = new CacheHelper<EnergyAccount>(EnergyAccount.class);

	public static List<EnergyAccount> getAccountList(String userId, int number) {
		if(accountCacheHelper.isCached(userId))
		{
			return accountCacheHelper.getCachedObjects(userId);
		}
		
		List<EnergyAccount> returnList = new ArrayList<EnergyAccount>();

		String accountPrefix = userId.substring(0, 3) + " - ";

		for (int count = 1; count < number + 1; count++) {
			String accountId = UUID.nameUUIDFromBytes((userId + "_" + count).getBytes()).toString();
			String accountNumber = UUID.nameUUIDFromBytes((userId + "_accountNumber_" + count).getBytes()).toString();
			String displayName = accountPrefix + "Account Display " + count;
			String openStatus = "OPEN";
			
			returnList.add(EnergyAccount.getInstance(accountId, openStatus, accountNumber, displayName, "2007-05-01", getPlans(accountPrefix)));
		}

		accountCacheHelper.cacheObjects(userId, returnList);

		return returnList;
	}

	private static List<Map<String, Object>> getPlans(String accountPrefix) {
		List<Map<String, Object>> plans = new ArrayList<Map<String, Object>>();
		
		for(int count = 0; count < NUM_PLANS; count++)
		{
			String nickname = accountPrefix + "Account Nickname " + count;
			List<String> servicePointIds = new ArrayList<String>();
			
			EnergyAccountPlanOverview planOverview = getPlanOverview(accountPrefix, count);
			EnergyAccountPlanDetail planDetail = getPlanDetail(accountPrefix, count);
			
			Map<String, Object> plan = new HashMap<String, Object>();
			plan.put("nickname", nickname);
			plan.put("servicePointIds", servicePointIds);
			plan.put("planOverview", planOverview);
			plan.put("planDetail", planDetail);
			
			plans.add(plan);
		}
		
		return plans;
	}
	
	private static EnergyAccountPlanOverview getPlanOverview(String accountPrefix, int count)
	{
		String displayName = "Plan " + accountPrefix + " " + count;
		String startDate = "2007-03-01";
		String endDate = "2037-03-01";
		
		return EnergyAccountPlanOverview.getInstance(displayName, startDate, endDate);
		
	}
	
	private static EnergyAccountPlanDetail getPlanDetail(String accountPrefix, int count)
	{
		String fuelType = "ELECTRICITY";
		boolean isContingentPlan = false;
		
		EnergyAccountPlanDetailAuthorisedContacts authorisedContacts = EnergyAccountPlanDetailAuthorisedContacts.getInstance("Tam", "Tran", "", "");
		
		return EnergyAccountPlanDetail.getInstance(fuelType, isContingentPlan, Collections.singletonList(authorisedContacts));

	}


}
