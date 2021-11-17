package com.revature.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.NotFoundExceptions;
import com.revature.model.Client;

public class ClientServiceTest {

//	private ClientService sut;

	// Positive test (happy path)
	@Test
	public void testGetAllClientsPositive() throws SQLException {
		
		ClientDAO mockClientDao = mock(ClientDAO.class); 
		
		Client client1 = new Client(10, "Bob", "Jones");
		Client client2 = new Client(100, "John", "Doe");
		Client client3 = new Client(15, "Jane", "Doe");
		
		List<Client> clientsFromDao = new ArrayList<>();
		clientsFromDao.add(client1);
		clientsFromDao.add(client2);
		clientsFromDao.add(client3);
		
		when(mockClientDao.getAllClients()).thenReturn(clientsFromDao);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		List<Client> actual = clientService.getAllClients();
		
		List<Client> expected = new ArrayList<>();
		expected.add(new Client(10, "Bob", "Jones"));
		expected.add(new Client(100, "John", "Doe"));
		expected.add(new Client(15, "Jane", "Doe"));
		
		Assertions.assertEquals(expected, actual);
		
	}
	
	// Negative Test
	@Test
	public void testGetAllClientsSQLExceptionOccursNegative() throws SQLException {
	
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getAllClients()).thenThrow(SQLException.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		Assertions.assertThrows(SQLException.class, () -> {
			
			clientService.getAllClients();
			
		});
		
	}
	
	
	// Positive test (happy path)
	@Test
	public void testGetClientByIdPositive() throws SQLException, InvalidParameterException, NotFoundExceptions {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Bach", "Tran"));
		
		ClientService clientService = new ClientService(mockClientDao);
		
		Client actual = clientService.getClientById("5");
		
		Assertions.assertEquals(new Client(5, "Bach", "Tran"), actual);
	}
	
	// Negative Test
	@Test
	public void testGetStudentByIdNotFoundNegative() throws SQLException, InvalidParameterException, NotFoundExceptions {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		Assertions.assertThrows(NotFoundExceptions.class, () -> {
			clientService.getClientById("1"); // ACT
		});
		
	}
	
	// Negative Test
	// InvalidParameterException is thrown
	@Test
	public void testGetClientByIdAlphabeticalIdNegative() {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.getClientById("abc"); // ACT
		});
	}
	
	// Negative Test
	// InvalidParameterException is thrown
	@Test
	public void testGetClientByIdDecimalIdNegative() {
		/*
		 * ARRANGE
		 */
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		// By default, any object returned from one of the methods called from the mock student dao will return null
		// So here we are not specifying any when(...).thenReturn(...);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		/*
		 * ACT AND ASSERT
		 */
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.getClientById("1.0"); // ACT
		});
		
		
	}
	
	/*
	 * StudentService's editFirstNameOfStudent(String studentId, String changedName)
	 */
	
	// Positive (happy path)
	@Test
	public void testEditFirstNameOfClientPositive() throws SQLException, NotFoundExceptions, InvalidParameterException {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Jane", "Doe"));
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Ashley", "Doe");
		when(mockClientDao.updateClient(eq(5), eq(dto))).thenReturn(new Client(5, "Ashley", "Doe"));
		
		ClientService clientService = new ClientService(mockClientDao);
		
		Client actual = clientService.editFirstNameOfClient("5", "Ashley");
		
		Client expected = new Client(5, "Ashley", "Doe"); // We expect the firstName property to be Ashley at this point
		
		Assertions.assertEquals(expected, actual);
		
	}
	
	// Negative
	// StudentNotFoundException case
	@Test
	public void testEditFirstNameOfClientButClientWithId10DoesNotExist() {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		Assertions.assertThrows(NotFoundExceptions.class, () -> {
			
			clientService.editFirstNameOfClient("10", "Bill"); // ACT
			
		});
	}
	
	// Negative
	// InvalidParameterException thrown
	@Test
	public void testEditFirstNameButIdProvidedIsNotAnInt() {
		/*
		 * ARRANGE
		 */
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		/*
		 * ACT and ASSERT
		 */
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
			clientService.editFirstNameOfClient("abcsdfsdfdssf3434", "Test"); // ACT
			
		});
		
	}
	
	/*
	 * StudentService's addStudent(AddOrUpdateStudentDTO dto) method
	 */
	
	// Positive (happy path)
	@Test
	public void testAddClientAllInformationCorrectInDTO() throws NotFoundExceptions, SQLException, InvalidParameterException {
	
		ClientDAO clientDao = mock(ClientDAO.class);
		
		AddOrUpdateClientDTO dtoIntoDao = new AddOrUpdateClientDTO("Bob", "Williams");
		
		when(clientDao.addClient(eq(dtoIntoDao))).thenReturn(new Client(100, "Bob", "Williams"));
		
		ClientService clientService = new ClientService(clientDao);

		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Bob", "Williams");
		Client actual = clientService.addClient(dto);
		
		Client expected = new Client(100, "Bob", "Williams");
		Assertions.assertEquals(expected, actual);
		
	}
	
	// Negative
	// Scenario: everything is correct except the firstName was left blank
	@Test
	public void testAddClientFirstNameBlankEverythingElseValid() throws NotFoundExceptions, SQLException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
				
		ClientService clientService = new ClientService(clientDao);
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("      ", "Tran");
		
		Assertions.assertThrows(NotFoundExceptions.class, () -> {
			
			clientService.addClient(dto);
			
		});
		
	
	}
	
	// Negative
	// Scenario: everything is correct except the lastName was left blank
	@Test
	public void testAddClientLastNameBlankEverythingElseValid() throws NotFoundExceptions, SQLException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
				
		ClientService clientService = new ClientService(clientDao);
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Billy", "                 ");
		
		Assertions.assertThrows(NotFoundExceptions.class, () -> {
			
			clientService.addClient(dto);
			
		});
		
	
	}
	
	// Negative
	// Scenario: everything is correct except both the firstName and lastName were left blank
	@Test
	public void testAddClientFirstNameAndLastNameBlankEverythingElseValid() throws NotFoundExceptions, SQLException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
				
		ClientService clientService = new ClientService(clientDao);
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("", "                 ");
		
		Assertions.assertThrows(NotFoundExceptions.class, () -> {
			clientService.addClient(dto);
			
		});
		
	}
	
	// Negative
	// Scenario: everything is correct except classification was invalidly spelled
//	@Test
//	public void testAddClientFreshmanSpelledIncorrectlyEverythingElseValid() throws InvalidParameterException, SQLException {
		/*
		 * ARRANGE
		 */
//		StudentDAO studentDao = mock(StudentDAO.class);
				
//		StudentService studentService = new StudentService(studentDao);
		
		/*
		 * ACT and ASSERT
		 */
//		AddOrUpdateStudentDTO dto = new AddOrUpdateStudentDTO("Billy", "    Tran             ", "Freshmon", 5);
		
//		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
//			studentService.addStudent(dto);
			
//		});
		
	}
	
	// Negative
	// Scenario: everything is correct except age is negative
//	@Test
//	public void testAddStudentAgeIsNegativeEverythingElseValid() throws InvalidParameterException, SQLException {
		/*
		 * ARRANGE
		 */
//		ClientDAO clientDao = mock(ClientDAO.class);
				
//		ClientService clientService = new ClientService(clientDao);
		
//		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Billy", "    Tran             ", "Freshman", -100);
		
//		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
//			clientService.addClient(dto);
			
//		});
		
//	}
	
	

