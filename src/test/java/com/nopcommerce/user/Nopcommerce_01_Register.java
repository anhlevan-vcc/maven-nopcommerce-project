package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;

public class Nopcommerce_01_Register extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	private String validEmail, inValidEmail, firstName, lastName, password, passLessThen6Char;
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
		inValidEmail = UserData.Rigester.EMAIL + generateRandomNumber() + "@123@123";
		password = UserData.Rigester.PASSWORD;
		passLessThen6Char = UserData.Rigester.PASS_LESS_THEN_6_CHAR;
		day = UserData.Rigester.DAY;
		month = UserData.Rigester.MONTH;
		year = UserData.Rigester.YEAR;

	}

	@Test
	public void User_01_Register_Empty_Data(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		logExtentV5("Register - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		logExtentV5("Register - Step 03: Verify First Name error message is displayed");
		assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");

		logExtentV5("Register - Step 04: Verify Last name error message is displayed");
		assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");

		logExtentV5("Register - Step 05: Verify Email error message is displayed");
		assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");

		logExtentV5("Register - Step 06: Verify Password error message is displayed");
		assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");

		logExtentV5("Register - Step 07: Verify Confirm Password error message is displayed");
		assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void User_02_Register_Invalid_Email(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		logExtentV5("Register - Step 02: Enter to Email textbox with value is '" + inValidEmail + "'");
		registerPage.inputToEmailTextbox(inValidEmail);

		logExtentV5("Register - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		logExtentV5("Register - Step 04: Verify Email error message is displayed");
		assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void User_03_Register_Pass_Less_Than_6_Char(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		logExtentV5("Register - Step 02: Enter to Password textbox with value is '" + passLessThen6Char + "'");
		registerPage.inputToPasswordTextbox(passLessThen6Char);

		logExtentV5("Register - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		logExtentV5("Register - Step 04: Verify Password error message is displayed");
		assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void User_04_Register_Invalid_Confirm_Password(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		logExtentV5("Register - Step 02: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		logExtentV5("Register - Step 03: Enter to Confirm Password textbox with value is '" + inValidEmail + "'");
		registerPage.inputToConfirmPasswordTextbox(inValidEmail);

		logExtentV5("Register - Step 04: Click to Register button");
		registerPage.clickToRegisterButton();

		logExtentV5("Register - Step 05: Verify Confirm password error message is displayed");
		assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@Test
	public void User_05_Register_Success(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		logExtentV5("Register - Step 02: Enter to First name textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		logExtentV5("Register - Step 03: Enter to Last name textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		logExtentV5("Register - Step 04: Select dropdown Date Of Birth Day '" + day + "'");
		registerPage.selectDropdownDay(day);

		logExtentV5("Register - Step 05: Select dropdown Date Of Birth month '" + month + "'");
		registerPage.selectDropdownMonth(month);

		logExtentV5("Register - Step 06: Select dropdown Date Of Birth year '" + year + "'");
		registerPage.selectDropdownYear(year);

		logExtentV5("Register - Step 07: Enter to email textbox with value is '" + validEmail + "'");
		registerPage.inputToEmailTextbox(validEmail);

		logExtentV5("Register - Step 08: Enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		logExtentV5("Register - Step 09: Enter to confirm password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		logExtentV5("Register - Step 10: Click to Register button");
		registerPage.clickToRegisterButton();

		logExtentV5("Register - Step 11: Verify register success message is displayed");
		assertEquals(registerPage.getMessageSuccess(), "Your registration completed");

	}

	@Test
	public void User_06_Register_Existing_Email(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		logExtentV5("Register - Step 02: Enter to First name textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		logExtentV5("Register - Step 03: Enter to Last name textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		logExtentV5("Register - Step 04: Select dropdown Date Of Birth Day '" + day + "'");
		registerPage.selectDropdownDay(day);

		logExtentV5("Register - Step 05: Select dropdown Date Of Birth month '" + month + "'");
		registerPage.selectDropdownMonth(month);

		logExtentV5("Register - Step 06: Select dropdown Date Of Birth year '" + year + "'");
		registerPage.selectDropdownYear(year);

		logExtentV5("Register - Step 07: Enter to email textbox with value is '" + validEmail + "'");
		registerPage.inputToEmailTextbox(validEmail);

		logExtentV5("Register - Step 08: Enter to password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		logExtentV5("Register - Step 09: Enter to confirm password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		logExtentV5("Register - Step 10: Click to Register button");
		registerPage.clickToRegisterButton();

		logExtentV5("Register - Step 11: Verify register success message is displayed");
		assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
