package components.popups;

import components.implementation.AbsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookiesPopUpComponent extends AbsComponent<CookiesPopUpComponent> implements IPopUp {
  private final String cookiesPopupLocator = "//*[contains (text(),'Посещая наш сайт, вы принимаете')]";
  private final String cookiesAcceptButtonLocator = "//*[contains (text(),'Посещая наш сайт, вы принимаете')]//following::button";

  public CookiesPopUpComponent(EventFiringWebDriver driver) {
    super(driver);
  }

  public void closeCookiesPopup() {
    waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(By.xpath(cookiesAcceptButtonLocator)));
    waiter.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath(cookiesAcceptButtonLocator)));
    driver.findElement(By.xpath(cookiesAcceptButtonLocator)).click();
  }

  @Override
  public void popUpShouldNotBeVisible() {
    waiter.waitForElementVisibility(driver.findElement(By.xpath(cookiesPopupLocator)));
  }

  @Override
  public void popUpShouldBeVisible() {
    waiter.waitForElementNonVisibility(driver.findElement(By.xpath(cookiesPopupLocator)));
  }
}
