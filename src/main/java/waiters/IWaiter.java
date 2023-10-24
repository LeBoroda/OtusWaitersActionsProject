package waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface IWaiter {
  boolean waitForCondition(ExpectedCondition condition);

  long IMPLICITLY_WAIT_SECOND = Long.parseLong(System.getProperty("webdriver.timeouts.implicitwait", "5000"));
}
