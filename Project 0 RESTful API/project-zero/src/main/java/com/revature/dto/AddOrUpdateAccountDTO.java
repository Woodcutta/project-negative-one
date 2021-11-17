package com.revature.dto;

import java.util.Objects;

public class AddOrUpdateAccountDTO {
	
	private int clientsId;
	private int balance;
	private int age;
//	public Object getBalance;
	
	public AddOrUpdateAccountDTO(int clientsId, int balance, int age) {
		super();
		this.clientsId = clientsId;
		this.balance = balance;
		this.age = age;
//		this.getBalance = getBalance;
	}

	public int getClientsId() {
		return clientsId;
	}

	public void setClientsId(int clientsId) {
		this.clientsId = clientsId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, balance, clientsId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateAccountDTO other = (AddOrUpdateAccountDTO) obj;
		return age == other.age && balance == other.balance && clientsId == other.clientsId;
	}

	@Override
	public String toString() {
		return "AddOrUpdateAccountDTO [clientsId=" + clientsId + ", balance=" + balance + ", age=" + age + "]";
	}

	
	
}

