package com.n26.challenge.controller;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;

public class CronSynchronizerTest {
	
	private CronSynchronizer cron;

	@Before
	public void setUp() throws Exception {
		cron = new CronSynchronizer();
	}

	@Test
	public void testInit() {
		cron.initCron();
		Long firstThreshold = cron.getTimeThreshold();
		
		Boolean waitDone = false;
		
		while (!waitDone) {
			LocalDateTime threshold = LocalDateTime.now().minusSeconds(TransactionController.statisticsTimeFrame + 2);
			ZoneId zoneId = ZoneId.systemDefault();
			long epoch = threshold.atZone(zoneId).toEpochSecond();
			
			if (firstThreshold >= epoch) {
				waitDone = true;
			}
		}
		
		Long secondThreshold = cron.getTimeThreshold();
		assert(firstThreshold != secondThreshold);
	}

}
