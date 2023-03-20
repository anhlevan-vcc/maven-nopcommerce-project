package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToNewsletterCheckbox() {
		waitForElementClickable(driver, CustomerInfoPageUI.NEWSLETTER_LINK);
		checkToDefaultCheckboxRadio(driver, CustomerInfoPageUI.NEWSLETTER_LINK);
	}

	public boolean isCustomerinfoPageDisplayed() {
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMERINFO_LINK);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMERINFO_LINK);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, CustomerInfoPageUI.SAVE_BUTTON);
		clickToElement(driver, CustomerInfoPageUI.SAVE_BUTTON);
	}

	public String getNotificationSuccessAtCustomerInfo() {
		waitForElementVisible(driver, CustomerInfoPageUI.NOTIFICATION_SUCCESS_TEXT_AT_CUSTOMERINFO);
		return getElementText(driver, CustomerInfoPageUI.NOTIFICATION_SUCCESS_TEXT_AT_CUSTOMERINFO);
	}


}
