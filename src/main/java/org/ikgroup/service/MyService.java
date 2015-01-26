package org.ikgroup.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface MyService {
	
	public void startProcess(String assignee);
	
	public List<Task> getTasks(String assignee);
	
	public void createDemoUsers();

}
