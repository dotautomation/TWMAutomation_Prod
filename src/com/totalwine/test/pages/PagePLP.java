package com.totalwine.test.pages;

import org.openqa.selenium.By;

public class PagePLP {

	//Facets
	public static final By WineVarietalFacet = By.linkText("Wine Varietal & Type");
	public static final By WineVarietalFacetValue = By.id("check_box_showmoreChardonnayvarietaltype");
	public static final By CountryStateFacet = By.linkText("Country/State");
	public static final By CountryStateFacetValue = By.id("check_box_showmoreCaliforniastate"); //Country/State Value
	
	//PLP ATY tab
	
	//PLP All stores tab
	
	//PLP List
	
	//PLP Grid
	
	//Messaging
	public static final By GlobalMessaging = By.cssSelector("section.plp-product-tabs > p.msg-noitems");
	public static final By PLPMessaging = By.cssSelector("section#plp-product-tab1 > p.msg-noitems");
			
			
			
}
