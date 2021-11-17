package com.revature.app;

//import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.controller.ExceptionMappingController;
import com.revature.controller.ClientController;
import com.revature.controller.AccountController;
//import com.revature.dao.StudentDAO;
//import com.revature.dto.AddOrUpdateStudentDTO;
//import com.revature.model.Student;

import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {

		Javalin app = Javalin.create();
		
		Logger logger = LoggerFactory.getLogger(Application.class);
		
		app.before(ctx -> {
			logger.info(ctx.method() + " request received to the" + ctx.path() + "endpoint");
		});
		
		ClientController controller1 = new ClientController();
		AccountController controller2 = new AccountController();
		
		controller1.registerEndpoints(app);
		controller2.registerEndpoints(app);
		
		ExceptionMappingController exceptionController = new ExceptionMappingController();
		exceptionController.mapExceptions(app);
		
		app.start();
		
	}
	
}