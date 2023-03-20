package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.ShoppingCartPageUI;

public class UserShoppingCartPageObject extends BasePage {
	private WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getWishlistQtyText() {
		waitForElementVisible(driver, ShoppingCartPageUI.WISHLIST_QTY_TEXT);
		return getElementText(driver, ShoppingCartPageUI.WISHLIST_QTY_TEXT);
	}

	public String getCartQtyText() {
		waitForElementVisible(driver, ShoppingCartPageUI.CART_QTY_TEXT);
		return getElementText(driver, ShoppingCartPageUI.CART_QTY_TEXT);
	}

	public UserComputersPageObject openComputerPage() {
		waitForElementClickable(driver, ShoppingCartPageUI.COMPUTER_AT_HEADER_LINK);
		clickToElement(driver, ShoppingCartPageUI.COMPUTER_AT_HEADER_LINK);
		return PageGeneratorManager.getUserComputersPage(driver);

	}
}
