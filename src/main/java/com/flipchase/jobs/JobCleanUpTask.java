/**
 * 
 */
package com.flipchase.jobs;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class JobCleanUpTask {

	private static Logger logger = LoggerFactory.getLogger(JobCleanUpTask.class);

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplateObject;

	private static String BATCH_STEP_EXECUTION_UPDATE_QUERY ="UPDATE BATCH_STEP_EXECUTION set STATUS = 'FAILED',EXIT_CODE = 'FAILED' , EXIT_MESSAGE = 'TERMINATED_UNEXPECTEDLY' WHERE STATUS IN ('STARTED','STARTING','RUNNING')";

	private static String BATCH_JOB_EXECUTION_UPDATE_QUERY = "UPDATE BATCH_JOB_EXECUTION set STATUS = 'FAILED',EXIT_CODE = 'FAILED' , EXIT_MESSAGE = 'TERMINATED_UNEXPECTEDLY' WHERE STATUS IN ('STARTED','STARTING','RUNNING')";

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void cleanUpStaleJobs() throws RuntimeException{

		if(dataSource == null) {
			throw new RuntimeException("No Datasource available.Please set datasource.");
		}
		final String[] sql = {BATCH_STEP_EXECUTION_UPDATE_QUERY,BATCH_JOB_EXECUTION_UPDATE_QUERY};
		jdbcTemplateObject.batchUpdate(sql);
		logger.info("Batch Job Meta Data Cleanup activity completed");
		return;
	}
}
