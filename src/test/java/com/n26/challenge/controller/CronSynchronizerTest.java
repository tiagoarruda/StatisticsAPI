package com.n26.challenge.controller;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Test;

public class CronSynchronizerTest {
	
	private CronSynchronizer cron;

	@Test
	public void testInit() {
		cron = new CronSynchronizer();
		Long firstThreshold = cron.getTimeThreshold();
		
		Boolean waitDone = false;
		
		while (!waitDone) {
			LocalDateTime threshold = LocalDateTime.now().minusSeconds(CronSynchronizer.statisticsTimeFrame + 2);
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
