package com.flipchase.jobs.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flipchase.jobs.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String FILE_NAME_IN_XML_FORMAT = ".xml";
	private final static String FILE_NAME_IN_CSV_FORMAT = ".csv";
	private final static String FILE_NAME_IN_LOG_FORMAT = ".log";

	@Value("${logs.base.dir}")
	private String logsDirectory;

	private String fileName;

	@Autowired
	private JobLogger jobLogger;

	public String getLogsDirectory() {
		return logsDirectory;
	}

	@Override
	public void printLogs(final String logsData) throws IOException {
		writeToFile(fileName, logsData);
	}

	@Override
	public void printLogs(final StringBuffer logsData) throws IOException {
		writeToFile(fileName, logsData.toString());
	}

	@Override
	public void setFileName(final String fileName) {
		if(fileName.contains(FILE_NAME_IN_CSV_FORMAT)) {
			String[] name = fileName.split(FILE_NAME_IN_CSV_FORMAT);
			this.fileName = name[0] + FILE_NAME_IN_LOG_FORMAT;
		} else if(fileName.contains(FILE_NAME_IN_XML_FORMAT)) {
			String[] name = fileName.split(FILE_NAME_IN_XML_FORMAT);
			this.fileName = name[0] + FILE_NAME_IN_LOG_FORMAT;
		}
		this.fileName = fileName + FILE_NAME_IN_LOG_FORMAT;
	}

	@Override
	public void setLogsDirectory(final String logsDirectory) {
		this.logsDirectory = logsDirectory;
	}

	@Override
	public void writeToFile(final String pFilename, final String pData) throws IOException {
		jobLogger.writeToFile(pFilename, pData, logsDirectory);
	}
	
	@Override
    public void printLogs(final String pFilename, final String pData) throws IOException {
        jobLogger.writeToFile(pFilename, pData, logsDirectory);
    }
	
	@Override
	public String getFileName() {
		return fileName;
	}

}
