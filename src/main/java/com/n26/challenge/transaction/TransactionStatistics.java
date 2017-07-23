package com.n26.challenge.transaction;

import java.util.logging.Logger;

import javax.inject.Singleton;

import com.n26.challenge.controller.CronSynchronizer;
import com.n26.challenge.controller.TransactionController;
import com.n26.challenge.exception.ExpiredTransaction;

@Singleton
public class TransactionStatistics {
	
	private final static Logger log = Logger.getLogger(TransactionStatistics.class.getName());
	
	private Double sum = 0.0;
	private Double avg = 0.0;
	private Double max = 0.0;
	private Double maxSecondLevel = 0.0;
	private Long maxOccurTime = 0L;
	private Long maxSecondLevelOccurTime = 0L;
	private Double min = Double.MAX_VALUE;
	private Double minSecondLevel = Double.MAX_VALUE;
	private Long minOccurTime = 0L;
	private Long minSecondLevelOccurTime = 0L;
	private Double count = 0.0;
	
	
	
	public void addTransaction(Transaction t) throws ExpiredTransaction {
		//Statiscs only for the time threshold - if transaction happened before just return
		if (t.getTimestamp() < CronSynchronizer.getTimeThreshold()) {
			throw new ExpiredTransaction();
		}
		
		//sum of all values
		sum += t.getAmount();
		
		//average
		try {
			avg	= sum / TransactionController.statisticsTimeFrame; 
		} catch (ArithmeticException ae) {
			avg = 0.0;
			log.severe("ERROR: The time frame to get statistics is configured to 0");
		}
		
		//maximum value
		if (t.getAmount() > this.max) {
			this.max = t.getAmount();
			this.maxOccurTime = t.getTimestamp();
		} else if (t.getAmount() > this.maxSecondLevel && t.getTimestamp() > this.maxOccurTime) {
			this.maxSecondLevel = t.getAmount();
			this.maxSecondLevelOccurTime = t.getTimestamp();
		}
		
		//minimum value
		if (t.getAmount() < this.min) {
			this.min = t.getAmount();
			this.minOccurTime = t.getTimestamp();
		} else if (t.getAmount() < this.minSecondLevel && t.getTimestamp() > this.minOccurTime) {
			this.minSecondLevel = t.getAmount();
			this.minSecondLevelOccurTime = t.getTimestamp();
		}
		
		this.count++;
	}
	
	public void decrementTransaction(Transaction t) {
		//decrement of all values
		sum -= t.getAmount();
		
		//average
		try {
			avg	= sum / TransactionController.statisticsTimeFrame; 
		} catch (ArithmeticException ae) {
			avg = 0.0;
			log.severe("ERROR: The time frame to get statistics is configured to 0");
		}
		
		this.count --;
	}
	
	public Double getSum() {
		return sum;
	}
	public Double getAvg() {
		return avg;
	}
	public Double getMax() {
		if (maxOccurTime < CronSynchronizer.getTimeThreshold()) {
			if (maxSecondLevel >= CronSynchronizer.getTimeThreshold()) {
				max = maxSecondLevel;
				maxOccurTime = maxSecondLevelOccurTime;
				maxSecondLevel = 0.0;
				maxSecondLevelOccurTime = 0L;
			} else {
				max = 0.0;
				maxOccurTime = 0L;
			}
		}
		return max;
	}
	public Double getMin() {
		if (minOccurTime < CronSynchronizer.getTimeThreshold()) {
			if (minSecondLevelOccurTime >= CronSynchronizer.getTimeThreshold()) {
				min = minSecondLevel;
				minOccurTime = minSecondLevelOccurTime;
				minSecondLevel = Double.MAX_VALUE;
				minSecondLevelOccurTime = 0L;
			} else {
				min = Double.MAX_VALUE;
				minOccurTime = 0L;
			}
		}
		return min;
	}
	public Double getCount() {
		return count;
	}
	
}
