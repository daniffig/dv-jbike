package com.jbike.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AddPenalizationJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		// TODO Add penalizations to users.

		System.out.println("Quartz!!");
	}

}
