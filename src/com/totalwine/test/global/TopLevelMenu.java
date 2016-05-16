package com.totalwine.test.global;

/*
 * Top Level Menu Validation
 * Workflow:
 * 	1. Access all top level menus
 * 	2. Validate all menus are exist and working fine.
 *  
 * Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 * 	5. AfterClass
 * 			Quit webdriver
 */

import java.io.IOException;
import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.interactions.Actions;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class TopLevelMenu extends Browser {
	
	String IP="71.193.51.0";
	
	@DataProvider(name="TopLevelMenuParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"global", "toplevel");
        return(retObjArr);
    }
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		   
	  }  
	
	@Test (dataProvider = "TopLevelMenuParameters")
	public void TopLevelMenuTest (String menu,String position,String tlcontent,String contents) throws InterruptedException, IOException {
		logger=report.startTest("Validate Top Level Menu Contents");
		SiteAccess.ActionAccessSite(driver, IP);
		
		//Validate top-level menu items
	    WebElement topLevelMenuItem = driver.findElement(By.cssSelector("ul.nav > li.menu:nth-child("+position+") > a"));
		Assert.assertEquals(topLevelMenuItem.getText(),menu);
		
		
		//Hover over each category
		Actions action=new Actions(driver);
		action.moveToElement(topLevelMenuItem).build().perform();
		
		//Validate menu contents
		String[] menuHeadings = tlcontent.split("\\|");
		String[] menuItems = contents.split("\\|");
		//WebElement subMenu = driver.findElement(By.xpath("//ul[contains(@class,'nav')]/li[2]/div/ul"));
		//WebElement subMenuItem = driver.findElement(By.xpath("//ul[contains(@class,'nav')]/li[2]/div/ul/li/a"));
		//int iCount = 0;
		int menuCount = driver.findElements(By.xpath("//ul[contains(@class,'nav')]/li["+position+"]/div/ul")).size(); //No. of Menus
		if (menu.contains("Beer")||menu.contains("Accessories") )
			menuCount = menuCount - 1; //Beer and Accessories & More menus do not have banner
		else 
			menuCount = menuCount - 2; //Wine and Spirits menus have banner
		System.out.println("No. of menus: "+menuCount);
		int itemCount = 0;
		for (int i=1;i<=(menuCount);i++) {
			itemCount = driver.findElements(By.xpath("//ul[contains(@class,'nav')]/li["+position+"]/div/ul["+i+"]/li/a")).size(); //No. of Items within each menu
//			System.out.println(">>>>>>>>>>>>>>>>>>"+driver.findElement(By.xpath("//ul[contains(@class,'nav')]/li["+position+"]/div/ul["+i+"]/div")).getText());

			//System.out.println("Expected: "+menuHeadings[i-1]);
//			Assert.assertEquals(driver.findElement(By.xpath("//ul[contains(@class,'nav')]/li["+position+"]/div/ul["+i+"]/div")).getText(), menuHeadings[i-1]);
			//logger.log(LogStatus.PASS, menuHeadings[i-1]+" is present in the top level menu");
			for (int j=1;j<=itemCount;j++) {
				System.out.println(driver.findElement(By.xpath("//ul[contains(@class,'nav')]/li["+position+"]/div/ul["+i+"]/li["+j+"]/a")).getText());
				//Assert.assertEquals(driver.findElement(By.xpath("//ul[contains(@class,'nav')]/li["+position+"]/div/ul["+i+"]/li["+j+"]/a")).getText(),menuItems[j-1]);
				//System.out.println("Expected Sub: "+menuItems[j-1]);
			}
		}
	}
}
