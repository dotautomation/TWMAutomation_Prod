package com.totalwine.test.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;

public class SiteAccess {
	public static void ActionAccessSite(WebDriver driver,String IP) throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(2000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
//		if (driver.findElement(PageGlobal.NewSiteIntroClose).isDisplayed())/
//			driver.findElement(PageGlobal.NewSiteIntroClose).click();
//		Thread.sleep(5000);
	}
	
	public static void ActionAccessMobileSite(WebDriver driver,String IP) throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		if (driver.findElement(By.id("btn-continue")).isDisplayed())
			driver.findElement(By.id("btn-continue")).click();
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	}
}
