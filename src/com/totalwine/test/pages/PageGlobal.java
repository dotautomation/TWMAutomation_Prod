package com.totalwine.test.pages;

import org.openqa.selenium.By;

public class PageGlobal {
	
	//Age Gate
	public static final By AgeGateYes = By.id("btnYes");
//	public static final By AgeGateNo = By.id("btnNo");
	public static final By AgeGateNo = By.cssSelector("#btnNo");
	
	//New Site Intro
	public static final By NewSiteIntroClose = By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close");
	
	//Top Nav
	public static final By TopNavClassesEvents = By.cssSelector("a.analyticsHeaderLink[href*=\"/events/\"]");
	public static final By TopNavGuidesAdvice = By.cssSelector("a[href*=\"/guides-and-advice\"]");
//	public static final By TopNavFindStore = By.cssSelector("a.analyticsHeaderLink[href*=\"/store-finder\"]");
	public static final By TopNavFindStore = By.cssSelector("div.top-header-wrapper > div.header-wrapper > ul:nth-child(2) > li:nth-child(1) > a > span.list-text.analyticsHeaderLink");
	public static final By TopNavAccount = By.cssSelector("div.top-header-wrapper > div.header-wrapper > ul:nth-child(2) > li:nth-child(2) > a > span.list-text");
	public static final By TopNavAccountLoggedin = By.cssSelector("a.analyticsHeaderLink[data-modal-id=loggedin-wrapper]");
	public static final By TopNavShoppingList = By.cssSelector("a.analyticsHeaderLink[href*=\"/shoppinglist\"]");
	public static final By StoreSelection = By.cssSelector("span.store-details-store-name.flyover-src");
	public static final By MiniCart = By.cssSelector("div.search-right-cont-add-to-cart.analyticsViewCart");
	
	//Search Bar
	
	//Top Level Menu
	
	//Footer
	public static final By FooterEmailSignup = By.cssSelector("span.footer-Email-text.analyticsJoinOurEmail");
	
	public static final By CustomerService = By.cssSelector("body > footer > div > div.footer-third-cont > ul > li:nth-child(4)");
	
	public static final By FB = By.cssSelector(".fluid-icons.fluid-icons-facebook.analyticsFB");
	public static final By Twitter = By.cssSelector(".fluid-icons.fluid-icons-twitter.analyticsTwi");
	public static final By GooglePlus = By.cssSelector(".fluid-icons.fluid-icons-googleplus.analyticsGPlus");
	public static final By YouTube = By.cssSelector(".fluid-icons.fluid-icons-youtube.analyticsYTube");
	public static final By Pinterest = By.cssSelector(".fluid-icons.fluid-icons-pinterest.analyticsPIn");
	public static final By Instagram = By.cssSelector(".fluid-icons.fluid-icons-instagram");
	public static final By Blog = By.cssSelector(".fluid-icons.fluid-icons-blog");
	

	//Location Intercept
	public static final By LocationInterceptNo = By.cssSelector("div.ChooseStoreButtons > button.btn.btn-gray");
	
	//Account Management
	
	//Not logged in
//	public static final By SignUp = By.cssSelector("#sign-in-overlay > div.login-description > div > div.left-content > p > a");
	public static final By SignUp = By.cssSelector(".btn.btn-red.analyticsGetStarted.post-links.post-links-bv");
//	public static final By SignInto = By.cssSelector("a.btn.btn-red.acc-link.analyticsSignIn");
	public static final By SignInto = By.cssSelector("div.top-header-wrapper > div.header-wrapper > ul:nth-child(3) > li:nth-child(2) > a > span.list-text");
	
	//Logged in
	public static final By LogOut = By.cssSelector("a.analyticsHeaderLink[href*=\"/logout\"]");
	public static final By AccountProfile = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/profile\"]");
	public static final By SavedCards = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/payment-details\"]");
	public static final By AddressBook = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/address-book\"]");
	public static final By Preferences = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/preferences\"]");
	public static final By OnlineOrderHistory = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/orders\"]");
	public static final By InStorePurchaseHistory = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/instoreorders\"]");
	public static final By ShoppingList = By.cssSelector("a.analyticsHeaderLink[href*=\"/shoppinglist\"]");
	public static final By TotalDiscoveryFAQs = By.cssSelector("a.analyticsHeaderLink[href*=\"/total-discovery/faq\"]");
}