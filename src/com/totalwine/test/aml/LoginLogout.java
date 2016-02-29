package com.totalwine.test.aml;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.totalwine.test.actions.AML;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.*;

public class LoginLogout {
	public static void main (String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		SiteAccess.ActionAccessSite(driver,"71.193.51.0");
		for (int i=0;i<15;i++) {
			AML.ActionLogin(driver,ConfigurationFunctions.TESTLOGIN,ConfigurationFunctions.TESTPWD);
			AML.ActionLogout(driver);
			Thread.sleep(3000);
		}
	}
}
