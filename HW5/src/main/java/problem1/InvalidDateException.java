package problem1;

/**
 * Exception extension, which throws an exception when one attempts to
 * submit a date incompatible with the context in which it is used.
 *
 * @author bello
 */
public class InvalidDateException extends Exception {

  /**
   * Generates an InvalidDateException object, which interrupts a method
   * when one attempts to utilize a date incompatible with the context
   * in which it is used.
   *
   * @param message - message indicating the reasoning of why the
   *                provided date is invalid
   */
  public InvalidDateException(String message) {
    super(message);
  }

}
