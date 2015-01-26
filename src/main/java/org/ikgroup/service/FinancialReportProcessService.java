package org.ikgroup.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface FinancialReportProcessService {

	public void startProcess();
	
	public void claimTask(String taskId, String user);

	public List<Task> getGroupTasks(String group);
	
	public List<Task> getTasks(String user);
	
	public void completeTask(String taskId);

}
