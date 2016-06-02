package com.totalwine.test.trials;

/*
 * Parent Class for Browser Parameterization
 * Browsers:
 * 1. Firefox
 * 2. Chrome
 * 3. IE
 * 6. iOS Safari (Apple iPhone 6)
 * 7. Android (Samsung Galaxy S4)
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;

public class ParallelBrowser {

	protected WebDriver driver;
	protected String hubURL = "http://prt-dotautotest.totalwine.com:5566/wd/hub";
	protected String hubURLTC = "http://PRT-D9FWQ32.totalwine.com:5566/wd/hub";
	protected String hubURLMK = "http://PRT-2RPSD12.totalwine.com:5566/wd/hub";
	protected String hubURLEC = "http://prt-55kxl32.totalwine.com:5566/wd/hub";
	protected String hubURLMdH = "http://PRT-4QRSD12.totalwine.com:5566/wd/hub";
	protected ExtentTest logger;
	protected static ExtentReports report = getReporter(); //Reporting v2
	protected JavascriptExecutor js = (JavascriptExecutor)driver;
	protected SoftAssert sAssert = new SoftAssert(); //Soft assertion
	
	@BeforeMethod
	
	@Parameters("browser") 
	public void openBrowser(@Optional String browser) {
		//Firefox
		if(browser.equalsIgnoreCase("FF")) {
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile testProfile = profile.getProfile("Automation");
			driver = new FirefoxDriver();
		}
		//Grid - Firefox
		if(browser.equalsIgnoreCase("GridFF")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
		    cap.setBrowserName("firefox");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURL),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		if(browser.equalsIgnoreCase("GridTC")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
		    cap.setBrowserName("firefox");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL("http://10.125.18.47:5566/wd/hub"),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		if(browser.equalsIgnoreCase("GridEC")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
		    cap.setBrowserName("firefox");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURLEC),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		//Grid - Chrome
		if(browser.equalsIgnoreCase("GridMdH")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
		    cap.setBrowserName("chrome");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURLMdH),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		if(browser.equalsIgnoreCase("GridMK")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
		    cap.setBrowserName("firefox");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURLMK),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String scrName = "FAIL_"+testResult.getName()+"_"+ConfigurationFunctions.now()+".png"; //Name of screenshot file
			String scrFileName = "C:\\Users\\rsud\\.jenkins\\userContent\\FailureScreenshots\\UAT\\"+scrName;
			File FailedFile = new File (scrFileName);
			FileUtils.copyFile(scrFile, FailedFile);
			String relativePath = "/userContent/FailureScreenshots/UAT/"+scrName; 
			String screenshot = logger.addScreenCapture(relativePath);
			System.out.println(testResult.getThrowable().toString().split(":")[0]); //Exception Handling & Reporting
			String logOutput = ExceptionHandler(testResult.getThrowable().toString().split(":")[0]);
			logger.log(LogStatus.FAIL, testResult.getName()+" failed",screenshot);
			logger.log(LogStatus.FAIL,"Error Stack: "+testResult.getThrowable());
			logger.log(LogStatus.FAIL,"Error Description: "+logOutput);
		}
		else if (testResult.getStatus() == ITestResult.SUCCESS) 
			logger.log(LogStatus.PASS,testResult.getName()+" passed");
		report.endTest(logger);
		report.flush();
		driver.close();
	}
	
	@AfterClass
	public void quit() throws IOException { 
		driver.quit();	
	}
	
	public static synchronized ExtentReports getReporter() {
		if (report == null) {
			//report = new ExtentReports(ConfigurationFunctions.RESULTSPATH+"BugfixTestResults "+ConfigurationFunctions.now()+".html", true, DisplayOrder.NEWEST_FIRST);
			report = new ExtentReports(ConfigurationFunctions.RESULTSPATH+"UATTestResults.html", true, DisplayOrder.NEWEST_FIRST);
		}
		return report;
	}
	
	public static String ExceptionHandler (String exception) {
		String log = null;
		if (exception.contains("AssertionError"))
			log = "An assertion failed indicating that an element was expected to be present or absent, but it wasn't" ;
		else if (exception.contains("NoSuchElementException"))
			log = "An expected element was not located on the page" ;
		else if (exception.contains("StaleElementReferenceException"))
			log = "An element no longer appears" ;
		else if (exception.contains("ElementNotVisibleException"))
			log = "Interaction with an expected element did not happen";
		return log;
	}
	
	public static void PageLoad(WebDriver driver) {
	    new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
}
