package com.n26.challenge.ws;

import org.glassfish.jersey.server.ResourceConfig;

public class StatisticsConfig extends ResourceConfig {
	public StatisticsConfig() {
		register(new StatisticsBinder());
		packages(true, "com.n26.challenge.ws");
		
	}
}
