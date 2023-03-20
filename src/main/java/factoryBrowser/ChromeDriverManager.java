package factoryBrowser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		// Ẩn log trên Console
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		// Sét ngôn ngữ cho trình duyệt
		// options.addArguments("--lang=vi");

		// Ẩn vị trí
		options.addArguments("--disable-geolocation");

		// chrome 111
		options.addArguments("--remote-allow-origins=*");

		// Ẩn thông báo
		options.addArguments("--disable-notifications");

		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");

		// Ẩn thông báo đang chạy auto trên trình duyệt
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		// Ẩn lưu pass

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		// Sét auto lưu file download vào thư mục
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", GlobalConstants.getGlobalConstants().getDownloadFile());
		options.setExperimentalOption("prefs", chromePrefs);

		// Chạy ở trình duyệt ẩn danh
		// options.addArguments("--incognito");
		return new ChromeDriver(options);
	}

}
