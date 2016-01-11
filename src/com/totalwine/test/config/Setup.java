package com.totalwine.test.config;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Setup {
	private WebDriver driver;

	@BeforeSuite
	  public void setUp() throws Exception {
		//this.driver = ConfigurationFunctions.driver;
	    driver.manage().window().maximize();
	    //ConfigurationFunctions.initialStartUp("71.193.51.0");
	  }
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
}
