package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminCustomerChildCreatePageUI;
import pageUIs.nopCommerce.admin.AdminCustomerChildPageUI;

public class AdminCustomerChildPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomerChildPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminCustomersChildCreatePageObject clickToButtonAddNew() {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerChildPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminCustomersChildCreatePage(driver);
	}

	public void unSelectDropdownRoles(String valueText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES);
		selectItemInCustomDropdownDisplayed(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES, AdminCustomerChildPageUI.DROPDOWN_ROLES_CHILD, valueText);
	}

	public void selectDropdownRoles(String valueText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES);
		selectItemInCustomDropdownDisplayed(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES, AdminCustomerChildPageUI.DROPDOWN_ROLES_CHILD, valueText);
	}

	public int getProductSize(String nameText) {
		waitIconLoadingInvisible(driver);
		waitForElementVisible(driver, AdminCustomerChildPageUI.NAME_TEXT, nameText);
		return getElementSize(driver, AdminCustomerChildPageUI.NAME_TEXT, nameText);
	}

	public AdminCustomersChildCreatePageObject clickButtonEditAtTable() {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.EDIT_BUTTON);
		clickToElement(driver, AdminCustomerChildPageUI.EDIT_BUTTON);
		return PageGeneratorManager.getAdminCustomersChildCreatePage(driver);
	}

	public String getMessageSuccessEdit() {
		waitForElementVisible(driver, AdminCustomerChildPageUI.MESSAGE_SUCCSEE_TEXT);
		String successMessage = getElementText(driver, AdminCustomerChildPageUI.MESSAGE_SUCCSEE_TEXT);
		int startIndex = successMessage.indexOf("The customer has been updated successfully.");
		if (startIndex >= 0) {
			String customerName = successMessage.substring(startIndex);
			return customerName;
		}
		return successMessage;
	}

	public void clickToIconClose() {
		waitForElementClickable(driver, AdminCustomerChildPageUI.CLOSE_ICON);
		clickToElement(driver, AdminCustomerChildPageUI.CLOSE_ICON);
	}
}
