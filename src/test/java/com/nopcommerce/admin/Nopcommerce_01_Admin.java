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
import pageObject.nopCommerce.admin.AdminCustomersChildCreatePageObject;
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

	@Test()
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

	@Test()
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

	@Test()
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

	@Test()
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

	@Test()
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

	@Test()
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
	public void Admin_07_Creat_New_Customer(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_07 - Step " + step() + ": Show tree folder Customers");
		adminDashboardPage.showFolderTreeByTextAdmin(driver, "Customers");

		logExtentV5("Admin_07 - Step " + step() + ": Open Customers page");
		adminCustomerChildPage = (AdminCustomerChildPageObject) adminDashboardPage.openPageCustomerChildByName(driver, "Customers");

		logExtentV5("Admin_07 - Step " + step() + ": Click to button 'Add new'");
		adminCustomersChildCreatePage = adminCustomerChildPage.clickToButtonAddNew();

		logExtentV5("Admin_07 - Step " + step() + ": Verify page ready");
		assertTrue(adminCustomersChildCreatePage.isPageLoadedSuccess(driver));

		logExtentV5("Admin_07 - Step " + step() + ": open tree folder Customer info");
		adminCustomersChildCreatePage.openCartTitleAtCustomerEditByText(driver, "Customer info");

		logExtentV5("Admin_07 - Step " + step() + ": Input to Email with value '" + AdminData.CustomerInfo.EMAIL + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Email", AdminData.CustomerInfo.EMAIL);

		logExtentV5("Admin_07 - Step " + step() + ": Input to Password with value '" + AdminData.CustomerInfo.PASSWORD + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Password", AdminData.CustomerInfo.PASSWORD);

		logExtentV5("Admin_07 - Step " + step() + ": Input to FirstName with value '" + AdminData.CustomerInfo.FIRST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "FirstName", AdminData.CustomerInfo.FIRST_NAME);

		logExtentV5("Admin_07 - Step " + step() + ": Input to LastName with value '" + AdminData.CustomerInfo.LAST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "LastName", AdminData.CustomerInfo.LAST_NAME);

		logExtentV5("Admin_07 - Step " + step() + ": Check to radio button Gender with value '" + AdminData.CustomerInfo.GENDER + "'");
		adminCustomersChildCreatePage.clickToCheckboxByLabelAdmin(driver, AdminData.CustomerInfo.GENDER);

		logExtentV5("Admin_07 - Step " + step() + ": Input to DateOfBirth with value '" + AdminData.CustomerInfo.DATE_OF_BIRTH + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "DateOfBirth", AdminData.CustomerInfo.DATE_OF_BIRTH);

		logExtentV5("Admin_07 - Step " + step() + ": Input to Company with value '" + AdminData.CustomerInfo.COMPANY + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Company", AdminData.CustomerInfo.COMPANY);

		logExtentV5("Admin_07 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomersChildCreatePage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_07 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES + "'");
		adminCustomersChildCreatePage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_07 - Step " + step() + ": Input to Comment with value '" + AdminData.CustomerInfo.ADMIN_COMMENT + "'");
		adminCustomersChildCreatePage.inputToTextaraComment(AdminData.CustomerInfo.ADMIN_COMMENT);

		logExtentV5("Admin_07 - Step " + step() + ": Click to button 'Save and continue'");
		adminCustomersChildCreatePage.clickToButtonSaveAndContinue();

		logExtentV5("Admin_07 - Step " + step() + ": Verify message displayed is '" + AdminData.CustomerInfo.MESSAGE_SUCCESS + "'");
		assertEquals(adminCustomersChildCreatePage.getMessageSuccess(), AdminData.CustomerInfo.MESSAGE_SUCCESS);

		logExtentV5("Admin_07 - Step " + step() + ": Close message");
		adminCustomersChildCreatePage.clickToIconClose();

		logExtentV5("Admin_07 - Step " + step() + ": Click to link 'Back to customer'");
		adminCustomerChildPage = adminCustomersChildCreatePage.clickToLinkBackToCustom();

		logExtentV5("Admin_07 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_07 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_07 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_07 - Step " + step() + ": Verify customer info in table");
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Name", AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Company name", AdminData.CustomerInfo.COMPANY));
	}

	@Test()
	public void Admin_08_Search_Customer_With_Email(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_08 - Step " + step() + ": Open Dashboard page");
		adminDashboardPage = (AdminDashboardPageObject) adminCustomerChildPage.openPageAtDashboardByName(driver, "Dashboard");

		logExtentV5("Admin_08 - Step " + step() + ": Show tree folder Customers");
		adminDashboardPage.showFolderTreeByTextAdmin(driver, "Customers");

		logExtentV5("Admin_08 - Step " + step() + ": Open Customers page");
		adminCustomerChildPage = (AdminCustomerChildPageObject) adminDashboardPage.openPageCustomerChildByName(driver, "Customers");

		logExtentV5("Admin_08 - Step " + step() + ": Input to Email with value '" + AdminData.CustomerInfo.EMAIL + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchEmail", AdminData.CustomerInfo.EMAIL);

		logExtentV5("Admin_08 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_08 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_08 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_08 - Step " + step() + ": Verify customer info in table");
		assertEquals(adminCustomerChildPage.getProductSize(AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME), 1);
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Name", AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Company name", AdminData.CustomerInfo.COMPANY));

	}

	@Test()
	public void Admin_09_Search_Customer_With_Firstname_Lastname(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_09 - Step " + step() + ": Refresh Customer child page");
		adminCustomerChildPage.refreshCurrentPage(driver);

		logExtentV5("Admin_09 - Step " + step() + ": Input to FirstName with value '" + AdminData.CustomerInfo.FIRST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchFirstName", AdminData.CustomerInfo.FIRST_NAME);

		logExtentV5("Admin_09 - Step " + step() + ": Input to LastName with value '" + AdminData.CustomerInfo.LAST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchLastName", AdminData.CustomerInfo.LAST_NAME);

		logExtentV5("Admin_09 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_09 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_09 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_09 - Step " + step() + ": Verify customer info in table");
		assertEquals(adminCustomerChildPage.getProductSize(AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME), 1);
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Name", AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Company name", AdminData.CustomerInfo.COMPANY));

	}

	@Test()
	public void Admin_10_Search_Customer_With_Company(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_10 - Step " + step() + ": Refresh Customer child page");
		adminCustomerChildPage.refreshCurrentPage(driver);

		logExtentV5("Admin_10 - Step " + step() + ": Input to Company with value '" + AdminData.CustomerInfo.COMPANY + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchCompany", AdminData.CustomerInfo.COMPANY);

		logExtentV5("Admin_10 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_10 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_10 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_10 - Step " + step() + ": Verify customer info in table");
		assertEquals(adminCustomerChildPage.getProductSize(AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME), 1);
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Name", AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Company name", AdminData.CustomerInfo.COMPANY));

	}

	@Test()
	public void Admin_11_Search_Customer_With_Full_Data(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_11 - Step " + step() + ": Refresh Customer child page");
		adminCustomerChildPage.refreshCurrentPage(driver);

		logExtentV5("Admin_11 - Step " + step() + ": Input to Email with value '" + AdminData.CustomerInfo.EMAIL + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchEmail", AdminData.CustomerInfo.EMAIL);

		logExtentV5("Admin_11 - Step " + step() + ": Input to FirstName with value '" + AdminData.CustomerInfo.FIRST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchFirstName", AdminData.CustomerInfo.FIRST_NAME);

		logExtentV5("Admin_11 - Step " + step() + ": Input to LastName with value '" + AdminData.CustomerInfo.LAST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchLastName", AdminData.CustomerInfo.LAST_NAME);

		logExtentV5("Admin_11 - Step " + step() + ": Input to LastName with value '" + AdminData.CustomerInfo.DAY + "'");
		adminCustomerChildPage.selectDropdownByName(driver, "SearchDayOfBirth", AdminData.CustomerInfo.DAY);

		logExtentV5("Admin_11 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.MONTH + "'");
		adminCustomerChildPage.selectDropdownByName(driver, "SearchMonthOfBirth", AdminData.CustomerInfo.MONTH);

		logExtentV5("Admin_11 - Step " + step() + ": Input to Company with value '" + AdminData.CustomerInfo.COMPANY + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchCompany", AdminData.CustomerInfo.COMPANY);

		logExtentV5("Admin_11 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_11 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_11 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_11 - Step " + step() + ": Verify customer info in table");
		assertEquals(adminCustomerChildPage.getProductSize(AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME), 1);
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Name", AdminData.CustomerInfo.FIRST_NAME + " " + AdminData.CustomerInfo.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Company name", AdminData.CustomerInfo.COMPANY));
	}

	@Test()
	public void Admin_12_Edit_Customer(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_12 - Step " + step() + ": Click to button 'Edit'");
		adminCustomersChildCreatePage = adminCustomerChildPage.clickButtonEditAtTable();

		logExtentV5("Admin_12 - Step " + step() + ": Input to Email with value '" + AdminData.CustomerEditInfo.EMAIL + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Email", AdminData.CustomerEditInfo.EMAIL);

		logExtentV5("Admin_12 - Step " + step() + ": Input to Password with value '" + AdminData.CustomerEditInfo.PASSWORD + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Password", AdminData.CustomerEditInfo.PASSWORD);

		logExtentV5("Admin_12 - Step " + step() + ": Input to FirstName with value '" + AdminData.CustomerEditInfo.FIRST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "FirstName", AdminData.CustomerEditInfo.FIRST_NAME);

		logExtentV5("Admin_12 - Step " + step() + ": Input to LastName with value '" + AdminData.CustomerEditInfo.LAST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "LastName", AdminData.CustomerEditInfo.LAST_NAME);

		logExtentV5("Admin_12 - Step " + step() + ": Input to GENDER with value '" + AdminData.CustomerEditInfo.GENDER + "'");
		adminCustomersChildCreatePage.clickToCheckboxByLabelAdmin(driver, AdminData.CustomerEditInfo.GENDER);

		logExtentV5("Admin_12 - Step " + step() + ": Input to DateOfBirth with value '" + AdminData.CustomerEditInfo.DATE_OF_BIRTH + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "DateOfBirth", AdminData.CustomerEditInfo.DATE_OF_BIRTH);

		logExtentV5("Admin_12 - Step " + step() + ": Input to Company with value '" + AdminData.CustomerEditInfo.COMPANY + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Company", AdminData.CustomerEditInfo.COMPANY);

		logExtentV5("Admin_12 - Step " + step() + ": Input to COMMENT with value '" + AdminData.CustomerEditInfo.ADMIN_COMMENT + "'");
		adminCustomersChildCreatePage.inputToTextaraComment(AdminData.CustomerEditInfo.ADMIN_COMMENT);

		logExtentV5("Admin_12 - Step " + step() + ": Click to button 'Save'");
		adminCustomerChildPage = adminCustomersChildCreatePage.clickToButtonSave();

		logExtentV5("Admin_12 - Step " + step() + ": Verify message displayed is '" + AdminData.CustomerEditInfo.MESSAGE_SUCCESS + "'");
		assertEquals(adminCustomerChildPage.getMessageSuccessEdit(), AdminData.CustomerEditInfo.MESSAGE_SUCCESS);

		logExtentV5("Admin_12 - Step " + step() + ": Input to SearchEmail with value '" + AdminData.CustomerEditInfo.EMAIL + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchEmail", AdminData.CustomerEditInfo.EMAIL);

		logExtentV5("Admin_12 - Step " + step() + ": Input to SearchFirstName with value '" + AdminData.CustomerEditInfo.FIRST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchFirstName", AdminData.CustomerEditInfo.FIRST_NAME);

		logExtentV5("Admin_12 - Step " + step() + ": Input to SearchLastName with value '" + AdminData.CustomerEditInfo.LAST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchLastName", AdminData.CustomerEditInfo.LAST_NAME);

		logExtentV5("Admin_12 - Step " + step() + ": Input to SearchDayOfBirth with value '" + AdminData.CustomerEditInfo.DAY + "'");
		adminCustomerChildPage.selectDropdownByName(driver, "SearchDayOfBirth", AdminData.CustomerEditInfo.DAY);

		logExtentV5("Admin_12 - Step " + step() + ": Input to SearchMonthOfBirth with value '" + AdminData.CustomerEditInfo.MONTH + "'");
		adminCustomerChildPage.selectDropdownByName(driver, "SearchMonthOfBirth", AdminData.CustomerEditInfo.MONTH);

		logExtentV5("Admin_12 - Step " + step() + ": Input to SearchCompany with value '" + AdminData.CustomerEditInfo.COMPANY + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchCompany", AdminData.CustomerEditInfo.COMPANY);

		logExtentV5("Admin_12 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerEditInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerEditInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_12 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerEditInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerEditInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_12 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_12 - Step " + step() + ": Verify customer info in table");
		assertEquals(adminCustomerChildPage.getProductSize(AdminData.CustomerEditInfo.FIRST_NAME + " " + AdminData.CustomerEditInfo.LAST_NAME), 1);
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Name", AdminData.CustomerEditInfo.FIRST_NAME + " " + AdminData.CustomerEditInfo.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAdmin(driver, "Company name", AdminData.CustomerEditInfo.COMPANY));
	}

	@Test()
	public void Admin_13_Add_New_Address(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_13 - Step " + step() + ": Refresh Customer child page");
		adminCustomerChildPage.refreshCurrentPage(driver);

		logExtentV5("Admin_13 - Step " + step() + ": Input to SearchEmail with value '" + AdminData.CustomerEditInfo.EMAIL + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchEmail", AdminData.CustomerEditInfo.EMAIL);

		logExtentV5("Admin_13 - Step " + step() + ": Input to SearchFirstName with value '" + AdminData.CustomerEditInfo.FIRST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchFirstName", AdminData.CustomerEditInfo.FIRST_NAME);

		logExtentV5("Admin_13 - Step " + step() + ": Input to SearchLastName with value '" + AdminData.CustomerEditInfo.LAST_NAME + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchLastName", AdminData.CustomerEditInfo.LAST_NAME);

		logExtentV5("Admin_13 - Step " + step() + ": Input to SearchDayOfBirth with value '" + AdminData.CustomerEditInfo.DAY + "'");
		adminCustomerChildPage.selectDropdownByName(driver, "SearchDayOfBirth", AdminData.CustomerEditInfo.DAY);

		logExtentV5("Admin_13 - Step " + step() + ": Input to SearchMonthOfBirth with value '" + AdminData.CustomerEditInfo.MONTH + "'");
		adminCustomerChildPage.selectDropdownByName(driver, "SearchMonthOfBirth", AdminData.CustomerEditInfo.MONTH);

		logExtentV5("Admin_13 - Step " + step() + ": Input to SearchCompany with value '" + AdminData.CustomerEditInfo.COMPANY + "'");
		adminCustomerChildPage.inputToTextboxByIDAdmin(driver, "SearchCompany", AdminData.CustomerEditInfo.COMPANY);

		logExtentV5("Admin_13 - Step " + step() + ": Unselect to Roles with value '" + AdminData.CustomerEditInfo.CUSTOMER_ROLES_RESGISTER + "'");
		adminCustomerChildPage.unSelectDropdownRoles(AdminData.CustomerEditInfo.CUSTOMER_ROLES_RESGISTER);

		logExtentV5("Admin_13 - Step " + step() + ": Select to Roles with value '" + AdminData.CustomerEditInfo.CUSTOMER_ROLES + "'");
		adminCustomerChildPage.selectDropdownRoles(AdminData.CustomerEditInfo.CUSTOMER_ROLES);

		logExtentV5("Admin_13 - Step " + step() + ": Click to button 'Search'");
		adminCustomerChildPage.clickToButtonByIDAdmin(driver, "search-customers");

		logExtentV5("Admin_13 - Step " + step() + ": Click to button 'Edit' in table");
		adminCustomersChildCreatePage = adminCustomerChildPage.clickButtonEditAtTable();

		logExtentV5("Admin_13 - Step " + step() + ": Close tree folder Customer info");
		adminCustomersChildCreatePage.closeCartTitleAtCustomerEditByText(driver, "Customer info");

		logExtentV5("Admin_13 - Step " + step() + ": Open tree folder Addresses");
		adminCustomersChildCreatePage.openCartTitleAtCustomerEditByText(driver, "Addresses");

		logExtentV5("Admin_13 - Step " + step() + ": Click to button 'Add new'");
		adminCustomersChildCreatePage.clickToAddNewAddressButton();

		logExtentV5("Admin_13 - Step " + step() + ": Input to Address_FirstName with value '" + AdminData.AddNewAddress.FIRST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_FirstName", AdminData.AddNewAddress.FIRST_NAME);

		logExtentV5("Admin_13 - Step " + step() + ": Input to Address_LastName with value '" + AdminData.AddNewAddress.LAST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_LastName", AdminData.AddNewAddress.LAST_NAME);

		logExtentV5("Admin_13 - Step " + step() + ": Input to Address_Email with value '" + AdminData.AddNewAddress.EMAIL + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Email", AdminData.AddNewAddress.EMAIL);

		logExtentV5("Admin_13 - Step " + step() + ": Input to Address_Company with value '" + AdminData.AddNewAddress.COMPANY + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Company", AdminData.AddNewAddress.COMPANY);

		logExtentV5("Admin_13 - Step " + step() + ": Input to Country with value '" + AdminData.AddNewAddress.COUNTRY + "'");
		adminCustomersChildCreatePage.selectDropdownByName(driver, "Address.CountryId", AdminData.AddNewAddress.COUNTRY);

		logExtentV5("Admin_13 - Step " + step() + ": Input to State with value '" + AdminData.AddNewAddress.STALE + "'");
		adminCustomersChildCreatePage.selectDropdownByName(driver, "Address.StateProvinceId", AdminData.AddNewAddress.STALE);

		logExtentV5("Admin_13 - Step " + step() + ": Input to CITY with value '" + AdminData.AddNewAddress.CITY + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_City", AdminData.AddNewAddress.CITY);

		logExtentV5("Admin_13 - Step " + step() + ": Input to ADD1 with value '" + AdminData.AddNewAddress.ADD1 + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Address1", AdminData.AddNewAddress.ADD1);

		logExtentV5("Admin_13 - Step " + step() + ": Input to ADD2 with value '" + AdminData.AddNewAddress.ADD2 + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Address2", AdminData.AddNewAddress.ADD2);

		logExtentV5("Admin_13 - Step " + step() + ": Input to ZIP with value '" + AdminData.AddNewAddress.ZIP + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_ZipPostalCode", AdminData.AddNewAddress.ZIP);

		logExtentV5("Admin_13 - Step " + step() + ": Input to PHONE_NUMBER with value '" + AdminData.AddNewAddress.PHONE_NUMBER + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_PhoneNumber", AdminData.AddNewAddress.PHONE_NUMBER);

		logExtentV5("Admin_13 - Step " + step() + ": Input to FAX_NUMBER with value '" + AdminData.AddNewAddress.FAX_NUMBER + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_FaxNumber", AdminData.AddNewAddress.FAX_NUMBER);

		logExtentV5("Admin_13 - Step " + step() + ": Click to button 'Save'");
		adminCustomersChildCreatePage.clickToButtonByNormalizeSpace(driver, "Save");

		logExtentV5("Admin_13 - Step " + step() + ": Verify message displayed is '" + AdminData.AddNewAddress.MESSAGE_SUCCESS + "'");
		assertEquals(adminCustomerChildPage.getMessageSuccessAddress(), AdminData.AddNewAddress.MESSAGE_SUCCESS);

		logExtentV5("Admin_13 - Step " + step() + ": Click to icon 'Close' message");
		adminCustomerChildPage.clickToIconClose();

		logExtentV5("Admin_13 - Step " + step() + ": Click to link 'Back Customer Detail'");
		adminCustomerChildPage.clickBackCustomerDetailLink();

		logExtentV5("Admin_13 - Step " + step() + ": Verify Address info in table");
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "First name", AdminData.AddNewAddress.FIRST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Last name", AdminData.AddNewAddress.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Email", AdminData.AddNewAddress.EMAIL));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Phone number", AdminData.AddNewAddress.PHONE_NUMBER));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Fax number", AdminData.AddNewAddress.FAX_NUMBER));
		assertEquals(adminCustomerChildPage.getAddressInTable("Address"), AdminData.AddNewAddress.ADDRESS_IN_TABLE);

	}

	@Test()
	public void Admin_14_Edit_Address(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_14 - Step " + step() + ": Refresh Customer child page");
		adminCustomerChildPage.refreshCurrentPage(driver);

		logExtentV5("Admin_14 - Step " + step() + ": Click to button edit address");
		adminCustomerChildPage.clickToButtonEditInTableAddress();

		logExtentV5("Admin_14 - Step " + step() + ": Input to Address_FirstName with value '" + AdminData.EditAddress.FIRST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_FirstName", AdminData.EditAddress.FIRST_NAME);

		logExtentV5("Admin_14 - Step " + step() + ": Input to Address_LastName with value '" + AdminData.EditAddress.LAST_NAME + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_LastName", AdminData.EditAddress.LAST_NAME);

		logExtentV5("Admin_14 - Step " + step() + ": Input to Address_Email with value '" + AdminData.EditAddress.EMAIL + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Email", AdminData.EditAddress.EMAIL);

		logExtentV5("Admin_14 - Step " + step() + ": Input to Address_Company with value '" + AdminData.EditAddress.COMPANY + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Company", AdminData.EditAddress.COMPANY);

		logExtentV5("Admin_14 - Step " + step() + ": Input to Country with value '" + AdminData.EditAddress.COUNTRY + "'");
		adminCustomersChildCreatePage.selectDropdownByName(driver, "Address.CountryId", AdminData.EditAddress.COUNTRY);

		logExtentV5("Admin_14 - Step " + step() + ": Input to State with value '" + AdminData.EditAddress.STALE + "'");
		adminCustomersChildCreatePage.selectDropdownByName(driver, "Address.StateProvinceId", AdminData.EditAddress.STALE);

		logExtentV5("Admin_14 - Step " + step() + ": Input to CITY with value '" + AdminData.EditAddress.CITY + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_City", AdminData.EditAddress.CITY);

		logExtentV5("Admin_14 - Step " + step() + ": Input to ADD1 with value '" + AdminData.EditAddress.ADD1 + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Address1", AdminData.EditAddress.ADD1);

		logExtentV5("Admin_14 - Step " + step() + ": Input to ADD2 with value '" + AdminData.EditAddress.ADD2 + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_Address2", AdminData.EditAddress.ADD2);

		logExtentV5("Admin_14 - Step " + step() + ": Input to ZIP with value '" + AdminData.EditAddress.ZIP + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_ZipPostalCode", AdminData.EditAddress.ZIP);

		logExtentV5("Admin_14 - Step " + step() + ": Input to PHONE_NUMBER with value '" + AdminData.EditAddress.PHONE_NUMBER + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_PhoneNumber", AdminData.EditAddress.PHONE_NUMBER);

		logExtentV5("Admin_14 - Step " + step() + ": Input to FAX_NUMBER with value '" + AdminData.EditAddress.FAX_NUMBER + "'");
		adminCustomersChildCreatePage.inputToTextboxByIDAdmin(driver, "Address_FaxNumber", AdminData.EditAddress.FAX_NUMBER);

		logExtentV5("Admin_14 - Step " + step() + ": Click to button 'Save'");
		adminCustomersChildCreatePage.clickToButtonByNormalizeSpace(driver, "Save");

		logExtentV5("Admin_14 - Step " + step() + ": Verify message displayed is '" + AdminData.EditAddress.MESSAGE_SUCCESS + "'");
		assertEquals(adminCustomerChildPage.getMessageSuccessEditAddress(), AdminData.EditAddress.MESSAGE_SUCCESS);

		logExtentV5("Admin_14 - Step " + step() + ": Click to icon 'Close' message");
		adminCustomerChildPage.clickToIconClose();

		logExtentV5("Admin_14 - Step " + step() + ": Click to link 'Back Customer Detail'");
		adminCustomerChildPage.clickBackCustomerDetailLink();

		logExtentV5("Admin_14 - Step " + step() + ": Verify Address info in table");
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "First name", AdminData.EditAddress.FIRST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Last name", AdminData.EditAddress.LAST_NAME));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Email", AdminData.EditAddress.EMAIL));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Phone number", AdminData.EditAddress.PHONE_NUMBER));
		assertTrue(adminCustomerChildPage.isProductInfoDisplayedInTableAddressAdmin(driver, "Fax number", AdminData.EditAddress.FAX_NUMBER));
		assertEquals(adminCustomerChildPage.getAddressInTable("Address"), AdminData.EditAddress.ADDRESS_IN_TABLE);
	}

	@Test()
	public void Admin_15_Delete_Address(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_15 - Step " + step() + ": Refresh Customer child page");
		adminCustomerChildPage.refreshCurrentPage(driver);

		logExtentV5("Admin_15 - Step " + step() + ": Click to button delete address");
		adminCustomerChildPage.clickToButtonDeleteInTableAddress();

		logExtentV5("Admin_15 - Step " + step() + ": Access Alert delete");
		adminCustomerChildPage.accessAlertDelete();

		logExtentV5("Admin_14 - Step " + step() + ": Verify Address info in table");
		assertEquals(adminCustomerChildPage.getMessageDeleteSuccess(), AdminData.EditAddress.MESSAGE_DEL_SUCCESS);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private AdminDashboardPageObject adminDashboardPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminCatalogPageObject adminCatalogPage;
	private AdminProductsPageObject adminProductsPage;
	private AdminCustomersChildCreatePageObject adminCustomersChildCreatePage;
	private AdminCustomerChildPageObject adminCustomerChildPage;

}
