package com.n26.challenge.controller;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.n26.challenge.transaction.Transaction;
import com.n26.challenge.transaction.TransactionStatistics;

@Singleton
public class TransactionController {
	
	private final static Logger log = Logger.getLogger(TransactionController.class.getName());
	
	public static final Integer statisticsTimeFrame = 60;
	
	@Inject
	private TransactionStatistics statistics;

	public void addTransaction(Transaction t) {
		log.info("New trasaction at time" + t.getTimestamp() + "\n Transaction amount: " + t.getAmount());
	}
	
	public void setStatistics(TransactionStatistics statistics) {
		this.statistics = statistics;
	}
}
