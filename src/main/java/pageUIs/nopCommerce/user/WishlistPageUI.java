package pageUIs.nopCommerce.user;

public class WishlistPageUI {

	public static final String TABLE_HEADER_INDEX_BY_HEADER_CLASS = "//table[@class='cart']//tr//th[@class='%s']//preceding-sibling::th";
	public static final String TABLE_NAME_VALUE_BY_HEADER_INDEX = "//table[@class='cart']//tbody//tr/*[%s]/a[text()='%s']";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "//table[@class='cart']//tbody//tr/*[%s]//span[text()='%s']";
	public static final String SHARE_LINK = "//div[@class='share-info']//a";
	public static final String PAGE_TITLE_TEXT = "//div[@class='page-title']//h1";
	public static final String WISHLIST_LINK = "//a[@class='ico-wishlist']";
	public static final String REMOVE_ICON = "//button[@class='remove-btn']";
	public static final String MESSAGE_TEXT = "//div[@class='no-data']";

	public static final String TABLE_NAME_VALUE_BY_NAME = "//table[@class='cart']//tbody//tr/*[4]/a[text()='%s']";

}
