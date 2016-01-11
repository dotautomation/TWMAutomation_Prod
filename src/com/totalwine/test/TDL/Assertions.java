package com.totalwine.test.TDL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Assertions {
	 public void testUntitled() throws Exception {
		 	WebDriver driver = new FirefoxDriver();
		    Assert.assertEquals(driver.findElements(By.linkText("Conte Priola Pinot Grigio")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false);
		    // Next Page (Login/Checkout as Guest)
		    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false);
		    // Checkout Tab 1
		    Assert.assertEquals(driver.findElements(By.cssSelector("a.instorepickup-tab")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.linkText("Edit Cart")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.linkText("Do you want to use a Promo Code?")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("section.instorepickup > h2")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("section.giftmessage > div.checkStyle > label")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("section.someoneelsepicking > div.checkStyle > label")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
		    // Checkout Tab 2
		    Assert.assertEquals(driver.findElements(By.cssSelector("a.billing-tab")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("span.hover.icon-que")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.linkText("Edit Cart")).isEmpty(),false);
		    // Checkout Tab 3
		    Assert.assertEquals(driver.findElements(By.cssSelector("a.review-tab")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-instore\"]")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-pymnt\"]")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw\"]")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-list-img-wdlogo > img")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_1\"]")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_2\"]")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total anTax\"]")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total co-pr-item-total\"]")).isEmpty(),false);
		    // Order Confirmation
		    Assert.assertEquals(driver.findElements(By.linkText("Post to Facebook")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-help-link")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
		  }


}
