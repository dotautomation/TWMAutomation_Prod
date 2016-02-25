package com.totalwine.test.productlist;

/*
 * PLP Filter Workflow
 * Workflow:
 * 	1. Access the PLP for Wine > White Wine
 * 	2. Select the facet filters and validate the following elements associated with each selection: 
 * 	  a. Price Range: Validate that the price on the top PLP tile is within the price range selection
 *    b. Rating Source: Validate that the first PLP tile depicts the rating source selected
 *    c. Rating Range: Validate that the rating badge is present on the first PLP tile
 *    d. Country: Validate that the country selected is displayed as an attribute on the first PLP tile
 *    e. Appelation: Validate that the selected appelation is listed on the first PLP tile
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
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class Filter extends Browser {

	//private WebDriver driver;
	private String IP="71.193.51.0";
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile testProfile = profile.getProfile("WebDriver");

	@BeforeMethod
	  public void setUp() throws Exception {
		//this.driver = ConfigurationFunctions.driver;
		//driver = new FirefoxDriver(testProfile);
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void FilterTest () throws InterruptedException, BiffException, IOException, AWTException {
		logger=report.startTest("PLP Filter Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		//Browse to PLP
	    //Access Wine PLP
		WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/wine/white-wine/c/')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[contains(@href,'000002?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		//Thread.sleep(5000);
		
		//Varietal
	    //driver.findElement(By.xpath("//li[4]/div/ul/li/a/span")).click();
		driver.findElement(By.linkText("Wine Varietal & Type")).click();
		driver.findElement(By.id("check_box_showmoreChardonnayvarietaltype")).click();
		//driver.findElement(By.id("check_box_showmoreAlbarinovarietaltype")).click();
		
	    Thread.sleep(3000);
	    //String facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String facetValue = driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a.filter-link > span.filter-value")).getText();
	    //String varietalType = driver.findElement(By.cssSelector("a[class=analyticsProductName]")).getText();
	    String varietalType = driver.findElement(By.xpath("(//a[contains(text(),'Chardonnay')])[2]")).getText();
	    //String varietalType = driver.findElement(By.xpath("(//a[contains(text(),'Albarino')])[2]")).getText();
	    System.out.println(facetValue);
	    System.out.println(varietalType);
	    	Assert.assertTrue(varietalType.contains(facetValue));
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    	Thread.sleep(3000);
	    	
		//Country
	    WebElement scroll_Country = driver.findElement(By.linkText("Country/State"));
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.linkText("Country/State")).click();
	    //driver.findElement(By.xpath("//a[contains(text(),'Country')]")).click();
	    driver.findElement(By.id("check_box_showmoreCaliforniastate")).click();
	    //driver.findElement(By.id("check_box_showmoreArgentinastate")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.cssSelector("a.analyticsCountryState")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    
	    //Price Range
	    WebElement wineMove1 = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove1).build().perform(); 
	    WebElement scroll_Price = driver.findElement(By.linkText("Price Range"));
	    //WebElement scroll = driver.findElement(By.linkText("plppricevalue"));
	 	scroll_Price.sendKeys(Keys.ARROW_DOWN);
	    //Robot scrollPrice = new Robot();
	    //scrollPrice.mousePress(KeyEvent.VK_PAGE_DOWN);
	    //scrollPrice.mouseRelease(KeyEvent.VK_PAGE_DOWN);
	 	driver.findElement(By.xpath("//a[contains(text(),'Price')]")).click();
	 	//driver.findElement(By.xpath("//a[contains(text(),'plppricevalue')]")).click();
	 	driver.findElement(By.id("check_box_showmoreUp to $10plppricevalue")).sendKeys(Keys.ARROW_DOWN);
	 	driver.findElement(By.id("check_box_showmoreUp to $10plppricevalue")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String priceValue = driver.findElement(By.cssSelector("span.price")).getText();
	    	System.out.println("Price Range: "+facetValue+"|"+priceValue);
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    	 
	    //Rating Source
	    WebElement scroll_RatingSource = driver.findElement(By.linkText("Rating Source"));
	    //scroll = driver.findElement(By.linkText("ratingsource"));
	 	scroll_RatingSource.sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.xpath("//a[contains(text(),'Rating Source')]")).click();
	    Thread.sleep(2000);
	    //driver.findElement(By.xpath("//a[contains(text(),'ratingsource')]")).click();
	    //driver.findElement(By.id("check_box_showmoreWine Enthusiastratingsource")).click();
	    driver.findElement(By.id("check_box_showmoreAntonio Galloniratingsource")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.cssSelector("span.plp-product-desc-winespec-desc-title")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    
	    //Rating Range
	    //scroll = driver.findElement(By.linkText("Rating Range"));
	    WebElement scroll_RatingRange = driver.findElement(By.linkText("Top Rated"));
	 	scroll_RatingRange.sendKeys(Keys.ARROW_DOWN);
	    //driver.findElement(By.xpath("//a[contains(text(),'Rating Range')]")).click();
	 	driver.findElement(By.xpath("//a[contains(text(),'Top Rated')]")).click();
	    driver.findElement(By.id("check_box_showmore89 and Belowratingrange")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String ratingRange = driver.findElement(By.cssSelector("span.plp-product-desc-winespec-left-badge")).getText();
	    	System.out.println("Rating Range: "+facetValue+"|"+ratingRange);
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    
	    //Appellation
	    WebElement scroll_Appelation = driver.findElement(By.linkText("Appellation"));
	    //scroll = driver.findElement(By.linkText("appellation"));
	 	scroll_Appelation.sendKeys(Keys.ARROW_DOWN);
	 	scroll_Appelation.sendKeys(Keys.ARROW_DOWN);
	    //driver.findElement(By.linkText("Appellation")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'Appellation')]")).click();
	    //driver.findElement(By.xpath("//a[contains(text(),'appellation')]")).click();
	    driver.findElement(By.id("check_box_showmoreRussian River Valleyappellation")).click();
	    //driver.findElement(By.id("check_box_showmoreAlexander Valleyappellation")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    //Assert.assertEquals(facetValue, driver.findElement(By.xpath("(//a[contains(text(),'Russian River Valley')])[2]")).getText());
	    Assert.assertTrue(driver.findElement(By.xpath("(//a[contains(text(),'Russian River Valley')])[2]")).getText().contains(facetValue));
	    //Assert.assertEquals(facetValue, driver.findElement(By.xpath("(//a[contains(text(),'Alexander Valley')])[2]")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	}
}
