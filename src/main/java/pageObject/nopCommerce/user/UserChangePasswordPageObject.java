package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.ChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage {
	private WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getNotificationSuccessAtChangePass() {
		waitForElementVisible(driver, ChangePasswordPageUI.NOTIFICATION_SUCCESS_TEXT_AT_CHANGE_PASS);
		return getElementText(driver, ChangePasswordPageUI.NOTIFICATION_SUCCESS_TEXT_AT_CHANGE_PASS);
	}

	public void clickToCloseIcon() {
		waitForElementClickable(driver, ChangePasswordPageUI.CLOSE_ICON);
		clickToElement(driver, ChangePasswordPageUI.CLOSE_ICON);
	}

	public UserHomePageObject clickToLogOutLink() {
		waitForElementInvisible(driver, ChangePasswordPageUI.NOTIFICATION_SUCCESS_TEXT_AT_CHANGE_PASS);
		waitForElementClickable(driver, ChangePasswordPageUI.LOGOUT_LINK);
		clickToElement(driver, ChangePasswordPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

}
