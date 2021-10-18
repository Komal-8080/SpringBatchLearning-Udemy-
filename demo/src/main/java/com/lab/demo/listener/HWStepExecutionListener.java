package com.lab.demo.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HWStepExecutionListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("This is for before step execution "+stepExecution.getJobExecution().getExecutionContext());
		
		System.out.println("Inside step print job parameters defined in edit configurations under projectargs: "+stepExecution.getJobExecution().getJobParameters());
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("This is for after step execution "+stepExecution.getJobExecution().getExecutionContext());
		return null;
	}	

}
