package com.revature.service;


import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.NotFoundExceptions;
import com.revature.model.Client;

public class ClientService {
	
	private Logger logger = LoggerFactory.getLogger(ClientService.class);

	private ClientDAO clientDao; 
	
	public ClientService(ClientDAO clientDao) {
	
		this.clientDao = clientDao;
		
	}
	
	public ClientService() {
		this.clientDao = new ClientDAO();
	}
	
	public Client editFirstNameOfClient(String clientId, String changedFirstName) throws SQLException, NotFoundExceptions, InvalidParameterException {
		
		try {
			int id = Integer.parseInt(clientId);
			
			Client clientToEdit = this.clientDao.getClientById(id);
			
			if (clientToEdit == null) {
				throw new NotFoundExceptions("Client with an id of " + clientId + " was not found");
			}
			
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedFirstName, clientToEdit.getLastName());
			
			Client updatedClient = this.clientDao.updateClient(id, dto);
			
			return updatedClient;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("clientId provided is not an int covertable value");
		}
		
	}

	public Client addClient(AddOrUpdateClientDTO dto) throws SQLException, InvalidParameterException {
		
		try {
			
			if (dto.getFirstName().trim().equals("")) {
				throw new NotFoundExceptions("Input not found for first name of client");
			}
			if (dto.getLastName().trim().equals("")) {
				throw new NotFoundExceptions("Input not found for last name of client");
			}
			
			dto.setFirstName(dto.getFirstName());
			dto.setLastName(dto.getLastName());
			
			Client addClient = this.clientDao.addClient(dto); 
			
			return addClient;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("clientId provided is not an int covertable value");
		}
	}

	public List<Client> getAllClients() throws SQLException{
		
		logger.info("getAllClients() invoked");
		
		List<Client> clients = this.clientDao.getAllClients();
		
		return clients;
	}

	public Client getClientById(String clientId) throws SQLException, InvalidParameterException, NotFoundExceptions {
		
		try {
			int id = Integer.parseInt(clientId);
			
			Client getClient = this.clientDao.getClientById(id);
			
			if (getClient == null) {
				throw new NotFoundExceptions("New client with an id of " + clientId + " was not added");
			}
			
			return getClient;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("ClientId provided is not an int covertable value");
		}
	}

	public Client editClientById(String clientId, String firstName, String lastName) throws SQLException, InvalidParameterException {
		
		try {
			int id = Integer.parseInt(clientId);
			
			Client clientToEdit = this.clientDao.getClientById(id);
			
			if (clientToEdit == null) {
				throw new NotFoundExceptions("New client with an id of " + clientId + " was not added");
			}
			
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(firstName, lastName);
			
			Client editClient = this.clientDao.editClient(id, dto);
			
			return editClient;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("clientId provided is not an int covertable value");
		}
	}

	public void deleteClientById(String clientId, String accountId) throws SQLException, InvalidParameterException {
		try {
			int id = Integer.parseInt(clientId);
			
			Client clientToDelete = this.clientDao.getClientById(id);
			
			if (clientToDelete == null) {
				throw new NotFoundExceptions("Was not able to delete " + clientId);
			}
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("clientId provided is not an int covertable value");
	}
	
}

	public void deleteAllAccountsById(String id) {
		
		
	}
}	
