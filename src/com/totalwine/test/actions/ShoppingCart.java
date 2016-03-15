package com.totalwine.test.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ShoppingCart {

	//** Mouse Hover Actions on header tabs
	public static void MouseHoverWine (WebDriver driver) throws InterruptedException {
		Thread.sleep(7000);
		Actions action = new Actions(driver);
	    WebElement WineTab = driver.findElement(By.cssSelector("nav > ul > li:nth-child(2) > a"));
	    action.moveToElement(WineTab).moveToElement(driver.findElement(By.cssSelector("li:nth-child(2) > div > ul:nth-child(1) > li:nth-child(9) > a > b"))).click().build().perform();
	    Thread.sleep(5000);   	    
	}
	
	//** Adding items into cart
	public static void ATC (WebDriver driver) throws InterruptedException {
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		System.out.println(productId);
		Thread.sleep(2000);   
	    driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click(); //Clicking the ATC button
		Thread.sleep (5000);
	}
}
