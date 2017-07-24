package com.n26.challenge.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.n26.challenge.exception.ExpiredTransaction;
import com.n26.challenge.transaction.Transaction;
import com.n26.challenge.transaction.TransactionStatistics;

public class TransactionControllerTest {

	TransactionStatistics statistics;
	TransactionController controller;
	
	@Before
	public void setUp() throws Exception {
		statistics = new TransactionStatistics();
		controller = new TransactionController();
		controller.statistics = statistics;
	}

	@Test
	public void testExpiredTransaction() {
		Transaction t = new Transaction();
		t.setAmount(20.8);
		t.setTimestamp(1L);
		
		try {
			controller.addTransaction(t);
			fail("Exception is expected");
		} catch (ExpiredTransaction e) {
			//Exception should be throwed
		} catch (Exception e) {
			fail("Only aceptable exception is ExpiredTransaction");
		}
	}
	
	@Test
	public void testAddTransaction() {
		Transaction t = new Transaction();
		t.setAmount(20.8);
		t.setTimestamp(Long.MAX_VALUE);
		
		try {
			controller.addTransaction(t);
		} catch (ExpiredTransaction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assert(statistics.getSum() == 20.8);
		assert(statistics.getAvg() == 20.8 / CronSynchronizer.statisticsTimeFrame);
		assert(statistics.getMax() == 20.8);
		assert(statistics.getMin() == 20.8);
		assert(statistics.getCount() == 1);
	}

	@Test
	public void testMultipleTransactions() {
		Transaction t = new Transaction();
		t.setAmount(20.8);
		t.setTimestamp(Long.MAX_VALUE);
		
		Transaction t2 = new Transaction();
		t2.setAmount(20.9);
		t2.setTimestamp(Long.MAX_VALUE);
		try {
			controller.addTransaction(t);
			controller.addTransaction(t2);
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
