package problem01.model;

import problem01.view.InvalidNullArgumentException;

/**
 * Class which determines whether arguments utilized within function
 * calls can be classified as null.
 */
public class CheckNullUtils {

  /**
   * Determines whether or not the provided argument is null, throwing an exception
   * if the former.
   *
   * @param argument - An argument utilized within the call of another function
   *
   * @throws InvalidNullArgumentException - thrown when a null argument is utilized
   * in unsupported fashion.
   */
  public static void evaluate(Object argument) throws InvalidNullArgumentException {
    if (argument == null || argument.equals("null")) {
      throw new InvalidNullArgumentException();
    }
  }
}
