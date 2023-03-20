package com.nopcommerce.user;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_Register;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.productdetail.UserBuildComputerDetailPageObject;
import reportConfig.ExtentTestManager;

public class Nopcommerce_07_Order extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerinfoPage;
	private UserBuildComputerDetailPageObject buildComputerDetailPage;

	private String validEmail, validPassword;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("DEV") String serverName, @Optional("Chrome") String browser, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		driver = getBrowserDriverAll(envName, serverName, browser, ipAddress, portNumber, osName, osVersion, browserVersion);
		this.browser = browser.toUpperCase();
		homePage = PageGeneratorManager.getUserHomePage(driver);

		validEmail = Common_01_Register.email;
		validPassword = Common_01_Register.password;

		// logExtentV5( "Pre-condition - Step 01: Open login page");
		loginPage = homePage.openLoginPage();

		// logExtentV5( "Pre-condition - Step 02: Login with Email and password with value is '" + validEmail + "'+'" + validPassword +
		// "'");
		homePage = loginPage.loginAsUser(validEmail, validPassword);

		// logExtentV5( "Pre-condition - Step 03: Verify My account link displayed");
		assertTrue(homePage.isMyAccountLinkDisPlayed());

		// logExtentV5( "Pre-condition - Step 04: Open Build your own computer page");
		buildComputerDetailPage = (UserBuildComputerDetailPageObject) homePage.openComputersPage().openPageCategoriesComputersByName(driver, "Desktops ")
				.openPageAtDesktopsByName(driver, "Build your own computer");

	}

	@Test
	public void Order_01_Add_Product_To_Cart(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_01 - Step 01: Select dropdown Processor with value is '2.2 GHz Intel Pentium Dual-Core E2200'");
		buildComputerDetailPage.selectDropdownByName(driver, "product_attribute_1", "2.2 GHz Intel Pentium Dual-Core E2200");

		logExtentV5("Order_01 - Step 01: Select dropdown RAM with value is '8GB [+$60.00]'");
		buildComputerDetailPage.selectDropdownByName(driver, "product_attribute_2", "8GB [+$60.00]");

		logExtentV5("Order_01 - Step 01: Select radio button HDD with value is '400 GB [+$100.00]'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, "400 GB [+$100.00]");

		logExtentV5("Order_01 - Step 01: Select radio button OS with value is 'Vista Premium [+$60.00]'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, "Vista Premium [+$60.00]");

		logExtentV5("Order_01 - Step 01: Select checkbox Software with value is 'Microsoft Office [+$50.00]'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, "Microsoft Office [+$50.00]");

		logExtentV5("Order_01 - Step 01: Select checkbox Software with value is 'Acrobat Reader [+$10.00]'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, "Acrobat Reader [+$10.00]");

		logExtentV5("Order_01 - Step 01: Select checkbox Software with value is 'Total Commander [+$5.00]'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, "Total Commander [+$5.00]");

		logExtentV5("Order_01 - Step 01: Click button Add to cart");
		buildComputerDetailPage.clickToButtonByText(driver, "Add to cart");

	}

	@Test
	public void Order_02_(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_02 - Step 01:");

	}

	@Test
	public void Order_03_(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_03 - Step 01:");
	}

	@Test
	public void Order_04_(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_04 - Step 01:");

	}

	@Test
	public void Order_05_(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("");

	}

	@Test
	public void Order_06_(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("");

	}

	@Test
	public void Order_07_(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
