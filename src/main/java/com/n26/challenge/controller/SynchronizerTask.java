package com.n26.challenge.controller;

import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.n26.challenge.transaction.Transaction;

public class SynchronizerTask extends TimerTask {
	
	private final static Logger log = Logger.getLogger(SynchronizerTask.class.getName());
	
	@Inject
	TransactionController controller;

	@Override
	public void run() {
		log.info("Update threshold");
		
		CronSynchronizer.updateThreshold();
		List<Transaction> transactions = TransactionController.transactions.get(CronSynchronizer.getTimeThreshold()); 
		if (transactions != null) {
			for (Transaction t : transactions) {
				TransactionController.statistics.decrementTransaction(t);
			}
		}
	}

	public void setController(TransactionController controller) {
		this.controller = controller;
	}
	
}
