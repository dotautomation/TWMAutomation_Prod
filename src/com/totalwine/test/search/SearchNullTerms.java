package com.totalwine.test.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.totalwine.test.config.ConfigurationFunctions;

public class SearchNullTerms {
	
	@Test
	public void SearchNullTermsTest () throws InterruptedException, IOException {
		
	/*	String[] searchTerms={"johnny walker","Pliny","louis jadot","suntory","Champagne and Sparkling Wine Celebrate Today","non alcoholic wine","kettle one",
				"peter michael","Pliny the elder","Johnny walker blue","case discount","pineapple sculpin","casillero del diablo","seven falls","grey goose vodka",
				"johnny walker black","the old liquor store","singani","louis latour","Alaska distillery","Cakebread Chardonnay Napa","lowenbrau",
				"Chateau St Michelle Wine Tasting featuring their Chardonnay Canoe Ridge and Riesling Eroica","J Lohr Cabernet Seven Oaks",
				"Gentleman Jack Whiskey, Woodford Reserve Bourbon","albino armani","ommegang-game-of-thrones-fire-and-blood-red-ale","Reisetbauer","gift certificate",
				"motorhead","rewards program","cantina zaccagnini","suze","limerick lane","jim bean","bombay gin","seven deadly zins","Request Missing Points",
				"Belvedere vodka","return policy","boones farm","non-alcoholic wine","Champagne splits","Jack Daniels Tasting (Honey, Fire, Gentleman, Black)",
				"blantons bourbon","torre de ona","gray goose","dammit jim","not your fathers rootbeer","pera manca","hook and ladder",
				"Macallan Single Malts Experience: From the Smallest Stills in Speyside","Smith and hook","Joseph drouhin","total discovery","kettle one vodka",
				"FRANCISCAN ESTATE","1/6 keg","Glenmorangie Scotch Tasting","missing points","Voulet","very old barton","the federalist","seven daughters","japanese whiskey",
				"lafinca","Cake read","Bella glos","employment","johny walker","benovia","beaulieu vineyard","champagne split","lost angel pinot noir","Castello del Poggio",
				"ketel one vodka","jack daniels 1.75","kegerator","Skyfall","Cakebread Cabernet, 2012","Menage a trois","Wycliff","Old grandad","rip van winkle","3 floyds",
				"Tessellae","michele chiarlo","bud lite","helium","Chateau Ste. Michelle - Grand Opening Tasting","catuaba","McCallen Scotch Tasting","Liano","helium infused wine",
				"Kings ginger","Pabst blue ribbon","domaine chandon","alpha estate","wineberry"};*/
		
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".csv";
		File logFile=new File(timeLog);
		
		//Instantiate output file
		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		writer.write("Search term,All stores count");
		writer.newLine();
		
		File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.totalwine.com/?remoteTestIPAddress=71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    
	    File inputFile = new File("SearchTerms.txt"); //Input file containing search terms
		BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
		    String line;
		    while ((line = br.readLine()) != null) { //Read input file
		    	writer.write(line+",");
				driver.findElement(By.id("header-search-text")).clear();
			    driver.findElement(By.id("header-search-text")).sendKeys(line); //Enter Search Term in box
			    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click(); //Click search button
			    Thread.sleep(3000);
			    if (driver.findElements(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).size()!=0) //Check for All stores tab
			    {
				    String allStoreCount = driver.findElement(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).getText(); //Extract text from All stores tab
				    System.out.println(line+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")"))); 
				    String s = Objects.toString(allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")), null); //Extract count of search results from All store tab
					writer.write(s);
			    }
			    else if (driver.findElement(By.cssSelector("h1")).getText().contains("Oops, we are experiencing")) { //Check for HTTP500
			    	driver.get("http://www.totalwine.com/?remoteTestIPAddress=71.193.51.0"); //Reaccess homepage
			    	Thread.sleep(3000);
			    	driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
			    	Thread.sleep(3000);
			    	writer.write("HTTP500"); //Indicate HTTP500 occurance for search term in output file 
			    }
			    else
			    	writer.write(driver.getCurrentUrl());
			    	writer.newLine();
	    	}
	    
/*
	    for (String searchTerm : searchTerms) {
	    	writer.write(searchTerm+",");
			driver.findElement(By.id("header-search-text")).clear();
		    driver.findElement(By.id("header-search-text")).sendKeys(searchTerm);
		    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click();
		    Thread.sleep(3000);
		    //String allStoreCount = driver.findElement(By.cssSelector("ul.plp-product-tabs-wrapper > li:nth-child(2) > h2 > a.analyticsPLPDisp")).getText();
		    //if (driver.findElements(By.cssSelector("img[alt=\"logo.png\"]")).size()!=0)
		    if (driver.findElements(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).size()!=0)
		    	//writer.write(driver.getCurrentUrl());
		    //else if (driver.findElements(By.cssSelector("a.last[href*=\"totalwine.com/customer-service\"]")).size()!=0)
		    	//writer.write(driver.getCurrentUrl());
		    //else if (driver.findElements(By.cssSelector(" a.last[href*=\"totalwine.com/about-us\"]")).size()!=0)
		    	//writer.write(driver.getCurrentUrl());
		    //else 
		    {
			    String allStoreCount = driver.findElement(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).getText();
			    System.out.println(searchTerm+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")));
			    String s = Objects.toString(allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")), null);
				writer.write(s);
		    }
		    else if (driver.findElement(By.cssSelector("h1")).getText().contains("Oops, we are experiencing")) {
		    	driver.get("http://www.totalwine.com/?remoteTestIPAddress=71.193.51.0");
		    	Thread.sleep(3000);
		    	driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		    	Thread.sleep(3000);
		    	writer.write("HTTP500");
		    }
		    else
		    	writer.write(driver.getCurrentUrl());
			writer.newLine();
	    }*/
		driver.close();
		writer.close(); //Close output file
		br.close(); //Close input file
	}
}
