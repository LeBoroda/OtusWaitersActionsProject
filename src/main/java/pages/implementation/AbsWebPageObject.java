package pages.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import waiters.IWaiter;
import waiters.StandardWaiter;

public class AbsWebPageObject {

  protected WebDriver driver;
  protected Actions actions;
  protected IWaiter waiter;

  public AbsWebPageObject(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    this.waiter = new StandardWaiter(driver);
  }

}
