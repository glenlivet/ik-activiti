package org.ikgroup.controller;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.ikgroup.domain.http.ProcInstRepresentation;
import org.ikgroup.service.EventDrivenProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/event")
@Controller
public class EventDrivenProcRestController {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private EventDrivenProcService eventService;

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	@ResponseBody
	public void startProcessInstance() {
		runtimeService.startProcessInstanceByKey("catchSignal");
	}

	@RequestMapping(value = "/signal", method = RequestMethod.POST)
	@ResponseBody
	public void throwSignal() {
		runtimeService.signalEventReceived("alert");
	}

	@RequestMapping(value = "/procInsts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public  List<ProcInstRepresentation> listProcInstByKey(@RequestParam String procDefKey){
		List<ProcessInstance> pis = runtimeService.createProcessInstanceQuery()
	    .processDefinitionKey(procDefKey)
	    .list();
		return ProcInstRepresentation.buildByProcessInstances(pis);
	}
	
	@RequestMapping(value = "/execJob", method = RequestMethod.POST)
	@ResponseBody
	public void execJobByProcInstId(@RequestParam String procInstId){
		eventService.execJobByProcInstId(procInstId);
	}
}
