package factories;

import exceptions.BrowserSupportException;
import factories.implementation.ChromeSettings;
import factories.implementation.FireFoxSettings;
import factories.implementation.IBrowserSettings;
import factories.implementation.OperaSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {
  private String browserName = System.getProperty("browser", "chrome");

  public WebDriver createDriver() {
    WebDriver driver;
    switch (browserName) {
      case "chrome": {
        IBrowserSettings chromeSettings = new ChromeSettings();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeSettings.configureDriver());
        return driver;
      }
      case "firefox": {
        IBrowserSettings fireFoxSettings = new FireFoxSettings();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(fireFoxSettings.configureDriver());
        return driver;
      }
      case "opera": {
        IBrowserSettings operaSettings = new OperaSettings();
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver(operaSettings.configureDriver());
        return driver;
      }
      default: {
        throw new BrowserSupportException(browserName);
      }
    }
  }
}
