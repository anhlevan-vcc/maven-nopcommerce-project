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

}
