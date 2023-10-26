package pages;

import annotations.Path;
import components.CourseTileComponent;
import components.popups.CookiesPopUpComponent;
import data.CourseTitleData;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.implementation.AbsBasePage;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  public MainPage(EventFiringWebDriver driver) {
    super(driver);
  }

  public void findCourseByTitle(CourseTitleData courseTitle) {
    CookiesPopUpComponent popUpComponent = new CookiesPopUpComponent(driver);
    popUpComponent.closeCookiesPopup();
    CourseTileComponent courseTile = new CourseTileComponent(driver);
    courseTile.getCourseByTitle(courseTitle.getName());
  }

  public void choseCourseByDate(String condition) {
    CookiesPopUpComponent popUpComponent = new CookiesPopUpComponent(driver);
    popUpComponent.closeCookiesPopup();
    CourseTileComponent courseTile = new CourseTileComponent(driver);
    courseTile.getCourseByDate(condition);
  }

}
