package com.totalwine.test.pages;

import org.openqa.selenium.By;
	
public class PageChangingStore {
		public static final By YourStore = By.cssSelector("span.store-details-store.store-header");
		public static final By ChangeLocation = By.cssSelector("tr:nth-child(1) > td:nth-child(2) > a");
		public static final By FindStoreTab = By.cssSelector("div.store-filter-wrapper > ul > li:nth-child(1) > a");
		public static final By ChooseShipingDistinationTab = By.cssSelector("li:nth-child(2) > a");
		public static final By ZipForm = By.cssSelector("#newStoreSearch_box");
		public static final By SearchButton = By.cssSelector("div.store-filter.store > form > button");
		public static final By MakeThisMyStoreButton = By.cssSelector("#changeStoreBtn");
	}

