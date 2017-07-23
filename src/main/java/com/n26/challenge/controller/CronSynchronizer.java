package com.n26.challenge.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;

import javax.inject.Singleton;

@Singleton
public class CronSynchronizer {
	private static Long timeThreshold = getThreshold();
	private static ZoneId zoneId = ZoneId.systemDefault();
	
	public CronSynchronizer() {
		super();
		Timer cron = new Timer();
		
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
