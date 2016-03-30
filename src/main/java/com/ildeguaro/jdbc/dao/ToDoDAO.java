package com.ildeguaro.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ildeguaro.dominio.ToDo;
import com.ildeguaro.jdbc.config.MyConnect;

public class ToDoDAO {	
	
	public static List<ToDo> getAll(){		
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            List<ToDo> retornar = new ArrayList<ToDo>();		
            con = MyConnect.getConnection();		
            try {
                    stmt = con.createStatement();
                    String sql = new String("select * from todo ");
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                            retornar.add(new ToDo(rs.getInt("id"), rs.getString("task"), rs.getBoolean("done")));     

            }
            } catch (SQLException e) {			
                    e.printStackTrace();
            }finally {
                    try {
                            con.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }		

            return retornar;
	}
	
	
	public static ToDo getToDoById(Integer id){		
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            ToDo retornar = null;

            con = MyConnect.getConnection();
            try {
                    stmt = con.createStatement();
                    String sql = new String("select * from todo ");
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                            retornar = new ToDo(rs.getInt("id"), rs.getString("task"), rs.getBoolean("done"));     

            }
            } catch (SQLException e) {			
                    e.printStackTrace();
            }finally {
                    try {
                            con.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }		

            return retornar;
	}
	
	
	public static int saveOne(ToDo task){		
            Connection con = null;
            Statement stmt = null;		
            int retornar = 0;

            con = MyConnect.getConnection();
            try {
                    stmt = con.createStatement();
                    String sql = new String("insert into todo(task,done) values ("+task.getTask()+",0)");
                    retornar = stmt.executeUpdate(sql);

            } catch (SQLException e) {			
                    e.printStackTrace();
            }finally {
                    try {
                            con.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }		

            return retornar;
	}

}
