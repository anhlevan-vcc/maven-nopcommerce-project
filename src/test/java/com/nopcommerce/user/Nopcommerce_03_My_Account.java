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
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserChangePasswordPageObject;
import pageObject.nopCommerce.user.UserComputersPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserDesktopsPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewPageObject;
import pageObject.nopCommerce.user.UserProductReviewDetailPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.productdetail.UserBuildComputerDetailPageObject;
import reportConfig.ExtentTestManager;

public class Nopcommerce_03_My_Account extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserComputersPageObject computersPage;
	private UserDesktopsPageObject desktopsPage;
	private UserBuildComputerDetailPageObject buildComputerPage;
	private UserProductReviewDetailPageObject productReviewsDetailPage;
	private UserMyProductReviewPageObject myProductReviewPage;

	private String validEmail, firstName, lastName, password, newPassword, reviewTitle, reviewText, productReview;
	private String updateGender, updateFirstName, updateLastName, updateEmail, updateCompanyName;
	private String day, month, year, updateDay, updateMonth, updateYear;

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

		updateGender = UserData.CustomerInfo.GENDER;
		updateFirstName = UserData.CustomerInfo.FIRST_NAME;
		updateLastName = UserData.CustomerInfo.LAST_NAME;
		updateEmail = UserData.CustomerInfo.EMAIL + generateRandomNumber() + "@gmail.com";
		updateCompanyName = UserData.CustomerInfo.COMPANY_NAME;
		updateDay = UserData.CustomerInfo.DAY;
		updateMonth = UserData.CustomerInfo.MONTH;
		updateYear = UserData.CustomerInfo.YEAR;

		newPassword = UserData.ChangePassword.NEW_PASSWORD;

		reviewTitle = UserData.ProductReviewsDetail.REVIEW_TITLE;
		reviewText = UserData.ProductReviewsDetail.REVIEW_TEXT;
		productReview = UserData.ProductReviewsDetail.PRODUCT_REVIEW;

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

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 11: Click to Login link");
		loginPage = registerPage.clickToLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 12 : Login with Email and password with value is '" + validEmail + "'+'" + password + "'");
		homePage = loginPage.loginAsUser(validEmail, password);

	}

	@Test
	public void My_Account_01_Update_Info_Customer(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 01: Navigate to 'Customer info' page");
		customerInfoPage = homePage.openMyAccountCustomerInfoPage();

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 02: Click to radio button gender 'Male'");
		customerInfoPage.clickToRadioButtonByLabel(driver, updateGender);

		assertTrue(customerInfoPage.isRadioMaleSelected(driver, updateGender));

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 03: Enter to First name textbox with value is '" + updateFirstName + "'");
		customerInfoPage.inputToTextboxByID(driver, "FirstName", updateFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 04: Enter to Last name textbox with value is '" + updateLastName + "'");
		customerInfoPage.inputToTextboxByID(driver, "LastName", updateLastName);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 05: Select dropdown Date Of Birth Day '" + updateDay + "'");
		customerInfoPage.selectDropdownByName(driver, "DateOfBirthDay", updateDay);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 06: Select dropdown Date Of Birth Day '" + updateMonth + "'");
		customerInfoPage.selectDropdownByName(driver, "DateOfBirthMonth", updateMonth);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 07: Select dropdown Date Of Birth Day '" + updateYear + "'");
		customerInfoPage.selectDropdownByName(driver, "DateOfBirthYear", updateYear);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 08: Enter to Last name textbox with value is '" + updateEmail + "'");
		customerInfoPage.inputToTextboxByID(driver, "Email", updateEmail);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 09: Enter to Last name textbox with value is '" + updateCompanyName + "'");
		customerInfoPage.inputToTextboxByID(driver, "Company", updateCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 10: Click to Save button");
		customerInfoPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo - Step 12 : Verify notification success is displayed");
		assertEquals(customerInfoPage.getNotificationSuccessAtCustomerInfo(), "The customer info has been updated successfully.");

	}

	@Test
	public void My_Account_02_Add_Info_Addresses(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 01: Navigate to 'Addresses' page");
		addressPage = (UserAddressPageObject) customerInfoPage.openPageAtMyAccountByName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 02: Click to 'Add new' button");
		addressPage.clickToButtonByText(driver, "Add new");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 03: Enter to First name textbox with value is 'Automation'");
		addressPage.inputToTextboxByID(driver, "Address_FirstName", "Automation");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 04: Enter to Last name textbox with value is 'FC'");
		addressPage.inputToTextboxByID(driver, "Address_LastName", "FC");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 05: Enter to Email textbox with value is '" + updateEmail + "'");
		addressPage.inputToTextboxByID(driver, "Address_Email", updateEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 06: Enter to Company textbox with value is '" + updateCompanyName + "'");
		addressPage.inputToTextboxByID(driver, "Address_Company", updateCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 07: Select dropdown country with value is 'Viet Nam'");
		addressPage.selectDropdownByName(driver, "Address.CountryId", "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 08: Select dropdown State / province with value is 'Other'");
		addressPage.selectDropdownByName(driver, "Address.StateProvinceId", "Other");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 09: Enter to City textbox with value is 'Hà nội'");
		addressPage.inputToTextboxByID(driver, "Address_City", "Hà nội");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 10: Enter to Address 1 textbox with value is 'Hoàng mai'");
		addressPage.inputToTextboxByID(driver, "Address_Address1", "Hoàng mai");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 11: Enter to Address 2 textbox with value is 'Đại Kim'");
		addressPage.inputToTextboxByID(driver, "Address_Address2", "Đại Kim");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 12: Enter to Zip / postal code textbox with value is '550000'");
		addressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", "Hà nội");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 13: Enter to Phone number textbox with value is '0123456789'");
		addressPage.inputToTextboxByID(driver, "Address_PhoneNumber", "0123456789");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 14: Enter to Fax number textbox with value is '9876543210'");
		addressPage.inputToTextboxByID(driver, "Address_FaxNumber", "9876543210");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 15: Click to 'SAVE' button");
		addressPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "Addresses - Step 16 : Verify notification success is displayed");
		assertEquals(addressPage.getNotificationSuccessAtMyAccount(), "The new address has been added successfully.");

	}

	@Test
	public void My_Account_03_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 01: Navigate to 'Change password' page");
		changePasswordPage = (UserChangePasswordPageObject) addressPage.openPageAtMyAccountByName(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 02: Enter to Old password textbox with value is '" + password + "'");
		changePasswordPage.inputToTextboxByID(driver, "OldPassword", password);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 03: Enter to New password textbox with value is '" + newPassword + "'");
		changePasswordPage.inputToTextboxByID(driver, "NewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 04: Enter to Confirm new password textbox with value is '" + newPassword + "'");
		changePasswordPage.inputToTextboxByID(driver, "ConfirmNewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 05: Click to 'Change password' button");
		changePasswordPage.clickToButtonByText(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 06: Verify notification success is displayed");
		assertEquals(changePasswordPage.getNotificationSuccessAtChangePass(), "Password was changed");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 07: Close bar notification");
		changePasswordPage.clickToCloseIcon();

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 08: Click to 'Log out' link");
		homePage = changePasswordPage.clickToLogOutLink();

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 09: Click to 'Log in' link");
		loginPage = homePage.openLoginPage();

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 10: Login with Email and password with value is '" + updateEmail + "'+'" + password + "'");
		loginPage.loginAsUser(updateEmail, password);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 11: Verify email error message is displayed");
		assertEquals(loginPage.getErrorMessageExistingEmail(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 12: Login with Email and password with value is '" + updateEmail + "'+'" + newPassword + "'");
		homePage = loginPage.loginAsUser(updateEmail, newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 13: Verify email error message is displayed");
		assertTrue(homePage.isMyAccountLinkDisPlayed());
	}

	@Test
	public void My_Account_04_My_Product_Reviews(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 01: Navigate to 'Computers' page");
		computersPage = homePage.openComputersPage();

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 02: Navigate to 'Desktops' page");
		desktopsPage = (UserDesktopsPageObject) computersPage.openPageCategoriesComputersByName(driver, "Desktops ");

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 03: Navigate to '" + productReview + "'");
		buildComputerPage = (UserBuildComputerDetailPageObject) desktopsPage.openPageAtDesktopsByName(driver, productReview);

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 04: Click to 'Add your review' link");
		productReviewsDetailPage = buildComputerPage.clickAddYourReviewLink();

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 05: Enter to Review title textbox with value is '" + reviewTitle + "'");
		productReviewsDetailPage.inputToTextboxByID(driver, "AddProductReview_Title", reviewTitle);

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 06: Enter to Review text textbox with value is '" + reviewText + "'");
		productReviewsDetailPage.inputToReviewTextbox(driver, reviewText);

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 07: Click Rating radio button 'Rating 4'");
		productReviewsDetailPage.clickToRatingRadioButton();

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 08: Click to 'Submit review' button");
		productReviewsDetailPage.clickToButtonByText(driver, "Submit review");

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 09: Navigate to 'Customer info' page");
		customerInfoPage = productReviewsDetailPage.clickMyAccountLink();

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 10: Navigate to 'My product reviews' page");
		myProductReviewPage = (UserMyProductReviewPageObject) customerInfoPage.openPageAtMyAccountByName(driver, "My product reviews");

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 11: Verify Review title displayed with value is '" + reviewTitle + "'");
		assertEquals(myProductReviewPage.getReviewTitleText(), reviewTitle);

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 12: Verify Review text displayed with value is '" + reviewText + "'");
		assertEquals(myProductReviewPage.getReviewText(), reviewText);

		ExtentTestManager.getTest().log(Status.INFO, "My Product Reviews - Step 12: Verify Product review displayed with value is '" + productReview + "'");
		assertEquals(myProductReviewPage.getProductReviewText(), productReview);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
