package com.n26.challenge.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Singleton;

import com.n26.challenge.exception.ExpiredTransaction;
import com.n26.challenge.transaction.Transaction;
import com.n26.challenge.transaction.TransactionStatistics;

@Singleton
public class TransactionController {
	
	private final static Logger log = Logger.getLogger(TransactionController.class.getName());
	
	public static Map<Long, List<Transaction>> transactions = new HashMap<Long, List<Transaction>>();
	
	//TODO Create a dependency injection for these two
	public static TransactionStatistics statistics = new TransactionStatistics();
	public static CronSynchronizer cron = new CronSynchronizer();

	public void addTransaction(Transaction t) throws ExpiredTransaction {
		log.info("New trasaction at time" + t.getTimestamp() + "\n Transaction amount: " + t.getAmount());
		statistics.addTransaction(t);
		
		List<Transaction> tList = transactions.get(t.getTimestamp());
		if (tList == null) {
			tList = new ArrayList<Transaction>();
			transactions.put(t.getTimestamp(), tList);
		}
		
		tList.add(t);
	}
	
	public TransactionStatistics getStatistics() {
		return statistics;
	}
	
}
