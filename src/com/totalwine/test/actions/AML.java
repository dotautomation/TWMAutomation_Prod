package com.totalwine.test.actions;

import org.openqa.selenium.WebDriver;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.pages.PageSignInModal;

public class AML {

	//Login
	public static void ActionLogin (WebDriver driver, String user,String password) throws InterruptedException {
		if (driver.findElement(PageGlobal.NewSiteIntroClose).isDisplayed())
			driver.findElement(PageGlobal.NewSiteIntroClose).click();
		Thread.sleep(5000);
		driver.findElement(PageGlobal.TopNavAccount).click();
		Thread.sleep(2000);
		driver.findElement(PageGlobal.SignInto).click();
		Thread.sleep(2000);
		driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(PageSignInModal.ModalUsername).clear();
	    driver.findElement(PageSignInModal.ModalUsername).sendKeys(ConfigurationFunctions.TESTLOGIN);
	    driver.findElement(PageSignInModal.ModalPassword).clear();
	    driver.findElement(PageSignInModal.ModalPassword).sendKeys(ConfigurationFunctions.TESTPWD);
	    driver.findElement(PageSignInModal.ModalSigninButton).click();
	    Thread.sleep(6000);
	}
	
	//Logout
	public static void ActionLogout (WebDriver driver) throws InterruptedException {
		driver.findElement(PageGlobal.TopNavAccountLoggedin).click();
		Thread.sleep(2000);
		driver.findElement(PageGlobal.LogOut).click();
	}
}
