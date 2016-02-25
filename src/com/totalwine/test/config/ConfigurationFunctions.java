package com.totalwine.test.config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.util.Random;
@SuppressWarnings({ "unused", "deprecation" })
public class ConfigurationFunctions {

	
	//Environment
	public static final String accessURL="http://www.totalwine.com";
	public static final String backofficeURL = "http://backoffice.totalwine.com";
	//Browser Configuration Variables
	public static final String locationSet = "http://www.totalwine.com/?remoteTestIPAddress=";//Phased Launch
	public static final String URL = "http://twmuatwebserver:webserveruattwm@uat.totalwine.com/?remoteTestIPAddress=";
	public static final ProfilesIni profile = new ProfilesIni();
	public static final FirefoxProfile testProfile = profile.getProfile("WebDriver");
	public static final String csCockpitURL = "http://backoffice.totalwine.com/cscockpit";
	public static final String resourcePath = "C:\\twmautomation\\TWMAutomation_Prod\\Resources\\AutomatedFlows.xls";
	public static final String CHROMEDRIVERPATH = "C:/twmautomation/lib/lib/chromedriver.exe";
	public static final String IEDRIVERPATH = "C:/twmautomation/lib/lib/IEDriverServer.exe";
	public static final String RESULTSPATH = "C:\\autoreports\\";
	public static final String TESTLOGIN = "rsud@live.com";
	public static final String TESTPWD = "yoyo55";
	//public static final WebDriver driver = new FirefoxDriver(testProfile);
	
	public static void DeleteCookies() {
		WebDriver driver=new FirefoxDriver();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebElement element = null;
		executor.executeScript("function deleteAllCookies() { var cookies = document.cookie.split(\";\"); for (var i = 0; i < cookies.length; i++) {var cookie = cookies[i];var eqPos = cookie.indexOf(\"=\");var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie; document.cookie = name + \"=;expires=Thu, 01 Jan 1970 00:00:00 GMT\";}}",element);
	}
	
	//Initial Startup
	public static void initialStartUpPL(String ipAddress) throws InterruptedException {
		WebDriver driver=new FirefoxDriver();
		driver.get(ConfigurationFunctions.URL+ipAddress);
		Thread.sleep(5000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		System.out.println(parentHandle);               //Prints the parent window handle
		if (driver.findElements(By.linkText("here")).isEmpty()==false) //Phased Launch Screen Handling
			driver.findElement(By.linkText("here")).click();
		for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
		        System.out.println(winHandle);
		        driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)              
		    }
		if (driver.findElements(By.linkText("here")).isEmpty()==false) //Phased Launch Screen Handling
			driver.findElement(By.linkText("here")).click();
		if (driver.findElements(By.id("btnYes")).isEmpty()==false) {
			driver.findElement(By.id("btnYes")).click();
			Thread.sleep(5000);
		}
		if (driver.findElements(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).isEmpty()==false) {
		    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		    Thread.sleep(5000);
		}
		driver.switchTo().defaultContent();
		Actions action = new Actions(driver);
		action.keyUp(Keys.CONTROL);
		action.build();
		action.perform();
		driver.switchTo().window(parentHandle);
	}
	
	//Initial Startup
		public static void initialStartUp(String IP) throws InterruptedException {
			WebDriver driver=new FirefoxDriver();
			driver.get(ConfigurationFunctions.URL+IP);
			Thread.sleep(5000);
			if (driver.findElements(By.linkText("here")).isEmpty()==false) //Phased Launch Screen Handling
				driver.findElement(By.linkText("here")).click();
			if (driver.findElements(By.id("btnYes")).isEmpty()==false) {
				driver.findElement(By.id("btnYes")).click();
				Thread.sleep(5000);
			}
			if (driver.findElements(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).isEmpty()==false) {
			    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
			    Thread.sleep(5000);
			}
		}
	
	
	//Random number generator
	public static int randInt() {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    return randomNum;
	}
	
	//Highlights a test element
	public static void highlightElement(WebDriver commonDriver,WebElement element) {
        for (int i = 0; i <2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) commonDriver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            }
        }
	
	public static boolean isElementPresent(By by) {
	    try {
	      WebDriver driver=new FirefoxDriver();
	      driver.findElements(by);
	      return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	      return false;
	    }
	}
	
	public static String[] removeDuplicate (String[] arr) {
		List<String> list = Arrays.asList(arr);
        Set<String> set = new HashSet<String>(list);
        String[] result = new String[set.size()];
        set.toArray(result);
		return result;
	}
	/*
	//Supplies an array of sites subjecting each of them to the Selenium Profiler
	public static void SiteProfiler (String [] sites,Selenium selenium) throws Exception {
		WritableWorkbook workbook = Workbook.createWorkbook(new File(configFunctions.now()+"_pub.xls"));
		WritableSheet sheet = workbook.createSheet(configFunctions.now(),0);
		for (int i=0;i<sites.length;i++) {
			if (!(sites[i].isEmpty())) {
				selenium.open(sites[i]);
			//	System.out.println(conde_url_array[i].toUpperCase());
				selenium.waitForPageToLoad("30000");
				
				try {
					PubSeleniumTrafficAnalyser.profiler(selenium,selenium.getLocation(),workbook,sheet);
				}
				catch (Exception e) {
					e.printStackTrace();
					workbook.write();
					workbook.close();
				}
			}
		}
		workbook.write();
		workbook.close();
	}*/
	/*
	public static void SiteProfilerBase (String site,Selenium selenium) throws Exception {
		WritableWorkbook workbook = Workbook.createWorkbook(new File(configFunctions.now()+"_pub.xls"));
		WritableSheet sheet = workbook.createSheet(configFunctions.now(),0);
		if (!(site.isEmpty())) {
				selenium.open(site);
				selenium.waitForPageToLoad("30000");
				try {
					PubSeleniumTrafficAnalyser.profiler(selenium,selenium.getLocation(),workbook,sheet);
				}
				catch (Exception e) {
					e.printStackTrace();
					workbook.write();
					workbook.close();
				}
			}
		workbook.write();
		workbook.close();
	}
	*/
	
	//Returns the system date and time
	public static String now() {
		String DATE_FORMAT_NOW = "yyyy-MM-dd HH mm ss";
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	  }
	
	//Reads and assimilates input parameters from Excel file
	public static String[][] getTableArray(String excelFilePath,
			String sheetName, String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(excelFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow, startCol, endRow, endCol, ci, cj;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol + 1,
					startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();
			System.out.println("startRow=" + startRow + ", endRow=" + endRow
					+ ", " + "startCol=" + startCol + ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;
			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");
		}
		return (tabArray);
	}
	
	//Converts a string into an array based on the specified delimiter
	public static String[] stringtoArray(String s, String sep) {
		StringBuffer buf = new StringBuffer(s);
		int arraysize = 1;
		for (int i = 0; i < buf.length(); i++) {
			if (sep.indexOf(buf.charAt(i)) != -1)
				arraysize++;
		}
		String[] elements = new String[arraysize];
		int y, z = 0;
		if (buf.toString().indexOf(sep) != -1) {
			while (buf.length() > 0) {
				if (buf.toString().indexOf(sep) != -1) {
					y = buf.toString().indexOf(sep);
					if (y != buf.toString().lastIndexOf(sep)) {
						elements[z] = buf.toString().substring(0, y);
						z++;
						buf.delete(0, y + 1);
					} else if (buf.toString().lastIndexOf(sep) == y) {
						elements[z] = buf.toString().substring(0,
								buf.toString().indexOf(sep));
						z++;
						buf.delete(0, buf.toString().indexOf(sep) + 1);
						elements[z] = buf.toString();
						z++;
						buf.delete(0, buf.length());
					}
				}
			}
		} else {
			elements[0] = buf.toString();
		}
		buf = null;
		return elements;
	}
}
