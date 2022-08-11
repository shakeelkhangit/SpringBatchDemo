package com.batch.config;

import javax.batch.runtime.BatchStatus;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobListenerExp extends JobExecutionListenerSupport  {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("BEFORE THE JOB.....");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("AFTER THE JOB.....");		
	}
	
	
	
}
