package factories;

import exceptions.BrowserSupportException;
import factories.implementation.ChromeSettings;
import factories.implementation.FireFoxSettings;
import factories.implementation.IBrowserSettings;
import factories.implementation.OperaSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory {
  private String browserName = System.getProperty("browser", "chrome");

  public EventFiringWebDriver createDriver() {
    EventFiringWebDriver driver;
    switch (browserName) {
      case "chrome": {
        IBrowserSettings chromeSettings = new ChromeSettings();
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver(chromeSettings.configureDriver()));
        return driver;
      }
      case "firefox": {
        IBrowserSettings fireFoxSettings = new FireFoxSettings();
        WebDriverManager.firefoxdriver().setup();
        driver = new EventFiringWebDriver(new FirefoxDriver(fireFoxSettings.configureDriver()));
        return driver;
      }
      case "opera": {
        IBrowserSettings operaSettings = new OperaSettings();
        WebDriverManager.operadriver().setup();
        driver = new EventFiringWebDriver(new OperaDriver(operaSettings.configureDriver()));
        return driver;
      }
      default: {
        throw new BrowserSupportException(browserName);
      }
    }
  }
}
