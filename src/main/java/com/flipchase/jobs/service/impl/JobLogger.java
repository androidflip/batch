package com.flipchase.jobs.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JobLogger {


	//Need to improve this method
	public synchronized void  writeToFile(final String pFilename,String pData,final String logsDirectory) throws IOException {

		// logs folder created if not present
		final File logDirectory = new File(logsDirectory);

		if (!logDirectory.exists()) {
			logDirectory.mkdirs();
		}
		// if file does not exists, then create it
		final File file = new File(logsDirectory,pFilename);
		boolean fileExists = false;
		if (!file.exists()) {
			file.createNewFile();
			fileExists = true;
		}
		final BufferedWriter out = new BufferedWriter(new FileWriter(logsDirectory
				+ pFilename, true));
		if(!fileExists) {
			out.newLine();
		}
		pData = decorateLogs(pData);
		out.write(pData);
		out.flush();
		out.close();
	}

	/**
	 * @param pData
	 */
	private String decorateLogs(String pData) {
		final StringBuilder sb = new StringBuilder();
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss");
		final String formattedDate = sdf.format(date);
		sb.append(formattedDate);
		sb.append("  ");
		final StackTraceElement[] ste = new Throwable().getStackTrace();
		sb.append(ste[4].getClassName());
		sb.append("  ");
		sb.append(ste[4].getMethodName());
		sb.append(" ::  ");
		pData =  sb.append(pData).toString();
		return pData;
	}

}
