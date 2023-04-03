package pageUIs.nopCommerce.admin;

public class AdminCustomerChildPageUI {
	public static final String ADD_NEW_BUTTON = "//a[normalize-space()='Add new']";
	public static final String DROPDOWN_ROLES = "//div[@role='listbox']";
	public static final String DROPDOWN_ROLES_CHILD = "//div[@id='SelectedCustomerRoleIds-list']//div[@class='k-list-scroller']//li";
	public static final String NAME_TEXT = "//td[normalize-space()='%s']";
	public static final String EDIT_BUTTON = "(//a[contains(text(),'Edit')])[1]";
	public static final String MESSAGE_SUCCSEE_TEXT = "//div[@class='alert alert-success alert-dismissable']";
	public static final String CLOSE_ICON = "//button[contains(text(),'×')]";
}
