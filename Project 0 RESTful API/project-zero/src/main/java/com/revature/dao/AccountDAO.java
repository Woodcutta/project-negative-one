package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.model.Account;
import com.revature.util.JDBCUtility;

public class AccountDAO {
	
	public Account addAccount(AddOrUpdateAccountDTO account) throws SQLException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO accounts (accounts_balance, accounts_age, clients_id) " + " VALUES(?, ?, ?)";
		
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			pstmt.setInt(1, account.getBalance());
			pstmt.setInt(2, account.getAge());
			pstmt.setInt(3, account.getClientsId());
			int numberOfRecordsInserted = pstmt.executeUpdate(); // Instead of executeQuery, like we use for SELECT statements, INSERT, 
			
			if(numberOfRecordsInserted != 1) {
				throw new SQLException("Adding a new Account was unsuccessful");
	
			}
		
			ResultSet rs = pstmt.getGeneratedKeys();
		
			rs.next(); // iterating to the first "row"
			int automaticallyGeneratedId = rs.getInt(1); // grabbing the first columns' information from that "row"
		
			return new Account(account.getClientsId(), automaticallyGeneratedId, account.getBalance(), account.getAge());
		}
		
	}
	
	public List<Account> getAllAccounts() throws SQLException {
		
		List<Account> listOfAccount = new ArrayList<>();

		try (Connection con = JDBCUtility.getConnection()) {
			
			String sql = "SELECT * FROM accounts";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			
			ResultSet rs = pstmt.executeQuery(); // this returns a ResultSet object.

			while (rs.next()) { 
			
				int id1 = rs.getInt("clients_id");
				int id2 = rs.getInt("accounts_id");
				int balance = rs.getInt("accounts_balance");
				int age = rs.getInt("accounts_age");
			
				Account a = new Account(id1, id2, balance, age);
				
				listOfAccount.add(a);
			}
		}
		
		return listOfAccount;
	}
	
	public Account getAccountById(int clientId, int accountId) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE accounts_id = ? AND clients_id = ?"; 
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, accountId); // pass the value of the id variable into the 1st question mark (there is only 1 question mark)
			pstmt.setInt(2, clientId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Account(rs.getInt("clients_id"), rs.getInt("accounts_id"), rs.getInt("accounts_balance"), rs.getInt("accounts_age"));
			} else {
				return null;
			}
		}
		
	}
	
	// Update student will return a Student object corresponding to the record that was updated, and takes in 2 arguments corresponding with the studentId
	// whose row we would like to update, and the AddOrUpdateStudentDTO object containing the properties of what we want to update that row with
	public Account updateAccount(int clientId, int accountId, AddOrUpdateAccountDTO account) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE accounts "
						+ "SET accounts_balance = ?,"
						+ "    accounts_age = ?,"
						+ "    clients_id = ?,"
						+ "WHERE "
						+ "accounts_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, account.getBalance());
			pstmt.setInt(2, account.getAge());
			pstmt.setInt(3, clientId);
			pstmt.setInt(4, accountId);
			
			int numberOfRecordsUpdated = pstmt.executeUpdate();
			
			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to update accounts record w/ id of " + accountId);
			}
		}
		
		return new Account(clientId, accountId, account.getBalance(), account.getAge());
			
		}
		
	
	public void deleteAccountById(int id) throws SQLException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM accounts WHERE client_id = ?,";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted!= 1) {
				throw new SQLException("Unable to delete account record w/ id of " + id);
			}
		}
		
	}
	
	public void deleteAllAccounts() throws SQLException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM accounts ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted == 0) {
				throw new SQLException("Unable to delete account record w/ id of ");
			}
		}
		
	}
	
	
	
}
