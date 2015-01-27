package org.ikgroup.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;
import org.ikgroup.service.FinancialReportProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/financialReport")
@Controller()
public class FinancialReportProcessRestController {
	
	@Autowired
	private FinancialReportProcessService service;
	
	@RequestMapping(value = "/process", method = RequestMethod.POST)
	@ResponseBody
	public void startProcessInstance(){
		service.startProcess();
	}
	
	@RequestMapping(value = "/claim", method = RequestMethod.POST)
	@ResponseBody
	public void claimTask(@RequestBody TaskClaimer taskClaimer){
		service.claimTask(taskClaimer.getTaskId(), taskClaimer.getUser());
	}
	
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@ResponseBody
	public void completeTask(@RequestParam String taskId){
		service.completeTask(taskId);
	}
	
	@RequestMapping(value = "/groupTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TaskRepresentation> getGroupTasks(@RequestParam String group){
		List<Task> tasks = service.getGroupTasks(group);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName()));
		}
		return dtos;
	}
	
	@RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TaskRepresentation> getTasks(@RequestParam String user){
		List<Task> tasks = service.getTasks(user);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName()));
		}
		return dtos;
	}
	
	static class TaskClaimer {
		
		private String taskId;
		private String user;
		
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		
	}
	
	static class TaskRepresentation {

		private String id;
		private String name;

		public TaskRepresentation(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
