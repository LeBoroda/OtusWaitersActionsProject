package pages.implementation;

import annotations.Path;
import exceptions.PathSupportException;
import org.openqa.selenium.WebDriver;

public class AbsBasePage<T> extends AbsWebPageObject {

  private static final String BASE_URL = System.getProperty("base.url", "https://otus.ru");

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  public String adjustUrl() {
    return BASE_URL.endsWith("/") ? BASE_URL : BASE_URL + "/";
  }

  public String getPath() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = (Path) clazz.getAnnotation(Path.class);
      return path.value();
    }
    throw new PathSupportException(clazz.getName());
  }

  public T open() {
    driver.get(adjustUrl() + getPath());
    return (T) this;
  }
}
