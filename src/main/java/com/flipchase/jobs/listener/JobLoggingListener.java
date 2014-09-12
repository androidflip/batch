package com.flipchase.jobs.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobLoggingListener implements JobExecutionListener {

	/*
    @Autowired
    private LogService logService;
*/
    @Override
    public void beforeJob(final JobExecution jobExecution) {

    	/*
        final String logFileName = jobExecution.getJobInstance().getJobName();
        logService.setFileName(logFileName);
        jobExecution.getExecutionContext().put("jobName", logFileName);
        jobExecution.getExecutionContext().put("logService", logService);
        */
    }

    @Override
    public void afterJob(final JobExecution jobExecution) {
    	/*
        final String jobName = jobExecution.getJobInstance().getJobName();
        if (jobName != null && jobName.equalsIgnoreCase("enrollmentCleansor"))
            CommonBatchOperations.loggingFiles.clear();
        if (jobName != null && jobName.equalsIgnoreCase("receiveEnrollmentsFromEMJob"))
            CommonBatchReceiveOperations.loggingFiles.clear();
            */
    }

}