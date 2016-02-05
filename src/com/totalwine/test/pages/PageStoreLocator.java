package com.totalwine.test.pages;

import org.openqa.selenium.By;
	
public class PageStoreLocator {
		public static final By StoreSearchBox = By.id("storelocator-query");
		public static final By StoreSearchButton = By.id("storeFinderBtn");
		public static final By UseYourLocationButton = By.id("findStoresNearMe");
		public static final By SeeAllStoresLink = By.cssSelector("a.analyticsFindAllStores");
		public static final By DefaultRadiusDropdown = By.cssSelector("p.stores-found > span");
		public static final By RadiusDropdown = By.cssSelector("div.dist-dropdown");
		public static final By RadiusDropdownValues = By.cssSelector("div.dist-dropdown > span");
		//public static final By SearchResultsStoreNames = By.cssSelector("address > a.analyticsStoreLink");
		public static final By SearchResultsStoreNames = By.cssSelector("div > address");
	}
