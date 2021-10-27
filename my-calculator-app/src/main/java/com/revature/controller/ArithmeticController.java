package com.revature.controller;

import com.revature.service.ArithmeticService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

// The purpose of the controller layer is to receive information from a request
// We don't want to do actual data processing inside the controller layer
// 		That is the role of the service layer
public class ArithmeticController {

	public ArithmeticService arithmeticService;
	
	// Constructor
	public ArithmeticController(ArithmeticService arithmeticService) {
		this.arithmeticService = arithmeticService;
	}
	
	// This is what is known as a lambda
	// Think of it as similar to a method, but it is a method that can be passed around
	public Handler add = (ctx) -> {
		ctx.result("add lambda invoked");
		
		// Double class
		// the Double class has a static method called parseDouble that can take a String and return a double primitive representation of that String
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		// String + double -> String + String = String
		ctx.result(arithmeticService.doAddition(number1String, number2String)); // Because number1 and number2 are just Strings, this will do String concatentation and not our actual adding of numbers
	};
	
	// Define an instance method here
//	public void registerEndpoint(Javalin app) {
//		app.post("/add", add); // We are mapping the add lambda, which will be invoked whenever a client sends an HTTP POST request to "/add"
//	}
	
	public Handler sub = (ctx) -> {
		ctx.result("add lambda invoked");
		
	
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		ctx.result(arithmeticService.doSubtraction(number1String, number2String)); // Because number1 and number2 are just Strings, this will do String concatentation and not our actual adding of numbers
	};
	

	public Handler mult = (ctx) -> {
		ctx.result("add lambda invoked");
		
	
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		ctx.result(arithmeticService.doMultiplication(number1String, number2String)); 
	};
	
	
	public Handler div = (ctx) -> {
		ctx.result("add lambda invoked");
		
	
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		
		ctx.result(arithmeticService.doDivision(number1String, number2String)); 
	};
	
	public void registerEndpoint(Javalin app) {
		app.post("/add", add);
		app.post("/sub", sub);
		app.post("/mult", mult);
		app.post("/div", div);
		
	}
}
