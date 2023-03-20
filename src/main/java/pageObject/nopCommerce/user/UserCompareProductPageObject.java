package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CompareProductPageUI;

public class UserCompareProductPageObject extends BasePage {
	private WebDriver driver;

	public UserCompareProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductAtComparePageDisplayed(String labelRowText, String valueText) {
		int columnIndex = getElementSize(driver, CompareProductPageUI.TABLE_ROW_INDEX_BY_HEADER_TEXT, labelRowText) + 1;
		waitForElementVisible(driver, CompareProductPageUI.TABLE_CELL_VALUE_BY_ROW_INDEX, String.valueOf(columnIndex), valueText);
		return isElementDisplayed(driver, CompareProductPageUI.TABLE_CELL_VALUE_BY_ROW_INDEX, String.valueOf(columnIndex), valueText);
	}

	public boolean isProductNameAtComparePageDisplayed(String labelRowText, String valueText) {
		int columnIndex = getElementSize(driver, CompareProductPageUI.TABLE_ROW_INDEX_BY_HEADER_TEXT, labelRowText) + 1;
		waitForElementVisible(driver, CompareProductPageUI.TABLE_NAME_VALUE_BY_ROW_INDEX, String.valueOf(columnIndex), valueText);
		return isElementDisplayed(driver, CompareProductPageUI.TABLE_NAME_VALUE_BY_ROW_INDEX, String.valueOf(columnIndex), valueText);
	}

	public boolean isProductNameAtComparePageUnDisplayed(String valueText) {
		return isElementUndisplayed(driver, CompareProductPageUI.TABLE_NAME_VALUE_BY_NAME, valueText);
	}

	public boolean isClearListButtonDisplayed() {
		waitForElementVisible(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		return isElementDisplayed(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
	}

	public void clickToClearListButton() {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
	}

	public String getMessageNoItemToCompare() {
		waitForElementVisible(driver, CompareProductPageUI.NO_ITEM_TO_COMPARE_MESSAGE);
		return getElementText(driver, CompareProductPageUI.NO_ITEM_TO_COMPARE_MESSAGE);
	}


}
