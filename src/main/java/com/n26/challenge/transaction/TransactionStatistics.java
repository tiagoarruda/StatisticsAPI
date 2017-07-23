package com.n26.challenge.transaction;

import java.util.logging.Logger;

import javax.inject.Singleton;

import com.n26.challenge.controller.TransactionController;

@Singleton
public class TransactionStatistics {
	
	private final static Logger log = Logger.getLogger(TransactionStatistics.class.getName());
	
	private Double sum = 0.0;
	private Double avg = 0.0;
	private Double max = 0.0;
	private Long maxOccurTime;
	private Double min = 0.0;
	private Long minOccurTime;
	private Double count = 0.0;
	
	
	
	public void addTransaction(Transaction t) {
		//TODO don't execute if the time was before the threshold 
		//if (t.getTimestamp())
		
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
		//TODO remove max value if it's older than the threshold
		if (t.getAmount() > this.max) {
			this.max = t.getAmount();
		}
		
		//minimum value
		if (t.getAmount() < this.min) {
			this.min = t.getAmount();
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
		return max;
	}
	public Double getMin() {
		return min;
	}
	public Double getCount() {
		return count;
	}
	
}
