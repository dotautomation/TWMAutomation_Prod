package com.totalwine.test.performance;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import jxl.*; 
import jxl.read.biff.BiffException;

public class CachePrimer {
	static WebDriver driver;
	public static void main (String[] args) throws InterruptedException, BiffException, IOException  {
		File file = new File("C:/totalwine/Library/chromedriver.exe");
		Workbook workbook = Workbook.getWorkbook(new File("Top100.xls"));
		Sheet sheet = workbook.getSheet(0);
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//driver= new FirefoxDriver();
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get("http://www.totalwine.com/?remoteTestIPAddress=71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		//Cookie jsessionIdCookie = driver.manage().getCookieNamed("JSESSIONID");
		String jsessionIdCookieValue = driver.manage().getCookieNamed("JSESSIONID").getValue();
		//System.out.println(jsessionIdCookie); 		//Cookie
		//System.out.println(jsessionIdCookieValue);  //Cookie Value
		String [] jsessionIdCookieParts = jsessionIdCookieValue.split(Pattern.quote("."));
		//System.out.println(jsessionIdCookieParts[0]);
		
		for (int appServer=2;appServer<8;appServer++) {
			//Edit JSESSIONID cookie to hit specific App Server
			driver.manage().deleteCookieNamed("JSESSIONID");
			Cookie ck = new Cookie("JSESSIONID",jsessionIdCookieParts[0]+".app"+appServer);
			driver.manage().addCookie(ck);
			for (int i_row_count=0;i_row_count<sheet.getRows();i_row_count++) {
				driver.get("http://www.totalwine.com"+sheet.getCell(0,i_row_count).getContents());
				//Thread.sleep(2000);
			}
			System.out.println("After Cookie: "+driver.manage().getCookieNamed("JSESSIONID").getValue());	
		}
		driver.close();
	}
}
				