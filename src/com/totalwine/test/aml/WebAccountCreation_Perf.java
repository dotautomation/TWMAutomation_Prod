package com.totalwine.test.aml;

/*
 * Web Account Registration Workflow
 * Workflow:
 * 	1. Click the "Account" link in top nav
 * 	2. Assert the presence of all links in the popup screen
 * 	3. Click the "Sign Up" link and navigate to the Registration screen
 * 	4. Complete the form with registration information and submit the form
 * 	5. Validate the presence of elements on the registration confirmation screen
 * 	6. Complete preferences and save the information
 *  7. Validate the elements presented on the Account home page.
 *  8. Log out and attempt to re-login using the same credentials
 *  9. Validate the logged in state ensuring the credentials work.
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;

public class WebAccountCreation_Perf  {
	
	private WebDriver driver;

	@BeforeMethod
	  public void setUp() throws Exception {
		File file = new File("C:/totalwine/Library/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	  }  
	
	@Test (invocationCount=2)
	public void RegistrationTest () throws InterruptedException, BiffException, IOException, AWTException {
		
		StringBuffer errorBuffer = new StringBuffer();
		//ConfigurationFunctions.initialStartUp("71.193.51.0");
		
		//driver.manage().window().maximize();
		driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
		
	    driver.findElement(By.linkText("Account")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Sign up")).click();
	    Thread.sleep(1000);
	    //driver.navigate().to("https://uat.totalwine.com/register");
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys("Automated");
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys("Tester");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("testload_"+ConfigurationFunctions.randInt()+"."+ConfigurationFunctions.randInt()+"@totalwine.com");
	    	String email = driver.findElement(By.id("email")).getAttribute("value");
	    	System.out.println("Registered Email Address: "+email);
	    driver.findElement(By.id("checkEmail")).clear();
	    driver.findElement(By.id("checkEmail")).sendKeys(email);
	    driver.findElement(By.id("pwd")).sendKeys("grapes");
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.passwordstrength > ul > li.active")).isEmpty(),false);
	    driver.findElement(By.id("pwd")).sendKeys("grapes123");
	    Assert.assertEquals(driver.findElements(By.xpath("//form[@id='newuserregistration']/section/div[2]/div[5]/div[11]/div/ul/li[3]")).isEmpty(),false);
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("");
	    driver.findElement(By.id("pwd")).sendKeys("grapes123!");
	    Assert.assertEquals(driver.findElements(By.xpath("//form[@id='newuserregistration']/section/div[2]/div[5]/div[11]/div/ul/li[4]")).isEmpty(),false);
	    driver.findElement(By.id("checkPwd")).sendKeys("grapes123!");
	    driver.findElement(By.id("phone")).sendKeys("3015470004");
	    Assert.assertEquals("301-547-0004", driver.findElement(By.id("phone")).getAttribute("value"));
	    driver.findElement(By.id("compName")).clear();
	    driver.findElement(By.id("compName")).sendKeys("Total Wine & More");
	    driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("6600 Rockledge Dr.");
	    driver.findElement(By.id("address2")).clear();
	    driver.findElement(By.id("address2")).sendKeys("Suite 210");
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("Bethesda");
	    //driver.findElement(By.cssSelector("span[tabindex=12]")).click();
	    
	    ///////////// OLD
	    //driver.findElement(By.xpath("//div[10]/div/div/span/span")).click();
	    ///////////// NEW
	    WebElement element = driver.findElement(By.xpath("//div[10]/div/div/span/span"));  
        new Actions(driver).moveToElement(element).perform();  
        element.click();
	    ///////////// NEW(END)
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[10]/div/div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys("20817");
	    driver.findElement(By.cssSelector("section.formbg.storepreferences")).click();
	    driver.findElement(By.cssSelector("#storePreferenceText > label")).click();
	    Assert.assertEquals(driver.findElements(By.cssSelector("input[name=preferredStoreDefault]")).isEmpty(),false);
	    
	    /*Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	    robot.keyRelease(KeyEvent.VK_PAGE_DOWN); 
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	    robot.keyRelease(KeyEvent.VK_PAGE_DOWN);*/
	    
	    WebElement element1 = driver.findElement(By.id("checkbox2"));  
        new Actions(driver).moveToElement(element1).perform(); 
	    
	    //driver.findElement(By.cssSelector("input[name=ageCheck]")).click();
	    //driver.findElement(By.cssSelector("input[name=termsAndCondCheck]")).click();
	    driver.findElement(By.id("checkbox2")).click();
	    driver.findElement(By.id("checkbox3")).click();
	    driver.findElement(By.id("btnnuregisteration")).click();
	    Thread.sleep(6000);
	}
	
	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\totalwine\\TWMAutomation\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
		}
		driver.close();
	}
	
}
