package otus.exceptions;

public class NoSuchCourseException extends RuntimeException{
  public NoSuchCourseException(String courseTitle) {
    super(String.format("Course %s is not found. Please check data", courseTitle));
  }
}
