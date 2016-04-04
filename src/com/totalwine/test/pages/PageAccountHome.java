package com.totalwine.test.pages;

import org.openqa.selenium.By;

	public class PageAccountHome {
		public static final By AccountHome = By.cssSelector("ul:nth-child(1) > li:nth-child(1) > ul > li:nth-child(1) > a > span");
		public static final By UpdateAccountDetails = By.cssSelector("#topLink1[class='analyticsUpdateAcc']");
		public static final By RequestMissingPoints = By.cssSelector("#topLink2 > div > div > a");
		public static final By BreadcrumbAccountHome  = By.cssSelector("div > ul > li:nth-child(3) > a");
		public static final By OnlineOrders  = By.cssSelector("ul:nth-child(1) > li:nth-child(2) > ul > li:nth-child(1) > a > span");
		public static final By InStoreOrders  = By.cssSelector("span:nth-child(1) > a:nth-child(3)");
		public static final By ChangeStore  = By.cssSelector("#topLink1[data-target='#editStore']");
		public static final By BrowseEvents  = By.cssSelector(".analyticsRegisterEvent");
		public static final By EditPreferredStore  = By.cssSelector("#editStore > div > div > div > p > a");
//		public static final By EditPreferredStore  = By.cssSelector("section#editStore.modal.fade.email-signup-overlay.co-create-acc.in div.modal-dialog div.modal-content > div.modal-body > p.close > a.btn-close");

		public static final By YourProfile = By.cssSelector("li:nth-child(1) > ul > li:nth-child(2) > a > span");
		public static final By EditPersonalInfo = By.cssSelector(".account-edit.analyticsEditProf[data-target='#editProfile']");
		public static final By EditProfileClose  = By.cssSelector("#editProfile > div > div > div > p > a");
		public static final By EditSignInInfo = By.cssSelector(".account-edit.analyticsEditProf[data-target='#editLogin']");
		public static final By EditSignInClose  = By.cssSelector("#editLogin > div > div > div > p > a");
		public static final By EditStoreDeliveryInfo = By.cssSelector(".account-edit.analyticsEditProf[data-target='#editStore']");
		public static final By EditStoreDeliveryClose  = By.cssSelector("#editStore > div > div > div > p > a");

		public static final By AddressBook = By.cssSelector("ul:nth-child(1) > li:nth-child(1) > ul > li:nth-child(4) > a > span");
		public static final By EditProfileAddress = By.cssSelector("span.account-edit.account-edit-address > a");
		public static final By EditAddressClose  = By.cssSelector("#btnClose[title='close']");
		public static final By AddNewAddress = By.cssSelector(".icon.icon-add-new-address.analyticsAddFunc");
		public static final By AddAddressClose  = By.cssSelector("#newAddress > div > div > div > p > a");
		
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
		public static final By OrderHistoryDetailISPPoints = By.cssSelector("div[id*=loyaltypointsInStorediv] > span");
}