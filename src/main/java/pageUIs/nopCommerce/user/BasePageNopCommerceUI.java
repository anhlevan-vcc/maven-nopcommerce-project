package pageUIs.nopCommerce.user;

public class BasePageNopCommerceUI {

	public static final String DYNAMIC_MY_ACCOUNT_PAGE = "//div[@class='side-2']//a[text()= '%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(text(),'%s')]";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_RADIOBUTTON_BY_LABEL = "//label[contains(text(),'%s')]/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::input";
	public static final String DYNAMIC_DESKTOPS_PRODUCT_PAGE = "//div[@class='product-grid']//a[text()='%s']";
	public static final String DYNAMIC_CATEGORIES_COMPUTER_PAGE = "//ul[@class='list']//a[text()='%s']";
	public static final String DYNAMIC_HOME_MENU_PAGE = "//ul[@class='top-menu notmobile']//a[contains(string(),'%s')]";
	public static final String DYNAMIC_HOME_FOOTER_PAGE = "//div[@class='footer']//a[contains(text(),'%s')]";

	public static final String CUSTOMERINFO_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Customer info']";
	public static final String ADDRESS_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Addresses']";
	public static final String REWARDPOINT_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Reward points']";
	public static final String MYPRODUCTREVIEW_LINK = "XPATH=//div[@class='side-2']//a[text()= 'My product reviews']";
	public static final String ORDERS_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Orders']";
	public static final String DOWNLOADABLE_PRODUCT_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Downloadable products']";
	public static final String BACK_IN_STOCK_SUB_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Back in stock subscriptions']";
	public static final String CHANGE_PASSWORD_LINK = "XPATH=//div[@class='side-2']//a[text()= 'Change password']";

	public static final String LOGOUT_LINK_AT_USER = "XPATH=//a[@class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "XPATH=//a[text()='Logout']";
	public static final String LOADING_ICON_AT_ADMIN = "XPATH=//div[@id='ajaxBusy']/span";

	public static final String TABLE_HEADER_INDEX_BY_HEADER_CLASS = "//table[@class='cart']//tr//th[@class='%s']//preceding-sibling::th";
	public static final String TABLE_NAME_VALUE_BY_HEADER_INDEX = "//table[@class='cart']//tbody//tr/*[%s]/a[text()='%s']";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "//table[@class='cart']//tbody//tr/*[%s]//span[text()='%s']";
	public static final String TABLE_NAME_VALUE_BY_NAME = "//table[@class='cart']//tbody//tr/*[4]/a[text()='%s']";

	public static final String DYNAMIC_INFO_BY_TITLE = "//strong[contains(text(),'%s')]//parent::div//following-sibling::ul//li[@class='%s']";
	public static final String ICON_LOADING = "//span[@id='states-loading-progress']";

	public static final String DYNAMIC_ADMIN_DASHBOARD_PAGE = "//p[normalize-space()='%s']";
	public static final String DYNAMIC_BUTTON_BY_ID = "//button[@id='%s']";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_TEXT = "//th[contains(text(),'%s')]//preceding-sibling::th";
	public static final String TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN = "//tbody/tr/td[%s][text()='%s']";

}
