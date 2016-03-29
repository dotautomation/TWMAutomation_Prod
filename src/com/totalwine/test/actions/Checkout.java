package com.totalwine.test.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.totalwine.test.pages.PageGlobal;

public class Checkout {

	//** By Passing Age Gate and Welcome Modal
	public static void AgeGateWelcome (WebDriver driver) throws InterruptedException {
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
//		if (driver.findElement(PageGlobal.NewSiteIntroClose).isDisplayed())
//			driver.findElement(PageGlobal.NewSiteIntroClose).click();
//		Thread.sleep(5000);
	}
	
	//** Guest Checkout Tab-1
	public static void GuestCheckoutTab1 (WebDriver driver) throws InterruptedException {

	}

	//** Guest Checkout Tab-2
	public static void GuestCheckoutTab2 (WebDriver driver) throws InterruptedException {

	}

	//** Guest Checkout Tab-3
	public static void GuestCheckoutTab3 (WebDriver driver) throws InterruptedException {
	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.cssSelector("button.btn-red.btn-place-order.anPlaceOrder")).click();
	    Thread.sleep(3000);
	}
	
	//** ISP Checkout Tab-1
	public static void ISPCheckoutTab1 (WebDriver driver) throws InterruptedException {

	}
	
	//** ISP Checkout Tab-2
	public static void ISPCheckoutTab2 (WebDriver driver) throws InterruptedException {

	}
	
	//** ISP Checkout Tab-3
	public static void ISPCheckoutTab3 (WebDriver driver) throws InterruptedException {

	}
	
	//** Checking for survey pop-up
	public static void SurverPopup (WebDriver driver) throws InterruptedException {
	    if (driver.findElements(By.xpath("//img[contains(@src,'https://qdistribution.qualtrics.com/WRQualtricsShared/Graphics//black_popup_x.png')]")).size()!=0)
	    	driver.findElement(By.xpath("//img[contains(@src,'https://qdistribution.qualtrics.com/WRQualtricsShared/Graphics//black_popup_x.png')]")).click();
	    Thread.sleep(10000);
	}
}
