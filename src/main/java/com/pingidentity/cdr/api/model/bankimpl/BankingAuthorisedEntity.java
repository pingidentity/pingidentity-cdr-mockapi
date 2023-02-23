package com.pingidentity.cdr.api.model.bankimpl;

import com.pingidentity.cdr.api.model.IModel;

public class BankingAuthorisedEntity implements IModel {

	private String description, financialInstitution, abn, acn, arbn;

	public BankingAuthorisedEntity()
	{
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinancialInstitution() {
		return financialInstitution;
	}

	public void setFinancialInstitution(String financialInstitution) {
		this.financialInstitution = financialInstitution;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}

	public String getAcn() {
		return acn;
	}

	public void setAcn(String acn) {
		this.acn = acn;
	}

	public String getArbn() {
		return arbn;
	}

	public void setArbn(String arbn) {
		this.arbn = arbn;
	}
}
