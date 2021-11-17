package com.revature.controller;

import java.util.List;
import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.service.AccountService;
import com.revature.exceptions.NotFoundExceptions;
import com.revature.model.Account;


import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController {
	
private AccountService accountService; 
	
	public AccountController () {
		this.accountService = new AccountService();
		
	}
	
	private Handler editAccountBalance = (ctx) -> {
		
		// extract the id from the URI path
//		String studentId = ctx.pathParam("id");
		
		String clientId = ctx.pathParam("id");
		String accountId = ctx.pathParam("account_id");
		
	
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);
		
		try {
			Account accountThatWasJustEdited = this.accountService.editAccountBalance(clientId, accountId, dto.getBalance());
			
			ctx.json(accountThatWasJustEdited);
		} catch (NotFoundExceptions e) {
			throw new NotFoundExceptions("Account not found");
		}
		
	};
	
	private Handler addAccount = (ctx) -> {
		
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);
		
		Account a = this.accountService.addAccount(dto);
		
		ctx.json(a);
		ctx.status(201); // 201 created
		
	};
	
//	private Handler getAllAccounts = (ctx) -> {
		
//		List<Account> accounts = this.accountService.getAllAccounts();
		
//		ctx.json(accounts);
//	};

	private Handler getAccountById = (ctx) -> {
		
		String id1 = ctx.pathParam("id");
		String id2 = ctx.pathParam("account_id");
		
		Account a = this.accountService.getAccountById(id1, id2);
		
		ctx.json(a);
		
	};

	private Handler editAccountById = (ctx) -> {
		
		String id1 = ctx.pathParam("id");
		String id2 = ctx.pathParam("account_id");
		
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);
		
		Account a = this.accountService.editAccountById(id2, id1, dto.getBalance(), dto.getAge());
		
		ctx.json(a);
	};

	private Handler deleteAccountById = (ctx) -> {
		
		String id = ctx.pathParam("id");
		
		this.accountService.deleteAccountById(id);
	
	};

	private Handler deleteAllAccounts = (ctx) -> {
	
	};
	
	
/*	private Handler getAccountsBetweenFourHundredAndTwoThousand = (ctx) -> {
		
		try {
			
			int id = Integer.parseInt(clientId);
			
			if(ctx.queryParam("moreThan") != null && ctx.queryParam("lessThan") != null) {
				
				int moreThan = Integer.parseInt(ctx.queryParam("moreThan"));
				int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
				
				account = this.accountDao.getAllAccountsByCliendId(id, moreThan, lessThan);
				
			} else if (ctx.queryParam("moreThan") != null) {
			
				int moreThan = Integer.parseInt(ctx.queryParam("moreThan"));
				
				account = this.accountDao.getAllAccountsByClientId(id, moreThan, 1000000);
				
			} else if (ctx.queryParam("lessThan") != null ) {
			
				int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
				
				account = this.accountDao.getAllAccountsByClientId(id, 0, lessThan);
				
			}  else {
				
				account = this.accountDao.getAllAccountsByClientId(id, 0, 1000000);
			
			}
			
			return account;
		
			}
			catch (NotFoundExceptions e) {
				throw new NotFoundExceptions("Client id not found");
			}*/
//	};	
	
	public void registerEndpoints(Javalin app) {
		app.post("/clients/{id} accounts", addAccount);
//		app.get("/clients/{id}/accounts", getAllAccounts);
		//app.get("/clients/{id}/accounts/{account_id}", getAccountsBetweenFourHundredAndTwoThousand);
		app.get("/clients/{id}/accounts/{account_id}", getAccountById);
		app.put("/clients/{id}/accounts/{account_id}", editAccountById);
		app.delete("/clients/{id}/accounts/{account_id}", deleteAccountById);
	}
}
