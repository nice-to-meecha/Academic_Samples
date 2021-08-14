package problem2;

/**
 * Exception extension, which throws an exception when one attempts to
 * establish an item with an unassociated creator.
 *
 * @author bello
 */
public class ImproperCreatorException extends Exception {

  public ImproperCreatorException() {
    super("That creator is not appropriate for this item.");
  }

}
