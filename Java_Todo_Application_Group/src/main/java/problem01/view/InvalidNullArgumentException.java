package problem01.view;

/**
 * Exception extension, which throws exceptions when a null argument
 * is utilized in unsupported fashion.
 */
public class InvalidNullArgumentException extends Exception {

  /**
   * Thrown when a null argument is utilized in unsupported fashion.
   */
  public InvalidNullArgumentException() {
    super("Null arguments are not supported by this program.");
  }

}
