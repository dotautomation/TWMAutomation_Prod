package com.totalwine.test.config;

public class Counter {
	public static void main (String[] args) throws InterruptedException {
		for (int i=600;i>0;i--) {
			if (i%5==0)
				System.out.println(i+" TDL Testing in Progress - Do Not Interrupt");
			else
				System.out.println(i);
			Thread.sleep(1000);
		}
	}
}
