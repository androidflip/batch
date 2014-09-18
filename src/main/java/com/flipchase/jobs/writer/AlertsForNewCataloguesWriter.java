package com.flipchase.jobs.writer;

import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.flipchase.jobs.service.LogService;

public class AlertsForNewCataloguesWriter implements ItemWriter<Object> {

	@Value("${broadcastAlertsForNewCatalogues}")
	private String broadcastAlertsForNewCatalogues;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public void write(List<? extends Object> items) throws Exception {
		logService.setFileName("batch-logs");
		broadcastMessages();
	}

	
	private void broadcastMessages() throws IOException {
		String response = restTemplate.postForObject(broadcastAlertsForNewCatalogues, null, String.class);
		logService.printLogs(response);
		System.out.println(response);
	}
}
