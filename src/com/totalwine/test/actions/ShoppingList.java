package com.totalwine.test.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingList {

	//** Clicking on Shopping List Sorting drop-down
	public static void SortingDropDown (WebDriver driver) throws InterruptedException {
	    driver.findElement(By.cssSelector("#shoppingListSort > div > span")).click(); 
	    Thread.sleep(2000);
	}
	
	//**Checking for presence of merge cart modal
	public static void MergeCartModal (WebDriver driver) throws InterruptedException {
	     if (driver.findElements(By.cssSelector("button.btn.btn-red.cartMergeBtn")).size()!=0) {
	    	driver.findElement(By.cssSelector("button.btn.btn-red.cartMergeBtn")).click();
	    	Thread.sleep(3000);
	    }
	}
	
	
}
