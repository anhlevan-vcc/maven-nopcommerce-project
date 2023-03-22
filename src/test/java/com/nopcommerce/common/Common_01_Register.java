package com.nopcommerce.common;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	public static String email, password;
	private String firstName, lastName, day, month, year;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeTest(description = "Create new Account for all Classes Test")
	public void Register(@Optional("local") String envName, @Optional("DEV") String serverName, @Optional("Chrome") String browser, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		driver = getBrowserDriverAll(envName, serverName, browser, ipAddress, portNumber, osName, osVersion, browserVersion);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = UserData.Rigester.FIRST_NAME;
		lastName = UserData.Rigester.LAST_NAME;
		email = UserData.Rigester.EMAIL + generateRandomNumber() + "@gmail.com";
		password = UserData.Rigester.PASSWORD;
		day = UserData.Rigester.DAY;
		month = UserData.Rigester.MONTH;
		year = UserData.Rigester.YEAR;

		log.info("Pre-condition - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-condition - Step 02: Enter to First name textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Pre-condition - Step 03: Enter to Last name textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Pre-condition - Step 04: Select dropdown Date Of Birth Day '" + day + "'");
		registerPage.selectDropdownDay(day);

		log.info("Pre-condition - Step 05: Select dropdown Date Of Birth month '" + month + "'");
		registerPage.selectDropdownMonth(month);

		log.info("Pre-condition - Step 06: Select dropdown Date Of Birth year '" + year + "'");
		registerPage.selectDropdownYear(year);

		log.info("Pre-condition - Step 07: Enter to email textbox with value is '" + email + "'");
		registerPage.inputToEmailTextbox(email);

		log.info("Pre-condition - Step 08: Enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Pre-condition - Step 09: Enter to confirm password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Pre-condition - Step 10: Click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Pre-condition - Step 11: Verify register success message is displayed");
		assertEquals(registerPage.getMessageSuccess(), "Your registration completed");

		driver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
