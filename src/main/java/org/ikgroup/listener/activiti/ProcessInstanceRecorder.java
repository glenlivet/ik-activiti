package org.ikgroup.listener.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

/**
 * 用来监听流程实例的新建、完成、撤销，从而往流程纪录表中新建相关数据。
 * 
 * @author glenlivet
 *
 */
public class ProcessInstanceRecorder implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		
	}

	@Override
	public boolean isFailOnException() {
		return true;
	}

}
