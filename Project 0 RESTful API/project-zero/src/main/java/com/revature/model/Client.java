package com.revature.model;

import java.util.Objects;

public class Client {

	private int clientId;
	private String clientFirstName;
	private String clientLastName;
	
	public Client() {
		
	}
	
	public Client(int clientId, String clientFirstName, String clientLastName) {
		
		this.clientId = clientId;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
		
	}
	
	public int getId() {
		return clientId;
	}
	
	public void setId(int id) {
		this.clientId = id;
	}

	public String getFirstName() {
		return clientFirstName;
	}
	
	public void setFirstName() {
		this.clientFirstName = clientFirstName;
	}
	
	public String getLastName() {
		return clientLastName;
	}
	
	public void setLastName() {
		this.clientFirstName = clientLastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientFirstName, clientId, clientLastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(clientFirstName, other.clientFirstName) && clientId == other.clientId
				&& Objects.equals(clientLastName, other.clientLastName);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientFirstName=" + clientFirstName + ", clientLastName="
				+ clientLastName + "]";
	}
	
}
