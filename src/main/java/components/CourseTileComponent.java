package components;

import static java.util.Map.Entry.comparingByValue;

import components.implementation.AbsComponent;
import data.MonthNameData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseTileComponent extends AbsComponent {

  private String courseTileSelector = "a[href*='https://otus.ru/lessons/']";
  private String courseNameSelector = String.format("%s div h5", courseTileSelector);
  private String courseDateLocator = "(//a[contains(@href,'https://otus.ru/lessons/')]//span[contains(text(),'С')])[%d]";

  public CourseTileComponent(EventFiringWebDriver driver) {
    super(driver);
  }

  public List<WebElement> getCourseTiles() {
    return driver.findElements(By.cssSelector(courseTileSelector));
  }

  public String getCourseName(WebElement courseTile) {
    return driver.findElement(By.cssSelector(courseNameSelector)).getText().toString();
  }

  private String getMonthNumber(String monthName) {
    Stream<MonthNameData> calDat = Arrays.stream(MonthNameData.values());
    return calDat.filter(month -> month.getName().equals(monthName)).findFirst().get().getNumber();
  }

  public LocalDate getCourseDate(int index) {
    LocalDate result = null;
    String[] eventStringDate = driver.findElement(By.xpath(String.format(courseDateLocator, index)))
        .getText().split(" ");
    String dayNumber = eventStringDate[1];
    if (Integer.parseInt(dayNumber) < 10)
      dayNumber = String.format("%s%s", "0", dayNumber);
    String yearNumber;
    if (eventStringDate.length > 3 && eventStringDate[3].equals("2024")) {
      yearNumber = "2024";
    } else {
      yearNumber = "2023";
    }
    String stringDate = String.format("%s-%s-%s", yearNumber, getMonthNumber(eventStringDate[2]), dayNumber);
    result = LocalDate.parse(stringDate);
    return result;
  }

  public void getCourseByTitle(String courseTitle) {
    List<WebElement> courses = getCourseTiles();

    WebElement courseTile = courses.stream()
        .filter(course -> getCourseName(course).equals(courseTitle))
        .collect(Collectors.toList()).get(0);

    Assertions.assertEquals(getCourseName(courseTile), courseTitle);

    actions.moveToElement(courseTile).click().build().perform();
  }

  public void getCourseByDate(String choiceCondition) {
    List<WebElement> courses = getCourseTiles();
    Map<WebElement, LocalDate> coursesWithDates = new HashMap<>();
    for (int i = 0; i < courses.size(); i++) {
      coursesWithDates.put(courses.get(i), getCourseDate(i + 1));
    }
    WebElement result = null;
    if (choiceCondition.toUpperCase(Locale.ROOT).equals("EARLIEST")) {
      result = coursesWithDates.entrySet().stream()
          .sorted(comparingByValue())
          .findFirst().get().getKey();
    } else {
      result = coursesWithDates.entrySet().stream()
          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
          .findFirst().get().getKey();
    }
    actions.moveToElement(result).click().build().perform();
  }
}
