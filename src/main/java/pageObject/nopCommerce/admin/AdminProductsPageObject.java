package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminProductsPageUI;

public class AdminProductsPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getProductSize(String productName) {
		waitIconLoadingInvisible(driver);
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_NAME, productName);
		return getElementSize(driver, AdminProductsPageUI.PRODUCT_NAME, productName);
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

	public void checkToRadioButton() {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminProductsPageUI.SUBCATEGORY_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, AdminProductsPageUI.SUBCATEGORY_CHECKBOX);

	}

	public String getProductNameisDisplayed() {
		waitIconLoadingInvisible(driver);
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_NAME_TITLE_DETAIL);
		return getElementText(driver, AdminProductsPageUI.PRODUCT_NAME_TITLE_DETAIL).substring(23).substring(0,
				getElementText(driver, AdminProductsPageUI.PRODUCT_NAME_TITLE_DETAIL).substring(23).length() - 21);
	}

	public String getAttributeValue(String value) {
		waitIconLoadingInvisible(driver);
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_NAME_ATTRIBUTE);
		return getElementAttribute(driver, AdminProductsPageUI.PRODUCT_NAME_ATTRIBUTE, value);
	}

}
