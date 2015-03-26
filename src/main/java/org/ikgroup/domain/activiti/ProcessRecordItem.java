package org.ikgroup.domain.activiti;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程纪录项
 * 
 * @author glenlivet
 *
 */
public class ProcessRecordItem implements Serializable {
	
	private static final long serialVersionUID = -2308534608733076296L;
	
	/**
	 * 流程相关类型：流程启动,
	 */
	public static final int TYPE_PROCESS_STARTED = 0;
	public static final int TYPE_PROCESS_END = 1;
	public static final int TYPE_PROCESS_CANCELLED = 2;
	
	/**
	 * 任务类型： 用户任务，自动任务，沟通，会签
	 */
	public static final int TYPE_TASK_USER = 3;
	public static final int TYPE_TASK_AUTO = 4;
	public static final int TYPE_TASK_LINK = 5;
	public static final int TYPE_TASK_VOTE = 6;
	
	/**
	 * 用户任务结果：通过，驳回
	 */
	public static final int RESULT_OVERRULE = 0;
	public static final int RESULT_APPROVE = 1;
	
	
	/**
	 * dbid
	 */
	private String id;

	/**
	 * 流程类型
	 */
	private Integer type;
	
	/**
	 * 关联对象id
	 */
	private String relativeKey;
	
	/**
	 * 责任人
	 */
	private String responsible;
	
	/**
	 * 处理结果
	 */
	private int result;
	
	/**
	 * 时间点
	 */
	private Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRelativeKey() {
		return relativeKey;
	}

	public void setRelativeKey(String relativeKey) {
		this.relativeKey = relativeKey;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
