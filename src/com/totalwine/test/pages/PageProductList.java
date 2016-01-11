package com.totalwine.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageProductList {
	
	@FindBy(id="plp-aty-tab")
	WebElement PLPATY1Tab;
	
	By PLPATYTab = By.id("plp-aty-tab");
	By PLPAllStoresTab = By.id("plp-productfull-tabs");
	
	
}
