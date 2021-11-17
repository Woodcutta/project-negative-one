package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.model.Client;
import com.revature.util.JDBCUtility;

public class ClientDAO {
	
	public Client addClient(AddOrUpdateClientDTO client) throws SQLException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO clients (clients_first_name, clients_last_name) " + " VALUES(?, ?)";
		
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			pstmt.setString(1, client.getFirstName());
			pstmt.setString (2, client.getLastName());
		
			int numberOfRecordsInserted = pstmt.executeUpdate(); // Instead of executeQuery, like we use for SELECT statements, INSERT, 
			
			if(numberOfRecordsInserted != 1) {
				throw new SQLException("Adding a new Client was unsuccessful");
	
			}
		
			ResultSet rs = pstmt.getGeneratedKeys();
		
			rs.next(); 
			int automaticallyGeneratedId = rs.getInt(1);
		
			return new Client(automaticallyGeneratedId, client.getFirstName(), client.getLastName());
		}
		
	}
	
	public List<Client> getAllClients() throws SQLException {
		
		List<Client> listOfClients = new ArrayList<>();

		try (Connection con = JDBCUtility.getConnection()) {
			
			String sql = "SELECT * FROM clients";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			
			ResultSet rs = pstmt.executeQuery(); // this returns a ResultSet object.

			while (rs.next()) { 
			
				int id = rs.getInt("clients_id");
				String firstName = rs.getString("clients_first_name");
				String lastName = rs.getString("clients_last_name");
			
				Client c = new Client(id, firstName, lastName);
				
				listOfClients.add(c);
			}
		}
		
		return listOfClients;
	}
	
	public Client getClientById(int id) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM clients WHERE clients_id = ?"; 
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id); // pass the value of the id variable into the 1st question mark (there is only 1 question mark)
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Client(rs.getInt("clients_id"), rs.getString("clients_first_name"), rs.getString("clients_last_name"));
			} else {
				return null;
			}
		}
		
	}
	
	
	public Client updateClient(int clientId, AddOrUpdateClientDTO client) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE clients "
						+ "SET clients_first_name = ?,"
						+ "    clients_last_name = ?,"
						+ "WHERE clients_id = ?,";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setInt(3, clientId);
			
			int numberOfRecordsUpdated = pstmt.executeUpdate();
			
			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to update client record w/ id of " + clientId);
			}
		}
		
		return new Client(clientId, client.getFirstName(), client.getLastName());
			
		}
		
	
	public void deleteStudentById(int id) throws SQLException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM students WHERE client_id = ?,";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted!= 1) {
				throw new SQLException("Unable to delete client record w/ id of " + id);
			}
		}
		
	}
	
	public void deleteAllClients() throws SQLException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM clients ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted == 0) {
				throw new SQLException("Unable to delete all clients (check if records exist in the table) ");
			}
		}
		
	}

	public Client editClient(int clientId, AddOrUpdateClientDTO dto) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "EDIT clients "
						+ "SET clients_first_name = ?,"
						+ "    clients_last_name = ?,"
						+ "WHERE clients_id = ?,";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getFirstName());
			pstmt.setString(2, dto.getLastName());
			pstmt.setInt(5, clientId);
			
			int numberOfRecordsUpdated = pstmt.executeUpdate();
			
			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to edit client record w/ id of " + clientId);
			}
			
			return new Client(clientId, dto.getFirstName(), dto.getLastName());
			
			
		}
	}
}

