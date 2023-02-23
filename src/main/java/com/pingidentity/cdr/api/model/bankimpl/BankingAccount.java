package com.pingidentity.cdr.api.model.bankimpl;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pingidentity.cdr.api.model.IModel;

public class BankingAccount implements IModel {

	private String accountId;
	private String displayName;
	private String nickname;

	private String openStatus;
	private String maskedNumber;
	private String productCategory;
	private String productName;
	private String accountNumber;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> addresses;

    @JsonInclude(Include.NON_NULL)
	private String bsb;

    @JsonInclude(Include.NON_NULL)
	private String bundleName;

    @JsonInclude(Include.NON_NULL)
	private String depositRate;

    @JsonInclude(Include.NON_NULL)
	private String lendingRate;

    @JsonInclude(Include.NON_NULL)
	private String specificAccountUType;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> termDeposit;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> creditCard;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> loan;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> depositRates;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> lendingRates;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> features;

    @JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> fees;

	private BankingAccount()
	{
		
	}
	
	private BankingAccount(String accountId, String accountNumber, String displayName, String nickname, String openStatus, String maskedNumber, String productCategory, String productName, boolean isOwned, List<Map<String, Object>> addresses)
	{
		this.accountId = accountId;
		this.displayName = displayName;
		this.nickname = nickname;
		this.openStatus = openStatus;
		this.maskedNumber = maskedNumber;
		this.productCategory = productCategory;
		this.productName = productName;
		this.isOwned = isOwned;
		this.accountNumber = accountNumber;
		this.addresses = addresses;
	}
	
	public static BankingAccount getInstance(String accountId, String accountNumber, String displayName, String nickname, String openStatus, String maskedNumber, String productCategory, String productName, boolean isOwned, List<Map<String, Object>> addresses)
	{
		return new BankingAccount(accountId, accountNumber, displayName, nickname, openStatus, maskedNumber, productCategory, productName, isOwned, addresses);
	}
	
	public String getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(String depositRate) {
		this.depositRate = depositRate;
	}

	public String getLendingRate() {
		return lendingRate;
	}

	public void setLendingRate(String lendingRate) {
		this.lendingRate = lendingRate;
	}

	public List<Map<String, Object>> getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(List<Map<String, Object>> creditCard) {
		this.creditCard = creditCard;
	}

	public List<Map<String, Object>> getLoan() {
		return loan;
	}

	public void setLoan(List<Map<String, Object>> loan) {
		this.loan = loan;
	}

	public List<Map<String, Object>> getDepositRates() {
		return depositRates;
	}

	public void setDepositRates(List<Map<String, Object>> depositRates) {
		this.depositRates = depositRates;
	}

	public List<Map<String, Object>> getLendingRates() {
		return lendingRates;
	}

	public void setLendingRates(List<Map<String, Object>> lendingRates) {
		this.lendingRates = lendingRates;
	}

	public List<Map<String, Object>> getFeatures() {
		return features;
	}

	public void setFeatures(List<Map<String, Object>> features) {
		this.features = features;
	}

	public List<Map<String, Object>> getFees() {
		return fees;
	}

	public void setFees(List<Map<String, Object>> fees) {
		this.fees = fees;
	}

	public String getBsb() {
		return bsb;
	}

	public void setBsb(String bsb) {
		this.bsb = bsb;
	}

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public String getSpecificAccountUType() {
		return specificAccountUType;
	}

	public void setSpecificAccountUType(String specificAccountUType) {
		this.specificAccountUType = specificAccountUType;
	}

	public List<Map<String, Object>> getTermDeposit() {
		return termDeposit;
	}

	public void setTermDeposit(List<Map<String, Object>> termDeposit) {
		this.termDeposit = termDeposit;
	}

	private boolean isOwned;
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public void setMaskedNumber(String maskedNumber) {
		this.maskedNumber = maskedNumber;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAddresses(List<Map<String, Object>> addresses) {
		this.addresses = addresses;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public String getNickname() {
		return nickname;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public String getMaskedNumber() {
		return maskedNumber;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public String getProductName() {
		return productName;
	}

	@JsonProperty("isOwned")
	public boolean isOwned() {
		return isOwned;
	}

	public List<Map<String, Object>> getAddresses() {
		return addresses;
	}
	
	@Override
	public String toString()
	{
		return this.accountId;
	}
}
