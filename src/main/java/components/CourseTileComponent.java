package components;

import components.implementation.AbsComponent;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.List;
import java.util.stream.Collectors;

public class CourseTileComponent extends AbsComponent {

  private String courseTileSelector = "a[href*='https://otus.ru/lessons/']";
  private String courseNameSelector = String.format("%s div h5", courseTileSelector);

  public CourseTileComponent(EventFiringWebDriver driver) {
    super(driver);
  }

  public List<WebElement> getCourseTiles() {
    return driver.findElements(By.cssSelector(courseTileSelector));
  }

  public String getCourseName(WebElement courseTile) {
    return driver.findElement(By.cssSelector(courseNameSelector)).getText().toString();
  }

  public void getCourseByTitle(String courseTitle) {
    List<WebElement> courses = getCourseTiles();

    WebElement courseTile = courses.stream()
        .filter(course -> getCourseName(course).equals(courseTitle))
        .collect(Collectors.toList()).get(0);

    Assertions.assertEquals(getCourseName(courseTile), courseTitle);

    actions.moveToElement(courseTile).build().perform();
  }
}
