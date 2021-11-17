package com.revature.model;

import java.util.Objects;

public class Account {

	private int clientId;
	private int accountId;
	private int accountBalance;
	private int accountAge;
	
	public Account(int clientId, int accountId, int accountBalance, int accountAge) {
		super();
		this.clientId = clientId;
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.accountAge = accountAge;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getAccountAge() {
		return accountAge;
	}

	public void setAccountAge(int accountAge) {
		this.accountAge = accountAge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountAge, accountBalance, accountId, clientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountAge == other.accountAge && accountBalance == other.accountBalance && accountId == other.accountId
				&& clientId == other.clientId;
	}

	@Override
	public String toString() {
		return "Account [clientId=" + clientId + ", accountId=" + accountId + ", accountBalance=" + accountBalance
				+ ", accountAge=" + accountAge + "]";
	}
	
	
	
}