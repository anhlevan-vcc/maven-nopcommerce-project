package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CheckOutPageUI;

public class UserCheckOutPageObject extends BasePage {
	private WebDriver driver;

	public UserCheckOutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCheckoutPaymentInfoText() {
		waitForElementVisible(driver, CheckOutPageUI.INFO_TEXT);
		return getElementText(driver, CheckOutPageUI.INFO_TEXT);
	}

	public void clickToButtonContinue(String textValue) {
		waitForElementClickable(driver, CheckOutPageUI.CONTINUE_BUTTON, textValue);
		clickToElement(driver, CheckOutPageUI.CONTINUE_BUTTON, textValue);
	}

	public String getOrderSuccessMessage() {
		waitForElementVisible(driver, CheckOutPageUI.MESSAGE_ORDER_SUCCESS);
		return getElementText(driver, CheckOutPageUI.MESSAGE_ORDER_SUCCESS);
	}

	public String getOrderPageTitle() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_PAGE_TITLE);
		return getElementText(driver, CheckOutPageUI.ORDER_PAGE_TITLE);
	}

	public boolean isOrderNumberDisplayed() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_NUMBER);
		return isElementDisplayed(driver, CheckOutPageUI.ORDER_NUMBER);
	}

	public String getOrderNumberText() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_NUMBER);
		return getElementText(driver, CheckOutPageUI.ORDER_NUMBER);
	}

	public void clickConfirmButton() {
		scrollToElement(driver, CheckOutPageUI.CONFIRM_BUTTON);
		highlightElement(driver, CheckOutPageUI.CONFIRM_BUTTON);
		waitForElementClickable(driver, CheckOutPageUI.CONFIRM_BUTTON);
		clickToElement(driver, CheckOutPageUI.CONFIRM_BUTTON);

	}

}
