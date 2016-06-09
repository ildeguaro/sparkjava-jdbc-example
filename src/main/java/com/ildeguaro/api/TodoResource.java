package com.ildeguaro.api;

import spark.*;


import com.google.gson.Gson;
import com.ildeguaro.dominio.ToDo;
import com.ildeguaro.jdbc.dao.ToDoDAO;
import org.codehaus.jackson.map.ObjectMapper;


public class TodoResource {
	
	
    public static void main(String[] args) {
        
     	Spark.port(5000);
     	
       	Spark.options("/*", (request,response)->{
   		 
    	    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
    	    if (accessControlRequestHeaders != null) {
    	        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
    	    }
    	 
    	    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
    	    if(accessControlRequestMethod != null){
    	    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
    	    }
    	 
    	    return "OK";
    	});
    	 
    	Spark.before((request,response)->{
    	    response.header("Access-Control-Allow-Origin", "*");
    	});
        
        Spark.get("/api/todos","application/json",(req, resp) -> {
            resp.status(200);					   
            return new Gson().toJson(ToDoDAO.getAll());
               
        });
        
        Spark.get("/api/todos/:id", "application/json",(req, resp) -> {
        	Integer id = Integer.parseInt(req.params(":id"));
        	ToDo toDo = ToDoDAO.getToDoById(id);
        	if (toDo != null) {
        		resp.status(200);
        		return new Gson().toJson(ToDoDAO.getToDoById(id));
        	} else {
        		resp.status(404);
        		return "404: Elemento no encontrado";    		
        	}
        });
        
        Spark.put("/api/todos/:id", "text/html", (req, resp) -> {
        	Integer id = Integer.parseInt(req.params(":id"));
        	ToDo toDo = ToDoDAO.getToDoById(id);
        	Integer actualizado = 0;
        	String mensaje = "";
        	if (toDo != null) {
        		ToDo updatedTask = new ObjectMapper().readValue(req.body(), ToDo.class);
        		actualizado = ToDoDAO.updateOne(id, updatedTask);
        		if (actualizado > 0) {
        			resp.status(200);	
        			mensaje = "Actualizado";
        		} else {
        			resp.status(500);
        			mensaje = "Error en la actualizacion";
        		}
        	} else {
        		resp.status(404);
        		mensaje = "404: Elemento no encontrado";
        	}
        	return mensaje;
        });
        
        Spark.delete("/api/todos/:id", "text/html", (req, resp) -> {
        	Integer id = Integer.parseInt(req.params(":id"));
        	Integer resultado = ToDoDAO.deleteOne(id);
        	if (resultado > 0) {
        		resp.status(200);
        		return "Elininado";
        	} else {
        		resp.status(404);
        		return "404: Elemento no encontrado";
        	}
        });
        
        Spark.post("/api/todos","application/json",(req, resp) -> {
            resp.status(200);        
            ToDo todo = new ObjectMapper().readValue(req.body(), ToDo.class);
            ToDoDAO.saveOne(todo);	   
            return new Gson().toJson(todo);
                
        });
    }
}