package com.flipchase.jobs;

import java.util.Date;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MainJobHandler extends QuartzJobBean {

	private static Logger logger = LoggerFactory.getLogger(MainJobHandler.class);

	/**
	 * Special key in job data map for the name of a job to run.
	 */
	static final String JOB_NAME = "jobName";

	private JobLocator jobLocator;

	private JobLauncher jobLauncher;

	private JobExplorer jobExplorer;

	public void setJobExplorer(final JobExplorer jobExplorer) {
		this.jobExplorer = jobExplorer;
	}

	/**
	 * Public setter for the {@link JobLocator}.
	 * 
	 * @param jobLocator
	 *            the {@link JobLocator} to set
	 */
	public void setJobLocator(final JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	/**
	 * Public setter for the {@link SmallGroupJobLauncher}.
	 * 
	 * @param jobLauncher
	 *            the {@link SmallGroupJobLauncher} to set
	 */
	public void setJobLauncher(final JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	@Override
	public void executeInternal(final JobExecutionContext context) {

		final Map<String, Object> jobDataMap = context.getMergedJobDataMap();
		final String jobName = (String) jobDataMap.get(JOB_NAME);

		if (jobExplorer.findRunningJobExecutions(jobName).size() != 0) {
			return;
		}

		logger.info("Quartz trigger firing with Spring Batch jobName=" + jobName);
		final JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
		try {
			final JobExecution je = jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
			final ExitStatus exitStatus = je.getExitStatus();
			logger.info(exitStatus.getExitCode() + ":" + exitStatus.getExitDescription());
		} catch (final Exception e) {
			logger.error(e.getMessage() + "\n");
			e.printStackTrace();
		}
	}

	/*
	 * Copy parameters that are of the correct type over to {@link
	 * JobParameters}, ignoring jobName.
	 * 
	 * @return a {@link JobParameters} instance
	 */
	private JobParameters getJobParametersFromJobMap( final Map<String, Object> jobDataMap) {
		return new JobParametersBuilder().addDate("currentTime", new Date()).toJobParameters();
	}
}
