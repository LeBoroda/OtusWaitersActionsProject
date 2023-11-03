package pages;

import annotations.Path;
import components.CourseTileComponent;
import data.CourseTitleData;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.implementation.AbsBasePage;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  public MainPage(EventFiringWebDriver driver) {
    super(driver);
  }

  public void findCourseByTitle(CourseTitleData courseTitle) {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(driver);
    courseTile.getCourseByTitle(courseTitle.getName());
  }

  public void getLatestCourse() {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(driver);
    courseTile.getCourseByDate("LATEST");
  }

  public void getEarliestCourse() {
    closeCookiesPopUpComponent();
    CourseTileComponent courseTile = new CourseTileComponent(driver);
    courseTile.getCourseByDate("EARLIEST");
  }

}
