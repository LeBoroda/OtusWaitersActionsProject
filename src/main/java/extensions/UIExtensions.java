package extensions;

import annotations.Driver;
import annotations.Page;
import factories.WebDriverFactory;
import listeners.MouseListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {
  private EventFiringWebDriver driver = null;

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    this.driver = new WebDriverFactory().createDriver();
    driver.register(new MouseListener());
    Class clazz = extensionContext.getTestInstance().get().getClass();

    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      if (field.isAnnotationPresent(Page.class)) {
        Constructor pageConstructor = field.getType().getConstructor(EventFiringWebDriver.class);
        AccessController.doPrivileged((PrivilegedAction<Void>)
            () -> {
              try {
                field.setAccessible(true);
                field.set(extensionContext.getTestInstance().get(), pageConstructor.newInstance(driver));
              } catch (Exception exception) {
                throw new Error(String.format("Could not access or sed WebDriver in field %s.", field), exception);
              }
              return null;
            });
      }

      Class fieldClazz = field.getType();
      if (field.isAnnotationPresent(Driver.class) && fieldClazz.getName().equals(EventFiringWebDriver.class.getName())) {
        AccessController.doPrivileged((PrivilegedAction<Void>)
            () -> {
              try {
                field.setAccessible(true);
                field.set(extensionContext.getTestInstance().get(), driver);
              } catch (IllegalAccessException exception) {
                throw new Error(String.format("Could not access or sed WebDriver in field %s.", field), exception);
              }
              return null;
            });
      }
    }
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) {
    if (this.driver != null) {
      this.driver.close();
      this.driver.quit();
    }
  }

}
