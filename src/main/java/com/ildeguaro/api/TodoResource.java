package com.ildeguaro.api;
import spark.*;


import com.google.gson.Gson;
import com.ildeguaro.dominio.ToDo;
import com.ildeguaro.jdbc.dao.ToDoDAO;
import org.codehaus.jackson.map.ObjectMapper;


public class TodoResource {
	
    public static void main(String[] args) {
        
        Spark.port(5000);
        
        Spark.get("/api/todos","application/json",(req, resp) -> {
            resp.status(200);					   
            return new Gson().toJson(ToDoDAO.getAll());
               
        });		
        Spark.post("/api/todos/save","application/json",(req, resp) -> {
            resp.status(200);        
            ToDo todo = new ObjectMapper().readValue(req.body(), ToDo.class);
            ToDoDAO.saveOne(todo);	   
            return new Gson().toJson("exito:true");
                
        });
    }
}


