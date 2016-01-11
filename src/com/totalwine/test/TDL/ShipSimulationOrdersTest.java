package com.totalwine.test.TDL;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.*;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Keys;


public class ShipSimulationOrdersTest {

	public String locationSet = "http://twmuatwebserver:webserveruattwm@uat.totalwine.com/?remoteTestIPAddress=173.213.113.111";

	public static WebDriver driver;

	 @BeforeMethod
	  public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	  }  

	@Test (invocationCount=10) //Runs for a 24 hour period, every ~15 minutes
	public void TDLloadTest () throws InterruptedException, AWTException {
		driver.get(locationSet);
		Thread.sleep(5000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		System.out.println(parentHandle);               //Prints the parent window handle 
		if (driver.findElements(By.linkText("here")).isEmpty()==false) //Phased Launch Screen Handling
			driver.findElement(By.linkText("here")).click();
		for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
		        System.out.println(winHandle);
		        driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)              
		    }


		//Age Gate
		if (driver.findElements(By.id("btnYes")).isEmpty()==false) {
			driver.findElement(By.id("btnYes")).click();
			Thread.sleep(5000);
		}

		//New Site Intro
		if (driver.findElements(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).isEmpty()==false) {
		    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		    Thread.sleep(5000);
		}  

		// Add to Cart
		driver.navigate().to("http://uat.totalwine.com/wine/red-wine/cabernet-sauvignon/null/red-theory-cabernet-sauvignon/p/124357750?s=1202&igrules=true"); //Sacramento
		Thread.sleep(3000);
	    driver.findElement(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type > div.pickup-ship > div.left-ship > #shipping")).click(); //Set Ship
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type > div.pdp-buy > #addToCartForm124357750 > #overview-qty > div.dropdown.qtydropdown > div.customselect > span.itemval")).click();
	    driver.findElement(By.xpath("//div[@id='overview-qty']/div/div/div/div/div/ul/li[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@id='124357750-1'])[3]")).click();
	    Thread.sleep(3000);
	    
		driver.navigate().to("http://uat.totalwine.com/wine/red-wine/red-blend/null/wildhaven-blazing-red-columbia-valley/p/123222750?s=1202&igrules=true"); //Sacramento
		Thread.sleep(3000);
	    driver.findElement(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type > div.pdp-buy > #addToCartForm123222750 > #overview-qty > div.dropdown.qtydropdown > div.customselect > span.itemval")).click();
		driver.findElement(By.xpath("//div[@id='overview-qty']/div/div/div/div/div/ul/li[2]")).click();
		 driver.findElement(By.xpath("(//button[@id='123222750-1'])[3]")).click();
	    Thread.sleep(3000);
	    
	    //Shopping Cart
	    driver.get("http://twmuatwebserver:webservertwm@uat.totalwine.com/cart");
	    Thread.sleep(3000);
	    WebElement scroll = driver.findElement(By.id("salesTaxId"));
	    scroll.sendKeys(Keys.PAGE_DOWN);
	    driver.findElement(By.id("zipCode")).click();
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys("89005");
	    driver.findElement(By.cssSelector("input.anZipForm")).click();
	    Thread.sleep(3000);
	  
	    driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")).click();
	    //driver.findElement(By.cssSelector("li[data-val= FEDEX_2_DAY]")).click();
	    driver.findElement(By.xpath("//form/div/div[2]/div/div/div/ul/li[3]")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("checkout")).click();
	    Thread.sleep(5000);
	    
	    // Next Page (Login/Checkout as a guest user)
	    driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")).click();
	    Thread.sleep(3000);
	    
	    // Checkout Tab 1
	    driver.findElement(By.id("nickName")).clear();
	    driver.findElement(By.id("nickName")).sendKeys("ShipTest");
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys("Store");
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys("Training");
	    driver.findElement(By.id("companyName")).clear();
	    driver.findElement(By.id("companyName")).sendKeys("TWM");
	    driver.findElement(By.id("addressLine1")).clear();
	    driver.findElement(By.id("addressLine1")).sendKeys("1048 Nevada Way");
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("Boulder City");
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys("postproductionvalidation@mail.com");
	    driver.findElement(By.id("shipping-phoneNumber")).click();
	    driver.findElement(By.id("shipping-phoneNumber")).clear();
	    driver.findElement(By.id("shipping-phoneNumber")).sendKeys("7778889999");
	    driver.findElement(By.id("btnShipAuth1")).click();
	    
	    
	    //Checkout Tab 2
	    driver.findElement(By.id("ssl_account_data")).click();
	    driver.findElement(By.id("ssl_account_data")).clear();
	    driver.findElement(By.id("ssl_account_data")).sendKeys("4124939999999990");
	    driver.findElement(By.id("custom_card_type")).click();
	    driver.findElement(By.xpath("//form[@id='form1']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/div/div/div/span/span")).click();
	    //driver.findElement(By.xpath("//td[2]/div/div/div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.xpath("//td[2]/div/div/div/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.xpath("//form[@id='form1']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/div[2]/div/div/span/span")).click();
	    //driver.findElement(By.xpath("//td[2]/div[2]/div/div/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.xpath("//td[2]/div[2]/div/div/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.id("ssl_cvv2cvc2")).clear();
	    driver.findElement(By.id("ssl_cvv2cvc2")).sendKeys("123");
	    driver.findElement(By.id("billingaddress")).click();
	    
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(10000);
	    
	    // Checkout Tab 3
	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.xpath("//form[@id='placeOrderForm1']/section/div/button")).click();
	    Thread.sleep(10000);
	    
	    // Order Confirmation
	    Assert.assertEquals(driver.findElements(By.linkText("Post to Facebook")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-help-link")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
	}

	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\totalwine\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+now()+".png")); 
		}
		driver.close();
		for (int counter=10;counter>0;counter--) {
			if (counter%5==0)
				System.out.println(counter+" Store Order Simulation in progress - Do not interrupt");
			else System.out.println(counter);
			Thread.sleep(1000);
		}
	}
	
	public static String now() {
		String DATE_FORMAT_NOW = "yyyy-MM-dd HH mm ss";
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	  }
}

