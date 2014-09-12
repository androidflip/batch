package com.flipchase.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class UploadProviderJobHandler extends QuartzJobBean implements StatefulJob {

	private static Logger logger = LoggerFactory.getLogger(UploadProviderJobHandler.class);

	@Autowired
	RestTemplate restTemplate;
	
	@Value(value="${uploadProviderUrl}")
	String uploadProviderUrl;
	
	@Override
	public void executeInternal(final JobExecutionContext context) {
		restTemplate.postForLocation(uploadProviderUrl, null);
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getUploadProviderUrl() {
		return uploadProviderUrl;
	}

	public void setUploadProviderUrl(String uploadProviderUrl) {
		this.uploadProviderUrl = uploadProviderUrl;
	}

	
}
