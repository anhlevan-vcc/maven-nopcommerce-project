package com.nopcommerce.admin;

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
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.admin.AdminProductsPageObject;

public class Nopcommerce_01_Admin extends BaseTest {
	private WebDriver driver;
	private String browser;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminCatalogPageObject adminCatalogPage;
	private AdminProductsPageObject adminProductsPage;

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
		logExtentV5("Admin_01 - Step 01: ");
		adminCatalogPage = (AdminCatalogPageObject) adminDashboardPage.openPageAtDashboardByName(driver, "Catalog");

		logExtentV5("Admin_01 - Step 01: ");
		adminProductsPage = (AdminProductsPageObject) adminCatalogPage.openPageAtDashboardByName(driver, "Products");

	}

	@Test()
	public void Admin_02_Login(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_01 - Step 01: ");

	}

	@Test()
	public void Admin_03_My_Account(Method method) {
		logStartTest(method.getName() + " - " + this.browser, method.getName());
		logExtentV5("Admin_01 - Step 01: ");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
