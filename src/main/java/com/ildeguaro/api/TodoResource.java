package com.ildeguaro.api;
import static spark.Spark.*;


import com.google.gson.Gson;
import com.ildeguaro.dominio.ToDo;
import com.ildeguaro.jdbc.dao.ToDoDAO;
import com.ildeguaro.util.UtilJson;
import java.util.Map;

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
        post("/api/todos/save","application/json", new Route() {
                public Object handle(Request req, Response resp) throws Exception {
                        resp.status(200);                                              
                        String task = UtilJson.mapFromBody(req).get("task");    
                        ToDoDAO.saveOne(new ToDo(0,task,true));	   
                   return new Gson().toJson("Exito true");
                }
        });
    }
    
    

}


