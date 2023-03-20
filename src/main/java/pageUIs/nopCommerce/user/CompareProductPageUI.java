package pageUIs.nopCommerce.user;

public class CompareProductPageUI {

	public static final String TABLE_ROW_INDEX_BY_HEADER_TEXT = "//label[text()='%s']//ancestor::tr//preceding-sibling::tr";
	public static final String TABLE_CELL_VALUE_BY_ROW_INDEX = "//table[@class='compare-products-table']//tr[%s]//td[contains(text(),'%s')]";
	public static final String TABLE_NAME_VALUE_BY_ROW_INDEX = "//table[@class='compare-products-table']//tr[%s]//td//a[contains(text(),'%s')]";
	public static final String CLEAR_LIST_BUTTON = "//a[@class='clear-list']";
	public static final String NO_ITEM_TO_COMPARE_MESSAGE = "//div[@class='no-data']";
	public static final String TABLE_NAME_VALUE_BY_NAME = "//table[@class='compare-products-table']//tr[3]//td//a[contains(text(),'%s')]";

}
