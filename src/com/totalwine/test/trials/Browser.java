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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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


public class Browser {

	protected WebDriver driver;
	protected String hubURL = "http://prt-dotautotest.totalwine.com:5566/wd/hub";
	protected ExtentTest logger;
	protected static ExtentReports report = getReporter(); //Reporting v2
	protected SoftAssert sAssert = new SoftAssert(); //Soft assertion
	
	@BeforeMethod
	
	@Parameters("browser") 
	public void openBrowser(@Optional String browser) {
		//Firefox
		if(browser.equalsIgnoreCase("FF")) {
			ProfilesIni profile = new ProfilesIni();
			//FirefoxProfile testProfile = profile.getProfile("WebDriver");
			FirefoxProfile testProfile = profile.getProfile("Automation");
			//FirefoxProfile automationProfile = new FirefoxProfile();
			//File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			//FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			//testProfile.setPreference("webdriver.load.strategy", "unstable");
			driver = new FirefoxDriver();
			//testProfile.setEnableNativeEvents(true);
		}
		//IE
		if (browser.equalsIgnoreCase("IE")) {
			File file = new File(ConfigurationFunctions.IEDRIVERPATH);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("ignoreProtectedModeSettings" , true);
			//Clear Cookies
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
				Thread.sleep(7000); //Wait till cookies clear
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver = new InternetExplorerDriver(caps);
			driver.manage().deleteAllCookies();
		}
		//Chrome
		if (browser.equalsIgnoreCase("Chrome")) {
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec("taskkill /f /im chromedriver.exe /t");
				Thread.sleep(7000); //Wait till cookies clear
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver();
		}
		//iOS (iPhone 6)
		if (browser.equalsIgnoreCase("iOS")) {
			File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Apple iPhone 6");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		}
		//Android (Samsung Galaxy S4)
		if (browser.equalsIgnoreCase("Android")) {
			File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Samsung Galaxy S4");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		}
		//iPad
		if (browser.equalsIgnoreCase("iPad")) {
			File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Apple iPad");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		}
		//Headless
		if (browser.equalsIgnoreCase("Headless")) {
			// Declaring and initialising the HtmlUnitWebDriver
			HtmlUnitDriver driver = new HtmlUnitDriver();
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
		//Grid - Chrome
		if(browser.equalsIgnoreCase("GridChrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
		    cap.setBrowserName("chrome");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURL),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		//Grid - IE
		if(browser.equalsIgnoreCase("GridIE")) {
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		    cap.setBrowserName("internet explorer");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURL),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		//Grid - iOS (iPhone 6)
		if (browser.equalsIgnoreCase("iOSGrid")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Apple iPhone 6");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		    cap.setBrowserName("chrome");
		    cap.setPlatform(Platform.VISTA);
		    try {
				driver = new RemoteWebDriver(new URL(hubURL),cap); //Hub URL
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Global 10 Seconds wait time
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String scrName = "FAIL_"+testResult.getName()+"_"+ConfigurationFunctions.now()+".png"; //Name of screenshot file
			String scrFileName = "C:\\Users\\rsud\\.jenkins\\userContent\\FailureScreenshots\\Production\\"+scrName;
			File FailedFile = new File (scrFileName);
			FileUtils.copyFile(scrFile, FailedFile);
			String relativePath = "/userContent/FailureScreenshots/Production/"+scrName; 
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
			report = new ExtentReports(ConfigurationFunctions.RESULTSPATH+"ProductionTestResults.html", true, DisplayOrder.NEWEST_FIRST);
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
