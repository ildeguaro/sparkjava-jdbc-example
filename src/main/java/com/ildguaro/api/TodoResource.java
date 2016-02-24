package com.ildguaro.api;
import static spark.Spark.*;


import com.google.gson.Gson;
import com.ildeguaro.dominio.ToDo;
import com.ildeguaro.jdbc.dao.ToDoDAO;

import spark.Request;
import spark.Response;
import spark.Route;

public class TodoResource {
	
	public static void main(String[] args) {			
		port(5000);		
		get("/api/todos", new Route() {
			public Object handle(Request req, Response resp) throws Exception {
				resp.status(200);					   
			   return new Gson().toJson(ToDoDAO.getAll());
			}
		});		
		post("/api/todos/save", new Route() {
			public Object handle(Request req, Response resp) throws Exception {
				resp.status(200);
				ToDoDAO.saveOne(new ToDo(0,req.queryParams("task"),true));	   
			   return new Gson().toJson("Exito true");
			}
		});
    }

}
