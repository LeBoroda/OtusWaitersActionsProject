package mainpage;

import annotations.Driver;
import annotations.Page;
import data.CourseTitleData;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class MainPage_Test {

  @Driver
  private WebDriver driver;
  @Page
  private MainPage mainPage;


  @Test
  public void mainPageIsOpenedTest() {
    mainPage
      .open()
        .findCourseByTitle(CourseTitleData.APACHEKAFKA);
  }
}
