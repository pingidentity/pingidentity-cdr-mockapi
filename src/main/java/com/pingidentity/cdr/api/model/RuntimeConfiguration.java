package com.pingidentity.cdr.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RuntimeConfiguration {

	@JsonProperty(required = false, defaultValue = "false", value = "ignore-account-headers")
	private Boolean isIgnoreAccountHeaders;

	public Boolean getIsIgnoreAccountHeaders() {
		return isIgnoreAccountHeaders;
	}

	public void setIsIgnoreAccountHeaders(Boolean isIgnoreAccountHeaders) {
		this.isIgnoreAccountHeaders = isIgnoreAccountHeaders;
	}
}
