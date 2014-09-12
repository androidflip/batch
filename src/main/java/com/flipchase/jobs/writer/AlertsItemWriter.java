package com.flipchase.jobs.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;


public class AlertsItemWriter implements ItemWriter<Object> {

	@Override
	public void write(List<? extends Object> items) throws Exception {
		System.out.println("alerts updated successfully...");
	}

}
