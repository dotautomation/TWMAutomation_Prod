package com.totalwine.test.pages;

import org.openqa.selenium.By;

public class PageHomepage {
	public static final By HomepageCarousel = By.cssSelector("div#homeCarousel");
	
	//Mobile
	public static final By MobileWineButton = By.cssSelector("a.btn-red.analyticsLinkComp[href*=\"/wine/c/c0020\"]"); 
	public static final By MobileSpiritsButton = By.cssSelector("a.btn-red.analyticsLinkComp[href*=\"/spirits/c/c0030\"]");
	public static final By MobileBeerButton = By.cssSelector("a.btn-red.analyticsLinkComp[href*=\"/beer/c/c0010\"]");
	public static final By MobileStoreFinderButton = By.cssSelector("a.btn-red.analyticsLinkComp[href*=\"/store-finder\"]");	
	public static final By MobileMyAccountButton = By.cssSelector("a.btn-red.analyticsLinkComp[href*=\"/my-account\"]");
}
