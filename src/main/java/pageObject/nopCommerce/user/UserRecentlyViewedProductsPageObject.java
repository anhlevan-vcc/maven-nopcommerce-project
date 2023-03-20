package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.RecentlyViewedProductsPageUI;

public class UserRecentlyViewedProductsPageObject extends BasePage {
	private WebDriver driver;

	public UserRecentlyViewedProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitleByIndex(String productName) {
		waitForElementVisible(driver, RecentlyViewedProductsPageUI.DYNAMIC_TITLE_PRODUCT_BY_INDEX, productName);
		return getElementText(driver, RecentlyViewedProductsPageUI.DYNAMIC_TITLE_PRODUCT_BY_INDEX, productName);
	}

}
