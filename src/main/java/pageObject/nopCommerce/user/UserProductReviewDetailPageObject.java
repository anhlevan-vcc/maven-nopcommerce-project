package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.ProductReviewDetailPageUI;

public class UserProductReviewDetailPageObject extends BasePage {
	private WebDriver driver;

	public UserProductReviewDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRatingRadioButton() {
		waitForElementClickable(driver, ProductReviewDetailPageUI.PRODUCT_RATING_RADIO_BUTTON);
		clickToElement(driver, ProductReviewDetailPageUI.PRODUCT_RATING_RADIO_BUTTON);
	}

	public UserCustomerInfoPageObject clickMyAccountLink() {
		waitForElementVisible(driver, ProductReviewDetailPageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, ProductReviewDetailPageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public void inputToReviewTextbox(WebDriver driver, String reviewText) {
		waitForElementVisible(driver, ProductReviewDetailPageUI.REVIEW_TEXTBOX);
		sendkeyToElement(driver, ProductReviewDetailPageUI.REVIEW_TEXTBOX, reviewText);
	}
}
