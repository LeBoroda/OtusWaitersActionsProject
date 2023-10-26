package components.implementation;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.implementation.AbsWebPageObject;

public abstract class AbsComponent<T> extends AbsWebPageObject {
  public AbsComponent(EventFiringWebDriver driver) {
    super(driver);
  }
}
