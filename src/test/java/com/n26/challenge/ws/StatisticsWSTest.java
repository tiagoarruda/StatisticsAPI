package com.n26.challenge.ws;

import org.junit.Before;
import org.junit.Test;

import com.n26.challenge.controller.CronSynchronizer;
import com.n26.challenge.controller.TransactionController;
import com.n26.challenge.transaction.Transaction;
import com.n26.challenge.transaction.TransactionStatistics;

public class StatisticsWSTest {
	
	StatisticsWS ws;
	TransactionController controller;
	TransactionStatistics statistics;

	@Before
	public void setUp() throws Exception {
		ws = new StatisticsWS();
		
		statistics = new TransactionStatistics();
		
		controller = new TransactionController();
		controller.statistics = statistics;
		ws.setController(controller);
		
	}

	@Test
	public void test() {
		Transaction t = new Transaction();
		t.setAmount(20.8);
		t.setTimestamp(Long.MAX_VALUE);
		
		ws.postTransaction(t);
		
		assert(statistics.getSum() == 20.8);
		assert(statistics.getAvg() == 20.8 / CronSynchronizer.statisticsTimeFrame);
		assert(statistics.getMax() == 20.8);
		assert(statistics.getMin() == 20.8);
		assert(statistics.getCount() == 1);
	}

}
