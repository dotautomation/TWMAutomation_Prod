package com.totalwine.test.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import jxl.*;
import jxl.read.biff.BiffException;

public class SearchNullTerms {
	
	WebDriver driver;
	BufferedWriter writer;
	Workbook inputWorkbook;
	
	@Test
	public void SearchNullTermsTest () throws InterruptedException, IOException, BiffException {
	
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".csv";
		File logFile=new File(timeLog);
		
		//Instantiate output file
		writer = new BufferedWriter(new FileWriter(logFile));
		writer.write("Search term,Search Type,All stores count,Did you mean?,Page Type,Top results,Categories");
		writer.newLine();
		
		File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
//		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.get(ConfigurationFunctions.accessURL+"/?remoteTestIPAddress=71.193.51.0");
		//Thread.sleep(5000);
		//driver.findElement(By.id("btnYes")).click();
		//Thread.sleep(5000);
	    //driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    //Thread.sleep(5000);
	    SiteAccess.ActionAccessSite(driver, "71.193.51.0");
	    
	    //Input file (excel)
	    inputWorkbook = Workbook.getWorkbook(new File("misspellings automation 2.xls"));
	    Sheet inputSheet = inputWorkbook.getSheet(0);
	    int rowCount = inputSheet.getRows();
	    String SearchTerm,SearchType; //SearchType Options = all,product,event,content
	    for (int i=1;i<rowCount;i++) { //Consider title row
	    	SearchTerm = inputSheet.getCell(0,i).getContents();
	    	SearchType = inputSheet.getCell(1,i).getContents();
	    	if (SearchType.equals(""))
	    		SearchType = "all";
	    	if (SearchTerm.equals(""))
	    		SearchTerm = "invalid search term";
	    	System.out.println("Searching for: "+SearchTerm+"|"+SearchType);
	    	writer.write(SearchTerm+",");
	    	writer.write(SearchType+",");
	    	//Select Search Type
//	    	driver.findElement(By.cssSelector("span.search-left-cont-three-Lines")).click();
//	    	Thread.sleep(2000);
//	    	driver.findElement(By.cssSelector("div.suggestion.flyover > a[data-href=\""+SearchType+"\"]")).click();
//	    	Browser.PageLoad(driver);//Thread.sleep(2000);
			driver.findElement(By.id("header-search-text")).clear();
		    driver.findElement(By.id("header-search-text")).sendKeys(SearchTerm); //Enter Search Term in box
		    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click(); //Click search button
		    Browser.PageLoad(driver);//Thread.sleep(3000);
		    if (driver.findElements(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).size()!=0) //Check for All stores tab
		    {
			    String allStoreCount = driver.findElement(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).getText(); //Extract text from All stores tab
			    //System.out.println(SearchTerm+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")"))); 
			    String s = Objects.toString(allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")), null); //Extract count of search results from All store tab
				writer.write(s+",");
				//Did you mean?
				if (driver.findElements(By.cssSelector("div.result-count > a > span")).size()!=0)
					writer.write(driver.findElement(By.cssSelector("div.result-count > a > span")).getText()+",");
				else
					writer.write(""+",");
				
				//Report whether SRP or BLP (redirect)
				if (driver.getCurrentUrl().contains("brand"))
					writer.write("Brand Page"+",");
				else if (driver.getCurrentUrl().contains("search"))
					writer.write("Search Page"+",");
				else
					writer.write("PLP Page"+",");
				JavascriptExecutor js = (JavascriptExecutor)driver;
				if (driver.findElements(By.cssSelector("a#plp-productfull-tabs")).size()!=0)
					//driver.findElement(By.cssSelector("a#plp-productfull-tabs")).click();
					js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a#plp-productfull-tabs")));
				else //driver.findElement(By.cssSelector("a#search-productfull-tabs")).click();
					js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a#search-productfull-tabs")));
				Browser.PageLoad(driver);//Thread.sleep(3000);
				driver.get(driver.getCurrentUrl()+"&pagesize=32");//Top 32 results
				Browser.PageLoad(driver);
				int searchResultsCount = driver.findElements(By.cssSelector("h2.plp-product-title > a.analyticsProductName")).size();//Extract results
				String searchResult = "";
				String facetResult = "";
				for (int elementCount=1;elementCount<=searchResultsCount;elementCount++) {
					for (int catCount=1;catCount<=driver.findElements(By.xpath("//li["+elementCount+"]/div/div/div/div/a")).size();catCount++) {
						facetResult+=driver.findElement(By.xpath("//li["+elementCount+"]/div/div/div/div/a["+catCount+"]")).getText()+"|";
					}
					searchResult+=driver.findElement(By.xpath("//li["+elementCount+"]/div/div/div/h2/a")).getText().replaceAll(",", "")
							+"("
							+driver.findElement(By.xpath("//li["+elementCount+"]/div/div/div/div/div[contains(@class,'plp-product-qty')]")).getText()
							+")"
							+","
							+facetResult.replaceAll(",","")
							+"\r\n"+","+","+","+","+","; //Ensure that output is formatted
					facetResult="";//Reset facet result for next search result
				}
				//System.out.println(searchResult);
				writer.write(searchResult);
		    }
		  //Check for HTTP500
		    else if (driver.findElement(By.cssSelector("h1")).getText().contains("Oops, we are experiencing")) { 
		    	driver.get(ConfigurationFunctions.accessURL+"/?remoteTestIPAddress=71.193.51.0"); //Reaccess homepage
		    	Thread.sleep(3000);
		    	driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		    	Thread.sleep(3000);
		    	writer.write("HTTP500"); //Indicate HTTP500 occurrence for search term in output file 
		    }
		  //Events search 
		    else if (driver.findElements(By.cssSelector("div.js-event-item")).size()!=0) { 
		    	writer.write(String.valueOf(driver.findElements(By.xpath("//div[contains(@class,'js-event-item')]")).size()));
		    }
		    else
		    	writer.write(driver.getCurrentUrl());
		    writer.newLine();
    	}
	    	
	    /*
	    
	    //Input file (text)
	    File inputFile = new File("SearchTerms.txt"); //Input file containing search terms
		BufferedReader br = new BufferedReader(new FileReader(inputFile)); 
		    String searchTerm;
		    while ((searchTerm = br.readLine()) != null) { //Read input file
		    	writer.write(searchTerm+",");
				driver.findElement(By.id("header-search-text")).clear();
			    driver.findElement(By.id("header-search-text")).sendKeys(searchTerm); //Enter Search Term in box
			    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click(); //Click search button
			    Thread.sleep(3000);
			    if (driver.findElements(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).size()!=0) //Check for All stores tab
			    {
				    String allStoreCount = driver.findElement(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).getText(); //Extract text from All stores tab
				    System.out.println(searchTerm+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")"))); 
				    String s = Objects.toString(allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")), null); //Extract count of search results from All store tab
					writer.write(s+",");
					//Did you mean?
					if (driver.findElements(By.cssSelector("div.result-count > a > span")).size()!=0)
						writer.write(driver.findElement(By.cssSelector("div.result-count > a > span")).getText()+",");
					else
						writer.write(""+",");
					int searchResultsCount = driver.findElements(By.cssSelector("h2.plp-product-title > a.analyticsProductName")).size();//Extract results
					String searchResult = "";
					for (int i=1;i<=searchResultsCount;i++) {
						searchResult+=driver.findElement(By.xpath("//li["+i+"]/div/div/div/h2/a")).getText()
								+"("
								+driver.findElement(By.xpath("//li["+i+"]/div/div/div/div/div[contains(@class,'plp-product-qty')]")).getText()
								+")"
								+",";
					}
					//System.out.println(searchResult);
					writer.write(searchResult);
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
	    } //Close comment block here
	    
		driver.close();
		writer.close(); //Close output file
		br.close(); //Close input text file
		*/
	    driver.close();
		writer.close(); //Close output file
	    inputWorkbook.close(); //Close input excel file
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String scrName = "FAIL_"+testResult.getName()+"_"+ConfigurationFunctions.now()+".png"; //Name of screenshot file
			String scrFileName = "C:\\Users\\rsud\\.jenkins\\userContent\\FailureScreenshots\\Search\\"+scrName;
			File FailedFile = new File (scrFileName);
			FileUtils.copyFile(scrFile, FailedFile);
			driver.close();
			writer.close(); //Close output file
		    inputWorkbook.close(); //Close input excel file
		}
	}
}