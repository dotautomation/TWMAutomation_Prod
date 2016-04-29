package com.totalwine.test.thirdparty;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.*;
import jxl.read.biff.BiffException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ConvergeSetUpPaymentForm {
	private WebDriver driver;
	private String baseUrl;
	private String password;
	private String accountNumber;
	private String userID;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Integer implicitlyWaitSeconds = 5;
/*
	// Change these variables as needed to control the script

	private Boolean isTestMode = false; // If true, it will just loop through
										// the terminals, but won't update any
										// fields. If false, it will make
										// updates/changes

	private Boolean isDemoURL = true; // If true, it will use the DEMO
										// environment
										// (demo.myvirtualmerchant.com). If
										// false, it will use the PRODUCTION
										// environment.
*/
	// Demo login credentials
	private String demoAccountNumber = "005485";
	private String demoUserID = "cpavetto";
	private String demoPassword = "Grapes001!^";

	// Prod login credentials
	private String prodAccountNumber = "555799";
	private String prodUserID = "cpavetto";
	private String prodPassword = "Grapes001!&";
	Workbook inputWorkbook;
	// Stores that need to be set up
	//List<String> terminals = new ArrayList<String>(Arrays.asList("TOTAL WINE COM DEMO 1108"));
	//List<String> terminals = new ArrayList<String>(Arrays.asList("TOTAL WINE COM 1606"));

	@BeforeTest
	public void setUp() throws Exception {
		driver = new FirefoxDriver();

		// Change baseUrl based on doing demo/prod
		

		driver.manage().timeouts().implicitlyWait(implicitlyWaitSeconds, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

	}

	@Test
	public void testSearch() throws Exception {
		
		Boolean isTestMode = false; // If true, it will just loop through
		// the terminals, but won't update any
		// fields. If false, it will make
		// updates/changes

		Boolean isDemoURL = false; // If true, it will use the DEMO
		// environment
		// (demo.myvirtualmerchant.com). If
		// false, it will use the PRODUCTION
		// environment.
		
		 //Input file (excel)
	    inputWorkbook = Workbook.getWorkbook(new File("converge.xls"));
	    Sheet inputSheet = inputWorkbook.getSheet(0);
	    int rowCount = inputSheet.getRows();
	    String storeString = null; 
	    for (int count=1;count<rowCount;count++) { //Consider title row
	    	storeString = inputSheet.getCell(0,count).getContents();
	    }
	    	System.out.println(storeString);
	    	//List<String> terminals = new ArrayList<String>(Arrays.asList("TOTAL WINE COM DEMO 1108"));
    	List<String> terminals = new ArrayList<String>(Arrays.asList(storeString));
		baseUrl = isDemoURL ? "https://demo.myvirtualmerchant.com" : "https://www.myvirtualmerchant.com";
		password = isDemoURL ? demoPassword : prodPassword;
		
		Boolean isDemo = baseUrl.contains("demo") ? true : false;

		if (isDemo) {
			driver.get(baseUrl + "/VirtualMerchantDemo/login.do");
			accountNumber = demoAccountNumber;
			userID = demoUserID;
		} else {
			driver.get(baseUrl + "/VirtualMerchant/login.do");
			accountNumber = prodAccountNumber;
			userID = prodUserID;
		}

		// Login to the UI
		driver.findElement(By.name("loginDto.viaKlixID")).clear();
		driver.findElement(By.name("loginDto.viaKlixID")).sendKeys(accountNumber);
		driver.findElement(By.name("loginDto.userID")).clear();
		driver.findElement(By.name("loginDto.userID")).sendKeys(userID);
		driver.findElement(By.name("loginDto.pwd")).clear();
		driver.findElement(By.name("loginDto.pwd")).sendKeys(password);
		driver.findElement(By.xpath("//div[@id='section_0']/div/div/form/button")).click();

		// Keep track of what's been done - for logging purposes
		List<String> terminalsDone = new ArrayList<String>();

		for (String terminal : terminals) {

			// Open terminal and navigate to payment fields page

			driver.findElement(By.linkText("Select Terminal")).click();
			new Select(driver.findElement(By.name("transSearchCriteriaDto.display"))).selectByVisibleText("1000");
			driver.findElement(By.cssSelector("option[value=\"1000\"]")).click();
			driver.findElement(By.xpath("//a[contains(text(),\"" + terminal + "\")]")).click();

			driver.findElement(By.xpath("//a[contains(text(),'Terminal')]")).click();

			driver.findElement(By.xpath("(//a[contains(text(),'Payment Fields')])[2]")).click();

			// Stop here and go to next terminal if it's test mode
			if (isTestMode) {
				continue;
			}

			System.out.println(String.format(" ************** Starting set up for terminal %s", terminal));

			// Part 1 - add custom fields

			// Add "Card Nick Name"
			if (!elementExistsByLinkText("custom_card_nickname")) {
				driver.findElement(By.name("addField")).click();
				driver.findElement(By.name("paymentFieldDto.fieldName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldName")).sendKeys("custom_card_nickname");
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Card Nick Name");
				driver.findElement(By.name("paymentFieldDto.fieldSize")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldSize")).sendKeys("100");
				if (!driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}

			// Add "Card Type"
			if (!elementExistsByLinkText("custom_card_type")) {
				driver.findElement(By.name("addField")).click();
				driver.findElement(By.name("paymentFieldDto.fieldName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldName")).sendKeys("custom_card_type");
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Card Type");
				new Select(driver.findElement(By.name("paymentFieldDto.fieldType"))).selectByVisibleText("Drop Down");
				driver.findElement(By.name("paymentFieldDto.dropDownOrRadioValues")).clear();
				driver.findElement(By.name("paymentFieldDto.dropDownOrRadioValues"))
						.sendKeys("VISA:MASTER:DISCOVER:AMEX");
				driver.findElement(By.name("paymentFieldDto.fieldSize")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldSize")).sendKeys("20");
				if (!driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.showInReceipt")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInReceipt")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (!driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}

			// Add "Save Card" checkbox
			if (!elementExistsByLinkText("custom_save_card")) {
				driver.findElement(By.name("addField")).click();
				driver.findElement(By.name("paymentFieldDto.fieldName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldName")).sendKeys("custom_save_card");
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Save Card");
				new Select(driver.findElement(By.name("paymentFieldDto.fieldType"))).selectByVisibleText("Drop Down");
				new Select(driver.findElement(By.name("paymentFieldDto.fieldType"))).selectByVisibleText("Checkbox");
				driver.findElement(By.name("paymentFieldDto.fieldSize")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldSize")).sendKeys("4");
				if (!driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.showInReceipt")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInReceipt")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}
				;
				driver.findElement(By.name("AddField.X")).click();
			}

			// Add "Address nickname" input
			if (!elementExistsByLinkText("custom_address_nickname")) {
				driver.findElement(By.name("addField")).click();
				driver.findElement(By.name("paymentFieldDto.fieldName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldName")).sendKeys("custom_address_nickname");
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Address nickname");
				driver.findElement(By.name("paymentFieldDto.fieldSize")).clear();
				driver.findElement(By.name("paymentFieldDto.fieldSize")).sendKeys("100");
				if (!driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}
				;
				if (!driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}
				;
				driver.findElement(By.name("AddField.X")).click();
			}

			// Part 2 - reorder fields

			// Grab the table
			WebElement table = driver.findElement(By.name("paymentFields"));

			// Now get all the TR elements from the table
			List<WebElement> allRows = table.findElements(By.tagName("tr"));

			// find where the custom card nickname field is, so we can adjust it
			// up to first position
			Integer currentRowPosition = getRowNumber(allRows,
					By.xpath(".//a[contains(@onclick, \"'U','custom_card_nickname'\")]/*[1]"));

			for (int i = 0; i < (currentRowPosition - 1); i++) {
				driver.findElement(By.xpath("//a[contains(@onclick, \"'U','custom_card_nickname'\")]/*[1]")).click();
			}

			// find where the custom card type field is, so we can adjust it up
			// to 3rd position

			// Re-define these since the page will have been refreshed by the
			// reordering
			table = driver.findElement(By.name("paymentFields"));
			allRows = table.findElements(By.tagName("tr"));

			currentRowPosition = getRowNumber(allRows,
					By.xpath(".//a[contains(@onclick, \"'U','custom_card_type'\")]/*[1]"));

			for (int i = 0; i < (currentRowPosition - 3); i++) {
				driver.findElement(By.xpath("//a[contains(@onclick, \"'U','custom_card_type'\")]/*[1]")).click();
			}

			// Part 3 - change system generated fields
			//
			// Note, some unnecessary fields may or may not be there,
			// If they are not present, we can skip them. Therefore,
			// these wrapped in elementExistsByLinkText()
			// However, other fields need to be present on the form. These are
			// NOT wrapped in elementExistsByLinkText()

			if (elementExistsByLinkText("ssl_account_data")) {
				driver.findElement(By.linkText("ssl_account_data")).click();
				if (!driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_cvv2cvc2_indicator")) {

				driver.findElement(By.linkText("ssl_cvv2cvc2_indicator")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_customer_code")) {

				driver.findElement(By.linkText("ssl_customer_code")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_salestax")) {

				driver.findElement(By.linkText("ssl_salestax")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_invoice_number")) {

				driver.findElement(By.linkText("ssl_invoice_number")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_reference_number")) {

				driver.findElement(By.linkText("ssl_reference_number")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}
				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_original_date")) {

				driver.findElement(By.linkText("ssl_original_date")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}
			if (elementExistsByLinkText("ssl_original_time")) {
				driver.findElement(By.linkText("ssl_original_time")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}
				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_egc_tender_type")) {
				driver.findElement(By.linkText("ssl_egc_tender_type")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}
			if (elementExistsByLinkText("ssl_account_type")) {
				driver.findElement(By.linkText("ssl_account_type")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}

			if (elementExistsByLinkText("ssl_customer_number")) {
				driver.findElement(By.linkText("ssl_customer_number")).click();
				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}
			if (elementExistsByLinkText("ssl_description")) {
				driver.findElement(By.linkText("ssl_description")).click();
				if (driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInVirtualTerminal")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.canBeChngdOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.showInReceipt")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInReceipt")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.showInEmailToCustomer")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInEmailToCustomer")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.showInEmailToMerchant")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.showInEmailToMerchant")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnApproval")).click();
				}

				if (driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).isSelected()) {
					driver.findElement(By.name("paymentFieldDto.forwardOnDecline")).click();
				}

				driver.findElement(By.name("AddField.X")).click();
			}

			// We need these fields on the form, therefore they are not wrapped
			// in elementExistsByLinkText()
			driver.findElement(By.linkText("ssl_first_name")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("First name");
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_last_name")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Last name");
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_last_name")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Company name");
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_avs_address")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Address line 1");
			if (driver.findElement(By.name("paymentFieldDto.required")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.required")).click();
			}
			;
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_address2")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Address line 2");
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_state")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Choose a state");
			new Select(driver.findElement(By.name("paymentFieldDto.fieldType"))).selectByVisibleText("Drop Down");
			driver.findElement(By.name("paymentFieldDto.dropDownOrRadioValues")).clear();
			driver.findElement(By.name("paymentFieldDto.dropDownOrRadioValues")).sendKeys(
					"Choose a state:Alabama:Alaska:Arizona:Arkansas:California:Colorado:Connecticut:Delaware:District of Columbia:Florida:Georgia:Hawaii:Idaho:Illinois:Indiana:Iowa:Kansas:Kentucky:Louisiana:Maine:Maryland:Massachusetts:Michigan:Minnesota:Mississippi:Missouri:Montana:Nebraska:Nevada:New Hampshire:New Jersey:New Mexico:New York:North Carolina:North Dakota:Ohio:Oklahoma:Oregon:Pennsylvania:Rhode Island:South Carolina:South Dakota:Tennessee:Texas:Utah:Vermont:Virginia:Washington:West Virginia:Wisconsin:Wyoming");
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_avs_zip")).click();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldDisplayName")).sendKeys("Zip code");
			if (driver.findElement(By.name("paymentFieldDto.required")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.required")).click();
			}
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_country")).click();
			new Select(driver.findElement(By.name("paymentFieldDto.fieldType"))).selectByVisibleText("Text");
			driver.findElement(By.name("paymentFieldDto.fieldSize")).clear();
			driver.findElement(By.name("paymentFieldDto.fieldSize")).sendKeys("40");
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}
			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_phone")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_email")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_company")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_first_name")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_last_name")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_address1")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_address2")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_city")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_state")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_zip")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_country")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			driver.findElement(By.linkText("ssl_ship_to_phone")).click();
			if (driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).isSelected()) {
				driver.findElement(By.name("paymentFieldDto.showOnPmtForm")).click();
			}

			driver.findElement(By.name("AddField.X")).click();

			// Part 4 - Rename the order and billing address sections

			if (!elementExistsByLinkText("Card Section")) {
				driver.findElement(By.linkText("Order Section")).click();
				driver.findElement(By.name("paymentFieldDto.sectionDisplayName")).clear();
				driver.findElement(By.name("paymentFieldDto.sectionDisplayName")).sendKeys("Card Section");
				driver.findElement(By.name("UpdateSection.X")).click();
				System.out.println(String.format("Done setting card section for terminal %s", terminal));
			} else {
				System.out.println(String.format("Card section already exists for terminal %s", terminal));
			}

			if (!elementExistsByLinkText("Billing address")) {
				driver.findElement(By.linkText("Billing Address")).click();
				driver.findElement(By.name("paymentFieldDto.sectionDisplayName")).clear();
				driver.findElement(By.name("paymentFieldDto.sectionDisplayName")).sendKeys("Billing address");
				driver.findElement(By.name("UpdateSection.X")).click();
				System.out.println(String.format("Done setting billing address section for terminal %s", terminal));
			} else {
				System.out.println(String.format("Billing address section already exists for terminal %s", terminal));
			}

			// Done - wrap up
			System.out.println(String.format(" ************** Finished setting up terminal %s", terminal));
			terminalsDone.add(terminal);

			// Output finished terminals list
			System.out.println(" ************** Completed terminals *******************");
			System.out.println(Arrays.toString(terminalsDone.toArray()));

		}

	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean elementExistsById(String id) {
		try {
			driver.findElement(By.id(id));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	private boolean elementExistsByLinkText(String linkText) {
		// speed up finding the element
		turnOffImplicitWaits();
		try {
			driver.findElement(By.linkText(linkText));
		} catch (NoSuchElementException e) {

			// turn implicit waiting back on
			turnOnImplicitWaits();
			return false;
		}
		// turn implicit waiting back on
		turnOnImplicitWaits();

		return true;
	}

	private boolean isChildElementPresent(WebElement we, By by) {
		try {
			we.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private Integer getRowNumber(List<WebElement> allRows, By by) {
		Integer rowIndex = 0;

		// speed up finding the element
		turnOffImplicitWaits();

		// And iterate over them, getting the cells
		for (WebElement row : allRows) {

			if (isChildElementPresent(row, by)) {
				System.out.println("Found element at row " + rowIndex);
				break;
			} else {
				System.out.println("Element not found at row " + rowIndex);
			}

			rowIndex++;
		}
		// turn implicit waiting back on
		turnOnImplicitWaits();

		return rowIndex;

	}

	private void turnOffImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	private void turnOnImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(implicitlyWaitSeconds, TimeUnit.SECONDS);
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
