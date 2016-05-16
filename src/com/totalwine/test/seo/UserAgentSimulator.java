package com.totalwine.test.seo;

/*
 This script simulates User Agents (mostly BOTS) and reports the behavior of the site
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class UserAgentSimulator extends Browser{
	
	//protected WebDriver driver;
	BufferedWriter writer = null;
	String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    File logFile = new File(timeLog);
    
    @BeforeTest
    public void initialize() throws IOException {
    	writer = new BufferedWriter(new FileWriter(logFile));
    }
    
	@DataProvider(name="BotsParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Bots", "botNameTest");
        return(retObjArr);
    }
	
	@Test (dataProvider = "BotsParameters")
	public void UserAgentValidation (String userAgent) throws InterruptedException, IOException {
		logger=report.startTest("Google/Bing Bots Test");
		File file = new File("C:/totalwine/Library/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("userAgent", userAgent);
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(capabilities);
		
		driver.get(ConfigurationFunctions.accessURL);
		Browser.PageLoad(driver);
		//Ensure that there's no HTTP500 for Bot traffic
		if (driver.findElements(By.cssSelector("div.content > h1")).size()!=0)
			if (driver.findElement(By.cssSelector("div.content > h1")).getText().contains("we are experiencing an issue with this page"))
				Assert.assertTrue(false,"Webpage is not rendering correctly for Bot traffic and is generating a HTTP500");
		writer.write(userAgent);
		System.out.print(userAgent);
		
		//Desktop (No Popup)
		if (driver.findElements(By.cssSelector("section.national-promotion")).size()!=0) {
			System.out.println(" is allowed and wasn't presented any popups");
			writer.write(" ,is allowed and wasn't presented any popups");
		}
		//Mobile (No popup)
		else if (driver.findElements(By.cssSelector("section.hp-way-fndg")).size()!=0) {
			System.out.println(" is allowed and wasn't presented any popups (mobile)");
			writer.write(" ,is allowed and wasn't presented any popups (mobile)");
		}
		//Browser Compatibility Message
		else if (driver.findElements(By.id("btn-continue")).size()!=0) { 
				System.out.println(" ,is allowed and was presented a browser incompatible message");
				writer.write(" ,is allowed and was presented a browser incompatible message");
			}
		//Age Gate
		else if(driver.findElements(By.id("btnYes")).size()!=0) {
				System.out.println(" is allowed and was presented an age gate");
				writer.write(" ,is allowed and was presented an age gate");
			}
		//New Site Intro
		else if(driver.findElements(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).size()!=0) {
			System.out.println(" is allowed and was presented the new site intro");
			writer.write(" ,is allowed and was presented the new site intro");
		}
		//Blocked Bot
		else {
			System.out.println(" ,is BLOCKED");
			writer.write(" ,is BLOCKED");
		}
	}

	@AfterTest
	public void closeWriter() throws IOException {
		System.out.println(logFile.getCanonicalPath());
		writer.close();
	}
}
