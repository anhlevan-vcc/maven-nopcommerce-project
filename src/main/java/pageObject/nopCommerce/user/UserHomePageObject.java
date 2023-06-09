package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject openRegisterPage() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject openLoginPage() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public String getMessageSuccess() {
		waitForElementVisible(driver, HomePageUI.HOME_SUCCESS_MASSAGE);
		return getElementText(driver, HomePageUI.HOME_SUCCESS_MASSAGE);
	}

	public UserCustomerInfoPageObject openMyAccountCustomerInfoPage() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public boolean isMyAccountLinkDisPlayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public UserSearchPageObject clickToSearchLink() {
		waitForElementClickable(driver, HomePageUI.SEARCH_LINK);
		clickToElement(driver, HomePageUI.SEARCH_LINK);
		return PageGeneratorManager.getUserSearchPage(driver);
	}

	public UserComputersPageObject openComputersPage() {
		waitForElementClickable(driver, HomePageUI.COMPUTER_AT_HEADER_LINK);
		clickToElement(driver, HomePageUI.COMPUTER_AT_HEADER_LINK);
		return PageGeneratorManager.getUserComputersPage(driver);
	}
}
