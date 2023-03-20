package pageObject.nopCommerce.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.nopCommerce.user.NotebooksPageUI;

public class UserNotebooksPageObject extends BasePage {
	private WebDriver driver;

	public UserNotebooksPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameSortByAscending() {
		// List<String> productUIList = new ArrayList<String>();
		// Khai báo ra 1 ArrayList để chứ các productname trên UI
		waitForElementInvisible(driver, NotebooksPageUI.ICON_LOADING);
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, NotebooksPageUI.PRODUCT_NAME_TEXTBOX);

		// Dùng vòng lặp để add tất cả các text vào ArrayList trên
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}

		// Tạo ta 1 ArrayList mới để Sort dữ liệu trong ArrayList cũ xem đúng ko
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		// So sánh 2 List bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByDescending() {
		// List<String> productUIList = new ArrayList<String>();
		// Khai báo ra 1 ArrayList để chứ các productname trên UI
		waitForElementInvisible(driver, NotebooksPageUI.ICON_LOADING);
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, NotebooksPageUI.PRODUCT_NAME_TEXTBOX);

		// Dùng vòng lặp để add tất cả các text vào ArrayList trên
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
		}

		// Tạo ta 1 ArrayList mới để Sort dữ liệu trong ArrayList cũ xem đúng ko
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		// Reverse lại cái productSortList đc Descending
		Collections.reverse(productSortList);

		// So sánh 2 List bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		// Sort theo giá tiền dùng kiểu dữ liệu Float
		waitForElementInvisible(driver, NotebooksPageUI.ICON_LOADING);
		ArrayList<Float> productUIList = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, NotebooksPageUI.PRODUCT_PRICE_TEXTBOX);
		for (WebElement productPrice : productPriceText) {
			// Get text giá tiền(đc kiểu String)->xóa ký tự $ ->Ép kiểu qua Float->rồi mới add vào list
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
			System.out.println("UI " + productPrice.getText());
		}
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);
		for (Float float1 : productSortList) {
			System.out.println("DB " + float1);

		}
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		waitForElementInvisible(driver, NotebooksPageUI.ICON_LOADING);
		ArrayList<Float> productUIList = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, NotebooksPageUI.PRODUCT_PRICE_TEXTBOX);
		for (WebElement productPrice : productPriceText) {
			// Get text giá tiền(đc kiểu String)->xóa ký tự $ ->Ép kiểu qua Float->rồi mới add vào list
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
			System.out.println("UI " + productPrice.getText());
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);
		// Reverse lại cái productSortList đc Descending
		Collections.reverse(productSortList);
		for (Float float1 : productSortList) {
			System.out.println("DB " + float1);

		}
		return productSortList.equals(productUIList);
	}

	public int getProductNameSize() {
		waitForElementInvisible(driver, NotebooksPageUI.ICON_LOADING);
		return getElementSize(driver, NotebooksPageUI.PRODUCT_NAME);
	}

	public boolean isNextIconDisplay() {
		waitForElementVisible(driver, NotebooksPageUI.NEXT_PAGING_ICON);
		return isElementDisplayed(driver, NotebooksPageUI.NEXT_PAGING_ICON);
	}

	public void clickToPageNumberIcon() {
		waitForElementVisible(driver, NotebooksPageUI.NUMBER_TWO_PAGING_ICON);
		clickToElement(driver, NotebooksPageUI.NUMBER_TWO_PAGING_ICON);
	}

	public boolean isPreviousIconDisplay() {
		waitForElementVisible(driver, NotebooksPageUI.PREVIOUS_PAGING_ICON);
		return isElementDisplayed(driver, NotebooksPageUI.PREVIOUS_PAGING_ICON);
	}

	public boolean isPageNumberUndisplayed() {
		waitForElementUndisplayed(driver, NotebooksPageUI.NUMBER_PAGING_ICON);
		return isElementUndisplayed(driver, NotebooksPageUI.NUMBER_PAGING_ICON);
	}

	public void clickAddIconByProductName(String productName, String textIcon) {
		waitForElementUndisplayed(driver, NotebooksPageUI.NOTIFI_SUCCESS_AT_ADD_COMPACE_TEXT);
		waitForElementClickable(driver, NotebooksPageUI.DYNAMIC_ADD_ICON, productName, textIcon);
		clickToElement(driver, NotebooksPageUI.DYNAMIC_ADD_ICON, productName, textIcon);
	}

	public String getNotificationSucsessAtAddToCompace() {
		waitForElementVisible(driver, NotebooksPageUI.NOTIFI_SUCCESS_AT_ADD_COMPACE_TEXT);
		return getElementText(driver, NotebooksPageUI.NOTIFI_SUCCESS_AT_ADD_COMPACE_TEXT);
	}

	public void clickCloseIcon() {
		waitForElementVisible(driver, NotebooksPageUI.CLOSE_ICON);
		clickToElement(driver, NotebooksPageUI.CLOSE_ICON);
	}

}
