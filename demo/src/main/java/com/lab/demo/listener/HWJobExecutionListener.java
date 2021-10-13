package com.lab.demo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HWJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		System.out.println("Before the job execution started "+jobExecution.getJobInstance().getJobName());
		System.out.println("Before the job execution started for job context "+jobExecution.getExecutionContext().toString());
		jobExecution.getExecutionContext().put("My name : ", "Komal");
		
		System.out.println("after ssetting the  job context "+jobExecution.getExecutionContext().toString());
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		System.out.println("after the job execution started for job context "+jobExecution.getExecutionContext().toString());
	}

}
