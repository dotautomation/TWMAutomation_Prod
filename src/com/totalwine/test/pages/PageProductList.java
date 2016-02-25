package com.totalwine.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PageProductList {
	
	//Common
	public static final By WDLogo =By.cssSelector("div.plp-list-img-wdlogo"); //WD Logo
	public static final By ProductTitle =By.cssSelector("h2.plp-product-title"); //Title
	public static final By ItemSize = By.cssSelector("div.plp-product-qty"); //Item Size
	public static final By SortDropdown = By.cssSelector("div.plp-product-sorting-sortby-dropdown > div > span.itemval");
	public static final By PLPHero = By.cssSelector("div.plp-opt-hero"); //Hero component
	public static final By PLPATY = By.cssSelector("a#plp-aty-tab"); //ATY
	public static final By PLPAllStores = By.cssSelector("a#plp-productfull-tabs"); //All stores
	public static final By ATYISP = By.cssSelector("a[href*=\"tab=aty_isp\"]"); //ATY > ISP
	public static final By ATYShip = By.cssSelector("a[href*=\"tab=aty_shipping\"]"); //ATY > Ship
	public static final By ATYBoth = By.cssSelector("a[href*=\"tab=aty_both\"]"); //ATY > Both
	public static final By ViewDropdown = By.cssSelector("div.plp-product-sorting-view-dropdown"); //Show dropdown
	public static final By ListView = By.cssSelector("a.analyticsViewAsList"); //View as list
	public static final By ListViewDefault = By.cssSelector("a.analyticsViewAsList.active.activeicon"); //Default list view
	public static final By GridView = By.cssSelector("a.analyticsViewAsGrid"); //View as grid
	
	
	
	//List View
	public static final By List = By.cssSelector("section.plp-product-content"); //List
	public static final By ListImage = By.cssSelector("div.plp-list-product-img");
	public static final By ListDesc = By.cssSelector("div.plp-product-desc-winespec-desc");
	public static final By ListReadMore = By.cssSelector("a.plp-readmore.analyticsReadMore");
	public static final By ListStars = By.cssSelector("span.stars");
	public static final By ListReviewCount = By.cssSelector("a.analyticsProductReviews");
	public static final By ListCountry = By.cssSelector("a.analyticsCountryState");
	public static final By ListRegion = By.cssSelector("a.analyticsRegion");
	public static final By ListShippingAvailBadge = By.cssSelector("span.icon-ship-avail.js-ship-avail");
	public static final By ListShippingNotAvailBadge = By.cssSelector("span.icon-ship-avail.js-ship-notavail");
	public static final By ListISPBadge = By.cssSelector("span.icon-pickup-avail.js-pick-avail");
	public static final By ListATC = By.cssSelector("button.anAddToCartInit");
	public static final By ListATL = By.cssSelector("button.anAddToListInit");
	public static final By ListPrice = By.cssSelector("div.plp-product-buy-price-mix");
	
	//ATC Interstitial
	public static final By InterTitle = By.cssSelector("h1.product-name");
	public static final By InterImage = By.cssSelector("img.anPDPImage");
	public static final By InterBrandLink = By.cssSelector("a.pdp-tab-overview-prod-img-bottle-img-link");
	public static final By InterVariationNote = By.cssSelector("div.price-vintag");
	public static final By InterProductCode = By.cssSelector("div.pdp-product-nos");
	public static final By InterDesc = By.cssSelector("div.right-full-desc");
	public static final By InterNotes = By.cssSelector("div.bottom-full-desc");
	public static final By InterVolume = By.cssSelector("div#overview-mililitres");
	public static final By InterISP = By.cssSelector("div.pickUp");
	public static final By InterShip = By.cssSelector("div.pickup-ship");
	public static final By InterQty = By.cssSelector("div.dropdown.qtydropdown");
	public static final By InterATC = By.cssSelector("button.anAddToCart");
	public static final By InterATL = By.cssSelector("button.anAddToListInit");
			
	//Grid View
	public static final By Grid = By.cssSelector("section.plp-product-content.grid"); //Grid
	public static final By GridImage = By.cssSelector("div.product-img-div"); //Product image
	public static final By GridExpertRating =By.cssSelector("div.wine-spectator-div"); //Expert Rating 
	public static final By GridMerchBadge =By.cssSelector("div.plp-list-img-staff-bot-bust"); //Merchandising badge
	public static final By GridLtdQty =By.cssSelector("div.plp-product-buy-limited"); //Ltd Qty.
	public static final By GridMix6Price =By.cssSelector("div.plp-product-buy-price-mix"); //Mix6 price
	public static final By GridEDLP = By.cssSelector("div.plp-product-buy-actual-price"); //EDLP
	public static final By GridRating =By.cssSelector("div.pdpRatingStars"); //Rating (stars) 
	public static final By GridReviews =By.cssSelector("a.analyticsProductReviews"); //No. of reviews
	public static final By GridShippingBadge =By.cssSelector("div.plp-product-shipping"); //Shipping badge
	public static final By GridPickupBadge =By.cssSelector("div.plp-product-delivery"); //Pickup badge
	public static final By GridATC =By.cssSelector("form.add_to_cart_form.clear_fix"); // Add to cart
	public static final By GridATL =By.cssSelector("button.btn.btn-brown-pattern.anAddToListInit"); //Add to listBy.cssSelector("div.plp-list-img-wdlogo"); //WD Logo
}
