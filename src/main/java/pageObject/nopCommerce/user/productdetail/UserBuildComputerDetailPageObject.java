package pageObject.nopCommerce.user.productdetail;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserProductReviewDetailPageObject;
import pageUIs.nopCommerce.user.BuildComputerPageUI;

public class UserBuildComputerDetailPageObject extends BasePage {
	private WebDriver driver;

	public UserBuildComputerDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserProductReviewDetailPageObject clickAddYourReviewLink() {
		waitForElementClickable(driver, BuildComputerPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, BuildComputerPageUI.ADD_YOUR_REVIEW_LINK);
		return PageGeneratorManager.getUserProductReviewDetailPage(driver);
	}

	public String getMessageSuccessAtBuildComputerDetail() {
		waitForElementVisible(driver, BuildComputerPageUI.MESSAGE_SUCCESS_TEXT);
		return getElementText(driver, BuildComputerPageUI.MESSAGE_SUCCESS_TEXT);
	}

	public void clickIconClose() {
		waitForElementClickable(driver, BuildComputerPageUI.CLOSE_ICON);
		clickToElement(driver, BuildComputerPageUI.CLOSE_ICON);
	}

	public void hoverToShoppingCart() {
		scrollToElement(driver, BuildComputerPageUI.SHOPPING_CART_ICON);
		waitForElementClickable(driver, BuildComputerPageUI.SHOPPING_CART_ICON);
		hoverMouseToElement(driver, BuildComputerPageUI.SHOPPING_CART_ICON);

	}

	public String getMessageCountDisplayed() {
		waitForElementVisible(driver, BuildComputerPageUI.MESSAGE_COUNT_TEXT);
		return getElementText(driver, BuildComputerPageUI.MESSAGE_COUNT_TEXT);
	}

	public String getProductNameDisplayed() {
		waitForElementVisible(driver, BuildComputerPageUI.PRODUCT_NAME_TEXT);
		return getElementText(driver, BuildComputerPageUI.PRODUCT_NAME_TEXT);
	}

	public String getAttributesDisplayed() {
		waitForElementVisible(driver, BuildComputerPageUI.ATTRIBUTES_TEXT);
		return getElementText(driver, BuildComputerPageUI.ATTRIBUTES_TEXT);
	}

	public String getPraceDisplayed() {
		waitForElementVisible(driver, BuildComputerPageUI.PRATE_TEXT);
		return getElementText(driver, BuildComputerPageUI.PRATE_TEXT);
	}

	public String getSubTotalDisplayed() {
		waitForElementVisible(driver, BuildComputerPageUI.SUB_TOTAL_TEXT);
		return getElementText(driver, BuildComputerPageUI.SUB_TOTAL_TEXT);
	}

}
