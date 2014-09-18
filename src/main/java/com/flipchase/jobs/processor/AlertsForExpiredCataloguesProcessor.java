package com.flipchase.jobs.processor;

import org.springframework.batch.item.ItemProcessor;


public class AlertsForExpiredCataloguesProcessor implements ItemProcessor<Boolean, Object> {

	@Override
	public Object process(Boolean item) throws Exception {
		System.out.println("deepak.kakkar");
		return item;
	}

}
