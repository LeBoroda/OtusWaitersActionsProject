package exceptions;

public class PathSupportException extends RuntimeException{
  public PathSupportException(String className) {
    super(String.format("Path to page %s is incorrect or invalid. Please check the path.", className));
  }
}
