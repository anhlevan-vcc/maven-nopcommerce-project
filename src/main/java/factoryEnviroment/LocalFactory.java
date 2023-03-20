package factoryEnviroment;

import org.openqa.selenium.WebDriver;

import factoryBrowser.BraveDriverManager;
import factoryBrowser.BrowserList;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.CocCocDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.OperaDriverManager;
import factoryBrowser.SafariDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case OPERA:
			driver = new OperaDriverManager().getBrowserDriver();
			break;
		case EDGE:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		case IE:
			driver = new IEDriverManager().getBrowserDriver();
			break;
		case SAFARI:
			driver = new SafariDriverManager().getBrowserDriver();
			break;
		case H_CHROME:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
		case H_FIREFOX:
			driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			break;
		case COCCOC:
			driver = new CocCocDriverManager().getBrowserDriver();
			break;
		case BRAVE:
			driver = new BraveDriverManager().getBrowserDriver();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
