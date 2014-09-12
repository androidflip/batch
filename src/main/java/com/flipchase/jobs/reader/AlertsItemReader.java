package com.flipchase.jobs.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


public class AlertsItemReader implements ItemReader<Boolean> {

	Boolean isWrittenOnce = false;
	@Override
	public Boolean read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(!isWrittenOnce) {
			isWrittenOnce = true;
			return true;
		}
		return null;
	}

}
