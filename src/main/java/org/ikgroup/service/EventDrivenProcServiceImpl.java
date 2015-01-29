package org.ikgroup.service;

import java.util.List;

import org.activiti.engine.ManagementService;
import org.activiti.engine.runtime.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventDrivenProcServiceImpl implements EventDrivenProcService {

	@Autowired
	private ManagementService managementService;

	@Override
	public void execJobByProcInstId(String procInstId) {
		List<Job> jobs = managementService.createJobQuery()
				.processInstanceId(procInstId).list();
		for (Job j : jobs) {
			String jobId = j.getId();
			managementService.executeJob(jobId);
		}
	}

}
