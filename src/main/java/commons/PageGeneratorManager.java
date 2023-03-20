package commons;

import org.openqa.selenium.WebDriver;

import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.user.UserSearchPageObject;
import pageObject.nopCommerce.user.UserShoppingCartPageObject;
import pageObject.nopCommerce.user.UserWishlistPageObject;
import pageObject.nopCommerce.user.productdetail.UserAllInOneDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserAppleMacBookProDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserAsusN551JKXO076HLaptopDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserBuildComputerDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserCustomPCDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserHPEnvySleekbookDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserHPSpectreXTProUltraBookDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserLenovoThinkpadLaptopDetailPageObject;
import pageObject.nopCommerce.user.productdetail.UserSamsungUltrabookDetailPageObject;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserBackInStockSubscriptionsPageObject;
import pageObject.nopCommerce.user.UserChangePasswordPageObject;
import pageObject.nopCommerce.user.UserCompareProductPageObject;
import pageObject.nopCommerce.user.UserComputersPageObject;
import pageObject.nopCommerce.user.UserCustomerInfoPageObject;
import pageObject.nopCommerce.user.UserDesktopsPageObject;
import pageObject.nopCommerce.user.UserDownloadableProductsPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewPageObject;
import pageObject.nopCommerce.user.UserNotebooksPageObject;
import pageObject.nopCommerce.user.UserOrdersPageObject;
import pageObject.nopCommerce.user.UserProductReviewDetailPageObject;
import pageObject.nopCommerce.user.UserRecentlyViewedProductsPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;

public class PageGeneratorManager {

	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}

	public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}

	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}

	public static UserOrdersPageObject getUserOrdersPage(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}

	public static UserDownloadableProductsPageObject getUserDownloadableProductsPage(WebDriver driver) {
		return new UserDownloadableProductsPageObject(driver);
	}

	public static UserBackInStockSubscriptionsPageObject getUserBackInStockSubscriptionsPage(WebDriver driver) {
		return new UserBackInStockSubscriptionsPageObject(driver);
	}

	public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}

	public static UserComputersPageObject getUserComputersPage(WebDriver driver) {
		return new UserComputersPageObject(driver);
	}

	public static UserDesktopsPageObject getUserDesktopsPage(WebDriver driver) {
		return new UserDesktopsPageObject(driver);
	}

	public static UserBuildComputerDetailPageObject getUserBuildComputerPage(WebDriver driver) {
		return new UserBuildComputerDetailPageObject(driver);
	}

	public static UserCustomPCDetailPageObject getUserCustomPCPage(WebDriver driver) {
		return new UserCustomPCDetailPageObject(driver);
	}

	public static UserAllInOneDetailPageObject getUserAllInOnePage(WebDriver driver) {
		return new UserAllInOneDetailPageObject(driver);
	}

	public static UserProductReviewDetailPageObject getUserProductReviewDetailPage(WebDriver driver) {
		return new UserProductReviewDetailPageObject(driver);
	}

	public static UserNotebooksPageObject getUserNotebooksPage(WebDriver driver) {
		return new UserNotebooksPageObject(driver);
	}

	public static UserAppleMacBookProDetailPageObject getUserAppleMacBookProPage(WebDriver driver) {
		return new UserAppleMacBookProDetailPageObject(driver);
	}

	public static UserHPEnvySleekbookDetailPageObject getUserHPEnvySleekbookDetailPage(WebDriver driver) {
		return new UserHPEnvySleekbookDetailPageObject(driver);
	}

	public static UserHPSpectreXTProUltraBookDetailPageObject getUserHPSpectreXTProUltraBookDetailPage(WebDriver driver) {
		return new UserHPSpectreXTProUltraBookDetailPageObject(driver);
	}

	public static UserLenovoThinkpadLaptopDetailPageObject getUserLenovoThinkpadLaptopDetailPage(WebDriver driver) {
		return new UserLenovoThinkpadLaptopDetailPageObject(driver);
	}

	public static UserAsusN551JKXO076HLaptopDetailPageObject getUserAsusN551JKXO076HLaptopDetailPage(WebDriver driver) {
		return new UserAsusN551JKXO076HLaptopDetailPageObject(driver);
	}

	public static UserSamsungUltrabookDetailPageObject getUserSamsungUltrabookDetailPage(WebDriver driver) {
		return new UserSamsungUltrabookDetailPageObject(driver);
	}

	public static UserWishlistPageObject getUserWishlistPage(WebDriver driver) {
		return new UserWishlistPageObject(driver);
	}

	public static UserShoppingCartPageObject getUserShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}

	public static UserCompareProductPageObject getCompareProductPage(WebDriver driver) {
		return new UserCompareProductPageObject(driver);
	}

	public static UserRecentlyViewedProductsPageObject getRecentlyViewedProductsPage(WebDriver driver) {
		return new UserRecentlyViewedProductsPageObject(driver);
	}
}
