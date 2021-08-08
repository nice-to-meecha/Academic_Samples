package problem01.model;

import java.util.Arrays;
import problem01.controller.ArgConstants;
import problem01.view.InvalidArgumentException;

/**
 * Class utilized to validate CLI arguments, ensuring their correctness.
 */
public class ValidateInput implements IValidateInput {

  private static final Integer NEXT_PREV_ARG = 1;

  private String[] cliArgs;

  /**
   * Generates a ValidateInput object, utilizing a String Array of arguments
   * that will be further processed.
   *
   * @param cliArgs - the String Array, whose contents will be processed for viability
   */
  public ValidateInput(String[] cliArgs) {
    this.cliArgs = cliArgs;
  }

  /**
   * Checks that the command line arguments contain a --csv-file flag and a path/to/file.
   * @return - returns the csv path/to/file provided
   * @throws InvalidArgumentException - thrown if missing one or both of the --csv-file flag and path/to/file.
   */
  @Override
  public String checkCSV() throws InvalidArgumentException {
    for (int i = 0; i < this.cliArgs.length; i++) {
      if (this.cliArgs[i].equals(ArgConstants.CSV_FILE)) {
        this.checkFollowingArg(i, ArgConstants.CSV_FILE, "<path/to/file>");
        return this.cliArgs[i + NEXT_PREV_ARG];
      }
    }
    throw new InvalidArgumentException("Arguments must contain a CSV file");
  }

  /**
   * Method used to ensure that at least one of the tree tasks is performed. If no task args were
   * provided, throws an exception
   * @param add - whether add task args are present
   * @param complete - whether complete task args are present
   * @param display - whether display task args are present
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  @Override
  public void checkNoTask(Boolean add, Boolean complete, Boolean display)
      throws InvalidArgumentException {
    if (!add & !display &!complete) {
      throw new InvalidArgumentException("One of the three tasks arguments must be provided.");
    }
  }


  /**
   * Checks that where a filename or folder name is required after an argument, that path is
   * provided
   *
   * @param i - position in array of args to check
   * @param currentArg - current argument being parsed
   * @param missingArg - the following argument that may be missing
   *
   * @throws InvalidArgumentException - thrown if the path following a template flag
   * is missing
   */
  @Override
  public void checkFollowingArg(Integer i, String currentArg, String missingArg) throws
      InvalidArgumentException {
    try {
      if (this.cliArgs[i + NEXT_PREV_ARG].startsWith("--")) {
        throw new InvalidArgumentException(currentArg + " must be followed by " + missingArg);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new InvalidArgumentException(currentArg + "must be followed by " + missingArg);
    }
  }

  /**
   * Returns the string array of command line arguments
   *
   * @return -cliArgs field
   */
  public String[] getCliArgs() {
    return this.cliArgs;
  }

  /**
   * Overrides the default equals() method to compare param object to this object
   *
   * @param o- object for comparison
   * @return - whether the objects are equal or not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    ValidateInput that = (ValidateInput) o;
    return Arrays.equals(this.cliArgs, that.cliArgs);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    return Arrays.hashCode(this.cliArgs);
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "ValidateInput{" +
        "cliArgs=" + Arrays.toString(this.cliArgs) +
        '}';
  }
}
