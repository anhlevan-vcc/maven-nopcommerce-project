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

}
