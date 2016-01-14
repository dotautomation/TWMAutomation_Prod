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
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.config.ConfigurationFunctions;

public class Browser {

	protected WebDriver driver;
	
	
	@BeforeMethod
	
	@Parameters("browser") 
	public void openBrowser(String browser) {
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
			//File file = new File("C:/totalwine/Library/IEDriverServer.exe");
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
			//File file = new File("C:/totalwine/Library/chromedriver.exe");
			File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver();
		}
		//iOS (iPhone 6)
		if (browser.equalsIgnoreCase("iOS")) {
			//File file = new File("C:/totalwine/Library/chromedriver.exe");
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
			//File file = new File("C:/totalwine/Library/chromedriver.exe");
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
		//Android (Samsung Galaxy S4)
		if (browser.equalsIgnoreCase("CUA")) {
			//File file = new File("C:/totalwine/Library/chromedriver.exe");
			File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		}
		//Headless
		if (browser.equalsIgnoreCase("Headless")) {
			// Declaring and initializing the HtmlUnitWebDriver
			HtmlUnitDriver driver = new HtmlUnitDriver();
		}
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(scrFile, new File("c:\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
			FileUtils.copyFile(scrFile, new File("C:\\Users\\rsud\\.jenkins\\userContent\\FailureScreenshots\\Production\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
			
		}
		driver.close();
	}
	
	@AfterClass
	public void quit() throws IOException { 
		driver.quit();	
	}
}
