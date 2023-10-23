package exceptions;

public class BrowserSupportException extends RuntimeException {
  public BrowserSupportException(String browserName) {
    super(String.format("Browser %s is not supported", browserName));
  }
}
