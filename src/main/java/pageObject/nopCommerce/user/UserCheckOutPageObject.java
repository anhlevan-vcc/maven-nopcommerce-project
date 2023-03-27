package pageObject.nopCommerce.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CheckOutPageUI;

public class UserCheckOutPageObject extends BasePage {
	private WebDriver driver;

	public UserCheckOutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCheckoutPaymentInfoText() {
		waitForElementVisible(driver, CheckOutPageUI.INFO_TEXT);
		return getElementText(driver, CheckOutPageUI.INFO_TEXT);
	}

	public void clickToButtonContinue(String textValue) {
		waitForElementClickable(driver, CheckOutPageUI.CONTINUE_BUTTON, textValue);
		clickToElement(driver, CheckOutPageUI.CONTINUE_BUTTON, textValue);
	}

	public String getOrderSuccessMessage() {
		waitForElementVisible(driver, CheckOutPageUI.MESSAGE_ORDER_SUCCESS);
		return getElementText(driver, CheckOutPageUI.MESSAGE_ORDER_SUCCESS);
	}

	public String getOrderPageTitle() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_PAGE_TITLE);
		return getElementText(driver, CheckOutPageUI.ORDER_PAGE_TITLE);
	}

	public boolean isOrderNumberDisplayed() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_NUMBER);
		return isElementDisplayed(driver, CheckOutPageUI.ORDER_NUMBER);
	}

	public String getOrderNumberText() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_NUMBER);
		return getElementText(driver, CheckOutPageUI.ORDER_NUMBER);
	}

	public void clickConfirmButton() {
		scrollToElement(driver, CheckOutPageUI.CONFIRM_BUTTON);
		highlightElement(driver, CheckOutPageUI.CONFIRM_BUTTON);
		waitForElementClickable(driver, CheckOutPageUI.CONFIRM_BUTTON);
		clickToElement(driver, CheckOutPageUI.CONFIRM_BUTTON);

	}

	public String getCurentDate() {
		waitForElementVisible(driver, CheckOutPageUI.ORDER_DATE);
		getElementText(driver, CheckOutPageUI.ORDER_DATE);

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = null;
		try {
			currentDate = dateFormat.parse(getElementText(driver, CheckOutPageUI.ORDER_DATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String newDateText = newDateFormat.format(currentDate);
		System.out.println("Ngày tháng hiện tại là: " + newDateText);
		return newDateText;
	}

}
