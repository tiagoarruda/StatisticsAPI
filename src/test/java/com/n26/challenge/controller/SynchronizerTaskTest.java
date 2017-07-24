package com.n26.challenge.controller;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class SynchronizerTaskTest {
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	SynchronizerTask task;

	@Before
	public void setUp() throws Exception {
		task = new SynchronizerTask();
		TransactionController controller = mock(TransactionController.class);
		task.setController(controller);
		
	}

	@Test
	public void test() {
		Long firstThreshold = CronSynchronizer.getTimeThreshold();
		task.run();
		Long secondThreshold = CronSynchronizer.getTimeThreshold();
		
		assert(firstThreshold != secondThreshold);
	}
	
}
