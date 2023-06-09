package commons;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserBackInStockSubscriptionsPageObject;
import pageObject.nopCommerce.user.UserChangePasswordPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserDownloadableProductsPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserMyProductReviewPageObject;
import pageObject.nopCommerce.user.UserOrdersPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(5);
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendKeyAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected String pasteUserAndPassToUrlOfAlert(WebDriver driver, String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
	}

	protected void switchToWindowByID(WebDriver driver, String WindowID) {
		Set<String> AllWindowID = driver.getWindowHandles();
		for (String id : AllWindowID) {
			if (!id.equals(WindowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> AllWindowIDs = driver.getWindowHandles();
		for (String id : AllWindowIDs) {
			driver.switchTo().window(id);
			String pageTitle = driver.getTitle();
			if (pageTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	protected void closeAllTabWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> AllWindowIDs = driver.getWindowHandles();
		for (String id : AllWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	// private By getByXpath(String xpathLocator) {
	// return By.xpath(xpathLocator);
	// }

	// locatorType : id= ; css= ; xpath= ; name= ; class =
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("")) {
			by = By.xpath(locatorType.substring(0));
		} else {
			throw new RuntimeException("Locator type is not Supported");
		}
		// System.out.println("locatorType: " + by);
		return by;
	}

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	private WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	/**/
	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = waitForElementVisible1(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	/**/
	protected WebElement waitForElementVisible1(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	/* Xóa dữ liệu trong textbox khi không dùng đc hàm clean ta dùng Ctrl+a=>Del */
	protected void cleanValueInElementByDeleteKey(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.DELETE);

	}

	/* Xóa dữ liệu trong textbox khi không dùng đc hàm clean ta dùng Ctrl+a=>Del */
	protected void cleanValueInElementByDeleteKey(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.DELETE);
	}

	protected void selectItemInDefaulfDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	protected void selectItemInDefaulfDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	protected String getSelectItemDefaulfDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectItemDefaulfDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String perenLocator, String childLocator, String expectedTextItem) {
		getWebElement(driver, perenLocator).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	protected void selectItemInCustomDropdownDisplayed(WebDriver driver, String perenLocator, String childLocator, String expectedTextItem) {
		getWebElement(driver, perenLocator).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement itemSample : allItems) {
			String AcctualItem = itemSample.getText();
			if (AcctualItem.equals(expectedTextItem)) {
				itemSample.click();
				break;
			}
		}
	}

	protected void editItemInCustomDropdown(WebDriver driver, String perenLocator, String childLocator, String expectedEditItem) {
		getWebElement(driver, perenLocator).sendKeys(expectedEditItem);
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement itemSample : allItems) {
			String AcctualItem = itemSample.getText();
			if (AcctualItem.equals(expectedEditItem)) {
				itemSample.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void unCheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void unCheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();

		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideGlobalTimeout(driver, longTimeout);

		if (elements.size() == 0) {
			System.out.println("Case 3: Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Case 2: Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Case 1: Element in DOM and visible");
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideGlobalTimeout(driver, longTimeout);

		if (elements.size() == 0) {
			System.out.println("Case 3: Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Case 2: Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Case 1: Element in DOM and visible");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	protected boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementEnable(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	protected void openLinkNewWindow(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		WebElement link = getWebElement(driver, locatorType);
		action.moveToElement(link).contextClick(link).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

	}

	protected void openLinkNewWindow(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		WebElement link = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		action.moveToElement(link).contextClick(link).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;

	}

	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/*
	 * Sử dụng cho wait element in DOM/not in DOM and override implicit timeout
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTimeout(driver, longTimeout);
	}

	/*
	 * Sử dụng cho wait element in DOM/not in DOM and override implicit timeout
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideGlobalTimeout(driver, longTimeout);
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/*
	 * Sử dụng cho wait cho attribute có chứa 1 đoạn text
	 */
	protected void waitForAttributeContainValue(WebDriver driver, String locatorType, String attribute, String value) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.attributeContains(getByLocator(locatorType), attribute, value));
	}

	protected void waitForAttributeContainValue(WebDriver driver, String locatorType, String attribute, String value, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.attributeContains(getByLocator(getDynamicXpath(locatorType, dynamicValues)), attribute, value));
	}

	public WebElement findElementFluentwait(WebDriver driver, String locatorType) {
		FluentWait<WebDriver> fluentDriver;
		fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				return driver.findElement(By.xpath(locatorType));
			}
		});
	}

	public WebElement findElementFluentwait(WebDriver driver, String locatorType, String... dynamicValues) {
		FluentWait<WebDriver> fluentDriver;
		fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
			}
		});
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!= null) && (jQuery.active === 0);");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		// Đường dẫn thư mục uploadFiles
		String filePath = GlobalConstants.getGlobalConstants().getUploadFile();

		// Đường dẫn của tất cả các file
		// 1 file :hoadao.jpg
		// Nhiều file thì phai báo thành mảng rồi dùng vòng lặp qua từ giá trị của mảng
		// String[] fileNames = {"hoadao.jpg","hoahong.jpg","hoamai.jpg","hoasen.jpg",};
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim(); // hàm trim() : dùng cắt bỏ all khoảng trắng ở đầu và cuối của chuỗi
		// getWebElement(driver, BasePageJQueryUI.BUTTON_UPLOAD_FILE).sendKeys(fullFileName);
	}

	private long allTime = GlobalConstants.getGlobalConstants().getAllTime();
	private long pollingTime = GlobalConstants.getGlobalConstants().getPollingTime();
	private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
	private long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();

	// Tối ưu ở bài LV7
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CUSTOMERINFO_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CUSTOMERINFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserOrdersPageObject openOrdersPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ORDERS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ORDERS_LINK);
		return PageGeneratorManager.getUserOrdersPage(driver);
	}

	public UserDownloadableProductsPageObject openDownloadableProductsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DOWNLOADABLE_PRODUCT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.DOWNLOADABLE_PRODUCT_LINK);
		return PageGeneratorManager.getUserDownloadableProductsPage(driver);
	}

	public UserBackInStockSubscriptionsPageObject openBackInStockSubscriptionsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.BACK_IN_STOCK_SUB_LINK);
		clickToElement(driver, BasePageNopCommerceUI.BACK_IN_STOCK_SUB_LINK);
		return PageGeneratorManager.getUserBackInStockSubscriptionsPage(driver);
	}

	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARDPOINT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARDPOINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public UserChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getUserChangePasswordPage(driver);
	}

	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.MYPRODUCTREVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MYPRODUCTREVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}

	// tối ưu ở bài LV9
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_MY_ACCOUNT_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_MY_ACCOUNT_PAGE, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Orders":
			return PageGeneratorManager.getUserOrdersPage(driver);
		case "Downloadable products":
			return PageGeneratorManager.getUserDownloadableProductsPage(driver);
		case "Back in stock subscriptions":
			return PageGeneratorManager.getUserBackInStockSubscriptionsPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public BasePage openPageAtDesktopsByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DESKTOPS_PRODUCT_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_DESKTOPS_PRODUCT_PAGE, pageName);
		switch (pageName) {
		case "Build your own computer":
			return PageGeneratorManager.getUserBuildComputerPage(driver);
		case "Digital Storm VANQUISH 3 Custom Performance PC":
			return PageGeneratorManager.getUserCustomPCPage(driver);
		case "Lenovo IdeaCentre 600 All-in-One PC":
			return PageGeneratorManager.getUserAllInOnePage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public BasePage openPageCategoriesComputersByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CATEGORIES_COMPUTER_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_CATEGORIES_COMPUTER_PAGE, pageName);
		switch (pageName) {
		case "Desktops ":
			return PageGeneratorManager.getUserDesktopsPage(driver);
		case "Notebooks ":
			return PageGeneratorManager.getUserNotebooksPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public BasePage openPageAtHomeByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_HOME_MENU_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_HOME_MENU_PAGE, pageName);
		switch (pageName) {
		case "Computers":
			return PageGeneratorManager.getUserComputersPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public BasePage openPageAtHomeFooterByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_HOME_FOOTER_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_HOME_FOOTER_PAGE, pageName);
		switch (pageName) {
		case "Compare products list":
			return PageGeneratorManager.getCompareProductPage(driver);
		case "Recently viewed products":
			return PageGeneratorManager.getRecentlyViewedProductsPage(driver);
		case "Shopping cart":
			return PageGeneratorManager.getUserShoppingCartPage(driver);
		case "My account":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public BasePage openPageAtNotebooksByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DESKTOPS_PRODUCT_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_DESKTOPS_PRODUCT_PAGE, pageName);
		switch (pageName) {
		case "Apple MacBook Pro 13-inch":
			return PageGeneratorManager.getUserAppleMacBookProPage(driver);
		case "Asus N551JK-XO076H Laptop":
			return PageGeneratorManager.getUserAsusN551JKXO076HLaptopDetailPage(driver);
		case "HP Envy 6-1180ca 15.6-Inch Sleekbook":
			return PageGeneratorManager.getUserHPEnvySleekbookDetailPage(driver);
		case "HP Spectre XT Pro UltraBook":
			return PageGeneratorManager.getUserHPSpectreXTProUltraBookDetailPage(driver);
		case "Lenovo Thinkpad X1 Carbon Laptop":
			return PageGeneratorManager.getUserLenovoThinkpadLaptopDetailPage(driver);
		case "Samsung Series 9 NP900X4C Premium Ultrabook":
			return PageGeneratorManager.getUserSamsungUltrabookDetailPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public BasePage openPageAtDashboardByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_ADMIN_DASHBOARD_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_ADMIN_DASHBOARD_PAGE, pageName);
		switch (pageName) {
		case "Catalog":
			return PageGeneratorManager.getAdminCatalogPage(driver);
		case "Products":
			return PageGeneratorManager.getAdminProductsPage(driver);
		case "Dashboard":
			return PageGeneratorManager.getAdminDashboardPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public void showFolderTreeByTextAdmin(WebDriver driver, String folderText) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_ADMIN_ICON_SHOW_FOLDER, folderText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_ADMIN_ICON_SHOW_FOLDER, folderText);
	}

	public BasePage openPageCustomerChildByName(WebDriver driver, String pageName) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_ADMIN_CUSTOMER_CHILD_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_ADMIN_CUSTOMER_CHILD_PAGE, pageName);
		switch (pageName) {
		case "Customers":
			return PageGeneratorManager.getAdminCustomerChildPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	public void waitIconLoadingInvisible(WebDriver driver) {
		waitForElementInvisible(driver, BasePageNopCommerceUI.LOADING_ICON_AT_ADMIN);
	}

	public void waitIconLoadingVisible(WebDriver driver) {
		waitForElementVisible(driver, BasePageNopCommerceUI.LOADING_ICON_AT_ADMIN);
	}

	// tối ưu ở bài LV8
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementInvisible(driver, BasePageNopCommerceUI.LOADING_ICON_AT_ADMIN);
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

	/**
	 * Enter to dynamic Textbox by ID
	 * 
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	public void inputToTextboxByIDAdmin(WebDriver driver, String textboxID, String value) {
		waitIconLoadingInvisible(driver);
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	public boolean isProductInfoDisplayedInTableAdmin(WebDriver driver, String headerColumnText, String cellValue) {
		int columnIndex = getElementSize(driver, BasePageNopCommerceUI.TABLE_HEADER_INDEX_BY_HEADER_TEXT, headerColumnText) + 1;
		waitForElementVisible(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN, String.valueOf(columnIndex), cellValue);
		return isElementDisplayed(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN, String.valueOf(columnIndex), cellValue);
	}

	public boolean isProductInfoDisplayedInTableAddressAdmin(WebDriver driver, String headerColumnText, String cellValue) {
		overrideGlobalTimeout(driver, shortTimeout);
		int columnIndex = getElementSize(driver, BasePageNopCommerceUI.TABLE_HEADER_INDEX_BY_HEADER_TEXT, headerColumnText) + 1;
		overrideGlobalTimeout(driver, longTimeout);
		waitForElementVisible(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN, String.valueOf(columnIndex), cellValue);
		return isElementDisplayed(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN, String.valueOf(columnIndex), cellValue);
	}

	public boolean isProductInfoUnDisplayedInTableAddressAdmin(WebDriver driver, String headerColumnText, String cellValue) {
		overrideGlobalTimeout(driver, shortTimeout);
		int columnIndex = getElementSize(driver, BasePageNopCommerceUI.TABLE_HEADER_INDEX_BY_HEADER_TEXT, headerColumnText) + 1;
		overrideGlobalTimeout(driver, longTimeout);
		waitForElementVisible(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN, String.valueOf(columnIndex), cellValue);
		return isElementUndisplayed(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN, String.valueOf(columnIndex), cellValue);
	}

	/**
	 * Click to dynamic Button by Text dùng contains(text(),'')
	 * 
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	/**
	 * Click to dynamic Button by Text dùng Normalize-space(),'')
	 * 
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByNormalizeSpace(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_NORMAL, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_NORMAL, buttonText);
	}

	/**
	 * Click to dynamic Button by ID
	 * 
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByIDAdmin(WebDriver driver, String buttonID) {
		waitIconLoadingInvisible(driver);
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_ID, buttonID);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_ID, buttonID);
	}

	public void openCartTitleAtCustomerEditByText(WebDriver driver, String titleText) {
		waitIconLoadingInvisible(driver);
		if (isElementUndisplayed(driver, BasePageNopCommerceUI.ICON_MINUS, titleText)) {
			waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_TITLE_BY_TEXT, titleText);
			clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_TITLE_BY_TEXT, titleText);
		}
	}

	public void closeCartTitleAtCustomerEditByText(WebDriver driver, String titleText) {
		waitIconLoadingInvisible(driver);
		if (getWebElement(driver, BasePageNopCommerceUI.ICON_MINUS, titleText).isDisplayed()) {
			waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_TITLE_BY_TEXT, titleText);
			clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_TITLE_BY_TEXT, titleText);
		}
	}

	public void waiForIconMinusVisible(WebDriver driver, String titleText) {
		waitForElementVisible(driver, BasePageNopCommerceUI.ICON_MINUS, titleText);
	}

	/**
	 * Select item in Dropdown by Name Attribute
	 * 
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void selectDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaulfDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}

	/**
	 * Click to dynamic Radio by Label Name
	 * 
	 * @param driver
	 * @param radioLabelName
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radioLabelName);
	}

	/**
	 * Uncheck to dynamic Radio by Label Name
	 * 
	 * @param driver
	 * @param radioLabelName
	 */
	public void unCheckToRadioButtonByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radioLabelName);
		unCheckToDefaultCheckbox(driver, BasePageNopCommerceUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radioLabelName);
	}

	/**
	 * Verify to dynamic Radio by Label Name selected
	 * 
	 * @param driver
	 * @param radioLabelName
	 */
	public boolean isRadioMaleSelected(WebDriver driver, String radioLabelName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radioLabelName);
		return isElementSelected(driver, BasePageNopCommerceUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radioLabelName);
	}

	/**
	 * Click to dynamic Checkbox by Label Name
	 * 
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
	}

	public void clickToCheckboxByLabelAdmin(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_ADMIN, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_ADMIN, checkboxLabelName);
	}

	/**
	 * Get value in Textbox by textboxID
	 * 
	 * @param driver
	 * @param textboxID
	 * @param value
	 * @return
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxID, String AttributeValue) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, AttributeValue, textboxID);
	}

	public boolean isProductNameDisplayed(WebDriver driver, String headerColumnClass, String cellValue) {
		int columnIndex = getElementSize(driver, BasePageNopCommerceUI.TABLE_HEADER_INDEX_BY_HEADER_CLASS, headerColumnClass) + 1;
		waitForElementVisible(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex), cellValue);
		return isElementDisplayed(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex), cellValue);
	}

	public boolean isProductDisplayed(WebDriver driver, String headerColumnClass, String cellValue) {
		overrideGlobalTimeout(driver, shortTimeout);
		int columnIndex = getElementSize(driver, BasePageNopCommerceUI.TABLE_HEADER_INDEX_BY_HEADER_CLASS, headerColumnClass) + 1;
		overrideGlobalTimeout(driver, longTimeout);
		waitForElementVisible(driver, BasePageNopCommerceUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex), cellValue);
		return isElementDisplayed(driver, BasePageNopCommerceUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex), cellValue);
	}

	public boolean isProductNameUnDisplayed(WebDriver driver, String cellValue) {
		return isElementUndisplayed(driver, BasePageNopCommerceUI.TABLE_NAME_VALUE_BY_NAME, cellValue);
	}

	public String getConfirmOrderInfoByTitle(WebDriver driver, String titleText, String infoByClass) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_INFO_BY_TITLE, titleText, infoByClass);
		return getElementText(driver, BasePageNopCommerceUI.DYNAMIC_INFO_BY_TITLE, titleText, infoByClass);
	}
}
