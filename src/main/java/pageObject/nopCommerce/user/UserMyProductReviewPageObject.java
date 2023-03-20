package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.MyProductReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	private WebDriver driver;

	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getReviewTitleText() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEW_TITLE_TEXT);
		return getElementText(driver, MyProductReviewPageUI.REVIEW_TITLE_TEXT);
	}

	public String getReviewText() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEW_TEXT);
		return getElementText(driver, MyProductReviewPageUI.REVIEW_TEXT);
	}

	public String getProductReviewText() {
		waitForElementVisible(driver, MyProductReviewPageUI.PRODUCT_REVIEW_TEXT);
		return getElementText(driver, MyProductReviewPageUI.PRODUCT_REVIEW_TEXT);
	}
}
