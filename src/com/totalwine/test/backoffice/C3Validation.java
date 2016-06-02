package com.totalwine.test.backoffice;

/*
 * C3 Sanity Workflow
 * Workflow:
 * 1. Access the CS Cockpit
 * 2. Login using valid credentials
 * 3. Validate the menus displayed upon a successful login
 * 4. Find a customer and access it's record
 * 5. Validate the customer record
 * 6. Find an order and access it's record
 * 7. Validate the order record
 * 8. Log out
 *		
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 */

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.trials.ParallelBrowser;

public class C3Validation extends ParallelBrowser {
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@DataProvider(name="CSParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"CS", "CSParameters");
        return(retObjArr);
    } 
	
	@Test (invocationCount=5,dataProvider = "CSParameters")
	public void C3LoginTest (String customer,String order) throws InterruptedException {
		logger=report.startTest("CS Cockpit Login Test");
		driver.get(ConfigurationFunctions.backofficeURL+"/cscockpit");
		Thread.sleep(5000);
		C3Login();
		System.out.println("Successfully logged in");
		
		//Validate Links in left menu
		Assert.assertEquals(driver.findElements(By.linkText("Find Customer")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("New Customer")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("Email Signup")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("Find Order")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("Find Ticket")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("New Ticket")).isEmpty(), false);
		System.out.println("CS Cockpit Left Menu contents validated");
		
		//Find a customer
		driver.findElement(By.linkText("Find Customer")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).sendKeys(customer);
		//driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[2]")).clear();
		//driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[2]")).sendKeys("Sud");
		driver.findElement(By.xpath("//td[text()[contains(.,'Search')]]")).click();
		Thread.sleep(2000);
		while (driver.findElements(By.cssSelector("div.z-loading-indicator")).size()!=0)
			Thread.sleep(2000);
		System.out.println("Customer search validated");
		
		//Validate Customer search results
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()[contains(.,'WebMember')]]")).isEmpty(), false);
		driver.findElement(By.xpath("//td[text()[contains(.,'Select')]]")).click();
		//Thread.sleep(3000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ahp-rewardsClub")));
		Assert.assertEquals(driver.findElements(By.xpath("//span[text()[contains(.,'Customer Email ID')]]")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-rewardsClub")).isEmpty(),false);
		System.out.println("Customer record validated");
		
		//Find an order
		driver.findElement(By.linkText("Find Order")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).sendKeys(order);
		driver.findElement(By.xpath("//td[text()[contains(.,'Search')]]")).click();
		Thread.sleep(2000);
		while (driver.findElements(By.cssSelector("div.z-loading-indicator")).size()!=0)
			Thread.sleep(2000);
		System.out.println("Order search validated");
		
		//Validate Order search results
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()[contains(.,'Cancelled')]]")).isEmpty(), false);
		//driver.findElement(By.xpath("//td[text()[contains(.,'Select')]]")).click();
		driver.findElement(By.xpath("//td[contains(@class, 'z-button-cm') and text() = 'Select']")).click();
		Thread.sleep(3000);
		//Handling Active Customer popup (new with 3/9/2016 hotfix)
		driver.findElement(By.xpath("//td[text()[contains(.,'Yes')]]")).click();
		Thread.sleep(2000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[text()[contains(.,'AUTHORIZATION')]]"))));
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()[contains(.,'AUTHORIZATION')]]")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.cssSelector("img.listViewCellImage")).isEmpty(),false);
		System.out.println("Order record validated");
		
		//Logout
		driver.findElement(By.xpath("//span[text()[contains(.,'Menu')]]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()[contains(.,'Logout')]]")).click();
		System.out.println("Sucessfully logged out");
	}
	
	public void C3Login() throws InterruptedException {
		//C3 Login
		driver.findElement(By.cssSelector("input[name=j_username]")).clear();
		driver.findElement(By.cssSelector("input[name=j_username]")).sendKeys("rsud");;
		driver.findElement(By.cssSelector("input[name=j_password]")).clear();
		driver.findElement(By.cssSelector("input[name=j_password]")).sendKeys(ConfigurationFunctions.TESTPWD);
		driver.findElement(By.cssSelector("td.z-button-cm")).click();
		//Thread.sleep(10000);
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Find Customer")));
	}
}
