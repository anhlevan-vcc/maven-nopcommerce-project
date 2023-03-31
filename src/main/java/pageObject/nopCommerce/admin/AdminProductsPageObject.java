package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminProductsPageUI;

public class AdminProductsPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getProductSize() {
		waitIconLoadingInvisible(driver);
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_NAME);
		return getElementSize(driver, AdminProductsPageUI.PRODUCT_NAME);
	}

	public void unCheckToRadioButton() {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminProductsPageUI.SUBCATEGORY_CHECKBOX);
		unCheckToDefaultCheckbox(driver, AdminProductsPageUI.SUBCATEGORY_CHECKBOX);
	}

	public String getDataTableEmpty() {
		waitForElementVisible(driver, AdminProductsPageUI.MESSAGE_DATA_EMPTY);
		return getElementText(driver, AdminProductsPageUI.MESSAGE_DATA_EMPTY);
	}

}
