package components.popups;

import components.implementation.AbsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CookiesPopUpComponent extends AbsComponent<CookiesPopUpComponent> {
  private final String cookiesPopupLocator = "//*[contains (text(),'Посещая наш сайт, вы принимаете')]";
  private final String cookiesAcceptButtonLocator = "//*[contains (text(),'Посещая наш сайт, вы принимаете')]//following::button";

  public CookiesPopUpComponent(EventFiringWebDriver driver) {
    super(driver);
  }

  public void closeCookiesPopup() {
    waiter.waitForElementVisibility(driver.findElement(By.xpath(cookiesPopupLocator)));
    driver.findElement(By.xpath(cookiesAcceptButtonLocator)).click();
  }
}
