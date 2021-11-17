package com.revature.controller;

import java.util.List;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.service.ClientService;
import com.revature.exceptions.NotFoundExceptions;
import com.revature.model.Client;


import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientController {
	
private ClientService clientService; 
	
	public ClientController () {
		this.clientService = new ClientService();
		
	}
	
	private Handler editClientFirstName = (ctx) -> {
		
		// extract the id from the URI path
//		String studentId = ctx.pathParam("id");
		
		String clientId = ctx.pathParam("id");
		
	
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
	try {
			Client clientThatWasJustEdited = this.clientService.editFirstNameOfClient(clientId, dto.getFirstName());
			
			ctx.json(clientThatWasJustEdited);
		} catch (NotFoundExceptions e) {
			ctx.status(404);
			ctx.json(e);
		}
		
	};
	
	private Handler addClient = (ctx) -> {
		
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client c = this.clientService.addClient(dto);
		
		ctx.json(c);
		ctx.status(201); // 201 created
		
	};
	
	private Handler getAllClients = (ctx) -> {
		
		List<Client> clients = this.clientService.getAllClients();
		
		ctx.json(clients);
	};

	private Handler getClientById = (ctx) -> {
		
		String id = ctx.pathParam("id");
		
		Client c = this.clientService.getClientById(id);
		
		ctx.json(c);
		
	};

	private Handler editClientById = (ctx) -> {
		
		String id = ctx.pathParam("id");
		
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client c = this.clientService.editClientById(id, dto.getFirstName(), dto.getLastName());
		
		ctx.json(c);
	};

	private Handler deleteClientById = (ctx) -> {
		
		String id = ctx.pathParam("id");
		this.clientService.deleteAllAccountsById(id);
		this.clientService.deleteClientById(id, id);
	
	};

	private Handler deleteAllClients = (ctx) -> {
	
	};
	
	public void registerEndpoints(Javalin app) {
		app.patch("/clients/{id}/firstname", editClientFirstName);
		app.post("/clients", addClient);
		app.get("/clients", getAllClients);
		app.get("clients/{id}", getClientById);
		app.put("/clients/{id}", editClientById);
		app.delete("/clients/{id}", deleteClientById);
		app.delete("/clients", deleteAllClients);
		
	}

}