package org.ikgroup.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/msgStart")
@Controller
public class MsgStartProcessRestController {

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 通过msg name找到由该种msg触发的流程
	 * 
	 * @param msgName
	 * @return
	 */
	@RequestMapping(value = "/procDef", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProcessDefRepresentation findProcDefByMsgName(@RequestParam String msgName) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.messageEventSubscriptionName(msgName).singleResult();
		return new ProcessDefRepresentation(pd.getId(), pd.getName());
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	@ResponseBody
	public void startProcess(@RequestBody StartProcessRepresentation startProcessRepresentation) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("person", startProcessRepresentation.getAssignee());
		ProcessInstance pi = runtimeService.startProcessInstanceByMessage(startProcessRepresentation.getMessage(), variables);
	}
	
	static class ProcessDefRepresentation {
		
		private String id;
		
		private String name;
		
		ProcessDefRepresentation(String id, String name){
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
		
		private String message;

		private String assignee;

		public String getAssignee() {
			return assignee;
		}

		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}
}
