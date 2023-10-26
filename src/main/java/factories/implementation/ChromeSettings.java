package factories.implementation;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IBrowserSettings {
  @Override
  public MutableCapabilities configureDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--homepage=about:blank");
    chromeOptions.addArguments("--enable-extensions");

    return chromeOptions;
  }
}
