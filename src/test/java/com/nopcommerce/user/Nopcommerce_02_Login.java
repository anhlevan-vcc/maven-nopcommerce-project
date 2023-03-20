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
import reportConfig.ExtentTestManager;

public class Nopcommerce_02_Login extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

	private String validEmail, inValidEmail, firstName, lastName, password;
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
		day = UserData.Rigester.DAY;
		month = UserData.Rigester.MONTH;
		year = UserData.Rigester.YEAR;

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01 : Open login page");
		loginPage = homePage.openLoginPage();
	}

	@Test
	public void Login_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 : Click button 'Log In'");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 : Verify email error message is displayed");
		assertEquals(loginPage.getErrorMessageEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 : Enter to Email textbox with value is '" + inValidEmail + "'");
		loginPage.inputToEmailTextbox(inValidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 : Click button 'Log In'");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 : Verify email error message is displayed");
		assertEquals(loginPage.getErrorMessageEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Unregistered_Email(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 : Enter to Email textbox with value is '" + validEmail + "'");
		loginPage.inputToEmailTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 : Click button 'Log In'");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 : Verify email error message is displayed");
		assertEquals(loginPage.getErrorMessageExistingEmail(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Valid_Email_And_Empty_Password(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
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
		registerPage.refreshCurrentPage(driver);
		loginPage = homePage.openLoginPage();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 : Enter to Email textbox with value is '" + validEmail + "'");
		loginPage.inputToEmailTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 : Click button 'Log In'");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 : Verify email error message is displayed");
		assertEquals(loginPage.getErrorMessageExistingEmail(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Valid_Email_And_Wrong_Password(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 : Enter to Email textbox with value is '" + validEmail + "'");
		loginPage.inputToEmailTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 : Enter to Password textbox with value is '" + validEmail + "'");
		loginPage.inputToPasswordTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 : Click button 'Log In'");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 : Verify email error message is displayed");
		assertEquals(loginPage.getErrorMessageExistingEmail(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_And_Password(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01 : Enter to Email textbox with value is '" + validEmail + "'");
		loginPage.inputToEmailTextbox(validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02 : Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03 : Click button 'Log In'");
		homePage = loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04 : Verify email error message is displayed");
		assertTrue(homePage.isMyAccountLinkDisPlayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
