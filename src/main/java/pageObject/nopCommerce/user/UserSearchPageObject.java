package pageObject.nopCommerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.SearchPageUI;

public class UserSearchPageObject extends BasePage {
	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

	public boolean isSearchWarningMessageIsDisplayed() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_WARNING_MESSAGE);
		return isElementDisplayed(driver, SearchPageUI.SEARCH_WARNING_MESSAGE);
	}

	public String getMessageSearchWarning() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_WARNING_MESSAGE);
		return getElementText(driver, SearchPageUI.SEARCH_WARNING_MESSAGE);
	}

	public String getMessageNoProductSearch() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_NO_PRODUCT_MESSAGE);
		return getElementText(driver, SearchPageUI.SEARCH_NO_PRODUCT_MESSAGE);
	}

	public int getProductSize() {
		waitForElementVisible(driver, SearchPageUI.PRODUCT_NAME);
		return getElementSize(driver, SearchPageUI.PRODUCT_NAME);
	}

	public boolean isProductName() {
		String key = "Lenovo";
		boolean product = true;
		List<WebElement> allProductName = getListWebElement(driver, SearchPageUI.PRODUCT_NAME);
		for (WebElement productName : allProductName) {
			System.out.println(productName.getText());
			product = productName.getText().contains(key);
		}
		return product;
	}

	public boolean isProductNameAbsolute() {
		String key = "Thinkpad X1 Carbon";
		boolean product = true;
		List<WebElement> allProductName = getListWebElement(driver, SearchPageUI.PRODUCT_NAME);
		for (WebElement productName : allProductName) {
			System.out.println(productName.getText());
			product = productName.getText().contains(key);
		}
		return product;
	}

	public boolean isProductNameSubCategories() {
		String key = "Apple MacBook Pro";
		boolean product = true;
		List<WebElement> allProductName = getListWebElement(driver, SearchPageUI.PRODUCT_NAME);
		for (WebElement productName : allProductName) {
			System.out.println(productName.getText());
			product = productName.getText().contains(key);
		}
		return product;
	}

}
