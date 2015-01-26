package org.ikgroup.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.ikgroup.domain.Person;
import org.ikgroup.persistence.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyServiceImpl implements MyService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private PersonMapper personMapper;

	public void startProcess(String assignee) {

		Person person = personMapper.findByUsername(assignee);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("person", person);
		runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
	}

	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}

	public void createDemoUsers() {
		if (personMapper.findAll().size() == 0) {
			personMapper.add(new Person("jbarrez", "Joram", "Barrez",
					new Date()));
			personMapper.add(new Person("trademakers", "Tijs",
					"Rademakers", new Date()));
		}
	}
}
