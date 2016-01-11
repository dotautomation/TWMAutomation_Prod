package com.totalwine.test.config;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressTimer {
	static int interval;
	static Timer timer;
	
	public static void Timer(String countdown) {
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    interval = Integer.parseInt(countdown);
	    System.out.print(countdown+",");
	    timer.scheduleAtFixedRate(new TimerTask() {	
	        public void run() {
	            System.out.print(setInterval()+",");	
	        }
	     }, delay, period);
	}

	private static final int setInterval() {
		if (interval == 1)
			timer.cancel();
		return --interval;
		}
	
	//public static void main (String args []) throws InterruptedException, IOException {
		//Timer("864");
		//int counter=100;
		//for (;counter>0;counter--) {
	    	//Thread.sleep(1000);
	    	//System.out.print(counter+" seconds to next order\r");
	    //}
	//}
	}