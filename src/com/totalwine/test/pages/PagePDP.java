package com.totalwine.test.pages;

import org.openqa.selenium.By;

public class PagePDP {
	//Tab 1 - Overview
	public static final By ATCButton = By.cssSelector("section.pdp-tab-overview-type > div > form.add_to_cart_form > div > button.anAddToCart");
	public static final By QtyDropdown = By.cssSelector("div.qtydropdown > div > span");
	public static final By QtyValue = By.cssSelector("div.qtydropdown > div > div > ul > li[data-val=\"3\"]"); // Qty=3
	public static final By PDPImage = By.cssSelector("div > img.anPDPImage");
	public static final By ViewAllLink = By.cssSelector("a.analyticsViewAllCoastline");
	public static final By ProductName = By.cssSelector("h1.product-name");
	public static final By RatingsCount = By.cssSelector("div.pdpRatingStars > a");
	public static final By CountryState = By.cssSelector("a.analyticsCountryState");
	public static final By ProductType = By.cssSelector("a.analyticsProductType");
	public static final By VarietalType = By.cssSelector("a.analyticsVarietalType");
	public static final By Email = By.cssSelector("span.email-logo");
	public static final By Print = By.cssSelector("span.print-logo");
	public static final By Share = By.cssSelector("span.share-logo");
	public static final By BuyBoxVolume = By.cssSelector("div#overview-mililitres > div > span");
	public static final By BuyBoxISPRadio = By.cssSelector("span#pickup");
	public static final By BuyBoxShipRadio = By.cssSelector("span#shipping");
	public static final By BuyBoxAvailableInXStore = By.cssSelector("a#nearbyStoreLink");
	public static final By SaveToListButton = By.cssSelector("section.pdp-tab-overview-type > div.pdp-buy > button.anAddToListInit");
	
	
	//Discard Modal
	public static final By DiscardConfirmation = By.cssSelector("button#discardConfProceedBtn");
	public static final By DiscardCancel = By.cssSelector("button#discardBtnClose");
	
	//Tab 2 - Product Details
	
	//Tab 3 - Ratings/Reviews
	
	//Tab 4 - Recommendations
}
