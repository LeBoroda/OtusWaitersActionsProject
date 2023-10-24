package pages.implementation;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import waiters.IWaiter;
import waiters.StandardWaiter;

public class AbsWebPageObject {

  protected EventFiringWebDriver driver;
  protected Actions actions;
  protected StandardWaiter waiter;

  public AbsWebPageObject(EventFiringWebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    this.waiter = new StandardWaiter(driver);
  }

}
