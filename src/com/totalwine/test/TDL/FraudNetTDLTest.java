package com.totalwine.test.TDL;

import java.io.File;
import java.io.IOException;

import org.testng.*;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.Keys;

import com.totalwine.test.config.ConfigurationFunctions;

@Deprecated
public class FraudNetTDLTest {
	
	public String locationSet = "http://twmuatwebserver:webserveruattwm@uat.totalwine.com/?remoteTestIPAddress=71.193.51.0";
	public static WebDriver driver;
	public int counter=864;
	
	 @BeforeClass
	  public void setUp() throws Exception {
		/*
		//Remote Execution Parameters
		   DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "Firefox");
		    caps.setCapability("browser_version", "37.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "7");
	    driver = new RemoteWebDriver(new URL("http://rajat43:nF9GrzCFjj3zjy692675@hub.browserstack.com/wd/hub"),caps);*/
		 
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile testProfile = profile.getProfile("WebDriver");
		driver = new FirefoxDriver(testProfile);
	    driver.manage().window().maximize();
	  }  
	 
	
	@Test (invocationCount=125) //Runs for a 24 hour period, every ~15 minutes
	public void TDLloadTest () throws InterruptedException {
		
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
		driver.navigate().to("http://twmuatwebserver:webserveruattwm@uat.totalwine.com/wine/white-wine/sauvignon-blanc/spier-sauvignon-blanc/p/96990750?s=1108&igrules=true"); //Sacramento
		//driver.navigate().to("http://twmuatwebserver:webserveruattwm@uat.totalwine.com/wine/white-wine/sauvignon-blanc/spier-sauvignon-blanc/p/96990750?s=1202&igrules=true"); //Henderson
		Thread.sleep(3000);
	    driver.findElement(By.xpath("(//button[@id='96990750-1'])[3]")).click();
	    Thread.sleep(2000);
	    //driver.findElement(By.cssSelector("div.search-right-cont-add-to-cart.analyticsViewCart")).click();
	    //driver.findElement(By.id("mini-cart_view-cart")).click();
	    driver.get("http://twmuatwebserver:webserveruattwm@uat.totalwine.com/cart");
	    Thread.sleep(3000);
	    
	    // Shopping Cart
	    WebElement scroll = driver.findElement(By.id("checkout"));
	    scroll.sendKeys(Keys.PAGE_DOWN);
	    //scroll.sendKeys(Keys.ARROW_UP);
	    //scroll.sendKeys(Keys.ARROW_UP);
	    driver.findElement(By.cssSelector("#deliveryModeInStore > div.customselect > span.itemval")).click();
	    driver.findElement(By.xpath("//form/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.id("checkout")).click();
	    Thread.sleep(3000);
	    
	    // Next Page (Login/Checkout as Guest)
	    driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")).click();
	    Thread.sleep(3000);
	    
	    // Checkout Tab 1
	    driver.findElement(By.id("shipping-email")).click();
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys("rsud79@gmail.com");
	    driver.findElement(By.id("btnPickup")).click();
	    Thread.sleep(5000);
	    
	    // Checkout Tab 2
	    driver.findElement(By.id("ssl_account_data")).click();
	    driver.findElement(By.id("ssl_account_data")).clear();
	    driver.findElement(By.id("ssl_account_data")).sendKeys("4124939999999990");
	    driver.findElement(By.id("custom_card_type")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder month\"]")).click();
	    driver.findElement(By.xpath("//td[2]/div/div/div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder year\"]")).click();
	    driver.findElement(By.xpath("//td[2]/div[2]/div/div/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.id("ssl_cvv2cvc2")).clear();
	    driver.findElement(By.id("ssl_cvv2cvc2")).sendKeys("123");
	    driver.findElement(By.id("ssl_first_name")).clear();
	    driver.findElement(By.id("ssl_first_name")).sendKeys("Automated");
	    driver.findElement(By.id("ssl_last_name")).clear();
	    driver.findElement(By.id("ssl_last_name")).sendKeys("Tester");
	    driver.findElement(By.id("ssl_avs_address")).clear();
	    driver.findElement(By.id("ssl_avs_address")).sendKeys("2800 South Alabama Ave.");
	    driver.findElement(By.id("ssl_company")).clear();
	    driver.findElement(By.id("ssl_company")).sendKeys("Test");
	    driver.findElement(By.id("ssl_address2")).clear();
	    driver.findElement(By.id("ssl_address2")).sendKeys("Test");
	    driver.findElement(By.id("ssl_city")).clear();
	    driver.findElement(By.id("ssl_city")).sendKeys("Monroeville");
	    //WebElement scroll_down = driver.findElement(By.name("process"));
	    //scroll_down.sendKeys(Keys.PAGE_DOWN);
	    
	    driver.findElement(By.xpath("//table[@id='tblAddress']/tbody/tr[7]/td[2]/div/div/span")).click();
	    driver.findElement(By.cssSelector("li[data-val=\"Alabama\"]")).click();
	    //driver.findElement(By.xpath("//td[2]/div/div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.id("ssl_avs_zip")).clear();
	    driver.findElement(By.id("ssl_avs_zip")).sendKeys("36460");
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(10000);
	    
	    // Checkout Tab 3
	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.xpath("//form[@id='placeOrderForm1']/section/div/button")).click();
	    Thread.sleep(10000);
	    
	    // Order Confirmation
	    System.out.println(System.currentTimeMillis());
	    //Thread.sleep(664000); //Executing 100 orders over 24 hours
	    Thread.sleep(5000); //Executing 100 orders over 24 hours
	}
	
	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\totalwine\\TWMAutomation\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
		}
		//ConfigurationFunctions.initialStartUp("71.193.51.0");
		TDLloadTest();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
