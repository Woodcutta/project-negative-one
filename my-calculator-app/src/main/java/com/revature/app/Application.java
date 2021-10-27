package com.revature.app;

import com.revature.controller.ArithmeticController;
import com.revature.service.ArithmeticService;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/", Location.CLASSPATH);
		}); // we are using the create() static method that belongs to the Javalin class to create a Javalin object
		// This Javalin object is what gives us a "handle" on the Jetty webserver that will receive HTTP requests and send HTTP responses
		
		// Instantiate our Controller
		ArithmeticController controller1 = new ArithmeticController(new ArithmeticService());
		controller1.registerEndpoint(app);
		
		app.start(8080); // Start the server on port 8080!
	}

}
