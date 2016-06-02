package com.totalwine.test.aml;

/*
 ****  Preference Updates
 ****  Work flow : 
 *  1. Click on "Account" > "Sign into your account"  (from the header) or "Account info" > "Account login" in footer section 
 *  2. In the Sign in popup > Signin using registered email and password.
 *  3. Member's Account Home page appear
 *  4. Click on the "Preferences" link from the left navigation panel.  
 *  5. change "On"/ "Off" in the "Send me email about" section.
 *  6. Check / Un-check  > wine / Beer /  Spirits section
 *  7. Change store preference
 *  8. click on "Save" button
 *  9. In the Account Home page verify message - "Your email preferences have been updated"
 **** Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke WebDriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close WebDriver
 * 	5. AfterClass
 * 			Quit WebDriver
 */
import java.io.IOException;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class PreferenceUpdates extends Browser {
	
	public String IP = "72.66.119.61";

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
	}  

	@Test
	public void PreferenceUpdatesTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Preference Updates Test");
		SiteAccess.ActionAccessSite(driver, IP);
		
	    //**Sign in modal with credential which has pre-existing order history, shopping list etc. 
	    Events.CustomLogin(driver);
	    
	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("html/body/main/section/section[1]/div/aside/section/ul[1]/li[1]/ul/li[5]/a/span")).click();
	    Thread.sleep(1000);
	    
	    // ** Selecting On/Off button using if/else statement
        if (driver.findElement(By.xpath(".//*[@id='firstPrefLogin']/div[2]/div[1]/div[1]/ul/li[2]/a")).isSelected()) {
            driver.findElement(By.xpath(".//*[@id='promotionsPreference']")).click();
        }   else  {
            driver.findElement(By.xpath(".//*[@id='firstPrefLogin']/div[2]/div[1]/div[1]/ul/li[2]/a")).click();
        }

        if (driver.findElement(By.xpath(".//*[@id='eventsAndNewsPreference']")).isSelected()) {
            driver.findElement(By.xpath(".//*[@id='firstPrefLogin']/div[2]/div[2]/div[1]/ul/li[2]/a']")).click();
        }   else  {
            driver.findElement(By.xpath(".//*[@id='eventsAndNewsPreference']")).click();
        }
        
        // ** Selecting radio button using if/else statement
        WebElement checkBox1 = driver.findElement(By.id("c0020"));
        if(!checkBox1.isSelected()){
            checkBox1.click();
        }
	    Thread.sleep(2000);
	    
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js1.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='prefFormSubmit']/div[3]/div/div[4]/div/div/span")));     
	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".btn.btn-red.anPrefSave"))); 
        sAssert.assertEquals(driver.findElements(By.cssSelector("div.ahp-heading")).isEmpty(),false,"If Preferrence save confirmation does't display then the test will fail");
	    
	    //** Logout
        Events.LogOut(driver);
	}
}