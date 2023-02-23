package com.pingidentity.cdr.api.model.energyimpl;

import java.util.List;

public class EnergyAccountPlan {
	private String nickname;
	private List<String> servicePointIds;
	private EnergyAccountPlanOverview planOverview;
	private EnergyAccountPlanDetail planDetail;
	
	private EnergyAccountPlan(String nickname, List<String> servicePointIds, EnergyAccountPlanOverview planOverview, EnergyAccountPlanDetail planDetail)
	{
		this.nickname = nickname;
		this.servicePointIds = servicePointIds;
		this.planOverview = planOverview;
		this.planDetail = planDetail;
	}
	
	public static EnergyAccountPlan getInstance(String nickname, List<String> servicePointIds, EnergyAccountPlanOverview planOverview, EnergyAccountPlanDetail planDetail)
	{
		return new EnergyAccountPlan(nickname, servicePointIds, planOverview, planDetail);
	}

	public String getNickname() {
		return nickname;
	}

	public List<String> getServicePointIds() {
		return servicePointIds;
	}

	public EnergyAccountPlanOverview getPlanOverview() {
		return planOverview;
	}

	public EnergyAccountPlanDetail getPlanDetail() {
		return planDetail;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setServicePointIds(List<String> servicePointIds) {
		this.servicePointIds = servicePointIds;
	}

	public void setPlanOverview(EnergyAccountPlanOverview planOverview) {
		this.planOverview = planOverview;
	}

	public void setPlanDetail(EnergyAccountPlanDetail planDetail) {
		this.planDetail = planDetail;
	}

	private EnergyAccountPlan()
	{
		
	}
	
}
