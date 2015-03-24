package org.ikgroup.domain;

public abstract class ProcessRecord {
	
	/**
	 * 节点名
	 */
	private String taskName;
	
	/**
	 * 节点标签
	 */
	private String taskLabel;
	
	/**
	 * 节点id
	 */
	private String taskId;
	
	/**
	 * 节点处理人名
	 */
	private String assigneeName;
	
	/**
	 * 节点处理人id
	 */
	private String assigneeId;

	/**
	 * 获取纪录信息。
	 * 
	 * @return
	 */
	public abstract String getRecordMessage();

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskLabel() {
		return taskLabel;
	}

	public void setTaskLabel(String taskLabel) {
		this.taskLabel = taskLabel;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

}
