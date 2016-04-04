package com.totalwine.test.pages;

import org.openqa.selenium.By;
	
public class PageChangingStore {
		public static final By YourStore = By.cssSelector("span.store-details-store-name.flyover-src");
		public static final By ChangeLocation = By.cssSelector("tr:nth-child(1) > td:nth-child(2) > a");
		public static final By FindStoreTab = By.cssSelector("div.store-filter-wrapper > ul > li:nth-child(1) > a");
		public static final By ZipForm = By.cssSelector("#newStoreSearch_box");
		public static final By SearchButton = By.cssSelector("div.store-filter.store > form > button");
		public static final By MakeThisMyStoreButton = By.cssSelector("#changeStoreBtn");

		public static final By ChooseShipingDistinationTab = By.cssSelector("#changeLocation > div > div > div > div > div > div.store-filter-wrapper > ul > li:nth-child(2) > a");
		public static final By ClickingDropDown = By.cssSelector("#changeLocation > div > div > div > div > div > div.store-filter.state > div > div > span > i");
		public static final By ShippingState = By.cssSelector(".undefined.anOption[data-val='US-CA']");
		public static final By MakeThisMyShippingStateButton = By.cssSelector("#changeShippingState");
		
	}

