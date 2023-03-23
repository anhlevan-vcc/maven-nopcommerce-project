package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.productdetail.UserBuildComputerDetailPageObject;
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

	public UserBuildComputerDetailPageObject clickEditProductLink() {
		waitForElementClickable(driver, ShoppingCartPageUI.EDIT_PRODUCT_LINK);
		clickToElement(driver, ShoppingCartPageUI.EDIT_PRODUCT_LINK);
		return PageGeneratorManager.getUserBuildComputerPage(driver);
	}

	public void clickRemoveIcon() {
		waitForElementClickable(driver, ShoppingCartPageUI.REMOVE_ICON);
		clickToElement(driver, ShoppingCartPageUI.REMOVE_ICON);
	}

	public String getMessageDisplayed() {
		waitForElementVisible(driver, ShoppingCartPageUI.MESSAGE_AT_SHOPPING_TEXT);
		return getElementText(driver, ShoppingCartPageUI.MESSAGE_AT_SHOPPING_TEXT);
	}

	public void inputToQtyTextbox(String qtyValue) {
		waitForElementVisible(driver, ShoppingCartPageUI.QTY_TEXTBOX);
		sendkeyToElement(driver, ShoppingCartPageUI.QTY_TEXTBOX, qtyValue);
	}

	public UserCheckOutPageObject clickToCheckOutButton() {
		waitForElementClickable(driver, ShoppingCartPageUI.CHECKOUT_BUTTON);
		clickToElement(driver, ShoppingCartPageUI.CHECKOUT_BUTTON);
		return PageGeneratorManager.getCheckOutPage(driver);
	}
}
