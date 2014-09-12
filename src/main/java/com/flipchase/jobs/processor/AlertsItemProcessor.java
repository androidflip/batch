package com.flipchase.jobs.processor;

import org.springframework.batch.item.ItemProcessor;


public class AlertsItemProcessor implements ItemProcessor<Boolean, Object> {

	@Override
	public Object process(Boolean item) throws Exception {
		System.out.println("deepak.kakkar");
		return item;
	}

}
