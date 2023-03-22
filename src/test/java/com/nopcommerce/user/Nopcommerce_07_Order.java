package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register;
import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserShoppingCartPageObject;
import pageObject.nopCommerce.user.productdetail.UserAllInOneDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserBuildComputerDetailPageObject;

public class Nopcommerce_07_Order extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserBuildComputerDetailPageObject buildComputerDetailPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserAllInOneDetailPageObject allInOneDetailPage;

	private String validEmail, validPassword;
	private String processor, ram, hdd, os, softwareOne, softwareTwo, softwareThree, subTotal, updatePrace, updateProcessor, updateRam, updateHdd, updateOs, updateSubTotal;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("DEV") String serverName, @Optional("Chrome") String browser, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		driver = getBrowserDriverAll(envName, serverName, browser, ipAddress, portNumber, osName, osVersion, browserVersion);
		this.browser = browser.toUpperCase();
		homePage = PageGeneratorManager.getUserHomePage(driver);

		validEmail = Common_01_Register.email;
		validPassword = Common_01_Register.password;

		processor = UserData.BuildYourOwnComputer.PROCESSOR;
		ram = UserData.BuildYourOwnComputer.RAM;
		hdd = UserData.BuildYourOwnComputer.HDD;
		os = UserData.BuildYourOwnComputer.OS;
		softwareOne = UserData.BuildYourOwnComputer.SOFTWARE_ONE;
		softwareTwo = UserData.BuildYourOwnComputer.SOFTWARE_TWO;
		softwareThree = UserData.BuildYourOwnComputer.SOFTWARE_THREE;
		subTotal = UserData.BuildYourOwnComputer.SUB_TOTAL;

		updateProcessor = UserData.BuildYourOwnComputer.UPDATE_PROCESSOR;
		updateRam = UserData.BuildYourOwnComputer.UPDATE_RAM;
		updateHdd = UserData.BuildYourOwnComputer.UPDATE_HDD;
		updateOs = UserData.BuildYourOwnComputer.UPDATE_OS;
		updatePrace = UserData.BuildYourOwnComputer.UPDATE_PRACE;
		updateSubTotal = UserData.BuildYourOwnComputer.UPDATE_SUB_TOTAL;

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
		logExtentV5("Order_01 - Step 01: Select dropdown Processor with value is '" + processor + "'");
		buildComputerDetailPage.selectDropdownByName(driver, "product_attribute_1", processor);

		logExtentV5("Order_01 - Step 02: Select dropdown RAM with value is '" + ram + "'");
		buildComputerDetailPage.selectDropdownByName(driver, "product_attribute_2", ram);

		logExtentV5("Order_01 - Step 03: Select radio button HDD with value is '" + hdd + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, hdd);

		logExtentV5("Order_01 - Step 04: Select radio button OS with value is '" + os + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, os);

		logExtentV5("Order_01 - Step 05: Select checkbox Software with value is '" + softwareOne + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, softwareOne);

		logExtentV5("Order_01 - Step 06: Select checkbox Software with value is '" + softwareTwo + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, softwareTwo);

		logExtentV5("Order_01 - Step 07: Select checkbox Software with value is '" + softwareThree + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, softwareThree);

		logExtentV5("Order_01 - Step 08: Click button Add to cart");
		buildComputerDetailPage.clickToButtonByText(driver, "Add to cart");

		logExtentV5("Order_01 - Step 09: Verify mesage success add to cart displayed");
		assertEquals(buildComputerDetailPage.getMessageSuccessAtBuildComputerDetail(), "The product has been added to your shopping cart");

		logExtentV5("Order_01 - Step 10: Close message");
		buildComputerDetailPage.clickIconClose();

		logExtentV5("Order_01 - Step 11: Hover mouse at shopping cart");
		buildComputerDetailPage.hoverToShoppingCart();

		logExtentV5("Order_01 - Step 12: Verify Message Count displayed");
		assertEquals(buildComputerDetailPage.getMessageCountDisplayed(), "There are 1 item(s) in your cart.");

		logExtentV5("Order_01 - Step 13: Verify Product Name displayed");
		assertEquals(buildComputerDetailPage.getProductNameDisplayed(), "Build your own computer");

		logExtentV5("Order_01 - Step 14: Verify Attributes product displayed");
		assertEquals(buildComputerDetailPage.getAttributesDisplayed(), "Processor: " + processor + "\n" + "RAM: " + ram + "\n" + "HDD: " + hdd + "\n" + "OS: " + os + "\n"
				+ "Software: " + softwareOne + "\n" + "Software: " + softwareTwo + "\n" + "Software: " + softwareThree);

		logExtentV5("Order_01 - Step 15: Verify Prace product displayed");
		assertEquals(buildComputerDetailPage.getPraceDisplayed(), "Unit price: " + subTotal);

		logExtentV5("Order_01 - Step 16: Verify Sub Total product displayed");
		assertEquals(buildComputerDetailPage.getSubTotalDisplayed(), "Sub-Total: " + subTotal);

	}

	@Test
	public void Order_02_Edit_Product_In_Shopping_Cart(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_02 - Step 01: Open Shopping Cart Page");
		shoppingCartPage = buildComputerDetailPage.openShoppingCartPage();

		logExtentV5("Order_02 - Step 02: Edit info product");
		buildComputerDetailPage = shoppingCartPage.clickEditProductLink();

		logExtentV5("Order_02 - Step 03: Select dropdown Processor with value is '" + updateProcessor + "'");
		buildComputerDetailPage.selectDropdownByName(driver, "product_attribute_1", updateProcessor);

		logExtentV5("Order_02 - Step 04: Select dropdown RAM with value is '" + updateRam + "'");
		buildComputerDetailPage.selectDropdownByName(driver, "product_attribute_2", updateRam);

		logExtentV5("Order_02 - Step 05: Select radio button HDD with value is '" + updateHdd + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, updateHdd);

		logExtentV5("Order_02 - Step 06: Select radio button OS with value is '" + updateOs + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, updateOs);

		logExtentV5("Order_02 - Step 07: Select checkbox Software with value is '" + softwareOne + "'");
		buildComputerDetailPage.clickToRadioButtonByLabel(driver, softwareOne);

		logExtentV5("Order_02 - Step 08: Uncheckbox Software with value is '" + softwareTwo + "'");
		buildComputerDetailPage.unCheckToRadioButtonByLabel(driver, softwareTwo);

		logExtentV5("Order_02 - Step 09: Uncheckbox Software with value is '" + softwareThree + "'");
		buildComputerDetailPage.unCheckToRadioButtonByLabel(driver, softwareThree);

		logExtentV5("Order_02 - Step 10: Verify un checkbox finish");
		assertFalse(buildComputerDetailPage.isCheckboxSelected());

		logExtentV5("Order_02 - Step 11: Verify Prace product displayed");
		assertEquals(buildComputerDetailPage.getProductPraceDisplayed(), updatePrace);

		logExtentV5("Order_02 - Step 12: Change quantity of products from 1 to 2");
		buildComputerDetailPage.inputToTextboxByID(driver, "product_enteredQuantity_1", "2");

		logExtentV5("Order_02 - Step 13: Click button Update");
		buildComputerDetailPage.clickToButtonByText(driver, "Update");

		logExtentV5("Order_02 - Step 14: Verify mesage success add to cart displayed");
		assertEquals(buildComputerDetailPage.getMessageSuccessAtBuildComputerDetail(), "The product has been added to your shopping cart");

		logExtentV5("Order_02 - Step 15: Close message");
		buildComputerDetailPage.clickIconClose();

		logExtentV5("Order_02 - Step 16: Hover mouse at shopping cart");
		buildComputerDetailPage.hoverToShoppingCart();

		logExtentV5("Order_02 - Step 17: Verify Message Count displayed");
		assertEquals(buildComputerDetailPage.getMessageCountDisplayed(), "There are 2 item(s) in your cart.");

		logExtentV5("Order_02 - Step 18: Verify Product Name displayed");
		assertEquals(buildComputerDetailPage.getProductNameDisplayed(), "Build your own computer");

		logExtentV5("Order_02 - Step 19: Verify Attributes product displayed");
		assertEquals(buildComputerDetailPage.getAttributesDisplayed(),
				"Processor: " + updateProcessor + "\n" + "RAM: " + updateRam + "\n" + "HDD: " + updateHdd + "\n" + "OS: " + updateOs + "\n" + "Software: " + softwareOne);

		logExtentV5("Order_02 - Step 20: Verify Prace product displayed");
		assertEquals(buildComputerDetailPage.getPraceDisplayed(), "Unit price: " + updatePrace);

		logExtentV5("Order_02 - Step 21: Verify Sub Total product displayed");
		assertEquals(buildComputerDetailPage.getSubTotalDisplayed(), "Sub-Total: " + updateSubTotal);

		logExtentV5("Order_02 - Step 22: Open Shopping Cart Page");
		shoppingCartPage = buildComputerDetailPage.openShoppingCartPage();

		logExtentV5("Order_02 - Step 23: Verify product name displayed");
		assertTrue(shoppingCartPage.isProductNameDisplayed(driver, "product", "Build your own computer"));

		logExtentV5("Order_02 - Step 24: Verify Attributes product displayed in table");
		assertEquals(buildComputerDetailPage.getAttributesAtTableDisplayed(),
				"Processor: " + updateProcessor + "\n" + "RAM: " + updateRam + "\n" + "HDD: " + updateHdd + "\n" + "OS: " + updateOs + "\n" + "Software: " + softwareOne);

		logExtentV5("Order_02 - Step 25: Verify updated product prices");
		assertTrue(shoppingCartPage.isProductDisplayed(driver, "unit-price", updatePrace));

		logExtentV5("Order_02 - Step 26: Check updated product SubTotal");
		assertTrue(shoppingCartPage.isProductDisplayed(driver, "subtotal", updateSubTotal));
	}

	@Test
	public void Order_03_Remove_From_Cart(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_03 - Step 01: Click icon remove product in table");
		shoppingCartPage.clickRemoveIcon();

		logExtentV5("Order_03 - Step 02: Verify message 'Your Shopping Cart is empty!' displayed");
		assertEquals(shoppingCartPage.getMessageDisplayed(), "Your Shopping Cart is empty!");

		logExtentV5("Order_03 - Step 03: Verify product at table undisplayed");
		assertTrue(shoppingCartPage.isProductNameUnDisplayed(driver, "Build your own computer"));
	}

	@Test
	public void Order_04_Update_Shopping_Cart(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_04 - Step 01: Open Lenovo IdeaCentre 600 All-in-One PC page");
		allInOneDetailPage = (UserAllInOneDetailPageObject) shoppingCartPage.openPageAtHomeByName(driver, "Computers").openPageCategoriesComputersByName(driver, "Desktops ")
				.openPageAtDesktopsByName(driver, "Lenovo IdeaCentre 600 All-in-One PC");

		logExtentV5("Order_04 - Step 02: Click to button 'Add to cart'");
		allInOneDetailPage.clickToButtonByText(driver, "Add to cart");

		logExtentV5("Order_04 - Step 03: Open Shopping cart page");
		shoppingCartPage = (UserShoppingCartPageObject) allInOneDetailPage.openPageAtHomeFooterByName(driver, "Shopping cart");

		logExtentV5("Order_04 - Step 04: Input to Qty textbox");
		shoppingCartPage.inputToQtyTextbox("5");

		logExtentV5("Order_04 - Step 05: Click to button 'Update shopping cart'");
		shoppingCartPage.clickToButtonByText(driver, "Update shopping cart");

		logExtentV5("Order_04 - Step 06: Verify subtotal is displayed correctly");
		shoppingCartPage.isProductDisplayed(driver, "subtotal", "$2,500.00");
	}

	@Test
	public void Order_05_Pay_By_Cheque_Or_Money(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("");

	}

	@Test
	public void Order_06_Pay_By_Credit_Card(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("");

	}

	@Test
	public void Order_07_Re_Order(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
