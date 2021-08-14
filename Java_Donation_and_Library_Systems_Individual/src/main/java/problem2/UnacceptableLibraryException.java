package problem2;

/**
 * Exception extension, which throws an exception when one attempts
 * to initialize a Catalog object with a null reference in place of
 * a library.
 *
 * @author bello
 */
public class UnacceptableLibraryException extends Exception {

  public UnacceptableLibraryException() {
    super("Null is not a viable library.");
  }

}
