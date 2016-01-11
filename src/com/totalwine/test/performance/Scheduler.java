package com.totalwine.test.performance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Scheduler extends TimerTask {

	@Override
	public void run() {
		System.out.println("The execution of task started at: " + new Date());
		// put task implementation here

		// put a sleep
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The execution of task finished at: " + new Date());

	}

	public static void main(String[] args) {

		TimerTask task = new Scheduler();
		Timer timer = new Timer(true);
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 12);
		today.set(Calendar.MINUTE, 5);
		today.set(Calendar.SECOND, 3);
		timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		
		System.out.println("The scheduler has started");
	}

}


