package com.revature.service;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.NotFoundExceptions;
import com.revature.model.Account;
import com.revature.model.Client;

public class AccountService {
	
	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	private AccountDAO accountDao; 
	
	public AccountService() {
		this.accountDao = new AccountDAO();
	}

	public Account editAccountBalance(String clientId, String accountId, Integer balance) throws SQLException, NotFoundExceptions {
		
		try {
			int id1 = Integer.parseInt(clientId);
			int id2 = Integer.parseInt(accountId);
			
			Account accountToEdit = this.accountDao.getAccountById(id2, id1);
			
			if (accountToEdit == null) {
				throw new NotFoundExceptions("Client with an id of " + clientId + " was not found");
			}
			
			AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO(id1, balance, accountToEdit.getAccountAge());
			
			Account updatedAccount = this.accountDao.updateAccount(id1, id2, dto);
			
			return updatedAccount;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("clientId provided is not an int covertable value");
		}
	}

	public Account addAccount(AddOrUpdateAccountDTO dto) throws SQLException, InvalidParameterException {
		
		try {
			
			dto.setBalance(dto.getBalance());
			dto.setAge(dto.getAge());
			
			Account addAccount = this.accountDao.addAccount(dto); 
			
			return addAccount;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("accountId provided is not an int covertable value");
		}
	}

	public List<Account> getAllAccounts() throws SQLException {
		
		logger.info("getAllAccounts() invoked");
		
		List<Account> accounts = this.accountDao.getAllAccounts();
		
		return accounts;
	}

	public Account getAccountById(String clientId, String accountId) throws SQLException, InvalidParameterException, NotFoundExceptions {
		
		try {
			
			int id1 = Integer.parseInt(accountId);
			int id2 = Integer.parseInt(clientId);
			
			Account getAccount = this.accountDao.getAccountById(id2, id1);
			
			if (getAccount == null) {
				throw new NotFoundExceptions("New client with an id of " + accountId + " was not added");
			}
			
			return getAccount;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("ClientId provided is not an int covertable value");
		}
	}

	public Account editAccountById(String accountId, String clientId, int balance, int age) throws SQLException {
		try {
			int id1 = Integer.parseInt(clientId);
			int id2 = Integer.parseInt(accountId);
			
			Account accountToEdit = this.accountDao.getAccountById(id2, id1);
			
			if (accountToEdit == null) {
				throw new NotFoundExceptions("New client with an id of " + accountId + " was not added");
			}
			
			AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO(id1, balance, age);
			
			Account editAccount = this.accountDao.updateAccount(id1, id2, dto);
			
			return editAccount;
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("clientId provided is not an int covertable value");
		}
	}

	public void deleteAccountById(String id) {
		// TODO Auto-generated method stub
		
	}
}
