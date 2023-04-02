package com.nopcommerce.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.AdminData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.admin.AdminCatalogPageObject;
import pageObject.nopCommerce.admin.AdminCustomerChildPageObject;
import pageObject.nopCommerce.admin.AdminCustomersPageObject;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.admin.AdminProductsPageObject;

public class Nopcommerce_01_Admin extends BaseTest {
	private WebDriver driver;
	private String browser;

	private String Email, password;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "osVersion", "browserVersion" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("DEV") String serverName, @Optional("Chrome") String browser, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("latest") String browserVersion) {
		driver = getBrowserDriverAll(envName, serverName, browser, ipAddress, portNumber, osName, osVersion, browserVersion);
		this.browser = browser.toUpperCase();
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		Email = AdminData.Login.EMAIL;
		password = AdminData.Login.PASSWORD;

		adminDashboardPage = adminLoginPage.loginAsAdmin(Email, password);
		assertTrue(adminDashboardPage.isPageLoadedSuccess(driver));

	}

	// @Test()
	public void Admin_01_Search_Product_Name(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_01 - Step " + step() + ": Open Catalog page");
		adminCatalogPage = (AdminCatalogPageObject) adminDashboardPage.openPageAtDashboardByName(driver, "Catalog");

		logExtentV5("Admin_01 - Step " + step() + ": Open Products page");
		adminProductsPage = (AdminProductsPageObject) adminCatalogPage.openPageAtDashboardByName(driver, "Products");

		logExtentV5("Admin_01 - Step " + step() + ": Input to Product name textbox with value is '" + AdminData.ProductPage.PRODUCT_NAME + "'");
		adminProductsPage.inputToTextboxByIDAdmin(driver, "SearchProductName", AdminData.ProductPage.PRODUCT_NAME);

		logExtentV5("Admin_01 - Step " + step() + ": Click to button Search");
		adminProductsPage.clickToButtonByIDAdmin(driver, "search-products");

		logExtentV5("Admin_01 - Step " + step() + ": Verify product info in table");
		assertEquals(adminProductsPage.getProductSize(AdminData.ProductPage.PRODUCT_NAME), 1);
		assertTrue(adminProductsPage.isProductInfoDisplayedInTableAdmin(driver, "Product name", AdminData.ProductPage.PRODUCT_NAME));
		assertTrue(adminProductsPage.isProductInfoDisplayedInTableAdmin(driver, "SKU", AdminData.ProductPage.SKU));
		assertTrue(adminProductsPage.isProductInfoDisplayedInTableAdmin(driver, "Price", AdminData.ProductPage.PRICE));
		assertTrue(adminProductsPage.isProductInfoDisplayedInTableAdmin(driver, "Stock quantity", AdminData.ProductPage.QUANTITY));
	}

	// @Test()
	public void Admin_02_Search_Product_Name(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_02 - Step " + step() + ": Input to Product name textbox with value is '" + AdminData.ProductPage.PRODUCT_NAME + "'");
		adminProductsPage.inputToTextboxByIDAdmin(driver, "SearchProductName", AdminData.ProductPage.PRODUCT_NAME);

		logExtentV5("Admin_02 - Step " + step() + ": Select dropdown Category with value is 'Computers'");
		adminProductsPage.selectDropdownByName(driver, "SearchCategoryId", "Computers");

		logExtentV5("Admin_02 - Step " + step() + ": Uncheck checkbox 'Search subcategories'");
		adminProductsPage.unCheckToRadioButton();

		logExtentV5("Admin_02 - Step " + step() + ": Click to button Search");
		adminProductsPage.clickToButtonByIDAdmin(driver, "search-products");

		logExtentV5("Admin_02 - Step " + step() + ": Verify message no data displayed");
		assertEquals(adminProductsPage.getDataTableEmpty(), "No data available in table");

	}

	// @Test()
	public void Admin_03_Search_Product_Name(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_03 - Step " + step() + ": Input to Product name textbox with value is '" + AdminData.ProductPage.PRODUCT_NAME + "'");
		adminProductsPage.inputToTextboxByIDAdmin(driver, "SearchProductName", AdminData.ProductPage.PRODUCT_NAME);

		logExtentV5("Admin_03 - Step " + step() + ": Select dropdown Category with value is 'Electronics'");
		adminProductsPage.selectDropdownByName(driver, "SearchCategoryId", "Electronics");

		logExtentV5("Admin_03 - Step " + step() + ": Check checkbox 'Search subcategories'");
		adminProductsPage.checkToRadioButton();

		logExtentV5("Admin_03 - Step " + step() + ": Click to button Search");
		adminProductsPage.clickToButtonByIDAdmin(driver, "search-products");

		logExtentV5("Admin_03 - Step " + step() + ": Verify product info in table");
		assertEquals(adminProductsPage.getProductSize(AdminData.ProductPage.PRODUCT_NAME), 1);
	}

	// @Test()
	public void Admin_04_Search_Product_Name(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_04 - Step " + step() + ": Input to Product name textbox with value is '" + AdminData.ProductPage.PRODUCT_NAME + "'");
		adminProductsPage.inputToTextboxByIDAdmin(driver, "SearchProductName", AdminData.ProductPage.PRODUCT_NAME);

		logExtentV5("Admin_04 - Step " + step() + ": Select dropdown Category with value is 'Electronics >> Camera & photo'");
		adminProductsPage.selectDropdownByName(driver, "SearchCategoryId", "Electronics >> Camera & photo");

		logExtentV5("Admin_04 - Step " + step() + ": Uncheck checkbox 'Search subcategories'");
		adminProductsPage.unCheckToRadioButton();

		logExtentV5("Admin_04 - Step " + step() + ": Click to button Search");
		adminProductsPage.clickToButtonByIDAdmin(driver, "search-products");

		logExtentV5("Admin_04 - Step " + step() + ": Verify product info in table");
		assertEquals(adminProductsPage.getProductSize(AdminData.ProductPage.PRODUCT_NAME), 1);
	}

	// @Test()
	public void Admin_05_Search_Product_Name(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_05 - Step " + step() + ": Input to Product name textbox with value is '" + AdminData.ProductPage.PRODUCT_NAME + "'");
		adminProductsPage.inputToTextboxByIDAdmin(driver, "SearchProductName", AdminData.ProductPage.PRODUCT_NAME);

		logExtentV5("Admin_05 - Step " + step() + ": Select dropdown Category with value is 'All'");
		adminProductsPage.selectDropdownByName(driver, "SearchCategoryId", "All");

		logExtentV5("Admin_05 - Step " + step() + ": Uncheck checkbox 'Search subcategories'");
		adminProductsPage.unCheckToRadioButton();

		logExtentV5("Admin_05 - Step " + step() + ": Select dropdown Manufacturer with value is 'HP'");
		adminProductsPage.selectDropdownByName(driver, "SearchManufacturerId", "HP");

		logExtentV5("Admin_05 - Step " + step() + ": Click to button Search");
		adminProductsPage.clickToButtonByIDAdmin(driver, "search-products");

		logExtentV5("Admin_05 - Step " + step() + ": Verify message no data displayed");
		assertEquals(adminProductsPage.getDataTableEmpty(), "No data available in table");
	}

	// @Test()
	public void Admin_06_Go_Directly_To_Product_SKU(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_06 - Step " + step() + ": Input to Go directly to product SKU");
		adminProductsPage.inputToTextboxByIDAdmin(driver, "GoDirectlyToSku", AdminData.ProductPage.SKU);

		logExtentV5("Admin_06 - Step " + step() + ": Click to button Go");
		adminProductsPage.clickToButtonByIDAdmin(driver, "go-to-product-by-sku");

		logExtentV5("Admin_06 - Step " + step() + ": Verify title product name displayed");
		assertEquals(adminProductsPage.getProductNameisDisplayed(), AdminData.ProductPage.PRODUCT_NAME);

		logExtentV5("Admin_06 - Step " + step() + ": Verify title product name displayed");
		assertEquals(adminProductsPage.getAttributeValue("value"), AdminData.ProductPage.PRODUCT_NAME);
	}

	@Test()
	public void Admin_07_Go_Directly_To_Product_SKU(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomersPage = (AdminCustomersPageObject) adminDashboardPage.openPageCustomersByName(driver, "Customers");

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage = (AdminCustomerChildPageObject) adminDashboardPage.openPageCustomerChildByName(driver, "Customers");

		logExtentV5("Admin_07 - Step " + step() + ": Click to button 'Add new'");
		adminCustomerChildPage.clickToButtonAddNew();

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "Email", AdminData.CustomerInfo.EMAIL);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "Password", AdminData.CustomerInfo.PASSWORD);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "FirstName", AdminData.CustomerInfo.FIRST_NAME);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "LastName", AdminData.CustomerInfo.LAST_NAME);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.clickToCheckboxByLabelAdmin(driver, AdminData.CustomerInfo.GENDER);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "DateOfBirth", AdminData.CustomerInfo.DATE_OF_BIRTH);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "Company", AdminData.CustomerInfo.COMPANY);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.inputToTextaraComment(AdminData.CustomerInfo.ADMIN_COMMENT);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.clickToButtonSaveAndContinue();

		logExtentV5("Admin_07 - Step " + step() + ": ");
		assertEquals(adminCustomerChildPage.getMessageSuccess(), AdminData.CustomerInfo.MESSAGE_SUCCESS);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.clickToLinkBackToCustom();

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_07 - Step " + step() + ": ");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

	}

	// @AfterClass(alwaysRun = true)
	// public void afterClass() {
	// closeBrowserAndDriver();
	// }

	private AdminDashboardPageObject adminDashboardPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminCatalogPageObject adminCatalogPage;
	private AdminProductsPageObject adminProductsPage;
	private AdminCustomersPageObject adminCustomersPage;
	private AdminCustomerChildPageObject adminCustomerChildPage;

}
