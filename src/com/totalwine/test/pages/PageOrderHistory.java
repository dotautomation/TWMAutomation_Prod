package com.totalwine.test.pages;

import org.openqa.selenium.By;

	public class PageOrderHistory {
		public static final By OnlineOrderHistory = By.cssSelector("section.left-nav-container > ul > li > ul > li > a[href*=\"/my-account/orders\"]");
		public static final By InStorePurchaseHistory = By.cssSelector("section.left-nav-container > ul > li > ul > li > a[href*=\"/my-account/instoreorders\"]");
		public static final By OrdersPlacedWithin = By.cssSelector("div.sort-drop.oh-ordersPlacedIn > div > div.customselect");
		public static final By OrderPlacedWithinDropdown = By.cssSelector("div.sort-drop.oh-ordersPlacedIn > div > div > span.itemval");
		public static final By Last24Months = By.cssSelector("li.undefined.anOption[data-val=\"24\"]");
		public static final By OrderHistoryAccordian = By.cssSelector("div.oh-accordion-closed");
		public static final By OrderDetailAccess = By.cssSelector("div.oh-accordion-table.hide:nth-child(3) > div > div.col-1 > span.icon");
		public static final By OrderHistoryDetailAddress = By.cssSelector("div.osShippingAddress");
		public static final By OrderHistoryDetailTotal = By.cssSelector("div.osTotalSection");
		public static final By OrderHistoryDetailProduct = By.cssSelector("div.prodTitle");
		public static final By OrderHistoryDetailUnitPrice = By.cssSelector("div.single-bottle");
		public static final By OrderHistoryDetailItemTotal = By.cssSelector("span.price-text.item-total");
		public static final By OrderHistoryDetailReorder = By.cssSelector("button.anReorder");
		public static final By OrderHistoryPagination = By.cssSelector("a.pager-next");
		//public static final By OrderHistoryDetailISPPoints = By.cssSelector("div.oh-accordion-table:nth-child(3) > div > section > div[id*=loyaltypointsInStorediv] > span");
		public static final By OrderHistoryDetailISPPoints = By.cssSelector("div[id*=loyaltypointsInStorediv] > span");
		
}
