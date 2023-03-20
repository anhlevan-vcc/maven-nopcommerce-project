package pageObject.nopCommerce.user.productdetail;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserWishlistPageObject;
import pageUIs.nopCommerce.user.AppleMacBookProPageUI;

public class UserAppleMacBookProDetailPageObject extends BasePage {
	private WebDriver driver;

	public UserAppleMacBookProDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getNotifitionSuccess() {
		// waitForElementInvisible(driver, AppleMacBookProPageUI.LOADING_ICON);
		waitForElementVisible(driver, AppleMacBookProPageUI.NOTI_SUCCESS_TEXT);
		return getElementText(driver, AppleMacBookProPageUI.NOTI_SUCCESS_TEXT);
	}

	public void clickToCloseIcon() {
		waitForElementClickable(driver, AppleMacBookProPageUI.CLOSE_ICON);
		clickToElement(driver, AppleMacBookProPageUI.CLOSE_ICON);
	}

	public UserWishlistPageObject clickToWishlistLink() {
		waitForElementInvisible(driver, AppleMacBookProPageUI.NOTI_SUCCESS_TEXT);
		waitForElementClickable(driver, AppleMacBookProPageUI.WISHLIST_LINK);
		clickToElement(driver, AppleMacBookProPageUI.WISHLIST_LINK);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}

}
