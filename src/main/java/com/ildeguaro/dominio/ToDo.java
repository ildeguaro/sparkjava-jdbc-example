package com.ildeguaro.dominio;

public class ToDo {

	private Integer id;
	private String task;
	private boolean done;
	
	public ToDo(){
		
	}
	
	public ToDo(Integer id, String task, boolean done){
		this.id = id;
		this.task = task;
		this.done = done;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
}
