package problem01.model;

import problem01.view.InvalidArgumentException;

/**
 * Interface describing the public functionality of a ValidateInput object,
 * comprising of the ability to check various CLI arguments for validity.
 */
public interface IValidateInput {

  /**
   * Checks that the command line arguments contain a --csv-file flag and a path/to/file.
   *
   * @return - returns the csv path/to/file provided
   *
   * @throws InvalidArgumentException - thrown if missing one or both of the
   * --csv-file flag and path/to/file.
   */
  String checkCSV() throws InvalidArgumentException;

  /**
   * Method used to ensure that at least one of the tree tasks is performed. If no task args were
   * provided, throws an exception
   *
   * @param add - whether add task args are present
   * @param complete - whether complete task args are present
   * @param display - whether display task args are present
   *
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  void checkNoTask(Boolean add, Boolean complete, Boolean display)
      throws InvalidArgumentException;

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
  void checkFollowingArg(Integer i, String currentArg, String missingArg)
      throws InvalidArgumentException;

}
