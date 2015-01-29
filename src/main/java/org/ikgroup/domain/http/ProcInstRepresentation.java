package org.ikgroup.domain.http;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

public class ProcInstRepresentation implements ProcessInstance{
	
	private String id;
	
	private boolean ended;
	
	private String activityId;
	
	private String processInstanceId;
	
	private String parentId;
	
	private String processDefinitionId;
	
	private String processDefinitionName;
	
	private String processDefinitionKey;
	
	private Integer processDefinitionVersion;
	
	private String deploymentId;
	
	private String businessKey;
	
	private boolean suspended;
	
	private Map<String, Object> processVariables;
	
	private String tenantId;
	
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public Integer getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}

	public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<ProcInstRepresentation> buildByProcessInstances(List<ProcessInstance> pis){
		List<ProcInstRepresentation> rtn = new ArrayList<ProcInstRepresentation>();
		for(ProcessInstance pi : pis){
			rtn.add(buildByProcessInstance(pi));
		}
		return rtn;
	}
	
	public static ProcInstRepresentation buildByProcessInstance(ProcessInstance pi){
		RuntimeException exception = null;
		ProcInstRepresentation pir = new ProcInstRepresentation();
		try {
			
			for(Method m : ProcInstRepresentation.class.getMethods()){
				String name = m.getName();
				if(name.startsWith("set")){
					String field = name.substring(3);
					Class<?> c = m.getParameterTypes()[0];
					Method mGet;
					if(c.equals(boolean.class))
						mGet = pi.getClass().getMethod("is" + field);
					else 
						mGet = pi.getClass().getMethod("get" + field);
					Object p = mGet.invoke(pi);
					m.invoke(pir, p);
				}
			}
		} catch (SecurityException e) {
			exception = new RuntimeException((Throwable)e);
		} catch (NoSuchMethodException e) {
			exception = new RuntimeException((Throwable)e);
		} catch (IllegalAccessException e) {
			exception = new RuntimeException((Throwable)e);
		} catch (IllegalArgumentException e) {
			exception = new RuntimeException((Throwable)e);
		} catch (InvocationTargetException e) {
			exception = new RuntimeException((Throwable)e);
		} finally {
			if(exception != null){
				throw exception;
			}
		}
		return pir;
	}

}
