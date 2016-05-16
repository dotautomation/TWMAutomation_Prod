package com.totalwine.test.aml;

import com.totalwine.test.actions.AML;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.*;
import java.io.IOException;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.trials.Browser;

public class LoginLogout extends Browser {
	public String IP = "71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
}
		@Test
		public void LoginLogoutTest () throws InterruptedException, BiffException, IOException {
			logger=report.startTest("Login Logout Test");
			SiteAccess.ActionAccessSite(driver, IP);
			AML.ActionLogin(driver,ConfigurationFunctions.TESTLOGIN,ConfigurationFunctions.TESTPWD);
			AML.ActionLogout(driver);
			Thread.sleep(3000);
	}
}