package com.totalwine.test.aml;

/*
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;

public class StickySessionTest  {
	
	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		File file = new File("C:/totalwine/Library/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    Thread.sleep(2000);
	    driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
		
	    driver.findElement(By.linkText("Account")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("a.acc-link.analyticsSignIn")).click();
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("rsud@live.com");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("yoyo55");
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(6000);
	    driver.manage().getCookies();
	
	    //Initial jsessionid capture from the page source
	    String pageSource = driver.getPageSource();
	    PrintWriter out = new PrintWriter(new FileWriter("pageSource.txt"));
	    out.println(pageSource);
	    out.close();
	  }  
	
	@Test (invocationCount=100)
	public void RegistrationTest () throws InterruptedException, BiffException, IOException {
		Actions action=new Actions(driver);
		WebElement toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); 
		action.moveToElement(toplevelnav).build().perform(); //Top Level Menu Hover
		WebElement plpnav=driver.findElement(By.xpath("//a[contains(@href,'/c/000002')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", plpnav);
		Thread.sleep(5000);
		//Assert.assertEquals(driver.findElements(By.cssSelector("//a[contains:('Welcome, ')]")).isEmpty(),false);
		Assert.assertEquals(true,driver.getPageSource().contains("Welcome, "));
		
		//Access the PDP
		WebElement plpmove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(plpmove).build().perform();
		driver.findElement(By.xpath("//a[contains(@href,'/c/000002?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		Thread.sleep(10000);
		Assert.assertEquals(true,driver.getPageSource().contains("Welcome, "));
		driver.manage().getCookies();
		driver.findElement(By.cssSelector("a.analyticsProductName")).click(); //Click the first item link in the PLP
		Thread.sleep(5000);
		//Assert.assertEquals(driver.findElements(By.xpath("//a[contains(text(),'Welcome, ']")).isEmpty(),false);
		Assert.assertEquals(true,driver.getPageSource().contains("Welcome, "));
		
		driver.findElement(By.linkText("Shopping List")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(true,driver.getPageSource().contains("Welcome, "));

		
	}
	
	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\totalwine\\TWMAutomation\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
			driver.manage().getCookies();
			String pageSource = driver.getPageSource();
		    PrintWriter out = new PrintWriter(new FileWriter("pageSource_after.txt"));
		    out.println(pageSource);
		    out.close();
		}
	}
}
