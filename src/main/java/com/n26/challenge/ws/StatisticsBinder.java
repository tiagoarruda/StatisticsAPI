package com.n26.challenge.ws;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.n26.challenge.controller.CronSynchronizer;
import com.n26.challenge.controller.TransactionController;
import com.n26.challenge.transaction.TransactionStatistics;

public class StatisticsBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(StatisticsWS.class).to(StatisticsWS.class);
		bind(CronSynchronizer.class).to(CronSynchronizer.class);
		bind(TransactionController.class).to(TransactionController.class);
		bind(TransactionStatistics.class).to(TransactionStatistics.class);
	}
	
}
