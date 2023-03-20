package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserComputersPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserNotebooksPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;

public class Nopcommerce_05_Sort_Display_Paging extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserComputersPageObject computersPage;
	private UserNotebooksPageObject notebooksPage;

	private String validEmail, firstName, lastName, password;
	private String day, month, year;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("DEV") String serverName, @Optional("Chrome") String browser, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		driver = getBrowserDriverAll(envName, serverName, browser, ipAddress, portNumber, osName, osVersion, browserVersion);
		this.browser = browser.toUpperCase();
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = UserData.Rigester.FIRST_NAME;
		lastName = UserData.Rigester.LAST_NAME;
		validEmail = UserData.Rigester.EMAIL + generateRandomNumber() + "@gmail.com";
		password = UserData.Rigester.PASSWORD;
		day = UserData.Rigester.DAY;
		month = UserData.Rigester.MONTH;
		year = UserData.Rigester.YEAR;

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 02: Enter to First name textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 03: Enter to Last name textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 04: Select dropdown Date Of Birth Day '" + day + "'");
		registerPage.selectDropdownDay(day);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 05: Select dropdown Date Of Birth month '" + month + "'");
		registerPage.selectDropdownMonth(month);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 06: Select dropdown Date Of Birth year '" + year + "'");
		registerPage.selectDropdownYear(year);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 07: Enter to email textbox with value is '" + validEmail + "'");
		registerPage.inputToEmailTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 08: Enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 09: Enter to confirm password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 10: Click to Register button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 11: Verify register success message is displayed");
		assertEquals(registerPage.getMessageSuccess(), "Your registration completed");

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 12 : Open login page");
		loginPage = registerPage.clickToLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 13 : Login with Email and password with value is '" + validEmail + "'+'" + password + "'");
		homePage = loginPage.loginAsUser(validEmail, password);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 14 : Verify My account link displayed");
		assertTrue(homePage.isMyAccountLinkDisPlayed());

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 15: Navigate to 'Computers' page");
		computersPage = homePage.openComputersPage();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 16: Navigate to 'Notebooks' page");
		notebooksPage = (UserNotebooksPageObject) computersPage.openPageCategoriesComputersByName(driver, "Notebooks ");

	}

	@Test
	public void Sort_01_A_To_Z(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 01 - Step 01: Select dropdown product sort order with value is 'Name: A to Z'");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Name: A to Z");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 01 - Step 02: Verify product name sort A-Z");
		assertTrue(notebooksPage.isProductNameSortByAscending());

	}

	@Test
	public void Sort_02_Z_To_A(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 02 - Step 01: Select dropdown product sort order with value is 'Name: Z to A'");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Name: Z to A");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 02 - Step 02: Verify product name sort Z-A");
		assertTrue(notebooksPage.isProductNameSortByDescending());
	}

	@Test
	public void Sort_03_Low_To_High(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 03 - Step 01: Select dropdown product sort order with value is 'Price: Low to High'");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Price: Low to High");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 03 - Step 02: Verify product name sort Low to High");
		assertTrue(notebooksPage.isProductPriceSortByAscending());
	}

	@Test
	public void Sort_04_High_To_Low(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 04 - Step 01: Select dropdown product sort order with value is 'Price: High to Low'");
		notebooksPage.selectDropdownByName(driver, "products-orderby", "Price: High to Low");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 04 - Step 02: Verify product name sort High to Low");
		assertTrue(notebooksPage.isProductPriceSortByDescending());
	}

	@Test
	public void Sort_05_Display_3_Product_Page(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 05 - Step 01: Select dropdown Display with value is '3'");
		notebooksPage.selectDropdownByName(driver, "products-pagesize", "3");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 05 - Step 02: Verify product Display 3 item");
		assertEquals(notebooksPage.getProductNameSize(), 3);

		ExtentTestManager.getTest().log(Status.INFO, "Sort 05 - Step 03: Verify paging next icon Display");
		assertTrue(notebooksPage.isNextIconDisplay());

		ExtentTestManager.getTest().log(Status.INFO, "Sort 05 - Step 04: Click to page 2");
		notebooksPage.clickToPageNumberIcon();

		ExtentTestManager.getTest().log(Status.INFO, "Sort 05 - Step 05: Verify paging previous icon Display");
		assertTrue(notebooksPage.isPreviousIconDisplay());

	}

	@Test
	public void Sort_06_Display_6_Product_Page(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 06 - Step 01: Select dropdown Display with value is '6'");
		notebooksPage.selectDropdownByName(driver, "products-pagesize", "6");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 06 - Step 02: Verify product Display 6 item");
		assertEquals(notebooksPage.getProductNameSize(), 6);

		ExtentTestManager.getTest().log(Status.INFO, "Sort 06 - Step 03: Verify paging number icon Undisplay");
		assertTrue(notebooksPage.isPageNumberUndisplayed());

	}

	@Test
	public void Sort_07_Display_9_Product_Page(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Sort 07 - Step 01: Select dropdown Display with value is '9'");
		notebooksPage.selectDropdownByName(driver, "products-pagesize", "9");

		ExtentTestManager.getTest().log(Status.INFO, "Sort 07 - Step 02: Verify product Display 6 item");
		assertEquals(notebooksPage.getProductNameSize(), 6);

		ExtentTestManager.getTest().log(Status.INFO, "Sort 07 - Step 03: Verify paging number icon Undisplay");
		assertTrue(notebooksPage.isPageNumberUndisplayed());

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
