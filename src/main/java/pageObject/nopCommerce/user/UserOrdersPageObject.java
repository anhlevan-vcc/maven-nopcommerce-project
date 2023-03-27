package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.OrderPageUI;

public class UserOrdersPageObject extends BasePage {
	private WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderNumberTextAtOrderPage() {
			waitForElementVisible(driver, OrderPageUI.ORDER_NUMBER);
			return getElementText(driver, OrderPageUI.ORDER_NUMBER).toUpperCase();
		}

}
