package controllers;

import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {

        Seeds.seedData();

        staticFileLocation("/public");

        EmployeesController employeesController = new EmployeesController();
        DepartmentsController departmentsController = new DepartmentsController();
        ManagersController managersController = new ManagersController();
        EngineersController engineersController = new EngineersController();
        LoginController loginController = new LoginController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String loggedInUser = LoginController.getLoggedInUsername(req, res);
            model.put("user", loggedInUser);

            model.put("template","templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
