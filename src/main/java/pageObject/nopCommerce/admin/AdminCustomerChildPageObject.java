package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminCustomerChildPageUI;

public class AdminCustomerChildPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomerChildPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToButtonAddNew() {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerChildPageUI.ADD_NEW_BUTTON);
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

	public void inputToTextaraComment(String adminComment) {
		waitForElementVisible(driver, AdminCustomerChildPageUI.COMMENT_TEXTARA);
		sendkeyToElement(driver, AdminCustomerChildPageUI.COMMENT_TEXTARA, adminComment);
	}

	public void clickToButtonSaveAndContinue() {
		waitForElementClickable(driver, AdminCustomerChildPageUI.SAVE_AND_CONTINUE_BUTTON);
		clickToElement(driver, AdminCustomerChildPageUI.SAVE_AND_CONTINUE_BUTTON);
	}

	public String getMessageSuccess() {
		waitForElementVisible(driver, AdminCustomerChildPageUI.MESSAGE_SUCCSEE_TEXT);
		System.out.println(getElementText(driver, AdminCustomerChildPageUI.MESSAGE_SUCCSEE_TEXT));
		String successMessage = getElementText(driver, AdminCustomerChildPageUI.MESSAGE_SUCCSEE_TEXT);
		int startIndex = successMessage.indexOf("The new customer has been added successfully.");
		if (startIndex >= 0) {
			String customerName = successMessage.substring(startIndex);
			return customerName;
		}
		System.out.println(getElementText(driver, AdminCustomerChildPageUI.MESSAGE_SUCCSEE_TEXT));
		return successMessage;
	}

	public void clickToLinkBackToCustom() {
		waitForElementClickable(driver, AdminCustomerChildPageUI.BACK_TO_CUSTOMER_LINK);
		clickToElement(driver, AdminCustomerChildPageUI.BACK_TO_CUSTOMER_LINK);

	}

	public void unSelectDropdownRoles1(String valueText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES1);
		selectItemInCustomDropdownDisplayed(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES1, AdminCustomerChildPageUI.DROPDOWN_ROLES_CHILD, valueText);

	}

	public void selectDropdownRoles1(String valueText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES1);
		selectItemInCustomDropdownDisplayed(driver, AdminCustomerChildPageUI.DROPDOWN_ROLES1, AdminCustomerChildPageUI.DROPDOWN_ROLES_CHILD, valueText);
	}
}
