package com.n26.challenge.transaction;

import org.junit.Before;
import org.junit.Test;

import com.n26.challenge.controller.CronSynchronizer;
import com.n26.challenge.exception.ExpiredTransaction;

public class TransactionStatisticsTest {
	
	TransactionStatistics statistics;
	
	@Before
	public void setUp() throws Exception {
		statistics = new TransactionStatistics();
	}

	@Test
	public void test() {
		Transaction t = new Transaction();
		t.setAmount(20.8);
		t.setTimestamp(Long.MAX_VALUE);
		
		Transaction t2 = new Transaction();
		t2.setAmount(20.9);
		t2.setTimestamp(Long.MAX_VALUE);
		try {
			statistics.addTransaction(t);
			statistics.addTransaction(t2);
		} catch (ExpiredTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assert(statistics.getSum() == 20.8 + 20.9);
		assert(statistics.getAvg() == (20.8 + 20.9) / CronSynchronizer.statisticsTimeFrame);
		assert(statistics.getMax() == 20.9);
		assert(statistics.getMin() == 20.8);
		assert(statistics.getCount() == 2);
	}

}
