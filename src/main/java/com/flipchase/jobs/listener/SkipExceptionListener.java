package com.flipchase.jobs.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

public class SkipExceptionListener implements SkipListener<Object, Object> {

	private static Logger logger = LoggerFactory.getLogger(SkipExceptionListener.class);


	private String logFileName;


	//@Autowired
	//private LogService logService;


	@Override
	public void onSkipInRead(final Throwable t) {
		String error = "Skipped the item in Reader :: Cause - Exception/Error occured :: "+t.getMessage();
		logError(error);
	}

	@Override
	public void onSkipInWrite(final Object item, final Throwable t) {
		String error = "Skipped the item in Writer :: "+item+" Cause - Exception/Error occured :: "+t.getMessage();
		logError(error);

	}

	@Override
	public void onSkipInProcess(final Object item, final Throwable t) {
		String error = "Skipped the item in Processor :: "+item+" Cause - Exception/Error occured :: "+t.getMessage();
		logError(error);

	}

	private void logError(final String error) {
		/*
		try {
			logService.setFileName(logFileName);
			logService.printLogs(error);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(final String logFileName) {
		this.logFileName = logFileName;
	}

}