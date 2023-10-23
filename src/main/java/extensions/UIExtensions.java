package extensions;

import annotations.Driver;
import annotations.Page;
import factories.WebDriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {
  private WebDriver driver = null;

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    this.driver = new WebDriverFactory().createDriver();
    Class clazz = extensionContext.getTestInstance().get().getClass();

    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      if (field.isAnnotationPresent(Page.class)) {
        Constructor pageConstructor = field.getType().getConstructor(WebDriver.class);
        AccessController.doPrivileged((PrivilegedAction<Void>)
            () -> {
              try {
                field.setAccessible(true);
                field.set(extensionContext.getTestInstance().get(), pageConstructor.newInstance(driver));
              } catch (Exception exception) {
                exception.printStackTrace();
              }
              return null;
            });
      }

      Class fieldClazz = field.getType();
      if (field.isAnnotationPresent(Driver.class) && fieldClazz.getName().equals(WebDriver.class.getName())) {
        AccessController.doPrivileged((PrivilegedAction<Void>)
            () -> {
              try {
                field.setAccessible(true);
                field.set(extensionContext.getTestInstance().get(), driver);
              } catch (Exception exception) {
                exception.printStackTrace();
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
