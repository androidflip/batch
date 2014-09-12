/**
 * 
 */
package com.flipchase.jobs.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class SampleStepExecutionListener implements StepExecutionListener {

	@Override
	public void beforeStep(final StepExecution stepExecution){
		final Long jobExecutionId =stepExecution.getJobExecutionId();
		final String jobName=stepExecution.getJobExecution().getJobInstance().getJobName();
		stepExecution.getExecutionContext().put("jobExecutionId", jobExecutionId);
		stepExecution.getExecutionContext().put("stepExecutionId", stepExecution.getId());
		stepExecution.getExecutionContext().put("jobName", jobName);
	}

	@Override
	public ExitStatus afterStep(final StepExecution stepExecution){
		return null;
	}

}