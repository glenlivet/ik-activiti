package org.ikgroup.service;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FinancialReportProcessServiceImpl implements
		FinancialReportProcessService {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@Override
	public void startProcess() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("financialReport");
		runtimeService.addUserIdentityLink(pi.getId(), "admin", IdentityLinkType.STARTER);
		runtimeService.suspendProcessInstanceById(null);
	}

	@Override
	public List<Task> getGroupTasks(String group) {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).list();
		return tasks;
	}

	@Override
	public List<Task> getTasks(String user) {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(user).list();
		return tasks;
	}

	@Override
	public void claimTask(String taskId, String user) {
		taskService.claim(taskId, user);
	}

	@Override
	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}

}
