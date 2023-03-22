package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;
import pageUIs.nopCommerce.user.WishlistPageUI;

public class UserWishlistPageObject extends BasePage {
	private WebDriver driver;

	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToShareLink() {
		waitForElementClickable(driver, WishlistPageUI.SHARE_LINK);
		clickToElement(driver, WishlistPageUI.SHARE_LINK);
	}

	public String getPageTitle() {
		waitForElementVisible(driver, WishlistPageUI.PAGE_TITLE_TEXT);
		return getElementText(driver, WishlistPageUI.PAGE_TITLE_TEXT);
	}

	public UserShoppingCartPageObject clickToButtonAddToCartByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		return PageGeneratorManager.getUserShoppingCartPage(driver);
	}

	public void clickToBack() {
		backToPage(driver);
	}

	public void clickRemoveIconInTable() {
		waitForElementClickable(driver, WishlistPageUI.REMOVE_ICON);
		clickToElement(driver, WishlistPageUI.REMOVE_ICON);
	}

	public String getMessageText() {
		waitForElementVisible(driver, WishlistPageUI.MESSAGE_TEXT);
		return getElementText(driver, WishlistPageUI.MESSAGE_TEXT);
	}

}
