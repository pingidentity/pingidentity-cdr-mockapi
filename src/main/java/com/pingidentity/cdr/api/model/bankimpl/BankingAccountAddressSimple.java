package com.pingidentity.cdr.api.model.bankimpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BankingAccountAddressSimple {
	private String addressUType;
	private SimpleAddress simple;
	
	private BankingAccountAddressSimple()
	{
		
	}
	
	private BankingAccountAddressSimple(SimpleAddress simple)
	{
		this.addressUType = "simple";
		this.simple = simple;
	}
	
	public static BankingAccountAddressSimple getInstance(String mailingName, String addressLine1, String addressLine2, String addressLine3,
			String postCode, String city, String state, String country)
	{
		SimpleAddress simple = new SimpleAddress(mailingName, addressLine1, addressLine2, addressLine3,
				postCode, city, state, country);
		
		return new BankingAccountAddressSimple(simple);
	}
	
	public String getAddressUType() {
		return addressUType;
	}

	public SimpleAddress getSimple() {
		return simple;
	}

	static class SimpleAddress
	{
		private SimpleAddress()
		{
			
		}
		
		public String getMailingName() {
			return mailingName;
		}

		public String getAddressLine1() {
			return addressLine1;
		}

		public String getAddressLine2() {
			return addressLine2;
		}

		public String getAddressLine3() {
			return addressLine3;
		}

		public String getPostCode() {
			return postCode;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getCountry() {
			return country;
		}

	    @JsonInclude(Include.NON_NULL)
		String mailingName;

	    @JsonInclude(Include.NON_NULL)
		String addressLine1;

	    @JsonInclude(Include.NON_NULL)
		String addressLine2;

	    @JsonInclude(Include.NON_NULL)
		String addressLine3;

	    @JsonInclude(Include.NON_NULL)
		String postCode;

	    @JsonInclude(Include.NON_NULL)
		String city;

	    @JsonInclude(Include.NON_NULL)
		String state;

	    @JsonInclude(Include.NON_NULL)
		String country;
		
		SimpleAddress(String mailingName, String addressLine1, String addressLine2, String addressLine3,
				String postCode, String city, String state, String country)
		{
			this.mailingName = mailingName;
			this.addressLine1 = addressLine1;
			this.addressLine2 = addressLine2;
			this.addressLine3 = addressLine3;
			this.postCode = postCode;
			this.city = city;
			this.state = state;
			this.country = country;
		}
	}
	
	
}
