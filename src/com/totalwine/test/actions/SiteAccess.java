package com.totalwine.test.actions;

import org.openqa.selenium.WebDriver;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;

public class SiteAccess {
	public static void ActionAccessSite(WebDriver driver,String IP) throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(2000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(2000);
	    driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(2000);
	}
}
