package com.totalwine.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.totalwine.test.trials.Browser;

public class PageCheckout extends Browser {
	protected static WebDriver driver;
	public static final String IEDRIVERPATH = "C:/twmautomation/lib/lib/IEDriverServer.exe";
	public static final WebElement CREDITCARDTEXTFIELD = driver.findElement(By.id("ssl_account_data"));
}
