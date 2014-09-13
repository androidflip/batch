package com.flipchase.jobs.service;

import java.io.IOException;
import java.io.Serializable;

public interface LogService extends Serializable {

    public void setFileName(String fileName);

    public String getFileName();

    public void setLogsDirectory(String logsDirectory);

    public void printLogs(StringBuffer logsData) throws IOException;

    public void printLogs(String logsData) throws IOException;

    public void printLogs(String pFilename, String pData) throws IOException;

    public void writeToFile(String pFilename, String pData) throws IOException;

}
