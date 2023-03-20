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
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserSearchPageObject;
import reportConfig.ExtentTestManager;

public class Nopcommerce_04_Search extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserSearchPageObject searchPage;

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

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 15 : Click search link at foodter");
		searchPage = homePage.clickToSearchLink();

	}

	@Test
	public void Search_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 01 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 02 : Verify search warning message is displayed");
		assertTrue(searchPage.isSearchWarningMessageIsDisplayed());
		assertEquals(searchPage.getMessageSearchWarning(), "Search term minimum length is 3 characters");
	}

	@Test
	public void Search_02_Data_Not_Exist() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Step 01 : Enter to search textbox wiht value is 'Macbook Pro 2050'");
		searchPage.inputToTextboxByID(driver, "q", "Macbook Pro 2050");

		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Step 02 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Step 03 : Verify search message is displayed");
		assertEquals(searchPage.getMessageNoProductSearch(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_03_Product_Name_Relative(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 01 : Enter to search textbox wiht value is 'Lenovo'");
		searchPage.inputToTextboxByID(driver, "q", "Lenovo");

		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 02 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 03 : Verify Product Size equals '2'");
		assertEquals(searchPage.getProductSize(), 2);

		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 04 : Verify Product name contain 'Lenovo'");
		assertTrue(searchPage.isProductName());
	}

	@Test
	public void Search_04_Product_Name_Absolute(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 01 : Enter to search textbox wiht value is 'ThinkPad X1 Carbon'");
		searchPage.inputToTextboxByID(driver, "q", "ThinkPad X1 Carbon");

		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 02 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 03 : Verify Product Size equals '1'");
		assertEquals(searchPage.getProductSize(), 1);

		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 04 : Verify Product name contain 'ThinkPad X1 Carbon'");
		assertTrue(searchPage.isProductNameAbsolute());
	}

	@Test
	public void Search_05_Parent_Categories(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 01 : Enter to search textbox wiht value is 'Apple MacBook Pro'");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 02 : Click to checkbox 'Advanced search'");
		searchPage.clickToRadioButtonByLabel(driver, "Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 03: Select dropdown Category 'Computers'");
		searchPage.selectDropdownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 04 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 05 : Verify search message is displayed");
		assertEquals(searchPage.getMessageNoProductSearch(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_06_Sub_Categories(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 01 : Enter to search textbox wiht value is 'Apple MacBook Pro'");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 02 : Click to checkbox 'Advanced search'");
		searchPage.clickToRadioButtonByLabel(driver, "Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 03: Select dropdown Category 'Computers'");
		searchPage.selectDropdownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 04 : Click to checkbox 'Automatically search sub categories search'");
		searchPage.clickToRadioButtonByLabel(driver, "Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 05 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 06 : Verify Product Size equals '1'");
		assertEquals(searchPage.getProductSize(), 1);

		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 07 : Verify Product name contain 'Apple MacBook Pro'");
		assertTrue(searchPage.isProductNameSubCategories());
	}

	@Test
	public void Search_07_Incorrect_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 01 : Enter to search textbox wiht value is 'Apple MacBook Pro'");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 02 : Click to checkbox 'Advanced search'");
		searchPage.clickToRadioButtonByLabel(driver, "Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 03: Select dropdown Category 'Computers'");
		searchPage.selectDropdownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 04 : Click to checkbox 'Automatically search sub categories search'");
		searchPage.clickToRadioButtonByLabel(driver, "Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 05: Select dropdown Manufacturer 'HP'");
		searchPage.selectDropdownByName(driver, "mid", "HP");

		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 06 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 07 : Verify search message is displayed");
		assertEquals(searchPage.getMessageNoProductSearch(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_Correct_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 01 : Enter to search textbox wiht value is 'Apple MacBook Pro'");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 02 : Click to checkbox 'Advanced search'");
		searchPage.clickToRadioButtonByLabel(driver, "Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 03: Select dropdown Category 'Computers'");
		searchPage.selectDropdownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 04 : Click to checkbox 'Automatically search sub categories search'");
		searchPage.clickToRadioButtonByLabel(driver, "Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 05: Select dropdown Manufacturer 'Apple'");
		searchPage.selectDropdownByName(driver, "mid", "Apple");

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 06 : Click to 'Search' buttton");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 07 : Verify Product Size equals '1'");
		assertEquals(searchPage.getProductSize(), 1);

		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 09 : Verify Product name contain 'Apple MacBook Pro'");
		assertTrue(searchPage.isProductNameSubCategories());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
