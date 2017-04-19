package io.softera.samples.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
	
	private Long accountId;
	
	private String accountName;
	
	private String accountAddress;
	
	@JsonCreator
	public Account(@JsonProperty("accountId") Long accountId, 
			@JsonProperty("accountName") String accountName, 
			@JsonProperty("accountAddress") String accountAddress) {
		
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountAddress = accountAddress;
	}

	public Long getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", accountAddress=" + accountAddress
				+ "]";
	}
}
