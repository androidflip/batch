package com.flipchase.jobs.listener;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class BatchJobSkipPolicy implements SkipPolicy{

	@Override
	public boolean shouldSkip(final Throwable t, final int skipCount) throws SkipLimitExceededException {
		return BatchJobSkipPolicy.class.isAssignableFrom(t.getClass());
	}

}