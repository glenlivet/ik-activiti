package org.ikgroup.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.activiti.engine.task.Task;
import org.ikgroup.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/onetask")
@Controller()
public class MyRestController {

	@Autowired
	private MyService myService;
	
	@PostConstruct
	public void init(){
		myService.createDemoUsers();
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	@ResponseBody
	public void startProcessInstance(
			@RequestBody StartProcessRepresentation startProcessRepresentation) {
		myService.startProcess(startProcessRepresentation.getAssignee());
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
		List<Task> tasks = myService.getTasks(assignee);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName()));
		}
		return dtos;
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

	static class StartProcessRepresentation {

		private String assignee;

		public String getAssignee() {
			return assignee;
		}

		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}
	}

}