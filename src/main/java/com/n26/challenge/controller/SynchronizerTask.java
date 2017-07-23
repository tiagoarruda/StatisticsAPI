package com.n26.challenge.controller;

import java.util.TimerTask;

public class SynchronizerTask extends TimerTask {

	@Override
	public void run() {
		CronSynchronizer.updateThreshold();
	}
	
}
