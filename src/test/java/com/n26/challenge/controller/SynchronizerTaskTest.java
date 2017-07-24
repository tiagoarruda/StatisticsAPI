package com.n26.challenge.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.n26.challenge.transaction.Transaction;
import com.n26.challenge.transaction.TransactionStatistics;

public class SynchronizerTaskTest {
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	SynchronizerTask task;

	@Before
	public void setUp() throws Exception {
		task = new SynchronizerTask();
		TransactionController controller = mock(TransactionController.class);
		when(controller.getTransacions()).thenReturn(new HashMap<Long, List<Transaction>>());
		task.setController(controller);
		
		TransactionStatistics statistics = mock(TransactionStatistics.class);
		task.setStatistics(statistics);
	}

	@Test
	public void test() {
		Long firstThreshold = CronSynchronizer.getTimeThreshold();
		task.run();
		Long secondThreshold = CronSynchronizer.getTimeThreshold();
		
		assert(firstThreshold != secondThreshold);
	}
	
}
