package com.totalwine.test.config;

public class ConsoleProgressBar {
	  static void updateProgress(double progressPercentage) {
	    //final int width = 50; // progress bar width in chars

	    //System.out.print("\r[");
	    int i = 0;
	    //for (; i <= (int)(progressPercentage*width); i++) {
	    for (; i <= (int)(progressPercentage); i++) {
	      System.out.print(i);
	    }
	    //for (; i < width; i++) {
	      //System.out.print(" ");
	    //}
	    //System.out.print("]");
	  }

	  public static void main(String[] args) {
	    try {
	      for (double progressPercentage = 0.0; progressPercentage < 1.0; progressPercentage += 0.01) {
	        updateProgress(progressPercentage);
	        Thread.sleep(1000);
	      }
	    } catch (InterruptedException e) {}
	  }
	}
