package com.pingidentity.cdr.api.model.bankimpl;

import com.pingidentity.cdr.api.model.IModel;

public class BankingPayee implements IModel {

	private final String payeeId, nickname, type;
	
	public BankingPayee(String payeeId, String nickname, String type)
	{
		this.payeeId = payeeId;
		this.nickname = nickname;
		this.type = type;
	}

	public String getPayeeId() {
		return payeeId;
	}

	public String getNickname() {
		return nickname;
	}

	public String getType() {
		return type;
	}
}
