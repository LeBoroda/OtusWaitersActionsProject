package components;

import static java.util.Map.Entry.comparingByValue;

import components.implementation.AbsComponent;
import data.MonthNameData;
import exceptions.SortingParameterException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseTileComponent extends AbsComponent<CourseTileComponent> {

  private final String courseTileLocator = "(//a[contains(@href,'https://otus.ru/lessons/')])/ancestor::div[1]";
  private final String courseNameLocator = String.format("%s//div//h5", courseTileLocator);
  private final String courseDateLocator = String.format("(%s//span[contains(text(),'С')])", courseTileLocator)+"[%d]";

  public CourseTileComponent(EventFiringWebDriver driver) {
    super(driver);
  }

  public List<WebElement> getCourseTiles() {
    return driver.findElements(By.xpath(courseTileLocator));
  }

  public String getCourseName() {
    return driver.findElement(By.xpath(courseNameLocator)).getText();
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
        .filter(course -> getCourseName().equals(courseTitle))
        .collect(Collectors.toList()).get(0);

    Assertions.assertEquals(getCourseName(), courseTitle);

    actions.moveToElement(courseTile).build().perform();
    courseTile.click();
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
    } else if (choiceCondition.toUpperCase(Locale.ROOT).equals("LATEST")) {
      result = coursesWithDates.entrySet().stream()
          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
          .findFirst().get().getKey();
    } else {
      throw new SortingParameterException(choiceCondition);
    }
    actions.moveToElement(result).build().perform();
    result.click();
  }
}
