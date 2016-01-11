package com.totalwine.test.global;

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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class URLChecker extends Browser {

	@Test 
	public void URLValidationTest () throws InterruptedException, IOException {
		
		driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    String testURL;
	    List<WebElement> link=driver.findElements(By.tagName("a"));
	    for(WebElement element:link) {
	    		testURL=element.getAttribute("href");
	        	if (testURL != null && testURL.contains("http")) {
	        		URL url = new URL(testURL);
	        		URLConnection uc = url.openConnection();
	        		String userpass = "twmlaunchsite" + ":" + "dot@internal";
	        		String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
	        		uc.setRequestProperty ("Authorization", basicAuth);
	        		int authCode = ((HttpURLConnection)uc).getResponseCode();
	        		System.out.println(testURL+"\t"+authCode);
	        	}
	        		//System.out.println(element.getAttribute("href"));
	        		//System.out.println(testURL);
	    }
	}
	
	@AfterMethod
	public void closeSession () throws IOException, InterruptedException { 
		driver.close();
	}
}
