package com.totalwine.test.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.pages.PageSignInModal;

public class PageLoadTime /*extends Browser*/ {
	protected WebDriver driver;

	@Test //(invocationCount=5)
	public void PageTimingTest () throws InterruptedException, IOException, WriteException {
		int count=49;//Take 50 measures
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("taskkill /f /im chromedriver.exe /t");
			rt.exec("taskkill /f /im chrome.exe /t");
			Thread.sleep(7000); //Wait till cookies clear
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//int count500=0;
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".csv";
		File logFile=new File(timeLog);
		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		writer.write("Initial page with Age Gate,Homepage,Wine Category Landing,Wine SubCat Landing,Wine PLP,Wine PDP,Login,Add to Cart,Shopping Cart,Change Store Modal,Change Store,Spirits Cat Land,Spirits SubCat Land,Spirits PLP,Spirits PDP,Beer Cat Land,Beer SubCat Land,Beer PLP,Beer PDP,Timestamp");
		writer.newLine();
		do {
			try {
				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				//File file = new File("C:/totalwine/Library/chromedriver.exe");
				File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				
				long start = System.currentTimeMillis();
				//Initial Load
				driver.get("http://uat.totalwine.com/?remoteTestIPAddress=98.169.134.0");
				long finish = System.currentTimeMillis();
				long totalTime = finish-start;
				//catch500(count500);
				//JSESSIONID
			    String jsessionIdCookieValue = driver.manage().getCookieNamed("JSESSIONID").getValue();
			    System.out.println(jsessionIdCookieValue);

				System.out.println("Initial page with Age Gate,"+totalTime); 
				String s = Objects.toString(totalTime, null);
				writer.write(s+",");
				
				driver.findElement(PageGlobal.AgeGateYes).click();
				Thread.sleep(5000);
//				driver.findElement(By.id("doNotShowCheck")).click();
//				driver.findElement(PageGlobal.NewSiteIntroClose).click();
			    Thread.sleep(5000);

			    //Homepage
			    start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com");
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Homepage,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				
				Thread.sleep(2000);
				
				//Wine CatLand
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/wine/c/c0020");
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Wine Category Landing,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Wine SubCatLand
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/wine/champagne-sparkling-wine/c/000005");
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Wine SubCat Landing,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Wine PLP
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/wine/champagne-sparkling-wine/c/000005?viewall=true");
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Wine PLP,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Wine PDP
				start = System.currentTimeMillis();
			    //driver.get("http://uat.totalwine.com/wine/white-wine/chardonnay/cloud-break-chardonnay/p/110892750");
			    driver.get("http://uat.totalwine.com/wine/red-wine/cabernet-sauvignon/radius-cabernet/p/109682750");
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Wine PDP,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);

			    //Access the sign in modal
			    driver.findElement(PageGlobal.TopNavAccount).click();
			    Thread.sleep(2000);
			    
			    //Enter valid credentials for an account having an online and in-store order history
			    driver.switchTo().frame("iframe-signin-overlay");
			    driver.findElement(PageSignInModal.ModalUsername).clear();
			    driver.findElement(PageSignInModal.ModalUsername).sendKeys("mhossain@totalwine.com");
			    driver.findElement(PageSignInModal.ModalPassword).clear();
			    driver.findElement(PageSignInModal.ModalPassword).sendKeys("grapes123");
				start = System.currentTimeMillis();
			    driver.findElement(PageSignInModal.ModalSigninButton).click();
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Login,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);

				//Merge Cart Modal
				if(driver.findElements(By.cssSelector("button.btn.btn-red.cartMergeBtn")).size()!=0) {
					driver.findElement(By.cssSelector("button.btn.btn-red.cartMergeBtn")).click();
					Thread.sleep(2000);
				}

				//ATC
			    driver.get("http://uat.totalwine.com/wine/red-wine/cabernet-sauvignon/radius-cabernet/p/109682750");
			    Thread.sleep(2000);
			    String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
				Thread.sleep(1000);
			    start = System.currentTimeMillis();
			    driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Add to Cart,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				
				//Cart Access
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/cart");
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Shopping Cart,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(5000);
				/* Commenting due to script issue
				//Checkout Tab 1
				String ISPOption="StandardPickup24Hr";
				WebElement scroll = driver.findElement(By.id("checkout"));
			    scroll.sendKeys(Keys.PAGE_DOWN);
			    Thread.sleep(2000);
			    driver.findElement(By.cssSelector("#deliveryModeInStore > div.customselect > span.itemval")).click();
			    Thread.sleep(2000);
			    driver.findElement(By.cssSelector("li[data-val="+ISPOption+"]")).click();
			    Thread.sleep(3000);
			    start = System.currentTimeMillis();
			    driver.findElement(By.id("checkout")).click();
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Checkout Tab 1,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				driver.findElement(By.linkText("Edit cart")).click();
				Thread.sleep(1000);
				WebElement scroll1 = driver.findElement(By.id("checkout"));
				scroll1.sendKeys(Keys.PAGE_DOWN);
			    scroll1.sendKeys(Keys.PAGE_DOWN); ////Laptop Mode
			    driver.findElement(By.cssSelector("a#RemoveProduct_0")).click();
			    Thread.sleep(2000);
				*/
				
				//Change Store Modal
				driver.findElement(By.cssSelector("div.parent-header-wrapper > div > nav > ul > li.header-chg-loc.nav-li.toggle-cont > a")).click();
				Thread.sleep(5000);
				start = System.currentTimeMillis();
				driver.findElement(By.cssSelector("#changeLocation > div > div > div > div > div > div.store-filter-wrapper > ul > li:nth-child(1) > a")).click();
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Change Store Modal,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Change Store
				driver.findElement(By.id("newStoreSearch_box")).click();
		        driver.findElement(By.id("newStoreSearch_box")).sendKeys("90210");
		        driver.findElement(By.xpath("//section[@id='changeLocation']//button[.='Search']")).click();
		        start = System.currentTimeMillis();
		        driver.findElement(By.id("changeStoreBtn")).click();
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Change Store,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Spirits CatLand
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/spirits/c/c0030");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Spirits Cat Land,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Spirits SubCatLand
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/spirits/scotch/c/000885");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Spirits SubCat Land,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Spirits PLP
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/spirits/scotch/c/000885?viewall=true");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Spirits PLP,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Sprits PDP
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/spirits/scotch/blended-scotch/johnnie-walker-black/p/636175");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Spirits PDP,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Beer CatLand
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/beer/c/c0010");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Beer Cat Land,"+totalTime);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Beer SubCatLand
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/beer/big-breweries/c/41513");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Beer SubCat Land,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Beer PLP
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/beer/big-breweries/c/41513?viewall=true");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Beer PLP,"+totalTime);
				writer.write(s+",");
				Thread.sleep(2000);
				
				//Beer PDP
				start = System.currentTimeMillis();
			    driver.get("http://uat.totalwine.com/beer/lager/light-lager/coors-light/p/3283124");
				//element = driver.findElement(By.cssSelector("div.carousel-indicators-wrapper"));
				finish = System.currentTimeMillis();
				totalTime = finish-start; 
				//catch500(count500);
				System.out.println("Beer PDP,"+totalTime);
				s = Objects.toString(totalTime, null);
				writer.write(s+",");
				Thread.sleep(2000);
				count--;
				writer.write(timestamp+",");
				writer.newLine();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				//pass=true;
				count--;
				writer.newLine();
				writer.close();
			}
		driver.quit();
		System.out.println(">>>>>>>>>>>>>>>>> "+count+" runs remaining");
		}
		//while (pass);
		while (count>=0);
		//System.out.println("No. of occurences of HTTP500 Errors: "+count500);
		writer.close();
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("C:\\Users\\rsud\\.jenkins\\userContent\\FailureScreenshots\\Performance\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png"));
		}
		driver.quit();
	}
	
	//Function to detect HTTP500
	public static int catch500(int count500) {
		//String pageTitle = driver.getTitle();
		//if (pageTitle.equals("500 Error | Total Wine & More"))
			//count500++;
		return count500;
	}
}	