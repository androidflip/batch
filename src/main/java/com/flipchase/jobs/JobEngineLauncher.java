package com.flipchase.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobEngineLauncher {

	private static Logger logger = LoggerFactory.getLogger(JobEngineLauncher.class);

	/**
	 * @param argshba
	 */
	public static void main(final String[] args) throws Exception {
		//cleanUpBatchJobs();
		new ClassPathXmlApplicationContext(new String[] { "classpath:/META-INF/launch-context.xml" }, true);
		logger.info("Job Engine started...");
	}


}
