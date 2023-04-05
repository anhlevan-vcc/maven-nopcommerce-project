package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminCustomerChildCreatePageUI;

public class AdminCustomersChildCreatePageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomersChildCreatePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void unSelectDropdownRoles(String valueText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.DROPDOWN_ROLES);
		selectItemInCustomDropdownDisplayed(driver, AdminCustomerChildCreatePageUI.DROPDOWN_ROLES, AdminCustomerChildCreatePageUI.DROPDOWN_ROLES_CHILD, valueText);

	}

	public void selectDropdownRoles(String valueText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.DROPDOWN_ROLES);
		sleepInSecond(3);
		selectItemInCustomDropdownDisplayed(driver, AdminCustomerChildCreatePageUI.DROPDOWN_ROLES, AdminCustomerChildCreatePageUI.DROPDOWN_ROLES_CHILD, valueText);
	}

	public void inputToTextaraComment(String adminComment) {
		waitForElementVisible(driver, AdminCustomerChildCreatePageUI.COMMENT_TEXTARA);
		sendkeyToElement(driver, AdminCustomerChildCreatePageUI.COMMENT_TEXTARA, adminComment);
	}

	public void clickToButtonSaveAndContinue() {
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.SAVE_AND_CONTINUE_BUTTON);
		clickToElement(driver, AdminCustomerChildCreatePageUI.SAVE_AND_CONTINUE_BUTTON);
	}

	public String getMessageSuccess() {
		waitForElementVisible(driver, AdminCustomerChildCreatePageUI.MESSAGE_SUCCSEE_TEXT);
		String successMessage = getElementText(driver, AdminCustomerChildCreatePageUI.MESSAGE_SUCCSEE_TEXT);
		int startIndex = successMessage.indexOf("The new customer has been added successfully.");
		if (startIndex >= 0) {
			String customerName = successMessage.substring(startIndex);
			return customerName;
		}
		return successMessage;
	}

	public AdminCustomerChildPageObject clickToLinkBackToCustom() {
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.BACK_TO_CUSTOMER_LINK);
		clickToElement(driver, AdminCustomerChildCreatePageUI.BACK_TO_CUSTOMER_LINK);
		return PageGeneratorManager.getAdminCustomerChildPage(driver);
	}

	public void clickToIconClose() {
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.CLOSE_ICON);
		clickToElement(driver, AdminCustomerChildCreatePageUI.CLOSE_ICON);
	}

	public AdminCustomerChildPageObject clickToButtonSave() {
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.SAVE_BUTTON);
		clickToElement(driver, AdminCustomerChildCreatePageUI.SAVE_BUTTON);
		return PageGeneratorManager.getAdminCustomerChildPage(driver);
	}

	public void clickToAddNewAddressButton() {
		waitIconLoadingInvisible(driver);
		scrollToElement(driver, AdminCustomerChildCreatePageUI.ADD_NEW_ADDRESS_BUTTON);
		waiForIconMinusVisible(driver, "Addresses");
		waitForElementClickable(driver, AdminCustomerChildCreatePageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerChildCreatePageUI.ADD_NEW_ADDRESS_BUTTON);
	}

}
