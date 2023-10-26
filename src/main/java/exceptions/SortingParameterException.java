package exceptions;

public class SortingParameterException extends RuntimeException{
  public SortingParameterException(String sortingParameter) {
    super(String.format("Sorting parameter %s for this test is not valid. Please check it.", sortingParameter));
  }
}
