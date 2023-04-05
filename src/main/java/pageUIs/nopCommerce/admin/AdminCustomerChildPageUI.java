package pageUIs.nopCommerce.admin;

public class AdminCustomerChildPageUI {
	public static final String ADD_NEW_BUTTON = "//a[normalize-space()='Add new']";
	public static final String DROPDOWN_ROLES = "//div[@role='listbox']";
	public static final String DROPDOWN_ROLES_CHILD = "//div[@id='SelectedCustomerRoleIds-list']//div[@class='k-list-scroller']//li";
	public static final String NAME_TEXT = "//td[normalize-space()='%s']";
	public static final String EDIT_BUTTON = "(//a[contains(text(),'Edit')])[1]";
	public static final String MESSAGE_SUCCSEE_TEXT = "//div[@class='alert alert-success alert-dismissable']";
	public static final String CLOSE_ICON = "//button[contains(text(),'×')]";
	public static final String BACK_CUSTOMER_DETAIL_LINK = "//a[normalize-space()='back to customer details']";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_TEXT = "//th[contains(text(),'%s')]//preceding-sibling::th";
	public static final String TABLE_NAME_VALUE_BY_HEADER_INDEX_ADMIN = "//tbody/tr/td[%s]";
	public static final String EDIT_BTN_TABLE_ADDRESS = "//a[normalize-space()='Edit']";
	public static final String DEL_BTN_TABLE_ADDRESS = "//a[normalize-space()='Delete']";
	public static final String MESSAGE_DEL_SUCCSEE_TEXT = "//div[text()='Addresses']//ancestor::div[@id='customer-address']//div[@class='card-body']//td";
}
