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
import pageObject.nopCommerce.user.UserCompareProductPageObject;
import pageObject.nopCommerce.user.UserComputersPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserNotebooksPageObject;
import pageObject.nopCommerce.user.UserRecentlyViewedProductsPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserShoppingCartPageObject;
import pageObject.nopCommerce.user.UserWishlistPageObject;
import pageObject.nopCommerce.user.productdetail.UserAppleMacBookProDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserAsusN551JKXO076HLaptopDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserHPEnvySleekbookDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserHPSpectreXTProUltraBookDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserLenovoThinkpadLaptopDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserSamsungUltrabookDetailPageObject;
import reportConfig.ExtentTestManager;

public class Nopcommerce_06_Wishlist_Compare_RecentReview extends BaseTest {
	private WebDriver driver;
	private String browser;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserComputersPageObject computersPage;
	private UserNotebooksPageObject notebooksPage;
	private UserWishlistPageObject wishlistPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCompareProductPageObject compareProductPage;
	private UserAppleMacBookProDetailPageObject appleMacBookProDetailPage;
	private UserAsusN551JKXO076HLaptopDetailPageObject asusN551JKXO076HLaptopDetailPage;
	private UserHPEnvySleekbookDetailPageObject hpEnvySleekbookDetailPage;
	private UserHPSpectreXTProUltraBookDetailPageObject hpSpectreXTProUltraBookDetailPage;
	private UserLenovoThinkpadLaptopDetailPageObject lenovoThinkpadLaptopDetailPage;
	private UserSamsungUltrabookDetailPageObject samsungUltrabookDetailPage;
	private UserRecentlyViewedProductsPageObject recentlyViewedProductsPage;

	private String validEmail, firstName, lastName, password;
	private String day, month, year;
	private String sku, productName0, productName1, productName2, productName3, productName4, productName5, price;

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

		sku = UserData.TableAtWishlistPage.SKU_TEXT;
		productName0 = UserData.TableAtWishlistPage.PRODUCT_NAME_0;
		productName1 = UserData.TableAtWishlistPage.PRODUCT_NAME_1;
		productName2 = UserData.TableAtWishlistPage.PRODUCT_NAME_2;
		productName3 = UserData.TableAtWishlistPage.PRODUCT_NAME_3;
		productName4 = UserData.TableAtWishlistPage.PRODUCT_NAME_4;
		productName5 = UserData.TableAtWishlistPage.PRODUCT_NAME_5;
		price = UserData.TableAtWishlistPage.PRICE_TEXT;

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
		loginPage = registerPage.clickToLoginLink();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 13 : Login with Email and password with value is '" + validEmail + "'+'" + password + "'");
		homePage = loginPage.loginAsUser(validEmail, password);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 14 : Verify My account link displayed");
		assertTrue(homePage.isMyAccountLinkDisPlayed());

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 15: Navigate to 'Computers' page");
		computersPage = homePage.openComputersPage();

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 16: Navigate to 'Notebooks' page");
		notebooksPage = (UserNotebooksPageObject) computersPage.openPageCategoriesComputersByName(driver, "Notebooks ");

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 17: Open product detail page '" + productName0 + "'");
		appleMacBookProDetailPage = (UserAppleMacBookProDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName0);
	}

	@Test
	public void Wishlist_01_Add_To_Wishlist(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 01: Click to 'Add to wishlist' button");
		appleMacBookProDetailPage.clickToButtonByText(driver, "Add to wishlist");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 02: Verify notifition success displayed");
		assertEquals(appleMacBookProDetailPage.getNotifitionSuccess(), "The product has been added to your wishlist");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 03: Close bar notification");
		appleMacBookProDetailPage.clickToCloseIcon();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 04: Click to 'Wishlist' link");
		wishlistPage = appleMacBookProDetailPage.clickToWishlistLink();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 05: Verify table contains '" + productName0 + "'");
		assertTrue(wishlistPage.isProductNameAtWishlistDisplayed("product", productName0));

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 06: Verify table contains '" + sku + "'");
		assertTrue(wishlistPage.isProductAtWishlistDisplayed("sku", sku));

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 07: Verify table contains '" + price + "'");
		assertTrue(wishlistPage.isProductAtWishlistDisplayed("unit-price", price));

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 08: Click share link");
		wishlistPage.clickToShareLink();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 01 - Step 09: Verify page title displayed");
		assertEquals(wishlistPage.getPageTitle(), "Wishlist of " + firstName + " " + lastName);
	}

	@Test
	public void Wishlist_02_Add_Product_To_Cart_From_Wishlist_Page(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 01: Click Back page");
		wishlistPage.clickToBack();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 02: Click checkbox 'Add to cart' in table");
		wishlistPage.clickToCheckboxByLabel(driver, "Add to cart:");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 03: Click to button 'Add to cart'");
		shoppingCartPage = wishlistPage.clickToButtonAddToCartByText(driver, "Add to cart");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 04: Verify wishlist qty displayed with value is '0'");
		assertEquals(shoppingCartPage.getWishlistQtyText(), "(0)");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 05: Verify wishlist qty displayed with value is '2'");
		assertEquals(shoppingCartPage.getCartQtyText(), "(2)");
	}

	@Test
	public void Wishlist_03_Remove_Product_In_Wishlist_Page(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 01: Navigate to 'Computers' page");
		computersPage = shoppingCartPage.openComputerPage();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 02: Navigate to 'Notebooks' page");
		notebooksPage = (UserNotebooksPageObject) computersPage.openPageCategoriesComputersByName(driver, "Notebooks ");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 03: Open product detail page '" + productName0 + "'");
		appleMacBookProDetailPage = (UserAppleMacBookProDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName0);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 04: Click to 'Add to wishlist' button");
		appleMacBookProDetailPage.clickToButtonByText(driver, "Add to wishlist");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 05: Close bar notification");
		appleMacBookProDetailPage.clickToCloseIcon();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 06: Click to 'Wishlist' link");
		wishlistPage = appleMacBookProDetailPage.clickToWishlistLink();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 07: Click to Remove icon in table");
		wishlistPage.clickRemoveIconInTable();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 08: Verify Message displayed");
		assertEquals(wishlistPage.getMessageText(), "The wishlist is empty!");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 03 - Step 09: Verify table UnDisplayed '" + productName0 + "'");
		assertTrue(wishlistPage.isProductNameAtWishlistUnDisplayed(productName0));
	}

	@Test
	public void Wishlist_04_Add_Product_To_Compare(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 01: Navigate to 'Computers' page");
		computersPage = (UserComputersPageObject) wishlistPage.openPageAtHomeByName(driver, "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 02: Navigate to 'Notebooks' page");
		notebooksPage = (UserNotebooksPageObject) computersPage.openPageCategoriesComputersByName(driver, "Notebooks ");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 03: Click Add to compare list at product '" + productName1 + "'");
		notebooksPage.clickAddIconByProductName(productName1, "Add to compare list");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 04: Verify Notification Sucsess displayed");
		assertEquals(notebooksPage.getNotificationSucsessAtAddToCompace(), "The product has been added to your product comparison");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 05: Click Add to compare list at product '" + productName0 + "'");
		notebooksPage.clickAddIconByProductName(productName0, "Add to compare list");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 06: Verify Notification Sucsess displayed");
		assertEquals(notebooksPage.getNotificationSucsessAtAddToCompace(), "The product has been added to your product comparison");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 07: Navigate to 'Compare products list' page");
		compareProductPage = (UserCompareProductPageObject) notebooksPage.openPageAtHomeFooterByName(driver, "Compare products list");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 08: Verify info product '" + productName0 + "' displayed");
		assertTrue(compareProductPage.isProductNameAtComparePageDisplayed("Name", productName0));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Price", "$1,800.00"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Screensize", "13.0"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("CPU Type", "Intel Core i5"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Memory", "4 GB"));

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 09: Verify info product '" + productName1 + "' displayed");
		assertTrue(compareProductPage.isProductNameAtComparePageDisplayed("Name", productName1));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Price", "$1,500.00"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Screensize", "15.6"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("CPU Type", "Intel Core i7"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Memory", "16 GB"));
		assertTrue(compareProductPage.isProductAtComparePageDisplayed("Hard drive", "1 TB"));

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 10: Verify 'Clear List' button displayed");
		assertTrue(compareProductPage.isClearListButtonDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 11: Click 'Clear List' button");
		compareProductPage.clickToClearListButton();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 12: Verify Message No Item To Compare displayed");
		assertEquals(compareProductPage.getMessageNoItemToCompare(), "You have no items to compare.");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 04 - Step 13: Verify info product '" + productName1 + " + " + productName0 + "' Undisplayed");
		assertTrue(compareProductPage.isProductNameAtComparePageUnDisplayed(productName1));
		assertTrue(compareProductPage.isProductNameAtComparePageUnDisplayed(productName0));
	}

	@Test
	public void Wishlist_05_Recently_Viewed_Products(Method method) {
		ExtentTestManager.startTest(method.getName() + " - " + this.browser, method.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 01: Navigate to 'Computers' page");
		computersPage = (UserComputersPageObject) compareProductPage.openPageAtHomeByName(driver, "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 02: Navigate to 'Notebooks' page");
		notebooksPage = (UserNotebooksPageObject) computersPage.openPageCategoriesComputersByName(driver, "Notebooks ");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 03: Open product detail page '" + productName0 + "'");
		appleMacBookProDetailPage = (UserAppleMacBookProDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName0);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 04: Click Back to page notebooks");
		appleMacBookProDetailPage.backToPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 05: Open product detail page '" + productName1 + "'");
		asusN551JKXO076HLaptopDetailPage = (UserAsusN551JKXO076HLaptopDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName1);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 06: Click Back to page notebooks");
		asusN551JKXO076HLaptopDetailPage.backToPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 07: Open product detail page '" + productName2 + "'");
		hpEnvySleekbookDetailPage = (UserHPEnvySleekbookDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName2);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 08: Click Back to page notebooks");
		hpEnvySleekbookDetailPage.backToPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 09: Open product detail page '" + productName3 + "'");
		hpSpectreXTProUltraBookDetailPage = (UserHPSpectreXTProUltraBookDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName3);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 10: Click Back to page notebooks");
		hpSpectreXTProUltraBookDetailPage.backToPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 11: Open product detail page '" + productName4 + "'");
		lenovoThinkpadLaptopDetailPage = (UserLenovoThinkpadLaptopDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName4);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 02 - Step 12: Click Back to page notebooks");
		lenovoThinkpadLaptopDetailPage.backToPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 13: Open product detail page '" + productName5 + "'");
		samsungUltrabookDetailPage = (UserSamsungUltrabookDetailPageObject) notebooksPage.openPageAtNotebooksByName(driver, productName5);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist 05 - Step 14: Navigate to 'Recently Viewed Products' page");
		recentlyViewedProductsPage = (UserRecentlyViewedProductsPageObject) samsungUltrabookDetailPage.openPageAtHomeFooterByName(driver, "Recently viewed products");

		ExtentTestManager.getTest().log(Status.INFO,
				"Wishlist 05 - Step 15: Verify info product '" + productName5 + " + " + productName4 + " + " + productName3 + "' order displayed");
		assertEquals(recentlyViewedProductsPage.getTitleByIndex("1"), productName5);
		assertEquals(recentlyViewedProductsPage.getTitleByIndex("2"), productName4);
		assertEquals(recentlyViewedProductsPage.getTitleByIndex("3"), productName3);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
