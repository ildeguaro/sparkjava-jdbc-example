package com.ildeguaro.util;

import com.google.gson.Gson;
import java.util.Map;
import spark.Request;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ildemaro-medina
 */
public class UtilJson {
    
    public static Map<String, String> mapFromBody(Request req){
         return new Gson().fromJson(req.body(), Map.class);  
         
    }
    
}
