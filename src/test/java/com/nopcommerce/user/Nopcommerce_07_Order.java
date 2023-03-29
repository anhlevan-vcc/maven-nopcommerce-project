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
import pageObject.nopCommerce.user.UserCheckOutPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserOrdersPageObject;
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
	private UserCheckOutPageObject checkOutPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserOrdersPageObject ordersPage;

	private String validEmail, validPassword;
	private String orderNumberText, orderDate;
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
		logExtentV5("Order_05 - Step 01: Select dropdown Gift wrapping with value is 'Yes [+$10.00]'");
		shoppingCartPage.selectDropdownByName(driver, "checkout_attribute_1", "Yes [+$10.00]");

		logExtentV5("Order_05 - Step 02: Click at checkbox I agree");
		shoppingCartPage.clickToRadioButtonByLabel(driver, "I agree with the terms");

		logExtentV5("Order_05 - Step 03: Click on the button 'Checkout'");
		checkOutPage = shoppingCartPage.clickToCheckOutButton();

		logExtentV5("Order_05 - Step 04: Uncheckbox 'Ship to the same address'");
		checkOutPage.unCheckToRadioButtonByLabel(driver, "Ship to the same address");

		logExtentV5("Order_05 - Step 05: Enter to First name textbox with value is + '" + UserData.CheckOut.FIRST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", UserData.CheckOut.FIRST_NAME);

		logExtentV5("Order_05 - Step 06: Enter to Last name textbox with value is '" + UserData.CheckOut.LAST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", UserData.CheckOut.LAST_NAME);

		logExtentV5("Order_05 - Step 07: Enter to Email textbox with value is '" + UserData.CheckOut.EMAIL + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", UserData.CheckOut.EMAIL);

		logExtentV5("Order_05 - Step 08: Enter to Company textbox with value is '" + UserData.CheckOut.COMPANY + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Company", UserData.CheckOut.COMPANY);

		logExtentV5("Order_05 - Step 09: Select dropdown country with value is '" + UserData.CheckOut.COUNTRY + "'");
		checkOutPage.selectDropdownByName(driver, "BillingNewAddress.CountryId", UserData.CheckOut.COUNTRY);

		logExtentV5("Order_05 - Step 10: Select dropdown State / province with value is '" + UserData.CheckOut.STALE + "'");
		assertTrue(checkOutPage.isPageLoadedSuccess(driver));
		checkOutPage.selectDropdownByName(driver, "BillingNewAddress.StateProvinceId", UserData.CheckOut.STALE);

		logExtentV5("Order_05 - Step 11: Enter to City textbox with value is '" + UserData.CheckOut.CITY + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_City", UserData.CheckOut.CITY);

		logExtentV5("Order_05 - Step 12: Enter to Address 1 textbox with value is '" + UserData.CheckOut.ADDRESS1 + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", UserData.CheckOut.ADDRESS1);

		logExtentV5("Order_05 - Step 13: Enter to Address 2 textbox with value is '" + UserData.CheckOut.ADDRESS2 + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address2", UserData.CheckOut.ADDRESS2);

		logExtentV5("Order_05 - Step 14: Enter to Zip / postal code textbox with value is '" + UserData.CheckOut.ZIP + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", UserData.CheckOut.ZIP);

		logExtentV5("Order_05 - Step 15: Enter to Phone number textbox with value is '" + UserData.CheckOut.PHONE_NUMBER + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", UserData.CheckOut.PHONE_NUMBER);

		logExtentV5("Order_05 - Step 16: Enter to Fax number textbox with value is '" + UserData.CheckOut.FAX + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FaxNumber", UserData.CheckOut.FAX);

		logExtentV5("Order_05 - Step 17: Click on the button 'Continue'");
		checkOutPage.clickToButtonByText(driver, "Continue");

		logExtentV5("Order_05 - Step 18: Select dropdown with value is 'New Address'");
		checkOutPage.selectDropdownByName(driver, "shipping_address_id", "New Address");

		logExtentV5("Order_05 - Step 19: Enter to First name textbox with value is + '" + UserData.CheckOut.FIRST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FirstName", UserData.CheckOut.FIRST_NAME);

		logExtentV5("Order_05 - Step 20: Enter to Last name textbox with value is '" + UserData.CheckOut.LAST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_LastName", UserData.CheckOut.LAST_NAME);

		logExtentV5("Order_05 - Step 21: Enter to Email textbox with value is '" + UserData.CheckOut.EMAIL + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Email", UserData.CheckOut.EMAIL);

		logExtentV5("Order_05 - Step 22: Enter to Company textbox with value is '" + UserData.CheckOut.COMPANY + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Company", UserData.CheckOut.COMPANY);

		logExtentV5("Order_05 - Step 23: Select dropdown country with value is 'Viet Nam'");
		checkOutPage.selectDropdownByName(driver, "ShippingNewAddress.CountryId", UserData.CheckOut.COUNTRY1);

		logExtentV5("Order_05 - Step 24: Select dropdown State / province with value is 'Other'");
		assertTrue(checkOutPage.isPageLoadedSuccess(driver));
		checkOutPage.selectDropdownByName(driver, "ShippingNewAddress.StateProvinceId", UserData.CheckOut.STALE1);

		logExtentV5("Order_05 - Step 25: Enter to City textbox with value is '" + UserData.CheckOut.CITY1 + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_City", UserData.CheckOut.CITY1);

		logExtentV5("Order_05 - Step 26: Enter to Address 1 textbox with value is '" + UserData.CheckOut.ADDRESS_SHIPPING + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address1", UserData.CheckOut.ADDRESS_SHIPPING);

		logExtentV5("Order_05 - Step 27: Enter to Address 2 textbox with value is '" + UserData.CheckOut.ADDRESS2 + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address2", UserData.CheckOut.ADDRESS2);

		logExtentV5("Order_05 - Step 28: Enter to Zip / postal code textbox with value is '" + UserData.CheckOut.ZIP + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", UserData.CheckOut.ZIP);

		logExtentV5("Order_05 - Step 29: Enter to Phone number textbox with value is '" + UserData.CheckOut.PHONE_NUMBER + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", UserData.CheckOut.PHONE_NUMBER);

		logExtentV5("Order_05 - Step 30: Enter to Fax number textbox with value is '" + UserData.CheckOut.FAX + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FaxNumber", UserData.CheckOut.FAX);

		logExtentV5("Order_05 - Step 31: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("shipping-buttons-container");

		logExtentV5("Order_05 - Step 32: Click radio button 'Shipping by land transport'");
		checkOutPage.clickToRadioButtonByLabel(driver, "Ground ($0.00)");

		logExtentV5("Order_05 - Step 33: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("shipping-method-buttons-container");

		logExtentV5("Order_05 - Step 34: Click radio button 'Check / Money Order'");
		checkOutPage.clickToRadioButtonByLabel(driver, "Check / Money Order");

		logExtentV5("Order_05 - Step 35: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("payment-method-buttons-container");

		logExtentV5("Order_05 - Step 36: Verify payment info displayed");
		assertEquals(checkOutPage.getCheckoutPaymentInfoText(), "NOP SOLUTIONS" + "\nyour address here," + "\nNew York, NY 10001" + "\nUSA");

		logExtentV5("Order_05 - Step 37: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("payment-info-buttons-container");

		logExtentV5("Order_05 - Step 38: Verify billing address info displayed");
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "name"), UserData.CheckOut.FIRST_NAME + " " + UserData.CheckOut.LAST_NAME);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "email"), "Email: " + UserData.CheckOut.EMAIL);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "phone"), "Phone: " + UserData.CheckOut.PHONE_NUMBER);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "fax"), "Fax: " + UserData.CheckOut.FAX);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "company"), UserData.CheckOut.COMPANY);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "address1"), UserData.CheckOut.ADDRESS1);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "address2"), UserData.CheckOut.ADDRESS2);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "city-state-zip"), UserData.CheckOut.CITY + "," + UserData.CheckOut.ZIP);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "country"), UserData.CheckOut.COUNTRY);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Payment", "payment-method"), "Payment Method: Check / Money Order");

		logExtentV5("Order_05 - Step 39: Verify shipping address info displayed");
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "name"), UserData.CheckOut.FIRST_NAME + " " + UserData.CheckOut.LAST_NAME);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "email"), "Email: " + UserData.CheckOut.EMAIL);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "phone"), "Phone: " + UserData.CheckOut.PHONE_NUMBER);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "fax"), "Fax: " + UserData.CheckOut.FAX);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "company"), UserData.CheckOut.COMPANY);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "address1"), UserData.CheckOut.ADDRESS_SHIPPING);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "address2"), UserData.CheckOut.ADDRESS2);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "city-state-zip"),
				UserData.CheckOut.CITY1 + "," + UserData.CheckOut.ZIP);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "country"), UserData.CheckOut.COUNTRY1);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping", "shipping-method"), "Shipping Method: Ground");

		logExtentV5("Order_05 - Step 40: Verify product name at table displayed");
		assertTrue(checkOutPage.isProductNameDisplayed(driver, "product", "Lenovo IdeaCentre 600 All-in-One PC"));

		logExtentV5("Order_05 - Step 41: Verify sku at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "sku", "LE_IC_600"));

		logExtentV5("Order_05 - Step 42: Verify price at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "unit-price", "$500.00"));

		logExtentV5("Order_05 - Step 43: Verify quantity at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "quantity", "5"));

		logExtentV5("Order_05 - Step 44: Verify subtotal at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "subtotal", "$2,500.00"));

		logExtentV5("Order_05 - Step 45: Click on the button 'Confirm'");
		checkOutPage.clickConfirmButton();

		logExtentV5("Order_05 - Step 46: Verify screen Thank you displayed");
		assertEquals(checkOutPage.getOrderPageTitle(), "Thank you");
		assertEquals(checkOutPage.getOrderSuccessMessage(), "Your order has been successfully processed!");
		assertTrue(checkOutPage.isOrderNumberDisplayed());

		logExtentV5("Order_05 - Step 47: Get order Number Text");
		orderNumberText = checkOutPage.getOrderNumberText();

		logExtentV5("Order_05 - Step 48: Open customer Info Page");
		customerInfoPage = (UserCustomerInfoPageObject) checkOutPage.openPageAtHomeFooterByName(driver, "My account");

		logExtentV5("Order_05 - Step 49: Open orders Page");
		ordersPage = (UserOrdersPageObject) customerInfoPage.openPageAtMyAccountByName(driver, "Orders");

		logExtentV5("Order_05 - Step 50: Verify order number at order page and check out page");
		assertEquals(ordersPage.getOrderNumberTextAtOrderPage(), orderNumberText);

		logExtentV5("Order_05 - Step 51: Get order date");
		orderDate = ordersPage.getCurentDate();

		logExtentV5("Order_05 - Step 52: Click on the button 'Details'");
		ordersPage.clickToButtonByText(driver, "Details");

		logExtentV5("Order_05 - Step 53: Verify order date at order page and order information page");
		assertEquals(ordersPage.getOrderDate(), orderDate);

	}

	@Test
	public void Order_06_Re_Order(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Order_06 - Step 01: ");
		shoppingCartPage = ordersPage.clickToButtonReOrder("Re-order");

		logExtentV5("Order_06 - Step 02: Input to Qty textbox");
		shoppingCartPage.inputToQtyTextbox("10");

		logExtentV5("Order_06 - Step 03: Click to button 'Update shopping cart'");
		shoppingCartPage.clickToButtonByText(driver, "Update shopping cart");

		logExtentV5("Order_06 - Step 04: Verify subtotal is displayed correctly");
		shoppingCartPage.isProductDisplayed(driver, "subtotal", "$5,000.00");

		logExtentV5("Order_05 - Step 02: Click at checkbox I agree");
		shoppingCartPage.clickToRadioButtonByLabel(driver, "I agree with the terms");

		logExtentV5("Order_05 - Step 03: Click on the button 'Checkout'");
		checkOutPage = shoppingCartPage.clickToCheckOutButton();

		logExtentV5("Order_05 - Step 04: Uncheckbox 'Ship to the same address'");
		checkOutPage.unCheckToRadioButtonByLabel(driver, "Ship to the same address");

		logExtentV5("Order_05 - Step 18: Select dropdown with value is 'New Address'");
		checkOutPage.selectDropdownByName(driver, "billing_address_id", "New Address");

		logExtentV5("Order_05 - Step 05: Enter to First name textbox with value is + '" + UserData.ReOrder.FIRST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", UserData.ReOrder.FIRST_NAME);

		logExtentV5("Order_05 - Step 06: Enter to Last name textbox with value is '" + UserData.ReOrder.LAST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", UserData.ReOrder.LAST_NAME);

		logExtentV5("Order_05 - Step 07: Enter to Email textbox with value is '" + UserData.ReOrder.EMAIL + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", UserData.ReOrder.EMAIL);

		logExtentV5("Order_05 - Step 08: Enter to Company textbox with value is '" + UserData.ReOrder.COMPANY + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Company", UserData.ReOrder.COMPANY);

		logExtentV5("Order_05 - Step 09: Select dropdown country with value is '" + UserData.ReOrder.COUNTRY + "'");
		checkOutPage.selectDropdownByName(driver, "BillingNewAddress.CountryId", UserData.ReOrder.COUNTRY);

		logExtentV5("Order_05 - Step 10: Select dropdown State / province with value is '" + UserData.ReOrder.STALE + "'");
		assertTrue(checkOutPage.isPageLoadedSuccess(driver));
		checkOutPage.selectDropdownByName(driver, "BillingNewAddress.StateProvinceId", UserData.ReOrder.STALE);

		logExtentV5("Order_05 - Step 11: Enter to City textbox with value is '" + UserData.ReOrder.CITY + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_City", UserData.ReOrder.CITY);

		logExtentV5("Order_05 - Step 12: Enter to Address 1 textbox with value is '" + UserData.ReOrder.ADDRESS1 + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", UserData.ReOrder.ADDRESS1);

		logExtentV5("Order_05 - Step 13: Enter to Address 2 textbox with value is '" + UserData.ReOrder.ADDRESS2 + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_Address2", UserData.ReOrder.ADDRESS2);

		logExtentV5("Order_05 - Step 14: Enter to Zip / postal code textbox with value is '" + UserData.ReOrder.ZIP + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", UserData.ReOrder.ZIP);

		logExtentV5("Order_05 - Step 15: Enter to Phone number textbox with value is '" + UserData.ReOrder.PHONE_NUMBER + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", UserData.ReOrder.PHONE_NUMBER);

		logExtentV5("Order_05 - Step 16: Enter to Fax number textbox with value is '" + UserData.ReOrder.FAX + "'");
		checkOutPage.inputToTextboxByID(driver, "BillingNewAddress_FaxNumber", UserData.ReOrder.FAX);

		logExtentV5("Order_05 - Step 17: Click on the button 'Continue'");
		checkOutPage.clickToButtonByText(driver, "Continue");

		logExtentV5("Order_05 - Step 18: Select dropdown with value is 'New Address'");
		checkOutPage.selectDropdownByName(driver, "shipping_address_id", "New Address");

		logExtentV5("Order_05 - Step 19: Enter to First name textbox with value is + '" + UserData.ReOrder.FIRST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FirstName", UserData.ReOrder.FIRST_NAME);

		logExtentV5("Order_05 - Step 20: Enter to Last name textbox with value is '" + UserData.ReOrder.LAST_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_LastName", UserData.ReOrder.LAST_NAME);

		logExtentV5("Order_05 - Step 21: Enter to Email textbox with value is '" + UserData.ReOrder.EMAIL + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Email", UserData.ReOrder.EMAIL);

		logExtentV5("Order_05 - Step 22: Enter to Company textbox with value is '" + UserData.ReOrder.COMPANY + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Company", UserData.ReOrder.COMPANY);

		logExtentV5("Order_05 - Step 23: Select dropdown country with value is 'Viet Nam'");
		checkOutPage.selectDropdownByName(driver, "ShippingNewAddress.CountryId", UserData.ReOrder.COUNTRY1);

		logExtentV5("Order_05 - Step 24: Select dropdown State / province with value is 'Other'");
		assertTrue(checkOutPage.isPageLoadedSuccess(driver));
		checkOutPage.selectDropdownByName(driver, "ShippingNewAddress.StateProvinceId", UserData.ReOrder.STALE1);

		logExtentV5("Order_05 - Step 25: Enter to City textbox with value is '" + UserData.ReOrder.CITY1 + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_City", UserData.ReOrder.CITY1);

		logExtentV5("Order_05 - Step 26: Enter to Address 1 textbox with value is '" + UserData.ReOrder.ADDRESS_SHIPPING + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address1", UserData.ReOrder.ADDRESS_SHIPPING);

		logExtentV5("Order_05 - Step 27: Enter to Address 2 textbox with value is '" + UserData.ReOrder.ADDRESS2 + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_Address2", UserData.ReOrder.ADDRESS2);

		logExtentV5("Order_05 - Step 28: Enter to Zip / postal code textbox with value is '" + UserData.ReOrder.ZIP + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", UserData.ReOrder.ZIP);

		logExtentV5("Order_05 - Step 29: Enter to Phone number textbox with value is '" + UserData.ReOrder.PHONE_NUMBER + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", UserData.ReOrder.PHONE_NUMBER);

		logExtentV5("Order_05 - Step 30: Enter to Fax number textbox with value is '" + UserData.ReOrder.FAX + "'");
		checkOutPage.inputToTextboxByID(driver, "ShippingNewAddress_FaxNumber", UserData.ReOrder.FAX);

		logExtentV5("Order_05 - Step 31: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("shipping-buttons-container");

		logExtentV5("Order_05 - Step 32: Click radio button 'The one day air shipping '");
		checkOutPage.clickToRadioButtonByLabel(driver, "Next Day Air ($0.00)");

		logExtentV5("Order_05 - Step 33: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("shipping-method-buttons-container");

		logExtentV5("Order_05 - Step 34: Click radio button 'Credit Card'");
		checkOutPage.clickToRadioButtonByLabel(driver, "Credit Card");

		logExtentV5("Order_05 - Step 35: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("payment-method-buttons-container");

		logExtentV5("Order_05 - Step 18: Select dropdown with value is 'Master card'");
		checkOutPage.selectDropdownByName(driver, "CreditCardType", "Master card");

		logExtentV5("Order_05 - Step 30: Enter to Fax number textbox with value is '" + UserData.PaymentInformation.CARD_NAME + "'");
		checkOutPage.inputToTextboxByID(driver, "CardholderName", UserData.PaymentInformation.CARD_NAME);

		logExtentV5("Order_05 - Step 30: Enter to Fax number textbox with value is '" + UserData.PaymentInformation.CARD_NUMBER + "'");
		checkOutPage.inputToTextboxByID(driver, "CardNumber", UserData.PaymentInformation.CARD_NUMBER);

		logExtentV5("Order_05 - Step 18: Select dropdown with value is '" + UserData.PaymentInformation.MONTH_NUMBER + "'");
		checkOutPage.selectDropdownByName(driver, "ExpireMonth", UserData.PaymentInformation.MONTH_NUMBER);

		logExtentV5("Order_05 - Step 18: Select dropdown with value is '" + UserData.PaymentInformation.YEAR_NUMBER + "'");
		checkOutPage.selectDropdownByName(driver, "ExpireYear", UserData.PaymentInformation.YEAR_NUMBER);

		logExtentV5("Order_05 - Step 30: Enter to Fax number textbox with value is '" + UserData.PaymentInformation.CARD_CODE + "'");
		checkOutPage.inputToTextboxByID(driver, "CardCode", UserData.PaymentInformation.CARD_CODE);

		logExtentV5("Order_05 - Step 37: Click on the button 'Continue'");
		checkOutPage.clickToButtonContinue("payment-info-buttons-container");

		logExtentV5("Order_05 - Step 38: Verify billing address info displayed");
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "name"), UserData.ReOrder.FIRST_NAME + " " + UserData.ReOrder.LAST_NAME);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "email"), "Email: " + UserData.ReOrder.EMAIL);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "phone"), "Phone: " + UserData.ReOrder.PHONE_NUMBER);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "fax"), "Fax: " + UserData.ReOrder.FAX);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "company"), UserData.ReOrder.COMPANY);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "address1"), UserData.ReOrder.ADDRESS1);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "address2"), UserData.ReOrder.ADDRESS2);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "city-state-zip"), UserData.ReOrder.CITY + "," + UserData.ReOrder.ZIP);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Billing Address", "country"), UserData.ReOrder.COUNTRY);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Payment", "payment-method"), "Payment Method: Credit Card");

		logExtentV5("Order_05 - Step 39: Verify shipping address info displayed");
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "name"), UserData.ReOrder.FIRST_NAME + " " + UserData.ReOrder.LAST_NAME);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "email"), "Email: " + UserData.ReOrder.EMAIL);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "phone"), "Phone: " + UserData.ReOrder.PHONE_NUMBER);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "fax"), "Fax: " + UserData.ReOrder.FAX);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "company"), UserData.ReOrder.COMPANY);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "address1"), UserData.ReOrder.ADDRESS_SHIPPING);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "address2"), UserData.ReOrder.ADDRESS2);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "city-state-zip"),
				UserData.ReOrder.CITY1 + "," + UserData.ReOrder.ZIP);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping Address", "country"), UserData.ReOrder.COUNTRY1);
		assertEquals(checkOutPage.getConfirmOrderInfoByTitle(driver, "Shipping", "shipping-method"), "Shipping Method: Next Day Air");

		logExtentV5("Order_05 - Step 40: Verify product name at table displayed");
		assertTrue(checkOutPage.isProductNameDisplayed(driver, "product", "Lenovo IdeaCentre 600 All-in-One PC"));

		logExtentV5("Order_05 - Step 41: Verify sku at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "sku", "LE_IC_600"));

		logExtentV5("Order_05 - Step 42: Verify price at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "unit-price", "$500.00"));

		logExtentV5("Order_05 - Step 43: Verify quantity at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "quantity", "10"));

		logExtentV5("Order_05 - Step 44: Verify subtotal at table displayed");
		assertTrue(checkOutPage.isProductDisplayed(driver, "subtotal", "$5,000.00"));

		logExtentV5("Order_05 - Step 45: Click on the button 'Confirm'");
		checkOutPage.clickButtonUntilNoAlertIsDisplayed();

		logExtentV5("Order_05 - Step 46: Verify screen Thank you displayed");
		assertEquals(checkOutPage.getOrderPageTitle(), "Thank you");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}