package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class MouseListener extends AbstractWebDriverEventListener {
  @Override
  public void beforeClickOn(WebElement element, WebDriver driver) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
  }

}
