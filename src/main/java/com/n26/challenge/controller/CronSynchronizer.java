package com.n26.challenge.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.logging.Logger;

import javax.inject.Singleton;

@Singleton
public class CronSynchronizer {
	private static Long timeThreshold = getThreshold();
	private static ZoneId zoneId = ZoneId.systemDefault();
	
	private final static Logger log = Logger.getLogger(CronSynchronizer.class.getName());
	
	public CronSynchronizer() {
		super();
		Timer cron = new Timer();
		SynchronizerTask task = new SynchronizerTask();
		
		log.info("Starting Timer Task");
		
		//run every second
		cron.scheduleAtFixedRate(task, 0, 1000);
		
	}
	
	protected static void updateThreshold() {
		timeThreshold = getThreshold();
	}
	
	private static Long getThreshold() {
		LocalDateTime threshold = LocalDateTime.now().minusSeconds(TransactionController.statisticsTimeFrame);
		long epoch = threshold.atZone(zoneId).toEpochSecond();
		
		return epoch;
	}
	
	public static Long getTimeThreshold() {
		//Avoid passing the reference outside
		long threshold = timeThreshold.longValue();
		return threshold;
	}
}
