package pageObject.nopCommerce.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.OrderPageUI;

public class UserOrdersPageObject extends BasePage {
	private WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderNumberTextAtOrderPage() {
		waitForElementVisible(driver, OrderPageUI.ORDER_NUMBER);
		return getElementText(driver, OrderPageUI.ORDER_NUMBER).toUpperCase();
	}

	public String getCurentDate() {
		waitForElementVisible(driver, OrderPageUI.ORDER_DATE);
		String orderText = getElementText(driver, OrderPageUI.ORDER_DATE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = null;
		try {
			currentDate = dateFormat.parse(orderText);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat newDateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy", new Locale("en", "EN"));
		String newDateText = newDateFormat.format(currentDate);
		// System.out.println("Ngày tháng hiện tại là: " + newDateText);
		return newDateText;
	}

	public String getOrderDate() {
		waitForElementVisible(driver, OrderPageUI.ORDER_DATE_DETAIL);
		return getElementText(driver, OrderPageUI.ORDER_DATE_DETAIL).substring(12);
	}

}
