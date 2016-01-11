package com.totalwine.test.TDL;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Keys;

public class FNTDL {

	public String locationSet = "http://twmuatwebserver:webserveruattwm@uat.totalwine.com/?remoteTestIPAddress=71.193.51.0";

	public static WebDriver driver;

	 @BeforeMethod
	  public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	  }  

	@Test (invocationCount=125) //Runs for a 24 hour period, every ~15 minutes
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
		driver.navigate().to("http://twmuatwebserver:webservertwm@uat.totalwine.com/wine/white-wine/sauvignon-blanc/null/spier-sauvignon-blanc/p/96990750?s=1108&igrules=true"); //Sacramento
		Thread.sleep(3000);
	    driver.findElement(By.xpath("(//button[@id='96990750-1'])[3]")).click();
	    Thread.sleep(2000);
	    driver.get("http://twmuatwebserver:webservertwm@uat.totalwine.com/cart");
	    Thread.sleep(3000);

	    // Shopping Cart
	    WebElement scroll = driver.findElement(By.cssSelector("div#deliveryModeInStore"));
	    scroll.sendKeys(Keys.PAGE_DOWN);
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector("div#deliveryModeInStore > div.customselect > span.itemval")).click();
	    Thread.sleep(1000);
	    //driver.findElement(By.cssSelector("div#deliveryModeInStore")).click();
	    driver.findElement(By.xpath("//form/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.id("checkout")).click();
	    Thread.sleep(3000);
    

	    // Next Page (Login/Checkout as Guest)
	    driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")).click();
	    Thread.sleep(3000);

	    // Checkout Tab 1
	    driver.findElement(By.id("shipping-email")).click();
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys("rsud@live.com");
	    driver.findElement(By.id("btnPickup")).click();
	    Thread.sleep(5000);

	    

	    // Checkout Tab 2
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
	    driver.findElement(By.id("ssl_first_name")).clear();
	    driver.findElement(By.id("ssl_first_name")).sendKeys("TDL");
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
	}

	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		driver.close();
		for (int counter=664;counter>0;counter--) {
			if (counter%5==0)
				System.out.println(counter+" TDL Testing in progress - Do not interrupt");
			else System.out.println(counter);
			Thread.sleep(1000);
		}
	}
}
