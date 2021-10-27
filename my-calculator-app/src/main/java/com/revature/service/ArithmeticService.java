package com.revature.service;

// The service layer is responsible for the processing of data. We would perform business logic inside of the service layer.
// Ex. imagine you are developing the backend for facebook. The service layer of Facebook's backend would contain business logic related to providing friend
// recommendations, ad recommendations, etc. for a particular Facebook user.
// All sorts of complex algorithms could be developed, and those would ultimately be part of this "service" layer.
// Sometimes, the service layer will be pretty simple (especially for CRUD applications)
// CRUD = Create, Read, Update, Delete
// CRUD applications are applications that are primarily designed to keep records of data
public class ArithmeticService {

	public String doAddition(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);
		
		double sum = number1 + number2;
		
		String result = "" + sum; // Convert from double representation of a number to a String representation
		
		return result;
		
	}	
	public String doSubtraction(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);
			
		double sub = number1 - number2;
			
		String result = "" + sub;
			
		return result;
	}	
	
	public String doMultiplication(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);
			
		double mult = number1 * number2;
			
		String result = "" + mult;
			
		return result;
	}
	
	public String doDivision(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);
			
		double div = number1 / number2;
			
		String result = "" + div;
			
		return result;
	}	
}