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
		waitForElementVisible(driver, CheckOutPageUI.CONTINUE_BUTTON, textValue);
		clickToElement(driver, CheckOutPageUI.CONTINUE_BUTTON, textValue);
	}

	public String getOrderSuccessMessage() {
		waitForElementVisible(driver, CheckOutPageUI.MESSAGE_ORDER_SUCCESS);
		return getElementText(driver, CheckOutPageUI.MESSAGE_ORDER_SUCCESS);
	}

}
